package com.zara4.api.imageprocessing;

public enum ColourEnhancement {
  NONE, IMPROVE_COLOUR, DINGY_ROOM;

  @Override
  public String toString() {
    switch (this) {
      case IMPROVE_COLOUR:
        return "improve-colour";
      case DINGY_ROOM:
        return "dingy-room";
      case NONE:
      default:
        return "none";
    }
  }
}
