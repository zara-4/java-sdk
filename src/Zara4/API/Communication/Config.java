package Zara4.API.Communication;


public class Config {

  private static String BASE_URL = "https://zara4.com";


  public static String BASE_URL() {
    return Config.BASE_URL;
  }


  public static void set_BASE_URL(String baseUrl) {
    Config.BASE_URL = baseUrl;
  }

}
