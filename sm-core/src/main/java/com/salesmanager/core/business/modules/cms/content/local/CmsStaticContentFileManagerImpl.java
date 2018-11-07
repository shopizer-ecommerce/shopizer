/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content.local;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.content.ContentAssetsManager;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
import com.salesmanager.core.business.modules.cms.impl.LocalCacheManagerImpl;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;



/**
 * Manages - Images - Files (js, pdf, css...) on a local web server
 * 
 * @author Carl Samson
 * @since 1.0.3
 *
 */
public class CmsStaticContentFileManagerImpl implements ContentAssetsManager {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER =
      LoggerFactory.getLogger(CmsStaticContentFileManagerImpl.class);
  private static CmsStaticContentFileManagerImpl fileManager = null;
  private static final String ROOT_NAME = "static";

  private static final String ROOT_CONTAINER = "files";

  private String rootName = ROOT_NAME;

  private LocalCacheManagerImpl cacheManager;

  @PostConstruct
  void init() {

    this.rootName = ((CMSManager) cacheManager).getRootName();
    LOGGER.info("init " + getClass().getName() + " setting root" + this.rootName);

  }


  public static CmsStaticContentFileManagerImpl getInstance() {

    if (fileManager == null) {
      fileManager = new CmsStaticContentFileManagerImpl();
    }

    return fileManager;

  }


  /**
   * <p>
   * Method to add static content data for given merchant.Static content data can be of following
   * type
   * 
   * <pre>
   * 1. CSS and JS files
   * 2. Digital Data like audio or video
   * </pre>
   * </p>
   * <p>
   * Merchant store code will be used to create cache node where merchant data will be stored,input
   * data will contain name, file as well type of data being stored.
   * 
   * @see FileContentType
   *      </p>
   * 
   * @param merchantStoreCode merchant store for whom data is being stored
   * @param inputStaticContentData data object being stored
   * @throws ServiceException
   * 
   */
  @Override
  public void addFile(final String merchantStoreCode, final InputContentFile inputStaticContentData)
      throws ServiceException {
    /*
     * if ( cacheManager.getTreeCache() == null ) { LOGGER.error(
     * "Unable to find cacheManager.getTreeCache() in Infinispan.." ); throw new ServiceException(
     * "CmsStaticContentFileManagerInfinispanImpl has a null cacheManager.getTreeCache()" ); }
     */
    try {



      // base path
      String rootPath = this.buildRootPath();
      Path confDir = Paths.get(rootPath);
      this.createDirectoryIfNorExist(confDir);

      // node path
      StringBuilder nodePath = new StringBuilder();
      nodePath.append(rootPath).append(merchantStoreCode);
      Path merchantPath = Paths.get(nodePath.toString());
      this.createDirectoryIfNorExist(merchantPath);

      // file path
      nodePath.append(Constants.SLASH).append(inputStaticContentData.getFileContentType())
          .append(Constants.SLASH);
      Path dirPath = Paths.get(nodePath.toString());
      this.createDirectoryIfNorExist(dirPath);

      // file creation
      nodePath.append(inputStaticContentData.getFileName());


      Path path = Paths.get(nodePath.toString());


      // file creation
      // nodePath.append(Constants.SLASH).append(contentImage.getFileName());


      // Path path = Paths.get(nodePath.toString());


      Files.copy(inputStaticContentData.getFile(), path, StandardCopyOption.REPLACE_EXISTING);



      // String nodePath = this.getNodePath(merchantStoreCode,
      // inputStaticContentData.getFileContentType());

      // final Node<String, Object> merchantNode = this.getNode(nodePath);

      // merchantNode.put(inputStaticContentData.getFileName(), IOUtils.toByteArray(
      // inputStaticContentData.getFile() ));

      LOGGER.info("Content data added successfully.");
    } catch (final Exception e) {
      LOGGER.error("Error while saving static content data", e);
      throw new ServiceException(e);

    }

  }

  /**
   * <p>
   * Method to add files for given store.Files will be stored in Infinispan and will be retrieved
   * based on the storeID. Following steps will be performed to store static content files
   * </p>
   * <li>Merchant Node will be retrieved from the cacheTree if it exists else new node will be
   * created.</li>
   * <li>Files will be stored in StaticContentCacheAttribute , which eventually will be stored in
   * Infinispan</li>
   * 
   * @param merchantStoreCode Merchant store for which files are getting stored in Infinispan.
   * @param inputStaticContentDataList input static content file list which will get
   *        {@link InputContentImage} stored
   * @throws ServiceException if content file storing process fail.
   * @see InputStaticContentData
   * @see StaticContentCacheAttribute
   */
  @Override
  public void addFiles(final String merchantStoreCode,
      final List<InputContentFile> inputStaticContentDataList) throws ServiceException {
    /*
     * if ( cacheManager.getTreeCache() == null ) { LOGGER.error(
     * "Unable to find cacheManager.getTreeCache() in Infinispan.." ); throw new ServiceException(
     * "CmsStaticContentFileManagerInfinispanImpl has a null cacheManager.getTreeCache()" ); }
     */
    try {


      // base path
      String rootPath = this.buildRootPath();
      Path confDir = Paths.get(rootPath);
      this.createDirectoryIfNorExist(confDir);

      // node path
      StringBuilder nodePath = new StringBuilder();
      nodePath.append(rootPath).append(merchantStoreCode);
      Path merchantPath = Paths.get(nodePath.toString());
      this.createDirectoryIfNorExist(merchantPath);



      for (final InputContentFile inputStaticContentData : inputStaticContentDataList) {


        // file path
        nodePath.append(Constants.SLASH).append(inputStaticContentData.getFileContentType())
            .append(Constants.SLASH);
        Path dirPath = Paths.get(nodePath.toString());
        this.createDirectoryIfNorExist(dirPath);

        // file creation
        nodePath.append(Constants.SLASH).append(inputStaticContentData.getFileName());


        Path path = Paths.get(nodePath.toString());

        Files.copy(inputStaticContentData.getFile(), path, StandardCopyOption.REPLACE_EXISTING);


        // String nodePath = this.getNodePath(merchantStoreCode,
        // inputStaticContentData.getFileContentType());

        // final Node<String, Object> merchantNode = this.getNode(nodePath);
        // merchantNode.put(inputStaticContentData.getFileName(), IOUtils.toByteArray(
        // inputStaticContentData.getFile() ));


      }



      LOGGER.info("Total {} files added successfully.", inputStaticContentDataList.size());

    } catch (final Exception e) {
      LOGGER.error("Error while saving content image", e);
      throw new ServiceException(e);

    }
  }


