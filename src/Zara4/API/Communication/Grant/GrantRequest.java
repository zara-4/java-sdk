package Zara4.API.Communication.Grant;

import Zara4.API.Communication.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


abstract public class GrantRequest {

  protected ArrayList<String> scopes;
  protected String clientId;
  protected String clientSecret;


  public GrantRequest(String clientId, String clientSecret, ArrayList<String> scopes) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.scopes = scopes;
  }

  /**
   * Get the grant type of this GrantRequest.
   *
   * @return The grant type of this GrantRequest.
   */
  public abstract String grantType();


  /**
   * @return array
   */
  public Map<String,String> getTokens() {
    return new HashMap<String,String>();
    //return Util.post(Util.url("/oauth/access_token"), this.data());
  }



  protected Map<String,String> data() {

    Map<String, String> data = new HashMap<String, String>();

    data.put("grant_type", this.grantType());
    data.put("client_id", this.clientId);
    data.put("client_secret", this.clientSecret);
    data.put("scope", String.join(",", this.scopes));

    return data;
  }



  /**
   * Add image processing to the request scope.
   *
   * @return this
   */
  public GrantRequest withImageProcessing() {
    this.scopes.add("image-processing");
    return this;
  }


  /**
   * Add usage to the request scope.
   *
   * @return $this
   */
  public GrantRequest withUsage() {
    this.scopes.add("usage");
    return this;
  }


}
