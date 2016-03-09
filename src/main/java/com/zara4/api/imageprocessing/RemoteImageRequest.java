package com.zara4.api.imageprocessing;

import com.zara4.api.communication.http.form.FormData;
import com.zara4.api.communication.http.form.TextParameter;


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
