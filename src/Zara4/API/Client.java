package Zara4.API;


import Zara4.API.Communication.AccessToken.AccessToken;
import Zara4.API.Communication.Authentication.ApplicationAuthenticator;
import Zara4.API.Communication.Config;
import Zara4.API.Communication.Http.Method;
import Zara4.API.Communication.Http.Response;
import Zara4.API.ImageProcessing.ProcessedImage;
import Zara4.API.ImageProcessing.Request;

import java.io.IOException;
import java.lang.*;

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

    this.accessToken = authenticator.acquireAccessToken();
  }


  /**
   * Process the given image processing Request.
   *
   * @param imageProcessingRequest The request to be processed.
   * @return The ProcessedImage.
   */
  public ProcessedImage processImage(Request imageProcessingRequest) {

    String url = Config.apiEndpointUrl() + "/api/image-processing/optimise";

    Zara4.API.Communication.Http.Request httpRequest =
        new Zara4.API.Communication.Http.Request(url, Method.POST);

    try {
      Response httpResponse = httpRequest.execute();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

}
