import com.zara4.api.communication.http.Method;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class MethodTest extends TestCase {

  @Test
  public void testToString() {
    assertEquals(Method.GET.toString(), "GET");
    assertEquals(Method.POST.toString(), "POST");
    assertEquals(Method.PUT.toString(), "PUT");
    assertEquals(Method.DELETE.toString(), "DELETE");
  }

}
