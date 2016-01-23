package Zara4.API.Communication.Http.Form;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class FormData extends ArrayList<Parameter> {


  public FormData(Map<String, String> data) {
    Set<String> keys = data.keySet();
    for (String key : keys) {
      this.add(new TextParameter(key, data.get(key)));
    }
  }

  public void appendTo(OutputStream output, PrintWriter writer, String boundary) throws IOException {
    for (Parameter param : this) {
      param.appendTo(output, writer, boundary);
    }
  }


}
