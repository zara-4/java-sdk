package Zara4.API.ImageProcessing;


public class Request {

  private String id;
  private String imageName;
  private long originalFileSize;

  public Request(String id, String imageName, long originalFileSize) {
    this.id = id;
    this.imageName = imageName;
    this.originalFileSize = originalFileSize;
  }


  public String id() {
    return this.id;
  }


  public String imageName() {
    return this.imageName;
  }


  public long originalFileSize() {
    return this.originalFileSize;
  }

}
