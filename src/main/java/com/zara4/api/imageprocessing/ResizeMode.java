package com.zara4.api.imageprocessing;

public enum ResizeMode {
  NONE, WEB_BUNDLE, STRETCH, CROP;

  @Override
  public String toString() {
    switch (this) {
      case STRETCH:
        return "stretch";
      case CROP:
        return "crop";
      case WEB_BUNDLE:
        return "web-bundle";
      case NONE:
      default:
        return "none";
    }
  }
}
