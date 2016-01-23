package Zara4.API.Communication.Http;


public class Response extends Sendable {

  public String content;
  public int responseCode;


  public Response(String content, int responseCode) {
    this.content = content;
    this.responseCode = responseCode;
  }


}
