package pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

/**
 * Created by siv on 2/16/15.
 */
public class AdministrationBasePage extends BasePage{
    private WebDriver driver;
    protected By productsLink = By.xpath(".//a[@href='#/admin/products']");
    protected By transactionDataLink = By.xpath(".//a[@href='#/admin/transactionData']");

    public AdministrationBasePage(WebDriver driver){
        super(driver);
        this.driver=driver;
        pageUtils.waitTillElementVisible(productsLink,15);
    }

    public TransactionDataPage clickOnTransactionDataLink(){
        pageUtils.clickElement(transactionDataLink);
        return new TransactionDataPage(driver);
    }

    public ProductsPage clickOnProductsLink(){
        pageUtils.clickElement(productsLink);
        return new ProductsPage(driver);
    }

}
