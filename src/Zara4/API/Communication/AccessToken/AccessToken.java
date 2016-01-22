package Zara4.API.Communication.AccessToken;

import java.util.Date;

public abstract class AccessToken {

  protected String clientId;
  protected String clientSecret;
  protected String accessToken;
  protected Date expiresAt;


  /**
   * Construct
   *
   * @param clientId The API client id
   * @param clientSecret The API client secret
   * @param accessToken The access token string.
   * @param expiresAt When does this AccessToken expire.
   */
  public AccessToken(
      String clientId, String clientSecret, String accessToken, Date expiresAt
  ) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.accessToken = accessToken;
    this.expiresAt = expiresAt;
  }


  /**
   * Get the token
   *
   * @return The token as a String.
   */
  public String token() {
    if(this.hasExpired()) {
      this.refresh();
    }
    return this.accessToken;
  }


  /**
   * Represent this AccessToken as a String.
   *
   * @return Represent this AccessToken as a String.
   */
  @Override
  public String toString() {
    return this.token();
  }


  /**
   * Refresh this AccessToken.
   */
  public abstract void refresh();


  /**
   * Has this AccessToken expired?
   *
   * @return Has this AccessToken expired?
   */
  public boolean hasExpired() {
    Date now = new Date();
    return now.getTime() > this.expiresAt.getTime();
  }


}