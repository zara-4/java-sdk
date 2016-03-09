package com.zara4.api.communication.authentication;

import com.zara4.api.communication.accesstoken.AccessToken;
import com.zara4.api.communication.accesstoken.RefreshableAccessToken;
import com.zara4.api.communication.accesstoken.ReissuableAccessToken;
import com.zara4.api.communication.grant.ClientCredentialsGrantRequest;
import com.zara4.api.communication.Util;
import java.util.Date;
import java.util.Map;


public class UserAuthenticator extends Authenticator {

  private String username;
  private String password;

  public UserAuthenticator(
      String clientId, String clientSecret, String username, String password
  ) {
    super(clientId, clientSecret);
    this.username = username;
    this.password = password;
  }

  @Override
  public AccessToken acquireAccessToken() {
    ClientCredentialsGrantRequest grant = new ClientCredentialsGrantRequest(
        this.clientId, this.clientSecret, this.scopes
    );
    Map<String,String> tokens = grant.getTokens();

    String accessToken = tokens.get("access_token");
    String refreshToken = tokens.get("refresh_token");
    Date expiresAt = Util.calculateExpiryTime(
        Integer.parseInt(tokens.get("expires_in"))
    );

    return new RefreshableAccessToken(
        this.clientId, this.clientSecret, accessToken, expiresAt, refreshToken
    );
  }
}
