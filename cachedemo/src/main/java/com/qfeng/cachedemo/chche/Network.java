package com.qfeng.cachedemo.chche;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by innershows on 16/6/21.
 */
public class Network {

    public static byte[] download(String url) {

        byte[] ret = null;
        try {
            HttpURLConnection conn = (HttpURLConnection)
                    new URL(url).openConnection();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();
                ret = is2ba(is);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }


    public static byte[] is2ba(InputStream is) {

        byte[] ret = null;
        ByteArrayOutputStream bos = null;
        try {

            bos = new ByteArrayOutputStream();

            int len = 0;
            byte[] buffer = new byte[512];

            while (-1 != (len = is.read(buffer))) {
                bos.write(buffer, 0, len);
            }

            ret = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }

                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }
}
