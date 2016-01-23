package Zara4.API.Communication.Http.Form;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.nio.file.Files;

public class FileParameter extends Parameter {

  private File file;

  public FileParameter(String name, File file) {
    super(name);
    this.file = file;
  }


  @Override
  public void appendTo(OutputStream output, PrintWriter writer, String boundary) throws IOException {
    // Send binary file.
    writer.append("--").append(boundary).append(CRLF);
    writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + this.file.getName() + "\"").append(CRLF);
    writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(this.file.getName())).append(CRLF);
    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    writer.append(CRLF).flush();
    Files.copy(this.file.toPath(), output);
    output.flush(); // Important before continuing with writer!
    writer.append(CRLF).flush(); // CRLF is important! It indicates end of boundary.
  }

}
