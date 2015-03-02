package pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.awt.*;

/**
 * Created by siv on 2/16/15.
 */
public class ProductsPage extends AdministrationBasePage{

    //public WebDriver driver;

    protected By productsSectionTitle = By.xpath(".//h3[text()='Products']");
    protected By browseFile = By.xpath(".//input[@placeHolder='Select a CSV file to upload']");
    protected By uploadButton = By.xpath(".//button[text()='Upload']");
    protected By productsListTable = By.xpath(".//ul[@class='product-list list-group']");
    protected By tempLooseElementFocus=By.xpath(".//span[text()='No Products.']");

    public ProductsPage(WebDriver driver) {
        super(driver);
        pageUtils.waitTillElementVisible(productsSectionTitle,15);
    }

    public void sendProductsFileLocation(String loc){
        pageUtils.clickElement(browseFile);
        pageUtils.uploadFileFromNativeWindow(loc);

    }

    public void clickUploadButton() {
        pageUtils.sleep(1000);
        pageUtils.clickElement(uploadButton);
        pageUtils.waitTillElementVisible(productsListTable,30);
    }


}
