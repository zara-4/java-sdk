package Zara4.API.ImageProcessing;

public class LocalImageRequest extends Request {

  protected String pathToImage;

  public LocalImageRequest(String pathToImage) {
    this.pathToImage = pathToImage;
  }

}