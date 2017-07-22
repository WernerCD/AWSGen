package com.wernercd.awsgen;

import java.io.File;
import java.net.URL;
import java.util.*;

public class GeneratePreSignedUrls extends ArrayList<GeneratePreSignedUrl> {
    private String accessKeyId;
    public String getAccessKeyId() {
        return accessKeyId;
    }
    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    private String secretKeyId;
    public String getSecretKeyId() {
        return secretKeyId;
    }
    public void setSecretKeyId(String secretKeyId) {
        this.secretKeyId = secretKeyId;
    }

    private String bucket;
    public String getBucket() {
        return bucket;
    }
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    private int expirationInMinutes;
    public int getExpirationInMinutes() {
        return expirationInMinutes;
    }
    public void setExpirationInMinutes(int exp) {
        this.expirationInMinutes = exp;
    }

    private String files;
    public String getFiles() {
        return files;
    }
    public void setFiles(String files) {
        this.files = files;
    }

    private ArrayList<FileUrl> fileUrls;
    public ArrayList<FileUrl> getFileUrls() {
        return fileUrls;
    }

    public GeneratePreSignedUrls() {
        accessKeyId = null;
        secretKeyId = null;
        bucket = null;
        files = null;
    }

//    public void GenerateFileUrls() {
//        System.out.println("GenerateFileUrls");
//        this.fileUrls = new ArrayList<>();
//
//        for(String line : filez) {
//            String l = line.trim();
//            System.out.println("GenerateFileUrls: File: ~" + l + "~");
//            if (l == null) continue;
//            if (l.length() == 0) continue;
////            if (l.matches("\\S")) continue;
//            System.out.println("GenerateFileUrls: FileAdd: ~" + l + "~");
//            this.fileUrls.add(new FileUrl(l));
//            System.out.println();
//        }
//        System.out.println();
//    }
    public void GenerateUrls() {
        System.out.println("GenerateUrls");

        ArrayList<FileUrl> _fileUrls = new ArrayList<>();
        for(String line : Arrays.asList(files.split(System.getProperty("line.separator"))))
        {
            String l = line.trim();
            if (l == null || l.length() == 0) continue;
            FileUrl fileUrl = new FileUrl(l);
            String file = fileUrl.getFile();
            System.out.println("GenerateUrls: ForFile: " + file);

            GeneratePreSignedUrl gu = new GeneratePreSignedUrl();
            gu.setAccessKeyId(getAccessKeyId());
            gu.setSecretKeyId(getSecretKeyId());
            gu.setBucket(getBucket());
            gu.setExpirationInMinutes(getExpirationInMinutes());
            gu.setFile(file);

            URL url = gu.getGeneratedUrl();
            fileUrl.setUrl(url);
            System.out.println("GenerateUrls: ForUrl: " + fileUrl.getUrl());
            _fileUrls.add(fileUrl);
        }
        this.fileUrls = _fileUrls;
        System.out.println("GenerateUrls: FinFin.");
    }
}
