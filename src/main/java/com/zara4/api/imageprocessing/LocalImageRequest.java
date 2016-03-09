package com.zara4.api.imageprocessing;

import com.zara4.api.communication.http.form.FileParameter;
import com.zara4.api.communication.http.form.FormData;

import java.io.File;

public class LocalImageRequest extends Request {

  protected String pathToImage;

  public LocalImageRequest(String pathToImage) {
    this.pathToImage = pathToImage;
  }

  @Override
  public FormData generateFormData() {
    FormData formData = super.generateFormData();
    formData.add(new FileParameter("file", new File(pathToImage)));
    return formData;
  }

}
