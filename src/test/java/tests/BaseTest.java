package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;


/**
 * Created by siv on 2/16/15.
 */
public class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void startDriver(){
         clearAllResultsOfSpark();
         driver = new FirefoxDriver();
         driver.manage().window().maximize();
         driver.get("http://localhost:9000/#/home");
    }

    public void clearAllResultsOfSpark(){
        System.out.println("Removing previous test data from the system...");

        try {
            FileUtils.cleanDirectory(new File("/home/siv/casca/casca-data/transaction-data"));
            FileUtils.cleanDirectory(new File("/home/siv/casca/casca-data/lucene/FP_RULES"));
            FileUtils.cleanDirectory(new File("/home/siv/casca/casca-data/lucene/PRODUCTS"));
            FileUtils.cleanDirectory(new File("/home/siv/casca/casca-data/lucene/TRANSACTION_DATA"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown(){
       // driver.quit();
    }


}
