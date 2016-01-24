package Zara4.API;


import Zara4.API.Communication.AccessToken.AccessToken;
import Zara4.API.Communication.Authentication.ApplicationAuthenticator;
import Zara4.API.Communication.Config;
import Zara4.API.Communication.Http.Form.FormData;
import Zara4.API.Communication.Http.Form.TextParameter;
import Zara4.API.Communication.Http.Method;
import Zara4.API.Communication.Http.Response;
import Zara4.API.ImageProcessing.ProcessedImage;
import Zara4.API.ImageProcessing.Request;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Client {

  protected AccessToken accessToken;

  protected String apiClientId;
  protected String apiClientSecret;


  /**
   * Basic client - anonymous usage.
   */
  public Client() {
    // Stub - for anonymous usage
  }


  /**
   * Application authenticated client.
   *
   * @param apiClientId The client id to be used for api communication
   * @param apiClientSecret The client secret to be used for api communication
   */
  public Client(String apiClientId, String apiClientSecret) {
    this.apiClientId = apiClientId;
    this.apiClientSecret = apiClientSecret;

    ApplicationAuthenticator authenticator = new ApplicationAuthenticator(
        apiClientId, apiClientSecret);

    authenticator.withImageProcessing().withUsage();

    this.accessToken = authenticator.acquireAccessToken();
  }


  /**
   * Process the given image processing Request.
   *
   * @param imageProcessingRequest The request to be processed.
   * @return The ProcessedImage.
   */
  public ProcessedImage processImage(Request imageProcessingRequest)
      throws Zara4.API.ImageProcessing.Exception, UnknownServerErrorException
  {
    try {

      String url = Config.apiEndpointUrl() + "/image-processing/optimise";

      FormData formData = imageProcessingRequest.generateFormData();
      if(this.accessToken != null) {
        formData.add(new TextParameter("access_token", this.accessToken.token()));
      }

      Zara4.API.Communication.Http.Request httpRequest =
          new Zara4.API.Communication.Http.Request(url, Method.POST, formData);

      Response httpResponse = httpRequest.execute();


      JSONObject json = httpResponse.contentAsJson();

      String status = json.get("status").toString();

      if(!status.equals("ok")) {
        throw new Zara4.API.ImageProcessing.Exception();
      }

      String requestId = json.get("request-id").toString();

      JSONObject compression = (JSONObject)json.get("compression");
      long bytesOriginal = Long.parseLong(compression.get("bytes-original").toString());
      long bytesCompressed = Long.parseLong(compression.get("bytes-compressed").toString());

      JSONObject generatedImages = (JSONObject)json.get("generated-images");
      JSONArray urls = (JSONArray)generatedImages.get("urls");
      String[] generatedImagesUrls = new String[generatedImages.size()];
      urls.toArray(generatedImagesUrls);

      return new ProcessedImage(
          imageProcessingRequest, requestId, generatedImagesUrls,
          bytesOriginal, bytesCompressed
      );

    } catch (IOException e) {
      e.printStackTrace();
      throw new UnknownServerErrorException();
    } catch (ParseException e) {
      throw new UnknownServerErrorException();
    }
  }


  /**
   * Download the given ProcessedImage and save it to the given path.
   *
   * @param processedImage The ProcessedImage to be downloaded.
   * @param savePath The path where the image should be saved.
   */
  public void downloadProcessedImage(
      ProcessedImage processedImage, String savePath
  ) {
    String url = processedImage.fileUrls[0];

    try {

      if(this.accessToken != null) {
        url += "?access_token=" + this.accessToken.token();
      }

      URL website = new URL(url);
      ReadableByteChannel rbc = Channels.newChannel(website.openStream());
      FileOutputStream fos = new FileOutputStream(savePath);
      fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
