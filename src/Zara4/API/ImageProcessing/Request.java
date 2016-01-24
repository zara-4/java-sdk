package Zara4.API.ImageProcessing;

import Zara4.API.Communication.Http.Form.FormData;
import java.util.HashMap;
import java.util.Map;


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


  public FormData generateFormData() {
    Map<String,String> data = new HashMap<String,String>();
    data.put("optimisation-mode", this.optimisationMode.toString());
    data.put("output-format", this.outputFormat.toString());
    data.put("resize-mode", this.resizeMode.toString());
    data.put("colour-enhancement", this.resizeMode.toString());
    return new FormData(data);
  }

}
