package Zara4.API.ImageProcessing;


public class ProcessedImage {

  protected Request request;
  protected long originalFileSize;
  protected long compressedFileSize;


  public ProcessedImage(
      Request request, long originalFileSize, long compressedFileSize
  ) {
    this.request = request;
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
   * Save the compressed image to the given save path.
   *
   * @param savePath Where the compressed image should be downloaded to
   */
  public void downloadTo(String savePath) {

  }


  /**
   * Get the ratio by which the image has been compressed.
   *
   * @return The ratio of compression.
   */
  public double compressionRatio() {
    return this.compressedFileSize / this.originalFileSize;
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

}
