package com.zara4.api.communication.http.form;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;


public class FormData extends ArrayList<Parameter> {

  public FormData() {
    //Stub
  }

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


  @Override
  public String toString() {
    String str = "--- FORM DATA ---";
    for (Parameter parameter : this) {
      str += "\r\n" + parameter.toString();
    }
    return str;
  }

}
