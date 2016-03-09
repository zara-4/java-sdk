package com.zara4.api.communication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;


public class Util {


  /**
   * Get the url to the given path.
   *
   * @param path
   * @return
   */
  public static String url(String path) {
    return Config.apiEndpointUrl() + path;
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

    /*
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
    */
    return "";
  }


  public static int UploadImage() throws IOException {

    String url = "http://example.com/upload";
    String charset = "UTF-8";
    String param = "value";
    File textFile = new File("/path/to/file.txt");
    File binaryFile = new File("/path/to/file.bin");
    String boundary = Long.toHexString(System.currentTimeMillis()); // Just generate some unique random value.
    String CRLF = "\r\n"; // Line separator required by multipart/form-data.

    URLConnection connection = new URL(url).openConnection();
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

    try {

      OutputStream output = connection.getOutputStream();
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);

      // Send normal param.
      writer.append("--" + boundary).append(CRLF);
      writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF);
      writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF);
      writer.append(CRLF).append(param).append(CRLF).flush();

      // Send text file.
      writer.append("--" + boundary).append(CRLF);
      writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.getName() + "\"").append(CRLF);
      writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF); // Text file itself must be saved in this charset!
      writer.append(CRLF).flush();
      Files.copy(textFile.toPath(), output);
      output.flush(); // Important before continuing with writer!
      writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

      // Send binary file.
      writer.append("--").append(boundary).append(CRLF);
      writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF);
      writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.getName())).append(CRLF);
      writer.append("Content-Transfer-Encoding: binary").append(CRLF);
      writer.append(CRLF).flush();
      Files.copy(binaryFile.toPath(), output);
      output.flush(); // Important before continuing with writer!
      writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.

      // End of multipart/form-data.
      writer.append("--").append(boundary).append("--").append(CRLF).flush();

    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

// Request is lazily fired whenever you need to obtain information about response.
    return ((HttpURLConnection) connection).getResponseCode();

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
