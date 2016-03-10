import com.zara4.api.Client;
import com.zara4.api.communication.Config;
import org.junit.Before;


public class TestCase {

  protected final String apiClientId = "9WQcfHDyd8BIJc9tg2Yx6qSjHn8bvKyPbFs";
  protected final String apiClientSecret = "Ua25inHBHWWwExMb4Xymb9k7K9l8f2I2X4h";
  protected Client apiClient;


  @Before
  public void setUp() throws com.zara4.api.imageprocessing.Exception {
    Config.enterDevelopmentMode();
    this.apiClient = new Client(apiClientId, apiClientSecret);
  }

}
