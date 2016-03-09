package com.zara4.api.communication.accesstoken;

import com.zara4.api.communication.grant.ClientCredentialsGrantRequest;
import com.zara4.api.communication.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ReissuableAccessToken extends AccessToken {

  private ArrayList<String> scopes;

  /**
   * Construct
   *
   * @param clientId     The API client id
   * @param clientSecret The API client secret
   * @param accessToken  The access token string.
   * @param expiresAt    When does this AccessToken expire.
   */
  public ReissuableAccessToken(
      String clientId, String clientSecret, String accessToken, Date expiresAt,
      ArrayList<String> scopes
  ) {
    super(clientId, clientSecret, accessToken, expiresAt);
    this.scopes = scopes;
  }



  @Override
  public void refresh() {
    ClientCredentialsGrantRequest grant = new ClientCredentialsGrantRequest(
        this.clientId, this.clientSecret, this.scopes
    );
    Map<String,String> tokens = grant.getTokens();

    this.accessToken = tokens.get("access_token");
    this.expiresAt = Util.calculateExpiryTime(
        Integer.parseInt(tokens.get("expires_in"))
    );
  }

}
