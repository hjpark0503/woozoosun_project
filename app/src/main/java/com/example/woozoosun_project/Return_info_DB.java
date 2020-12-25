package com.example.woozoosun_project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Return_info_DB extends Thread{

    boolean flag = false;

    private String user = "";

    private String name = "";
    private String id = "";
    private String phone = "";
    private String address = "";
    private String address2 = "";
    private String address3 = "";

    public Return_info_DB(String search){
        user = search;
    }

    public void run() {
        try {
            JSONObject json = readJsonFromUrl("http://49.50.165.159/woozoosun/info.php?name="+user);
            info(json.toString());
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void info(String parse) {
        try {
            JSONObject jsonObject = new JSONObject(parse);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            JSONObject jsonob = jsonArray.getJSONObject(0);
            name = jsonob.getString("name");
            id = jsonob.getString("id");
            phone = jsonob.getString("phone");
            address = jsonob.getString("address");
            address2 = jsonob.getString("address2");
            address3 = jsonob.getString("address3");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String get_name(){
        return name;
    }
    public String get_id(){
        return id;
    }
    public String get_phone(){
        return phone;
    }
    public List get_address(){
        List<String> addr = new ArrayList();
        addr.add(address);
        addr.add(address2);
        addr.add(address3);
        return addr;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
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
}