package Zara4.API.ImageProcessing;

import Zara4.API.Client;


public abstract class Request {

  //
  // Default request configuration
  //
  public OptimisationMode optimisationMode = OptimisationMode.COMPROMISE;
  public OutputFormat outputFormat = OutputFormat.MATCH;
  public ResizeMode resizeMode = ResizeMode.NONE;
  public ColourEnhancement colourEnhancement = ColourEnhancement.NONE;


  public Request() {
    this.optimisationMode = OptimisationMode.COMPROMISE;
    this.outputFormat = OutputFormat.MATCH;
    this.resizeMode = ResizeMode.NONE;
    this.colourEnhancement = ColourEnhancement.NONE;
  }


  public Request(
      OptimisationMode optimisationMode, OutputFormat outputFormat,
      ResizeMode resizeMode, ColourEnhancement colourEnhancement
  ) {
    this.optimisationMode = optimisationMode;
    this.outputFormat = outputFormat;
    this.resizeMode = resizeMode;
    this.colourEnhancement = colourEnhancement;
  }

}
