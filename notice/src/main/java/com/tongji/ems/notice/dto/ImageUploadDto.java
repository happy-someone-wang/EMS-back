package com.tongji.ems.notice.dto;

public class ImageUploadDto {
    private String file;
    private String fileName;

    public ImageUploadDto() {
    }

    public ImageUploadDto(String file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
