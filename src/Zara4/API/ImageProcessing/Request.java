package Zara4.API.ImageProcessing;


public class Request {

  private String id;
  private String imageName;
  private String originalFileSize;

  public Request(String id, String imageName, String originalFileSize) {
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


  public String originalFileSize() {
    return this.originalFileSize;
  }

}
