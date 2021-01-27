import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) throws IOException {
        String url  = "https://jsonplaceholder.typicode.com/posts";
        URL myURL = new URL(url);

        URLConnection connection = myURL.openConnection();

        System.out.println("Тип содержимого:" + connection.getContentType());
       ;

        System.out.println(connection.getContent().toString());





    }
}
