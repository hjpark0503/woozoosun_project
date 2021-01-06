package com.example.woozoosun_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item_Info_DB extends Thread{


    boolean flag = false;
    String name;
    String price;
    String photo_url;
    String brand;
    String comment;
    Bitmap photo;

    public Item_Info_DB(String name){
        this.name = name;
    }


    public void run(){
        try {
            JSONObject json = readJsonFromUrl("http://49.50.165.159/woozoosun/item_info.php?name="+name);
            friends(json.toString());
            URL url = new URL("http://49.50.165.159/woozoosun"+photo_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            photo = BitmapFactory.decodeStream(is);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }


    public void friends(String parse) {
        try {
            JSONObject jsonObject = new JSONObject(parse);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for(int i=0; i < jsonArray.length(); i++){
                JSONObject jsonob = jsonArray.getJSONObject(i);
                String item2 = jsonob.getString("price");
                String item3 = jsonob.getString("address");
                String item4 = jsonob.getString("brand");
                String item5 = jsonob.getString("comment");
                price = item2;
                photo_url = item3;
                brand = item4;
                comment = item5;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
