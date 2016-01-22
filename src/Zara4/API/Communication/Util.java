package Zara4.API.Communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;


public class Util {

  public static final String BASE_URL = "https://zara4.com";


  /**
   * Get the url to the given path.
   *
   * @param path
   * @return
   */
  public static String url(String path) {
    return Util.BASE_URL + path;
  }


  /**
   * Post the given $data to the given $url.
   */
  public static String post(String targetURL, Map<String,String> data) {

    String urlParameters = "";

    boolean first = true;
    for(Map.Entry<String, String> entry : data.entrySet()) {
      if(!first) {
        urlParameters += "&";
      } else {
        first = false;
      }
      urlParameters += entry.getKey() + "=" + entry.getValue();
    }


    HttpURLConnection connection = null;
    try {
      // Create connection
      URL url = new URL(targetURL);
      connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

      connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
      connection.setRequestProperty("Content-Language", "en-US");

      connection.setUseCaches(false);
      connection.setDoOutput(true);

      // Send request
      DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.close();

      // Get Response
      InputStream is = connection.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is));
      StringBuilder response = new StringBuilder();
      String line;
      while((line = rd.readLine()) != null) {
        response.append(line);
        response.append('\r');
      }
      rd.close();
      return response.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      if(connection != null) {
        connection.disconnect();
      }
    }
  }


  /**
   * Calculate the expiry time from the given lifetime time.
   *
   * @param expiresInSeconds
   * @return
   */
  public static Date calculateExpiryTime(int expiresInSeconds) {
    // Give 60 second buffer for expiry
    expiresInSeconds = expiresInSeconds - 60;
    long expiresInMilliseconds = expiresInSeconds * 1000;
    return new Date(new Date().getTime() + expiresInMilliseconds);
  }


}
