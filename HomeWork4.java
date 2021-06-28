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

public class HomeWork4 {
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

        waitElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Не найти заголовок статьи " + search_line,
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Не найти элемент открытия опций в статье" + search_line,
                5

        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "не найти элемен добавления статьи в закладки " + search_line,
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "не найти кнопку got it при добалвении статьи" + search_line,
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "не найти поле ввода создания закладки статьи" + search_line,
                5

        );
        String list_articles = "the most unic name of article";
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                list_articles,
                "не ввести текст в поле сохраненич списка статей для" + search_line,
                5

        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "не нажать ок при сохранении списка статей для " + search_line,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "не нажать крестик закрытия статьи " + search_line,
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Не найти элемент 'Search Wikipedia' для ввода",
                5

        );
        String search_line2 = "LOL";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line2,
                "Не найти требуемый элемент" + search_line2,
                5

        );
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text='"+search_line2+"']") ,
                "Не найти статью " + search_line2,
                5
        );

        waitElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Не найти заголовок статьи " + search_line2,
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Не найти элемент открытия опций " + search_line2,
                5

        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "добавления в статьи " + search_line2,
                5
        );


        waitForElementAndClick(
                By.xpath("//*[@text='"+list_articles+"']"),
                "не найти сохранененные стать для сохранения "+ search_line2 ,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "не нажать крестик закрытия статьи " + search_line2,
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "не найти элемент закладки на главной",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+list_articles+"']"),
                "не найти сохраненные статьи",
                10
        );

        swipeElementToLeft(
                By.xpath("//*[@text='"+search_line+"']"),
                "не удалось выполнить свайп для удаления элемента " + search_line

        );

        assertElementIsPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "'Элемент не присутвует на странице' " + search_line2
        );

        waitForElementAndClick(
                By.xpath("//*[@text='LOL']"),
                "не найти элемент статья" + search_line2,
                5
        );

        String title_after = waitForAlementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "не найти тайтл статьи" + search_line2,
                15
        );

        Assert.assertEquals(
                "Название статьи не совпадает с " + search_line2,
                search_line2,
                title_after

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
        { String default_message = "Ни один сохраненный элемент" + by.toString() + "не найден";
            throw new AssertionError(default_message + "" + error_message);
        }

    }






}
