package com.zara4.api.imageprocessing;

public enum OptimisationMode {
  HIGHEST, COMPROMISE, LOWEST;

  @Override
  public String toString() {
    switch (this) {
      case HIGHEST:
        return "highest";
      case LOWEST:
        return "lowest";
      case COMPROMISE:
      default:
        return "compromise";
    }
  }
}
