package com.salesmanager.core.business.modules.cms.product.local;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
import com.salesmanager.core.business.modules.cms.impl.LocalCacheManagerImpl;
import com.salesmanager.core.business.modules.cms.product.ProductAssetsManager;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.ProductImageSize;
import com.salesmanager.core.model.catalog.product.image.ProductImage;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.ImageContentFile;
import com.salesmanager.core.model.content.OutputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;

/**
 * Manager for storing and deleting image files from the CMS which is a web server
 * 
 * Manages - Product images
 * 
 * @author Carl Samson
 */
public class CmsImageFileManagerImpl
    implements ProductAssetsManager {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private static final Logger LOGGER = LoggerFactory.getLogger(CmsImageFileManagerImpl.class);

  private static CmsImageFileManagerImpl fileManager = null;

  private final static String ROOT_NAME = "";

  private final static String SMALL = "SMALL";
  private final static String LARGE = "LARGE";

  private static final String ROOT_CONTAINER = "products";

  private String rootName = ROOT_NAME;

  private LocalCacheManagerImpl cacheManager;

  @PostConstruct
  void init() {

    this.rootName = ((CMSManager) cacheManager).getRootName();
    LOGGER.info("init " + getClass().getName() + " setting root" + this.rootName);

  }

  public static CmsImageFileManagerImpl getInstance() {

    if (fileManager == null) {
      fileManager = new CmsImageFileManagerImpl();
    }

    return fileManager;

  }

  private CmsImageFileManagerImpl() {

  }

  /**
   * root/products/<merchant id>/<product id>/1.jpg
   */

  @Override
  public void addProductImage(ProductImage productImage, ImageContentFile contentImage)
      throws ServiceException {


    try {

      // base path
      String rootPath = this.buildRootPath();
      Path confDir = Paths.get(rootPath);
      this.createDirectoryIfNorExist(confDir);

      // node path
      StringBuilder nodePath = new StringBuilder();
      nodePath.append(rootPath).append(productImage.getProduct().getMerchantStore().getCode());
      Path merchantPath = Paths.get(nodePath.toString());
      this.createDirectoryIfNorExist(merchantPath);

      // product path
      nodePath.append(Constants.SLASH).append(productImage.getProduct().getSku())
          .append(Constants.SLASH);
      Path dirPath = Paths.get(nodePath.toString());
      this.createDirectoryIfNorExist(dirPath);

      // small large
      if (contentImage.getFileContentType().name().equals(FileContentType.PRODUCT.name())) {
        nodePath.append(SMALL);
      } else if (contentImage.getFileContentType().name()
          .equals(FileContentType.PRODUCTLG.name())) {
        nodePath.append(LARGE);
      }
      Path sizePath = Paths.get(nodePath.toString());
      this.createDirectoryIfNorExist(sizePath);


      // file creation
      nodePath.append(Constants.SLASH).append(contentImage.getFileName());


      Path path = Paths.get(nodePath.toString());
      InputStream isFile = contentImage.getFile();

      Files.copy(isFile, path, StandardCopyOption.REPLACE_EXISTING);


    } catch (Exception e) {

      throw new ServiceException(e);

    }

  }

  @Override
  public OutputContentFile getProductImage(ProductImage productImage) throws ServiceException {

    // the web server takes care of the images
    return null;

  }


  public List<OutputContentFile> getImages(MerchantStore store, FileContentType imageContentType)
      throws ServiceException {

    // the web server takes care of the images

    return null;

  }

  @Override
  public List<OutputContentFile> getImages(Product product) throws ServiceException {

    // the web server takes care of the images

    return null;
  }



  @Override
  public void removeImages(final String merchantStoreCode) throws ServiceException {

    try {


      StringBuilder merchantPath = new StringBuilder();
      merchantPath.append(buildRootPath()).append(Constants.SLASH).append(merchantStoreCode);

      Path path = Paths.get(merchantPath.toString());

      Files.deleteIfExists(path);


    } catch (Exception e) {
      throw new ServiceException(e);
    }


  }


  @Override
  public void removeProductImage(ProductImage productImage) throws ServiceException {


    try {


      StringBuilder nodePath = new StringBuilder();
      nodePath.append(buildRootPath()).append(Constants.SLASH)
          .append(productImage.getProduct().getMerchantStore().getCode()).append(Constants.SLASH)
          .append(productImage.getProduct().getSku());

      // delete small
      StringBuilder smallPath = new StringBuilder(nodePath);
      smallPath.append(Constants.SLASH).append(SMALL).append(Constants.SLASH)
          .append(productImage.getProductImage());


      Path path = Paths.get(smallPath.toString());

      Files.deleteIfExists(path);

      // delete large
      StringBuilder largePath = new StringBuilder(nodePath);
      largePath.append(Constants.SLASH).append(LARGE).append(Constants.SLASH)
          .append(productImage.getProductImage());


      path = Paths.get(largePath.toString());

      Files.deleteIfExists(path);

    } catch (Exception e) {
      throw new ServiceException(e);
    }


  }

  @Override
  public void removeProductImages(Product product) throws ServiceException {

    try {


      StringBuilder nodePath = new StringBuilder();
      nodePath.append(buildRootPath()).append(Constants.SLASH)
          .append(product.getMerchantStore().getCode()).append(Constants.SLASH)
          .append(product.getSku());


      Path path = Paths.get(nodePath.toString());

      Files.deleteIfExists(path);

    } catch (Exception e) {
      throw new ServiceException(e);
    }

  }


  @Override
  public List<OutputContentFile> getImages(final String merchantStoreCode,
      FileContentType imageContentType) throws ServiceException {

    // the web server taks care of the images

    return null;
  }

  @Override
  public OutputContentFile getProductImage(String merchantStoreCode, String productCode,
      String imageName) throws ServiceException {
    return getProductImage(merchantStoreCode, productCode, imageName,
        ProductImageSize.SMALL.name());
  }

  @Override
  public OutputContentFile getProductImage(String merchantStoreCode, String productCode,
      String imageName, ProductImageSize size) throws ServiceException {
    return getProductImage(merchantStoreCode, productCode, imageName, size.name());
  }

  private OutputContentFile getProductImage(String merchantStoreCode, String productCode,
      String imageName, String size) throws ServiceException {

    return null;

  }


  private String buildRootPath() {
    return new StringBuilder().append(getRootName()).append(Constants.SLASH).append(ROOT_CONTAINER)
        .append(Constants.SLASH).toString();

  }


  private void createDirectoryIfNorExist(Path path) throws IOException {

    if (Files.notExists(path)) {
      Files.createDirectory(path);
    }
  }

  public void setRootName(String rootName) {
    this.rootName = rootName;
  }

  public String getRootName() {
    return rootName;
  }

  public LocalCacheManagerImpl getCacheManager() {
    return cacheManager;
  }

  public void setCacheManager(LocalCacheManagerImpl cacheManager) {
    this.cacheManager = cacheManager;
  }



}
