package com.wernercd.awsgen;

import java.net.URL;

public class FileUrl {
    private String file;
    public String getFile() {
        return file;
    }
    public void setFile(String accessKeyId) {
        this.file = accessKeyId;
    }

    private URL url;
    public URL getUrl() {
        return url;
    }
    public void setUrl(URL url) {
        this.url = url;
    }

    public FileUrl(String file){
        this.file = file;
    }
}
