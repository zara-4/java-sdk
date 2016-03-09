package com.zara4.api.communication.grant;

import com.zara4.api.communication.Config;
import com.zara4.api.communication.http.form.FormData;
import com.zara4.api.communication.http.Method;
import com.zara4.api.communication.http.Request;
import com.zara4.api.communication.http.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
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

    FormData formData = new FormData(this.data());
    Request httpRequest = new Request(
        Config.oAuthAuthenticationEndPoint(), Method.POST, formData
    );
    try {
      Response httpResponse = httpRequest.execute();

      JSONObject jsonObj = httpResponse.contentAsJson();

      String accessToken = jsonObj.get("access_token").toString();
      String expiresIn = jsonObj.get("expires_in").toString();

      Map<String, String> tokens = new HashMap<String,String>();
      tokens.put("access_token", accessToken);
      tokens.put("expires_in", expiresIn);
      return tokens;

    } catch (IOException e) {
      e.printStackTrace();
      return null;
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }



  protected Map<String,String> data() {

    Map<String, String> data = new HashMap<String, String>();

    String scopes = "";
    String delim = "";
    for (String scope : this.scopes) {
      scopes += delim + scope;
      delim = ",";
    }

    data.put("grant_type", this.grantType());
    data.put("client_id", this.clientId);
    data.put("client_secret", this.clientSecret);
    data.put("scope", scopes);

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
