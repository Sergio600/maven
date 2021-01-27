import org.json.JSONArray;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;



public class Main {
    public static void main(String[] args) throws IOException {
        String url  = "https://jsonplaceholder.typicode.com/comments";
        String json = getJSON(url);
        System.out.println(json);


//        JSONObject obj = new JSONObject(json);
//
//        JSONArray arr= obj.getJSONArray("");
//        String result = arr.getJSONObject(0).getString("id");
//        System.out.println(result);


        JSONArray arr = new JSONArray(json);
        String s = arr.getJSONObject(0).toString();

        JSONObject jsonObject= arr.getJSONObject(0);



        System.out.println(jsonObject.getString("email"));
        System.out.println(jsonObject.length());
        System.out.println(jsonObject.getInt("id"));







    }

    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }
}
