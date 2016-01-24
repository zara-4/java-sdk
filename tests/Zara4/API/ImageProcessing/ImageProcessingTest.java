package Zara4.API.ImageProcessing;

import static org.junit.Assert.*;

import Zara4.API.TestCase;
import Zara4.API.UnknownServerErrorException;
import org.junit.Test;


public class ImageProcessingTest extends TestCase {


  @Test
  public void testDefaultOptimiseLocalImage()
      throws Exception, UnknownServerErrorException {
    LocalImageRequest originalImage = new LocalImageRequest("test-images/001.jpg");
    ProcessedImage processedImage = this.apiClient.processImage(originalImage);

    String fileName = Long.toHexString(System.currentTimeMillis()) + ".jpg";
    String path = "/tmp/" + fileName;

    processedImage.downloadTo(path);

    assertTrue(processedImage.compressionRatio() < 1);
    assertTrue(processedImage.compressionRatio() > 0);
  }



  @Test
  public void testDefaultOptimiseRemoteImage()
      throws UnknownServerErrorException, Exception
  {
    RemoteImageRequest originalImage = new RemoteImageRequest("https://zara4.com/img/comparison/beach/original.jpg");
    ProcessedImage processedImage = this.apiClient.processImage(originalImage);

    String fileName = Long.toHexString(System.currentTimeMillis()) + ".jpg";
    String path = "/tmp/" + fileName;

    processedImage.downloadTo(path);

    assertTrue(processedImage.originalFileSize == 402896);
    assertTrue(processedImage.compressedFileSize <= 62511);
    assertTrue(processedImage.compressedFileSize > 0);
  }

}
