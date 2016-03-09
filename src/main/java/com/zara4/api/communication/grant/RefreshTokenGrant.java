package com.zara4.api.communication.grant;

import java.util.ArrayList;
import java.util.Map;


public class RefreshTokenGrant extends GrantRequest {

  private String refreshToken;


  public RefreshTokenGrant(
      String clientId, String clientSecret, String refreshToken,
      ArrayList<String> scopes
  ) {
    super(clientId, clientSecret, scopes);
    this.refreshToken = refreshToken;
  }


  @Override
  public String grantType() {
    return "refresh_token";
  }


  /**
   *
   * @return
   */
  protected Map<String,String> data() {
    Map<String,String> data = super.data();
    data.put("refresh_token", this.refreshToken);
    return data;
  }

}
