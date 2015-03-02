package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.administration.AdministrationBasePage;
import utils.PageUtils;

/**
 * Created by siv on 2/16/15.
 */
public class BasePage {

    protected By cascaHome = By.xpath(".//a[text()='CASCA']");
    protected By adminstrationLink = By.xpath(".//a[text()='Administration']");
    protected By recommendationExplorerLink = By.xpath(".//a[text()='Recommendation Explorer']");

    protected PageUtils pageUtils;
    WebDriver driver ;

    public BasePage(WebDriver driver){
        this.driver = driver;
        pageUtils = new PageUtils(driver);
        pageUtils.waitTillElementVisible(cascaHome,15);
    }

    public AdministrationBasePage clickOnAdminstrationLink(){
        pageUtils.clickElement(adminstrationLink);
        return new AdministrationBasePage(driver);
    }


}
