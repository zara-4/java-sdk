package Zara4.API.Communication.Http.Form;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public abstract class Parameter {

  protected final String boundary;
  protected final String CRLF;

  protected String name;


  public Parameter(String name) {
    this.name = name;

    this.boundary = Long.toHexString(System.currentTimeMillis());
    this.CRLF = "\r\n";
  }


  /**
   * Append this Parameter to the given writer.
   *
   * @param output
   * @param writer
   */
  public abstract void appendTo(OutputStream output, PrintWriter writer, String boundary) throws IOException;

}
