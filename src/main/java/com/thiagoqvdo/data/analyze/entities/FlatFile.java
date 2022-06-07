package com.thiagoqvdo.data.analyze.entities;

public abstract class FlatFile {
    private String fileId;

    protected FlatFile(String id) {
        this.fileId = id;
    }

    public String getFileId() {
        return fileId;
    }

    @Override
    public String toString() {
        return "FlatFile{" +
                "fileiId='" + fileId + '\'' +
                '}';
    }
}
