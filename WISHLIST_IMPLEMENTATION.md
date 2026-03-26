# Wishlist Feature — Implementation Plan

## Overview
Allow authenticated customers to save products to a personal wishlist, view it, and move items to the cart.

---

## Phase 1 — Data Model (`sm-core-model`)

### 1.1 Create `WishlistItem` Entity
**File:** `sm-core-model/src/main/java/com/salesmanager/core/model/customer/WishlistItem.java`

```java
@Entity
@Table(name = "CUSTOMER_WISHLIST")
public class WishlistItem extends SalesManagerEntity<Long, WishlistItem> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "DATE_ADDED")
    private Date dateAdded = new Date();

    // getters + setters
}
```

---

## Phase 2 — Repository (`sm-core`)

### 2.1 Create `WishlistItemRepository`
**File:** `sm-core/src/main/java/com/salesmanager/core/business/repositories/customer/WishlistItemRepository.java`

```java
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByCustomerId(Long customerId);
    Optional<WishlistItem> findByCustomerIdAndProductId(Long customerId, Long productId);
    void deleteByCustomerIdAndId(Long customerId, Long id);
}
```

---

## Phase 3 — Service (`sm-core-modules`)

### 3.1 Interface
**File:** `sm-core-modules/src/main/java/com/salesmanager/core/business/services/customer/WishlistService.java`

```java
public interface WishlistService {
    WishlistItem addItem(Customer customer, Product product) throws ServiceException;
    void removeItem(Long customerId, Long itemId) throws ServiceException;
    List<WishlistItem> getWishlist(Long customerId) throws ServiceException;
}
```

### 3.2 Implementation
**File:** `sm-core-modules/src/main/java/com/salesmanager/core/business/services/customer/WishlistServiceImpl.java`

```java
@Service
public class WishlistServiceImpl implements WishlistService {

    @Autowired private WishlistItemRepository wishlistItemRepository;

    @Override
    public WishlistItem addItem(Customer customer, Product product) throws ServiceException {
        // avoid duplicates
        return wishlistItemRepository
            .findByCustomerIdAndProductId(customer.getId(), product.getId())
            .orElseGet(() -> wishlistItemRepository.save(new WishlistItem(customer, product)));
    }

    @Override
    public void removeItem(Long customerId, Long itemId) throws ServiceException {
        wishlistItemRepository.deleteByCustomerIdAndId(customerId, itemId);
    }

    @Override
    public List<WishlistItem> getWishlist(Long customerId) throws ServiceException {
        return wishlistItemRepository.findByCustomerId(customerId);
    }
}
```

---

## Phase 4 — API Layer (`sm-shop`)

### 4.1 Readable Model
**File:** `sm-shop/src/main/java/com/salesmanager/shop/model/customer/ReadableWishlistItem.java`

```java
public class ReadableWishlistItem {
    private Long id;
    private ReadableMinimalProduct product;
    private Date dateAdded;
    // getters + setters
}
```

### 4.2 Facade
**File:** `sm-shop/src/main/java/com/salesmanager/shop/store/facade/customer/WishlistFacade.java`

```java
@Service
public class WishlistFacade {

    @Autowired private WishlistService wishlistService;
    @Autowired private ProductService productService;
    @Autowired private CustomerService customerService;
    @Autowired private ReadableMinimalProductMapper productMapper;

    public List<ReadableWishlistItem> getWishlist(String customerEmail, MerchantStore store, Language language) {
        Customer customer = customerService.getByEmail(customerEmail);
        return wishlistService.getWishlist(customer.getId()).stream()
            .map(item -> toReadable(item, store, language))
            .collect(Collectors.toList());
    }

    public ReadableWishlistItem addItem(String customerEmail, Long productId, MerchantStore store, Language language) {
        Customer customer = customerService.getByEmail(customerEmail);
        Product product = productService.getById(productId);
        return toReadable(wishlistService.addItem(customer, product), store, language);
    }

    public void removeItem(String customerEmail, Long itemId) {
        Customer customer = customerService.getByEmail(customerEmail);
        wishlistService.removeItem(customer.getId(), itemId);
    }

    private ReadableWishlistItem toReadable(WishlistItem item, MerchantStore store, Language language) {
        ReadableWishlistItem r = new ReadableWishlistItem();
        r.setId(item.getId());
        r.setDateAdded(item.getDateAdded());
        r.setProduct(productMapper.convert(item.getProduct(), store, language));
        return r;
    }
}
```

