package Zara4.API.Communication.Grant;

import java.util.ArrayList;

public class ClientCredentialsGrantRequest extends GrantRequest {


  public ClientCredentialsGrantRequest(
      String clientId, String clientSecret, ArrayList<String> scopes
  ) {
    super(clientId, clientSecret, scopes);
  }


  @Override
  public String grantType() {
    return "client_credentials";
  }

}
