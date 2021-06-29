import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Work42 {
    private AndroidDriver driver;

    @Before
    public void setUo() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","emulator");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\nozar\\Desktop\\javaAuto\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    @Test public  void longTestSaveArticles()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Не найти элемент 'Search Wikipedia' для ввода",
                5

        );
        String search_line = "Appium";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Не найти требуемый элемент " + search_line,
                5

        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='"+search_line+"']") ,
                "Не найти элемент " + search_line,
                5
        );

        //тест будет падать всегда, ведь тайтл не успевает загрузиться
        assertElementIsPresent(
                By.xpath("org.wikipedia:id/view_page_title_text"),
                "Не найден элемент"


        );


    }
    private WebElement waitElementPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitElementPresent(By by, String error_message)
    {
        return waitElementPresent(by, error_message,4);
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitElementPresent(by,error_message, timeOutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitElementPresent(by,error_message, timeOutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private  boolean waitForElementNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitElementPresent(by, error_message, timeOutInSeconds);
        element.clear();
        return element;
    }


    public boolean assertElementHasText(By by, String error_message, String expect, long timeOutInSeconds){


        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(by, expect)
        );
    }

    protected  void swioeUp(int timeSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);
        action.press(x, start_y).waitAction(timeSwipe).moveTo(x, end_y).release().perform();


    }
    protected  void swioeUpQwick()
    {
        swioeUp(200);
    }
    protected void swipeToFindElement(By by, String error_message, int maxSwipe)
    {
        int alreadySwiped = 0;
        while (driver.findElements(by).size()==0){
            if(alreadySwiped > maxSwipe)
            {
                waitElementPresent(by, "Не найти элемент свайпами \n" + error_message, 0);
                return;
            }

            swioeUpQwick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitElementPresent(
                by,
                error_message,
                10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    private  int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        int i = elements.size();
        System.out.println(elements.size());
        return elements.size();
    }

    private  void assertElementNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0 )
        { String default_message = "Ни один элемент" + by.toString() + "не должен быть найден";
            throw new AssertionError(default_message + "" + error_message);
        }
    }

    private String waitForAlementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds){

        WebElement element = waitElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    private  void assertElementIsPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements < 1 )
        { String default_message = "Ни один элемент " + by.toString() + " не найден";
            throw new AssertionError(default_message + "");
        }

    }









}
