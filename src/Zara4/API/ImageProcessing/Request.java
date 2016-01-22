package Zara4.API.ImageProcessing;


public abstract class Request {

  //
  // Default request configuration
  //
  public OptimisationMode optimisationMode = OptimisationMode.COMPROMISE;
  public OutputFormat outputFormat = OutputFormat.MATCH;
  public ResizeMode resizeMode = ResizeMode.NONE;
  public ColourEnhancement colourEnhancement = ColourEnhancement.NONE;

  
  /**
   * Execute this request and get the resulting processed image.
   *
   * @return The ProcessedImage resulting from this Request.
   */
  public abstract ProcessedImage process();

}
