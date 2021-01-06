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
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class New_item_DB extends Thread{


    boolean flag = false;
    List<String> name = new ArrayList();
    List<String> price = new ArrayList();
    List<String> list3 = new ArrayList();
    List<Bitmap> image = new ArrayList<>();
    List<String> brand = new ArrayList();

    public void run(){
        try {
            JSONObject json = readJsonFromUrl("http://49.50.165.159/woozoosun/new_item.php");
            friends(json.toString());
            for(int i = 0; list3.size() > i ; i++){
                URL url = new URL("http://49.50.165.159/woozoosun"+list3.get(i));
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoOutput(true);
                conn.connect();
                InputStream is = conn.getInputStream();
                image.add(BitmapFactory.decodeStream(is));
            }
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
                String item = jsonob.getString("name");
                String item2 = jsonob.getString("price");
                String item3 = jsonob.getString("address");
                String item4 = jsonob.getString("brand");
                name.add(item);
                price.add(item2);
                list3.add(item3);
                brand.add(item4);
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
