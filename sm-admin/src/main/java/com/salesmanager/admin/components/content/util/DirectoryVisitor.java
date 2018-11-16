package com.salesmanager.admin.components.content.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryVisitor extends SimpleFileVisitor<Path> {

    private Path sourceDir;
    private Path targetDir;

    public DirectoryVisitor(Path sourceDir, Path targetDir) {
        this.sourceDir = sourceDir;
        this.targetDir = targetDir;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {

        try {
            Path targetFile =  targetDir.resolve(sourceDir.relativize(file));
            Files.copy(file, targetFile);
        } catch (IOException ex) {
            throw ex;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) throws IOException {
        try {
            Path newDir = targetDir.resolve(sourceDir.relativize(dir));
            if(!Files.exists(newDir)) {
                Files.createDirectory(newDir);
            }
        } catch (IOException ex) {
            throw ex;
        }

        return FileVisitResult.CONTINUE;
    }
}