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


  /**
   * Execute this request and get the resulting processed image.
   *
   * @return The ProcessedImage resulting from this Request.
   */
  public ProcessedImage process(Client client) {
    return client.processImage(this);
  }

}
