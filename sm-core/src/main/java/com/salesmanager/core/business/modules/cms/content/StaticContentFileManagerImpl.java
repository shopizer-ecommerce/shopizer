/**
 * 
 */
package com.salesmanager.core.business.modules.cms.content;

import java.util.List;
import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.modules.cms.content.infinispan.CmsStaticContentFileManagerImpl;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.content.OutputContentFile;


/**
 * @author Umesh Awasthi
 *
 */
public class StaticContentFileManagerImpl extends StaticContentFileManager {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  private FilePut uploadFile;
  private FileGet getFile;
  private FileRemove removeFile;



  @Override
  public void addFile(final String merchantStoreCode, final InputContentFile inputStaticContentData)
      throws ServiceException {
    uploadFile.addFile(merchantStoreCode, inputStaticContentData);

  }

  /**
   * Implementation for add static data files. This method will called respected add files method of
   * underlying CMSStaticContentManager. For CMS Content files
   * {@link CmsStaticContentFileManagerImpl} will take care of adding given content images with
   * Infinispan cache.
   * 
   * @param merchantStoreCode merchant store.
   * @param inputStaticContentDataList Input content images
   * @throws ServiceException
   */
  @Override
  public void addFiles(final String merchantStoreCode,
      final List<InputContentFile> inputStaticContentDataList) throws ServiceException {
    uploadFile.addFiles(merchantStoreCode, inputStaticContentDataList);
  }

  @Override
  public void removeFile(final String merchantStoreCode, final FileContentType staticContentType,
      final String fileName) throws ServiceException {
    removeFile.removeFile(merchantStoreCode, staticContentType, fileName);

  }


  @Override
  public OutputContentFile getFile(String merchantStoreCode, FileContentType fileContentType,
      String contentName) throws ServiceException {
    return getFile.getFile(merchantStoreCode, fileContentType, contentName);
  }

  @Override
  public List<String> getFileNames(String merchantStoreCode, FileContentType fileContentType)
      throws ServiceException {
    return getFile.getFileNames(merchantStoreCode, fileContentType);
  }

  @Override
  public List<OutputContentFile> getFiles(String merchantStoreCode, FileContentType fileContentType)
      throws ServiceException {
    return getFile.getFiles(merchantStoreCode, fileContentType);
  }

  @Override
  public void removeFiles(String merchantStoreCode) throws ServiceException {
    removeFile.removeFiles(merchantStoreCode);
  }



  public void setRemoveFile(FileRemove removeFile) {
    this.removeFile = removeFile;
  }

  public FileRemove getRemoveFile() {
    return removeFile;
  }

  public void setGetFile(FileGet getFile) {
    this.getFile = getFile;
  }

  public FileGet getGetFile() {
    return getFile;
  }

  public void setUploadFile(FilePut uploadFile) {
    this.uploadFile = uploadFile;
  }

  public FilePut getUploadFile() {
    return uploadFile;
  }


}
