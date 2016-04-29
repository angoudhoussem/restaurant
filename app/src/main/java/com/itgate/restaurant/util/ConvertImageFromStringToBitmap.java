package com.itgate.restaurant.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by macbook on 15/04/2016.
 */
public class ConvertImageFromStringToBitmap {

    public static Bitmap convert(String img){
        byte[] b = Base64.decode(img, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }
    public static Bitmap drawable_from_url(String url) throws java.net.MalformedURLException, java.io.IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection)new URL(url) .openConnection();
        connection.setRequestProperty("User-agent","Mozilla/4.0");

        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return x;
    }
}
