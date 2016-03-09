package com.zara4.api.communication.authentication;


import com.zara4.api.communication.accesstoken.AccessToken;
import com.zara4.api.communication.accesstoken.ReissuableAccessToken;
import com.zara4.api.communication.grant.ClientCredentialsGrantRequest;
import com.zara4.api.communication.Util;

import java.util.Date;
import java.util.Map;

public class ApplicationAuthenticator extends Authenticator {

  public ApplicationAuthenticator(String clientId, String clientSecret) {
    super(clientId, clientSecret);
  }

  @Override
  public AccessToken acquireAccessToken() {
    ClientCredentialsGrantRequest grant = new ClientCredentialsGrantRequest(
        this.clientId, this.clientSecret, this.scopes
    );
    Map<String,String> tokens = grant.getTokens();

    String accessToken = tokens.get("access_token");
    Date expiresAt = Util.calculateExpiryTime(
        Integer.parseInt(tokens.get("expires_in"))
    );

    return new ReissuableAccessToken(
        this.clientId, this.clientSecret, accessToken, expiresAt, this.scopes
    );
  }
}
