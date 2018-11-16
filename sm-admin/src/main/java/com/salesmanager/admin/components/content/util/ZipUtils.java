package com.salesmanager.admin.components.content.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

    private static final int BUFFER_SIZE = 4096;

    static public byte[] zipFolder(File dir) throws IOException {

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

    static public void zipFolder(File dir, File zipFile) throws IOException {

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


    static private void addFileToZip(String path, String srcFile, ZipOutputStream zout) throws IOException {

        File folder = new File(srcFile);
        if (folder.isDirectory()) {
            addFolderToZip(path, folder, zout);
        } else {
            byte[] buf = new byte[BUFFER_SIZE];
            int len;
            FileInputStream in = null;
            try {
                in = new FileInputStream(srcFile);
                zout.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
                while ((len = in.read(buf)) > 0) {
                    zout.write(buf, 0, len);
                }
            } finally {
                if(zout != null) zout.closeEntry();
                if(in != null) in.close();
            }

        }
    }

    static private void addFolderToZip(String path, File folder, ZipOutputStream zout) throws IOException {
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