### 4.3 REST Controller
**File:** `sm-shop/src/main/java/com/salesmanager/shop/store/api/v1/customer/WishlistApi.java`

```java
@RestController
@RequestMapping("/api/v1/customer/wishlist")
@Tag(name = "Wishlist", description = "Customer wishlist management")
public class WishlistApi {

    @Autowired private WishlistFacade wishlistFacade;

    @GetMapping
    public List<ReadableWishlistItem> getWishlist(
            @ApiIgnore MerchantStore store,
            @ApiIgnore Language language,
            Principal principal) {
        return wishlistFacade.getWishlist(principal.getName(), store, language);
    }

    @PostMapping("/{productId}")
    public ReadableWishlistItem addItem(
            @PathVariable Long productId,
            @ApiIgnore MerchantStore store,
            @ApiIgnore Language language,
            Principal principal) {
        return wishlistFacade.addItem(principal.getName(), productId, store, language);
    }

    @DeleteMapping("/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeItem(@PathVariable Long itemId, Principal principal) {
        wishlistFacade.removeItem(principal.getName(), itemId);
    }
}
```

**Endpoints summary:**

| Method | URL | Auth | Description |
|--------|-----|------|-------------|
| GET | `/api/v1/customer/wishlist` | Customer JWT | Get wishlist |
| POST | `/api/v1/customer/wishlist/{productId}` | Customer JWT | Add product |
| DELETE | `/api/v1/customer/wishlist/{itemId}` | Customer JWT | Remove item |

---

## Phase 5 — React Frontend

### 5.1 API Service
**File:** `src/services/wishlistApi.js`

```js
import axios from './axiosInstance'; // existing axios with auth interceptor

export const getWishlist = () => axios.get('/api/v1/customer/wishlist');
export const addToWishlist = (productId) => axios.post(`/api/v1/customer/wishlist/${productId}`);
export const removeFromWishlist = (itemId) => axios.delete(`/api/v1/customer/wishlist/${itemId}`);
```

### 5.2 Wishlist Context
**File:** `src/context/WishlistContext.jsx`

```jsx
const WishlistContext = createContext();

export function WishlistProvider({ children }) {
  const [items, setItems] = useState([]);

  useEffect(() => {
    getWishlist().then(res => setItems(res.data));
  }, []);

  const add = async (productId) => {
    const res = await addToWishlist(productId);
    setItems(prev => [...prev, res.data]);
  };

  const remove = async (itemId) => {
    await removeFromWishlist(itemId);
    setItems(prev => prev.filter(i => i.id !== itemId));
  };

  const isInWishlist = (productId) => items.some(i => i.product.id === productId);

  return (
    <WishlistContext.Provider value={{ items, add, remove, isInWishlist }}>
      {children}
    </WishlistContext.Provider>
  );
}

export const useWishlist = () => useContext(WishlistContext);
```

### 5.3 Heart Button Component
**File:** `src/components/WishlistButton.jsx`

```jsx
export default function WishlistButton({ productId }) {
  const { add, remove, isInWishlist, items } = useWishlist();
  const inList = isInWishlist(productId);
  const item = items.find(i => i.product.id === productId);

  const toggle = () => inList ? remove(item.id) : add(productId);

  return (
    <button onClick={toggle} aria-label={inList ? 'Remove from wishlist' : 'Add to wishlist'}>
      {inList ? '❤️' : '🤍'}
    </button>
  );
}
```

> Add `<WishlistButton productId={product.id} />` inside your existing `ProductCard` component.

### 5.4 Wishlist Page
**File:** `src/pages/account/WishlistPage.jsx`

