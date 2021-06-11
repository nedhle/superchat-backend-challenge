package app.util;

import app.enums.Link;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static String simpleGetRequest(String tmpUrl){
        URL url = null;
        HttpURLConnection con = null;
        StringBuilder response = new StringBuilder();
        StringBuilder res = new StringBuilder();
        try {
            url = new URL(tmpUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            response.append("Code : " + con.getResponseCode());
            response.append("Message : " + con.getResponseMessage());

            //get response
            boolean success = con.getResponseCode()>=200 && con.getResponseCode()<300;
            InputStream is = success ? con.getInputStream() : con.getErrorStream();

            if(is!=null) {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;

                while ((line = rd.readLine()) != null) {
                    res.append(line);
                    res.append('\r');
                }
                rd.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return res.toString();
    }
    public static String getBitCoinPrice(){
        return simpleGetRequest(Link.BTC_URL);
    }
}
