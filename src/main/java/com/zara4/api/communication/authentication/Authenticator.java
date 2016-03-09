package com.zara4.api.communication.authentication;

import com.zara4.api.communication.accesstoken.AccessToken;
import java.util.ArrayList;


public abstract class Authenticator {

  protected String clientId;
  protected String clientSecret;
  protected ArrayList<String> scopes;


  public Authenticator(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scopes = new ArrayList<String>();
  }


  /**
   * Acquire an AccessToken using this Authenticator.
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
   * Add usage to the Authenticator scope.
   *
   * @return this
   */
  public Authenticator withUsage() {
    this.scopes.add("usage");
    return this;
  }


}
