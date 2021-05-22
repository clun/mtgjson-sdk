package com.mtgjson.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.mtgjson.MtgJsonClient;
import com.mtgjson.utils.FileUtils;

public abstract class AbstractClient {
    
    protected abstract String getFileName();
    
    protected String getFileName(String ext) {
        return getFileName() + "." + ext;
    }
    
    protected String getUrl() {
        return MtgJsonClient.URL + getFileName();
    }
    
    protected String getUrl(String ext) {
        return MtgJsonClient.URL + getFileName() + "." + ext;
    }
    
    protected String getSha256(String ext) {
        try {
           return new BufferedReader(
                   new InputStreamReader(new URL(getUrl(ext + ".sha256"))
                           .openConnection()
                           .getInputStream(), StandardCharsets.UTF_8)).readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot read sha256 " + getUrl(ext + "sha256"), e);
        }
    }
    
    public void downloadJson(String destination) {
        FileUtils.downloadFileNio(getUrl(), destination);
    }
    public String getChecksum() {
        try {
            return new BufferedReader(
                    new InputStreamReader(new URL(getUrl("sha256"))
                            .openConnection()
                            .getInputStream(), StandardCharsets.UTF_8)).readLine();
         } catch (IOException e) {
             throw new IllegalArgumentException("Cannot read sha256 " + getUrl("sha256"), e);
         }
    }
    public void downloadZip(String destination) {
        FileUtils.downloadFileNio(getUrl("zip"), destination);
    }
    public String getChecksumZIP() {
        return getSha256("zip");
    }
    public void downloadXZ(String destination) {
        FileUtils.downloadFileNio(getUrl("xz"), destination);
    }
    public String getChecksumXZ() {
        return getSha256("xz");
    }
    public void downloadBZ2(String destination) {
        FileUtils.downloadFileNio(getUrl("bz2"), destination);
    }
    public String getChecksumBZ2() {
        return getSha256("xz");
    }
    public void downloadGZ(String destination) {
        FileUtils.downloadFileNio(getUrl("gz"), destination);
    }
    public String getChecksumGZ() {
        return getSha256("gz");
    }
    public void downloadAll(String destination) {
        downloadJson(destination + File.pathSeparator + getFileName());
        downloadZip(destination  + File.pathSeparator + getFileName("zip"));
        downloadXZ(destination   + File.pathSeparator + getFileName("xz"));
        downloadBZ2(destination  + File.pathSeparator + getFileName("bz2"));
        downloadGZ(destination   + File.pathSeparator + getFileName("gz"));
    }

}
