package com.zara4.api.communication.grant;

import java.util.ArrayList;
import java.util.Map;

public class PasswordGrant extends GrantRequest {

  protected String username;
  protected String password;

  public PasswordGrant(
      String clientId, String clientSecret, String username, String password,
      ArrayList<String> scopes
  ) {
    super(clientId, clientSecret, scopes);
    this.username = username;
    this.password = password;
  }

  @Override
  public String grantType() {
    return "password";
  }


  /**
   *
   *
   * @return
   */
  protected Map<String,String> data() {
    Map<String,String> data = super.data();
    data.put("username", this.username);
    data.put("password", this.password);
    return data;
  }

}
