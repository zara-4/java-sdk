package com.zara4.api.communication.accesstoken;

import com.zara4.api.communication.grant.RefreshTokenGrant;
import com.zara4.api.communication.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class RefreshableAccessToken extends AccessToken {

  private String refreshToken;


  /**
   * Construct
   *
   * @param clientId     The API client id
   * @param clientSecret The API client secret
   * @param accessToken  The access token string.
   * @param expiresAt    When does this AccessToken expire.
   */
  public RefreshableAccessToken(
      String clientId, String clientSecret, String accessToken, Date expiresAt,
      String refreshToken
  ) {
    super(clientId, clientSecret, accessToken, expiresAt);
    this.refreshToken = refreshToken;
  }



  @Override
  public void refresh() {
    RefreshTokenGrant grant = new RefreshTokenGrant(
        this.clientId, this.clientSecret, this.refreshToken,
        new ArrayList<String>()
    );
    Map<String,String> tokens = grant.getTokens();

    this.accessToken = tokens.get("access_token");
    this.expiresAt = Util.calculateExpiryTime(
        Integer.parseInt(tokens.get("expires_in"))
    );
    this.refreshToken = tokens.get("refresh_token");
  }


}