  /**
   * Method to return static data for given Merchant store based on the file name. Content data will
   * be searched in underlying Infinispan cache tree and {@link OutputStaticContentData} will be
   * returned on finding an associated file. In case of no file, null be returned.
   * 
   * @param store Merchant store
   * @param contentFileName name of file being requested
   * @return {@link OutputStaticContentData}
   * @throws ServiceException
   */
  @Override
  public OutputContentFile getFile(final String merchantStoreCode,
      final FileContentType fileContentType, final String contentFileName) throws ServiceException {

    throw new ServiceException("Not implemented for httpd image manager");

  }


  @Override
  public List<OutputContentFile> getFiles(final String merchantStoreCode,
      final FileContentType staticContentType) throws ServiceException {


    throw new ServiceException("Not implemented for httpd image manager");


  }



  @Override
  public void removeFile(final String merchantStoreCode, final FileContentType staticContentType,
      final String fileName) throws ServiceException {

    try {


      StringBuilder merchantPath = new StringBuilder();
      merchantPath.append(buildRootPath()).append(Constants.SLASH).append(merchantStoreCode)
          .append(Constants.SLASH).append(staticContentType).append(Constants.SLASH)
          .append(fileName);

      Path path = Paths.get(merchantPath.toString());

      Files.deleteIfExists(path);



    } catch (final Exception e) {
      LOGGER.error("Error while deleting files for {} merchant ", merchantStoreCode);
      throw new ServiceException(e);
    }


  }

  /**
   * Removes the data in a given merchant node
   */
  @SuppressWarnings("unchecked")
  @Override
  public void removeFiles(final String merchantStoreCode) throws ServiceException {

    LOGGER.debug("Removing all images for {} merchant ", merchantStoreCode);

    try {


      StringBuilder merchantPath = new StringBuilder();
      merchantPath.append(buildRootPath()).append(Constants.SLASH).append(merchantStoreCode);

      Path path = Paths.get(merchantPath.toString());

      Files.deleteIfExists(path);



    } catch (final Exception e) {
      LOGGER.error("Error while deleting content image for {} merchant ", merchantStoreCode);
      throw new ServiceException(e);
    }

  }



  /**
   * Queries the CMS to retrieve all static content files. Only the name of the file will be
   * returned to the client
   * 
   * @param merchantStoreCode
   * @return
   * @throws ServiceException
   */
  @Override
  public List<String> getFileNames(final String merchantStoreCode,
      final FileContentType staticContentType) throws ServiceException {



    try {

      StringBuilder merchantPath = new StringBuilder();
      merchantPath.append(buildRootPath()).append(merchantStoreCode).append(Constants.SLASH)
          .append(staticContentType);

      Path path = Paths.get(merchantPath.toString());

      List<String> fileNames = null;

      if (Files.exists(path)) {

        fileNames = new ArrayList<String>();
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        for (Path dirPath : directoryStream) {

          String fileName = dirPath.getFileName().toString();

          if (staticContentType.name().equals(FileContentType.IMAGE.name())) {
            // File f = new File(fileName);
            String mimetype = URLConnection.guessContentTypeFromName(fileName);
            // String mimetype= new MimetypesFileTypeMap().getContentType(f);
            if (!StringUtils.isBlank(mimetype)) {
              String type = mimetype.split("/")[0];
              if (type.equals("image")) {
                fileNames.add(fileName);
              }
            }
            // fileNames.add(fileName);

          } else {
            fileNames.add(fileName);
          }

        }
      }

      return fileNames;

    } catch (final Exception e) {
      LOGGER.error("Error while fetching file for {} merchant ", merchantStoreCode);
      throw new ServiceException(e);
    }

  }

  public void setRootName(String rootName) {
    this.rootName = rootName;
  }

  public String getRootName() {
    return rootName;
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


  public LocalCacheManagerImpl getCacheManager() {
    return cacheManager;
  }


  public void setCacheManager(LocalCacheManagerImpl cacheManager) {
    this.cacheManager = cacheManager;
  }



}
