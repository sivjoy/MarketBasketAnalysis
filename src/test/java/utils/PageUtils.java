package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Collections;

/**
 * Created by siv on 2/16/15.
 */
public class PageUtils {
    private WebDriver driver ;
    private long pageLoadTime;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public PageUtils(WebDriver driver){
        this.driver = driver;
        this.pageLoadTime = 15;
        js = (JavascriptExecutor)driver;
    }

    public void waitTillElementVisible(By by, Integer pageLoadTime){
        new WebDriverWait(driver,pageLoadTime).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public Boolean safeWaitTillElementVisible(By by, Integer pageLoadTime){
        try {
            new WebDriverWait(driver, pageLoadTime).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public void clickElement(By by){
        driver.findElement(by).click();
    }

    public void sendKeys(By by,String keys){
        driver.findElement(by).sendKeys(keys);
    }

    public void clearField(By by){
        driver.findElement(by).clear();
    }


    public void sleep(Integer waitTimeInMillis){
        try{
            Thread.sleep(waitTimeInMillis);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void sendKeysWithoutElement(Keys keys){
        Actions action = new Actions(driver);
        action.sendKeys(keys);

    }

    public void triggerOnChangeEvent(By by){
        WebElement ele = driver.findElement(by);
        this.sleep(1000);
        //js.executeScript("arguments[1].onchange();", "", ele);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
        //driver.findElement(By.tagName("body")).click();
    }


    /**
     * Click element using Java Script Executor command
     *
     * @param driver  the driver
     * @param by the element
     * @return true, if successful
     * @throws Exception
     */
    public void clickElementUsingJS(WebDriver driver,
                                              By by) {
        System.out.println("\n\nClicked using js....");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(by));
        this.sleep(2000);

    }


    /**
     * Provides file location to the native window for uploading.
     *
     * @param fileLocation
     */
    public void uploadFileFromNativeWindow(String fileLocation) {
        try {
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            this.sleep(1000);
        } catch (AWTException err1) {
            err1.printStackTrace();
            //return false;
        }
    }

    /**
     * Sets parameterized String data as clip board data.
     *
     * @param clipBoardText
     */
    public void setClipboardData(String clipBoardText) {
        StringSelection stringSelection = new StringSelection(clipBoardText);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }

}
