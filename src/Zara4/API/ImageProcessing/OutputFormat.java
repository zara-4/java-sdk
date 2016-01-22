package Zara4.API.ImageProcessing;

public enum OutputFormat {
  MATCH, JPEG, PNG;

  @Override
  public String toString() {
    switch (this) {
      case JPEG:
        return "jpeg";
      case PNG:
        return "png";
      case MATCH:
      default:
        return "match";
    }
  }
}
