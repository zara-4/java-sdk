package Zara4.API.ImageProcessing;

import org.json.simple.JSONObject;
import java.lang.*;


public class ProcessedImage {

  protected Request request;
  protected String requestId;
  public String[] fileUrls;
  protected long originalFileSize;
  protected long compressedFileSize;



  public ProcessedImage(
      Request request, String requestId, String[] fileUrls,
      long originalFileSize, long compressedFileSize
  ) {
    this.request = request;
    this.fileUrls = fileUrls;
    this.requestId = requestId;
    this.originalFileSize = originalFileSize;
    this.compressedFileSize = compressedFileSize;
  }


  /**
   * Get the file size (in bytes) of the original uncompressed image.
   *
   * @return The file size of the original image.
   */
  public long originalFileSize() {
    return this.originalFileSize;
  }


  /**
   * Get the file size (in bytes) of the compressed image.
   *
   * @return The file size of the compressed image.
   */
  public long compressedFileSize() {
    return this.compressedFileSize;
  }



  /**
   * Get the ratio by which the image has been compressed.
   *
   * @return The ratio of compression.
   */
  public double compressionRatio() {
    return (double)this.compressedFileSize / (double)this.originalFileSize;
  }


  /**
   * The percentage compression achieved.
   *
   * @return The saving achieved as a percentage.
   */
  public double percentageSaving() {
    return (1 - this.compressionRatio()) * 100;
  }


  /**
   * Was the original image compressed?
   *
   * @return Was compression achieved?
   */
  public boolean compressionWasAchieved() {
    return this.compressionRatio() < 1;
  }


  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("original-file-size", this.originalFileSize());
    json.put("compressed-file-size", this.compressedFileSize());
    json.put("percentage-saving", this.percentageSaving());
    json.put("compression-ratio", this.compressionRatio());
    return json;
  }


  public String toString() {
    return this.toJson().toJSONString();
  }

}