```jsx
export default function WishlistPage() {
  const { items, remove } = useWishlist();
  const { addToCart } = useCart(); // existing cart context

  if (!items.length) return <p>Your wishlist is empty.</p>;

  return (
    <div>
      <h1>My Wishlist</h1>
      {items.map(item => (
        <div key={item.id}>
          <img src={item.product.image} alt={item.product.name} />
          <span>{item.product.name}</span>
          <span>{item.product.price}</span>
          <button onClick={() => addToCart(item.product.id)}>Add to Cart</button>
          <button onClick={() => remove(item.id)}>Remove</button>
        </div>
      ))}
    </div>
  );
}
```

### 5.5 Routing
Add to your router config:
```jsx
<Route path="/account/wishlist" element={<PrivateRoute><WishlistPage /></PrivateRoute>} />
```

---

## File Checklist

### Backend
- [ ] `sm-core-model` → `WishlistItem.java`
- [ ] `sm-core` → `WishlistItemRepository.java`
- [ ] `sm-core-modules` → `WishlistService.java` + `WishlistServiceImpl.java`
- [ ] `sm-shop` → `ReadableWishlistItem.java`
- [ ] `sm-shop` → `WishlistFacade.java`
- [ ] `sm-shop` → `WishlistApi.java`

### Frontend
- [ ] `wishlistApi.js`
- [ ] `WishlistContext.jsx`
- [ ] `WishlistButton.jsx` (add to `ProductCard`)
- [ ] `WishlistPage.jsx`
- [ ] Route added for `/account/wishlist`
- [ ] `WishlistProvider` added to app root

---

## Phase 6 — Test Strategy

### 6.1 Unit Tests — Service Layer
**File:** `sm-core-modules/src/test/java/.../WishlistServiceImplTest.java`

```java
@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

    @Mock WishlistItemRepository wishlistItemRepository;
    @InjectMocks WishlistServiceImpl wishlistService;

    @Test
    void addItem_newProduct_savesAndReturns() {
        when(wishlistItemRepository.findByCustomerIdAndProductId(1L, 10L)).thenReturn(Optional.empty());
        when(wishlistItemRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        WishlistItem result = wishlistService.addItem(customer(1L), product(10L));

        verify(wishlistItemRepository).save(any());
        assertNotNull(result);
    }

    @Test
    void addItem_duplicate_doesNotSaveAgain() {
        WishlistItem existing = new WishlistItem();
        when(wishlistItemRepository.findByCustomerIdAndProductId(1L, 10L)).thenReturn(Optional.of(existing));

        WishlistItem result = wishlistService.addItem(customer(1L), product(10L));

        verify(wishlistItemRepository, never()).save(any());
        assertEquals(existing, result);
    }

    @Test
    void removeItem_callsDeleteWithCorrectIds() {
        wishlistService.removeItem(1L, 5L);
        verify(wishlistItemRepository).deleteByCustomerIdAndId(1L, 5L);
    }

    @Test
    void getWishlist_returnsItemsForCustomer() {
        when(wishlistItemRepository.findByCustomerId(1L)).thenReturn(List.of(new WishlistItem()));
        assertEquals(1, wishlistService.getWishlist(1L).size());
    }
}
```

### 6.2 Unit Tests — Facade
**File:** `sm-shop/src/test/java/.../WishlistFacadeTest.java`

```java
@ExtendWith(MockitoExtension.class)
class WishlistFacadeTest {

    @Mock WishlistService wishlistService;
    @Mock CustomerService customerService;
    @Mock ProductService productService;
    @Mock ReadableMinimalProductMapper productMapper;
    @InjectMocks WishlistFacade wishlistFacade;

    @Test
    void getWishlist_mapsItemsToReadable() {
        Customer customer = customer(1L);
        WishlistItem item = wishlistItem(customer, product(10L));

        when(customerService.getByEmail("user@test.com")).thenReturn(customer);
        when(wishlistService.getWishlist(1L)).thenReturn(List.of(item));
        when(productMapper.convert(any(), any(), any())).thenReturn(new ReadableMinimalProduct());

        List<ReadableWishlistItem> result = wishlistFacade.getWishlist("user@test.com", store(), language());

        assertEquals(1, result.size());
    }

    @Test
    void addItem_delegatesToServiceAndMaps() {
        when(customerService.getByEmail(any())).thenReturn(customer(1L));
        when(productService.getById(10L)).thenReturn(product(10L));
        when(wishlistService.addItem(any(), any())).thenReturn(new WishlistItem());
        when(productMapper.convert(any(), any(), any())).thenReturn(new ReadableMinimalProduct());

        assertNotNull(wishlistFacade.addItem("user@test.com", 10L, store(), language()));
    }
}
```

