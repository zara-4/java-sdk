package Zara4.API.Communication.Http;

import Zara4.API.Communication.Config;
import Zara4.API.Communication.Http.Form.FormData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class Request extends Sendable {

  private String url;
  private Method method;
  private FormData formData;


  public Request(String url, Method method) {
    this.url = url;
    this.method = method;
  }


  public Request(String url, Method method, FormData formData) {
    this.url = url;
    this.method = method;
    this.formData = formData;
  }


  /**
   * Execute this Request.
   *
   * @return
   * @throws IOException
   */
  public Response execute() throws IOException {

    OutputStream output = null;
    PrintWriter writer = null;

    // --- --- ---

    HttpURLConnection connection = (HttpURLConnection)(new URL(this.url).openConnection());
    connection.setRequestMethod(method.toString());

    try {

      //
      // Add form data (if specified)
      //
      if(this.formData != null) {
        String boundary = "----Zara4JavaSdkFormBoundary"
            + Long.toHexString(System.currentTimeMillis());

        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("User-Agent", Config.USER_AGENT);
        connection.setRequestProperty("Cache-Control", "no-cache");

        output = connection.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(output, "UTF-8"), true);

        formData.appendTo(output, writer, boundary);
        writer.append("--").append(boundary).append("--").append("\r\n").flush();
      }

      // --- --- ---

      //
      // Read response from server.
      //
      InputStream is = connection.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);

      String line;
      String content = br.readLine();
      while ((line = br.readLine()) != null) {
        content += "\r\n" + line;
      }


      //
      // Create HTTP Response
      //
      Response response = new Response(content, connection.getResponseCode());
      //connection.get

      return response;
    }


    //
    // Tidy up to ensure connection is closed appropriately.
    //
    finally {

      // Close writer
      if(writer != null) {
        writer.close();
      }

      // Close output
      if(output != null) {
        output.close();
      }

      // Close connection
      connection.disconnect();
    }
  }


}
