package Zara4.API.Communication.Http;

import java.util.HashMap;
import java.util.Map;


public class Sendable {


  protected Map<String,String> headers = new HashMap<String, String>();


  /**
   * Set the value of a header for this Request.
   *
   * @param key
   * @param value
   */
  public void setHeader(String key, String value) {
    this.headers.put(key, value);
  }

}