### 6.3 Integration Tests — REST API
**File:** `sm-shop/src/test/java/.../WishlistApiIT.java`

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WishlistApiIT {

    @Autowired MockMvc mockMvc;

    // Assumes a test customer JWT is available via helper
    @Test
    void getWishlist_authenticated_returns200() throws Exception {
        mockMvc.perform(get("/api/v1/customer/wishlist")
                .header("Authorization", "Bearer " + testCustomerToken()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray());
    }

    @Test
    void addItem_validProduct_returns200() throws Exception {
        mockMvc.perform(post("/api/v1/customer/wishlist/1")
                .header("Authorization", "Bearer " + testCustomerToken()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void removeItem_validItem_returns204() throws Exception {
        mockMvc.perform(delete("/api/v1/customer/wishlist/1")
                .header("Authorization", "Bearer " + testCustomerToken()))
            .andExpect(status().isNoContent());
    }

    @Test
    void getWishlist_unauthenticated_returns401() throws Exception {
        mockMvc.perform(get("/api/v1/customer/wishlist"))
            .andExpect(status().isUnauthorized());
    }
}
```

### 6.4 Frontend Tests — Context
**File:** `src/context/WishlistContext.test.jsx`

```jsx
test('add increases items count', async () => {
  addToWishlist.mockResolvedValue({ data: { id: 1, product: { id: 10 } } });

  const { result } = renderHook(() => useWishlist(), { wrapper: WishlistProvider });
  await act(() => result.current.add(10));

  expect(result.current.items).toHaveLength(1);
});

test('remove decreases items count', async () => {
  // seed one item
  getWishlist.mockResolvedValue({ data: [{ id: 1, product: { id: 10 } }] });
  removeFromWishlist.mockResolvedValue({});

  const { result } = renderHook(() => useWishlist(), { wrapper: WishlistProvider });
  await act(() => result.current.remove(1));

  expect(result.current.items).toHaveLength(0);
});
```

### 6.5 Frontend Tests — WishlistButton
**File:** `src/components/WishlistButton.test.jsx`

```jsx
test('renders unfilled heart when not in wishlist', () => {
  mockUseWishlist({ isInWishlist: () => false });
  render(<WishlistButton productId={1} />);
  expect(screen.getByLabelText('Add to wishlist')).toBeInTheDocument();
});

test('calls remove when already in wishlist', async () => {
  const remove = jest.fn();
  mockUseWishlist({ isInWishlist: () => true, items: [{ id: 99, product: { id: 1 } }], remove });

  render(<WishlistButton productId={1} />);
  await userEvent.click(screen.getByRole('button'));

  expect(remove).toHaveBeenCalledWith(99);
});
```

### Test Coverage Targets

| Layer | Type | Target |
|-------|------|--------|
| `WishlistServiceImpl` | Unit | 100% |
| `WishlistFacade` | Unit | 90%+ |
| `WishlistApi` | Integration | All endpoints + 401 case |
| `WishlistContext` | Unit (React) | add / remove / isInWishlist |
| `WishlistButton` | Component | toggle behaviour |
| `WishlistPage` | Component | render list + empty state |

### Tests
- [ ] `WishlistServiceImplTest.java`
- [ ] `WishlistFacadeTest.java`
- [ ] `WishlistApiIT.java`
- [ ] `WishlistContext.test.jsx`
- [ ] `WishlistButton.test.jsx`
- [ ] `WishlistPage.test.jsx`

---

| Task | Time |
|------|------|
| Entity + Repository | ~1h |
| Service + Facade | ~2h |
| REST API | ~1h |
| React UI | ~3h |
| Tests (backend) | ~2h |
| Tests (frontend) | ~1h |
| **Total** | **~10h** |
