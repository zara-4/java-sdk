package Zara4.API.Communication.Http;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Response extends Sendable {

  public String content;
  public int responseCode;


  public Response(String content, int responseCode) {
    this.content = content;
    this.responseCode = responseCode;
  }


  public JSONObject contentAsJson() throws ParseException {
    JSONParser parser = new JSONParser();
    Object obj = obj = parser.parse(this.content);
    return (JSONObject)obj;
  }

}
