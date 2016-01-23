package Zara4.API.Communication.Http.Form;

import java.io.OutputStream;
import java.io.PrintWriter;


public class TextParameter extends Parameter {

  protected String value;


  public TextParameter(String name, String value) {
    super(name);
    this.value = value;
  }

  @Override
  public void appendTo(OutputStream output, PrintWriter writer, String boundary) {
    writer.append("--" + boundary).append(CRLF);
    writer.append("Content-Disposition: form-data; name=\"" + this.name + "\"").append(CRLF);
    writer.append("Content-Type: text/plain; charset=UTF-8").append(CRLF);
    writer.append(CRLF).append(this.value).append(CRLF).flush();
  }

}
