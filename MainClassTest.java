import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass

{
    String x = getClassString();
    String z = "hello";
    @Test
    public void testGetClassString()

    {
        Assert.assertTrue("Строки не совпадают!",x.toLowerCase().contains(z.toLowerCase()));

    }


}
