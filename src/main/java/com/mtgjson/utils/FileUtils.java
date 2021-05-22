package com.mtgjson.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Utility class to downloads or load files
 */
public class FileUtils {
    
    /** Hide default. */
    private FileUtils() {}
    
    /**
     * Download using File HTTP.
     *
     * @param urlStr
     *          url
     * @param file
     *      current file
     * @throws IOException
     *      cannot download file
     */
    public static void downloadFile(String urlStr, String file) {
        URL url;
        FileOutputStream    fis = null;
        BufferedInputStream bis = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "bytes");
            bis = new BufferedInputStream(urlConnection.getInputStream());
            fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count=0;
            while((count = bis.read(buffer,0,1024)) != -1) {
                fis.write(buffer, 0, count);
            }
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Cannot read URL, invalid syntax",e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot download file",e);
        } finally {
            try {
                if (null != fis) fis.close();
                if (null!= bis)  bis.close();
            } catch (IOException e) {}
        }
    }
    
    /**
     * Download using NIO.
     *
     * @param urlStr
     *      target url
     * @param file
     *      target file
     * @throws IOException
     *      file not found or connection closed
     */
    public static void downloadFileNio(String urlStr, String file) {
        ReadableByteChannel rbc = null;
        FileOutputStream    fos = null;
        try {
            rbc = Channels.newChannel(new URL(urlStr).openStream());
            fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }  catch (IOException e) {
            throw new IllegalArgumentException("Cannot download file "+ urlStr, e);
        } finally {
            try { fos.close();rbc.close(); } catch (IOException e) {}
        }
    }
    
    /**
     * Download with streams.
     *
     * @param urlStr
     *      target url
     * @param file
     *      target file
     * @throws IOException
     */
    public static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

}
