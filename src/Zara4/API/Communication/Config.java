package Zara4.API.Communication;


public class Config {

  private static final String PRODUCTION_API_ENDPOINT = "https://zara4.com";
  private static final String DEVELOPMENT_API_ENDPOINT = "http://zara4.dev";

  // Assume production mode by default.
  private static String API_ENDPOINT = Config.PRODUCTION_API_ENDPOINT;


  /**
   * Get the base url of the API endpoint.
   *
   * @return The base url of the API endpoint.
   */
  public static String apiEndpointBaseUrl() {
    return Config.API_ENDPOINT;
  }


  /**
   * Configure production mode.
   */
  public static void enterProductionMode() {
    Config.API_ENDPOINT = PRODUCTION_API_ENDPOINT;
  }


  /**
   * Configure development mode.
   */
  public static void enterDevelopmentMode() {
    Config.API_ENDPOINT = DEVELOPMENT_API_ENDPOINT;
  }

}
