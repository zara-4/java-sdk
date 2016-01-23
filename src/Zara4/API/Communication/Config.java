package Zara4.API.Communication;


public class Config {

  public static final String VERSION = "1.0.0";
  public static final String USER_AGENT = "Zara 4 JAVA-SDK, Version-" + VERSION;

  private static final String PRODUCTION_API_ENDPOINT = "https://zara4.com";
  private static final String DEVELOPMENT_API_ENDPOINT = "http://zara4.dev";

  // Assume production mode by default.
  private static String BASE_URL = Config.PRODUCTION_API_ENDPOINT;


  public static String oAuthAuthenticationEndPoint() {
    return Config.BASE_URL + "/oauth/access_token";
  }


  /**
   * Get the base url of the API endpoint.
   *
   * @return The base url of the API endpoint.
   */
  public static String apiEndpointUrl() {
    return Config.BASE_URL + "/api";
  }


  /**
   * Configure production mode.
   */
  public static void enterProductionMode() {
    Config.BASE_URL = PRODUCTION_API_ENDPOINT;
  }


  /**
   * Configure development mode.
   */
  public static void enterDevelopmentMode() {
    Config.BASE_URL = DEVELOPMENT_API_ENDPOINT;
  }

}
