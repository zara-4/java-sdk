package Zara4.API.Communication.Authentication;

import Zara4.API.Communication.AccessToken.AccessToken;

import java.util.ArrayList;

public abstract class Authenticator {

  protected String clientId;
  protected String clientSecret;
  protected ArrayList<String> scopes;


  public Authenticator(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }


  /**
   * Acquire an AccessToken using this AccessToken.
   *
   * @return An AccessToken.
   */
  public abstract AccessToken acquireAccessToken();


  /**
   * Add image processing to the Authenticator scope.
   *
   * @return this
   */
  public Authenticator withImageProcessing() {
    this.scopes.add("image-processing");
    return this;
  }


  /**
   * Add image processing to the Authenticator scope.
   *
   * @return this
   */
  public Authenticator withUsage() {
    this.scopes.add("usage");
    return this;
  }


}
