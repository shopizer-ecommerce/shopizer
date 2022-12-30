package com.salesmanager.core.business.modules.cms.content;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.business.modules.cms.common.AssetsManager;
import com.salesmanager.core.business.modules.cms.impl.CMSManager;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.OutputContentFile;

import org.apache.commons.lang3.StringUtils;

public interface ContentAssetsManager extends AssetsManager, FileGet, FilePut, FileRemove, FolderPut, FolderList, FolderRemove, Serializable {
    public static final char UNIX_SEPARATOR = '/';
    public static final char WINDOWS_SEPARATOR = '\\';
    public static String DEFAULT_BUCKET_NAME = "shopizer";
    public static String DEFAULT_REGION_NAME = "us-east-1";
    public static final String ROOT_NAME = "files";

    CMSManager getCmsManager();

    default String bucketName() {
        String name = getCmsManager().getRootName();
        if (StringUtils.isBlank(name)) {
            name = DEFAULT_BUCKET_NAME;
        }
        return name;
    }

    default String nodePath(String store, FileContentType type) {

        StringBuilder builder = new StringBuilder();
        String root = nodePath(store);
        builder.append(root);
        if (type != null && !FileContentType.IMAGE.name().equals(type.name()) && !FileContentType.STATIC_FILE.name().equals(type.name())) {
            builder.append(type.name()).append(Constants.SLASH);
        }

        return builder.toString();

    }

    default String nodePath(String store) {

        StringBuilder builder = new StringBuilder();
        builder.append(ROOT_NAME).append(Constants.SLASH).append(store).append(Constants.SLASH);
        return builder.toString();

    }

    default OutputContentFile getOutputContentFile(byte[] byteArray) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(byteArray.length);
        baos.write(byteArray, 0, byteArray.length);
        OutputContentFile ct = new OutputContentFile();
        ct.setFile(baos);
        return ct;
    }

    default boolean isInsideSubFolder(String key) {
        int c = StringUtils.countMatches(key, Constants.SLASH);
        return c > 2;
    }

      default String getName(String filename) {
        if (filename == null) {
          return null;
        }
        int index = indexOfLastSeparator(filename);
        return filename.substring(index + 1);
      }
    
      default int indexOfLastSeparator(String filename) {
        if (filename == null) {
          return -1;
        }
        int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
        int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
        return Math.max(lastUnixPos, lastWindowsPos);
      }
    
    

}