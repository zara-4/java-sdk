package Zara4.API.ImageProcessing;

import Zara4.API.Communication.Http.Form.FormData;
import Zara4.API.Communication.Http.Form.TextParameter;


public class RemoteImageRequest extends Request {

  protected String url;

  public RemoteImageRequest(String url) {
    this.url = url;
  }

  @Override
  public FormData generateFormData() {
    FormData formData = super.generateFormData();
    formData.add(new TextParameter("url", this.url));
    return formData;
  }

}
