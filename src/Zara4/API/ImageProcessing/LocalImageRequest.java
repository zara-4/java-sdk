package Zara4.API.ImageProcessing;

import Zara4.API.Communication.Http.Form.FileParameter;
import Zara4.API.Communication.Http.Form.FormData;

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
