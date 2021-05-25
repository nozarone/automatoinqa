import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetClassNumber()
    {
        Assert.assertTrue("Возвращаемое число меньше!",this.getClassNumber() > 45);

    }


}
