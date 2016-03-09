package com.zara4.api.communication.http;

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
