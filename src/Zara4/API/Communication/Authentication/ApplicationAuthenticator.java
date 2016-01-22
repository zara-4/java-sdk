package Zara4.API.Communication.Authentication;


import Zara4.API.Communication.AccessToken.AccessToken;
import Zara4.API.Communication.AccessToken.ReissuableAccessToken;
import Zara4.API.Communication.Grant.ClientCredentialsGrantRequest;
import Zara4.API.Communication.Util;

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
