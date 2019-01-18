package com.salesmanager.admin.components.content.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private static final int BUFFER_SIZE = 4096;

    public static byte[] zipFolder(File dir) throws IOException {

        ZipOutputStream zout = null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        try {
            zout = new ZipOutputStream(bout);
            addFolderToZip("", dir, zout);
            zout.close();
            return bout.toByteArray();
        } finally {
            if(zout != null){
                zout.flush();
                zout.close();
            }
            if(bout != null){
                bout.close();
            }
        }
    }

    public static void zipFolder(File dir, File zipFile) throws IOException {

        ZipOutputStream zout = null;
        FileOutputStream fout = null;

        try {
            fout = new FileOutputStream(zipFile);
            zout = new ZipOutputStream(fout);

            addFolderToZip("", dir, zout);

        } finally {
            if(zout != null){
                zout.flush();
                zout.close();
            }
            if(fout != null){
                fout.close();
            }
        }
    }

    /**
     *
     * @author Ecommerce
     * @throws IOException
     */
  private static void addFileToZip(String path, String srcFile, ZipOutputStream zout)
      throws IOException {

    File folder = new File(srcFile);
    if (folder.isDirectory()) {
      addFolderToZip(path, folder, zout);
    } else {
      byte[] buf = new byte[BUFFER_SIZE];
      int len;
      try (FileInputStream in = new FileInputStream(srcFile)) {

        zout.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
        while ((len = in.read(buf)) > 0) {
          zout.write(buf, 0, len);
        }
        //remove in.close(); because try catch block close that automatically
      } catch (IOException e) {
        throw new IOException();
      }
    }
  }

     private static void addFolderToZip(String path, File folder, ZipOutputStream zout) throws IOException {
        if( folder != null ) {
            for (String fileName : folder.list()) {
                if (path.equals("")) {
                    addFileToZip(folder.getName(), folder.getPath() + "/" + fileName, zout);
                } else {
                    addFileToZip(path + "/" + folder.getName(), folder.getPath() + "/" + fileName, zout);
                }
            }
        }
    }
}
