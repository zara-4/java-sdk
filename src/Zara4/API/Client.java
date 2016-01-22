package Zara4.API;


import Zara4.API.Communication.AccessToken.AccessToken;
import Zara4.API.Communication.Authentication.ApplicationAuthenticator;
import Zara4.API.ImageProcessing.ProcessedImage;
import Zara4.API.ImageProcessing.Request;

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
   * @param request The request to be processed.
   * @return The ProcessedImage.
   */
  public ProcessedImage processImage(Request request) {
    return null;
  }

}
