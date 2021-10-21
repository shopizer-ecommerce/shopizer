package com.salesmanager.test.shop.integration.admin.user;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.repositories.user.UserRepository;
import com.salesmanager.core.business.services.merchant.MerchantStoreService;
import com.salesmanager.core.business.services.reference.language.LanguageService;
import com.salesmanager.core.business.services.system.EmailService;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.user.User;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.application.ShopApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Contains tests targeting {@link com.salesmanager.shop.admin.controller.user.UserController}
 */
@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UsersIntegrationTest {

    /** form param name for users 'adminName' */
    private static final String PARAM_ADMIN_NAME = "adminName";

    /** users for storing a test user */
    @Autowired
    private UserRepository userRepository;

    /** used to retrieve an existing merchant (which is a mandatory field for users) */
    @Autowired
    private MerchantStoreService merchantService;

    /** used to retrieve an existing Language instance */
    @Autowired
    private LanguageService languageService;

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    /** in this test, we do not want to send real emails by tested controller */
    @MockBean
    private EmailService emailService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @WithMockUser(roles = {"AUTH"})
    public void renameUser() throws Exception {
        // given a user in DB
        User existingUser = createMinimalUser();
        userRepository.save(existingUser);

        // when we want to change users 'adminName'
        Map<String, String> requestParams = toRequestParams(existingUser);
        String newName = "someNewName";
        requestParams.put(PARAM_ADMIN_NAME, newName);
        ResultActions resultActions = callSaveUserEndpoint(requestParams);

        // then the correct view is expected without any error
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name(ControllerConstants.Tiles.User.profile))
                .andExpect(model().hasNoErrors());
        // and the new 'adminName' is written to DB
        User actualUser = userRepository.findOne(existingUser.getId());
        assertThat(actualUser.getAdminName()).isEqualTo(newName);
    }

    /**
     * Calls the endpoint for saving a user with the given request parameters
     * @param requestParams the given request parameters
     * @return the mockMvc ResultActions
     * @throws Exception in case the call could not be performed
     */
    private ResultActions callSaveUserEndpoint(Map<String, String> requestParams) throws Exception {
        return mvc.perform(post("/admin/users/save.html")
                .params(toMultiMap(requestParams))
                .contentType(MediaType.APPLICATION_FORM_URLENCODED));
    }

    private MultiValueMap<String, String> toMultiMap(Map<String, String> requestParams) {
        MultiValueMap<String, String> mapResult = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> paramEntry : requestParams.entrySet()) {
            mapResult.add(paramEntry.getKey(), paramEntry.getValue());
        }
        return mapResult;
    }

    private Map<String, String> toRequestParams(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("id", user.getId().toString());
        map.put(PARAM_ADMIN_NAME, user.getAdminName());
        map.put("merchantStore.id", user.getMerchantStore().getId().toString());
        map.put("adminEmail", user.getAdminEmail());
        map.put("adminPassword", user.getAdminPassword());
        map.put("defaultLanguage.id", user.getDefaultLanguage().getId().toString());
        map.put("question1", user.getQuestion1());
        map.put("question2", user.getQuestion2());
        map.put("question3", user.getQuestion3());
        map.put("answer1", user.getAnswer1());
        map.put("answer2", user.getAnswer2());
        map.put("answer3", user.getAnswer3());
        return map;
    }

    private User createMinimalUser() throws ServiceException {
        User user = new User("name", "password", "email@shopizer.no");
        user.setMerchantStore(merchantService.getByCode(MerchantStore.DEFAULT_STORE));
        user.setDefaultLanguage(languageService.defaultLanguage());
        user.setQuestion1("q1");
        user.setAnswer1("a1");
        user.setQuestion2("q2");
        user.setAnswer2("a2");
        user.setQuestion3("q3");
        user.setAnswer3("a3");
        return user;
    }
}

