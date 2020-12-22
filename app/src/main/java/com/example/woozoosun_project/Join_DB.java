package com.example.woozoosun_project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Join_DB extends AsyncTask<Void, Integer, Void> {

    String name = "";
    String id = "";
    String pw = "";
    int phone;
    String addr1 = "";
    String addr2 = "";
    String addr3 = "";
    public Join_DB(String name, String id, String pw, int phone, String addr1, String addr2, String addr3){
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.phone = phone;
        this.addr1 = addr1;
        this.addr2 = addr2;
        this.addr3 = addr3;
    }

    @Override
    protected Void doInBackground(Void... unused) {

        //parameter data
        String param = "name=" + name + "&id=" + id + "&pw=" + pw + "&phone=" + phone + "&address=" + addr1 + "&address2=" + addr2 + "&address3=" + addr3 + "";
        try {
            /* 서버연결 */
            URL url = new URL(
                    "http://49.50.165.159/woozoosun/join.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.connect();

            /* 안드로이드 -> 서버 파라메터값 전달 */
            OutputStream outs = conn.getOutputStream();
            outs.write(param.getBytes("UTF-8"));
            outs.flush();
            outs.close();

            /* 서버 -> 안드로이드 파라메터값 전달 */
            InputStream is = null;
            BufferedReader in = null;
            String data = "";

            is = conn.getInputStream();
            in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
            String line = null;
            StringBuffer buff = new StringBuffer();
            while ( ( line = in.readLine() ) != null )
            {
                buff.append(line + "\n");
            }
            data = buff.toString().trim();
            Log.e("RECV DATA",data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
