package app.util;

import app.enums.BtcCurrency;
import app.enums.PlaceHolderMapper;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class GenericUtil {

    public static List<?> object2list(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[])obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>)obj);
        }
        return list;
    }

    public static String array2string(String[] arr){
        String str = String.join(",", arr);
        return str;
    }

    public static String filter4placeholder(String str, Map<String,String> map){
        StringBuilder tmp = new StringBuilder();
        if (str == null || str.length() == 0)
            return tmp.toString();
        tmp.append(str);
        
        for (int startIdx = tmp.indexOf("${"); startIdx > -1; startIdx = tmp.indexOf("${", startIdx + 2)) {
            int endIdx = tmp.indexOf("}", startIdx + 2);
            String subStr = tmp.substring(startIdx + 2, endIdx);
            String replaced = null;
            for (String key: map.keySet()){

                if(subStr.equals(key)){
                    replaced = map.get(key) != null ? map.get(key) : null;
                }

                if (replaced != null){
                    tmp.replace(startIdx, endIdx + 1, replaced);
                    replaced = null;
                }
            }
            if(subStr.contains(PlaceHolderMapper.BTC)){//for bitcoin value
                replaced = String.valueOf(filter4bitcoinValue(subStr));
            }
            if (replaced != null){
                tmp.replace(startIdx, endIdx + 1, replaced);
                replaced = null;
            }
        }
        return tmp.toString();
    }

    public static String filter4bitcoinValue(String key){

        String res = HttpUtil.getBitCoinPrice();
        try {
            JSONObject json = new JSONObject(res);

            if(key.contains(BtcCurrency.USD.toLowerCase(Locale.ROOT))) {
                System.out.println((String.valueOf(new JSONObject(String.valueOf(json.get("bpi"))).get("USD"))));

                return String.valueOf(new JSONObject(String.valueOf(new JSONObject(String.valueOf(json.get("bpi"))).get(BtcCurrency.USD))).get("rate_float"));
            }
            if(key.contains(BtcCurrency.EUR.toLowerCase(Locale.ROOT)))
                return String.valueOf(new JSONObject(String.valueOf(new JSONObject(String.valueOf(json.get("bpi"))).get(BtcCurrency.EUR))).get("rate_float"));
            if(key.contains(BtcCurrency.GBP.toLowerCase(Locale.ROOT)))
                return String.valueOf(new JSONObject(String.valueOf(new JSONObject(String.valueOf(json.get("bpi"))).get(BtcCurrency.GBP))).get("rate_float"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "0";
    }
}
