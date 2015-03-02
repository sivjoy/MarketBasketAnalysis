package pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by siv on 2/16/15.
 */
public class TransactionDataPage extends AdministrationBasePage {

    private WebDriver driver;

    protected By transactionDataSectionTitle = By.xpath(".//h3[text()='Transaction Data']");
    protected By browseFile = By.xpath(".//input[@class='form-control']");
    protected By startButton = By.xpath(".//button[text()='Start']");
    protected By viewResults = By.xpath("(.//a[@class='btn btn-default'])[1]");
    protected By addTransactionDataButton = By.xpath(".//button[text()='Add Transaction Data']");
    protected By support = By.xpath(".//input[@ng-model='formData.minSupport']");
    protected By minConfidence = By.xpath(".//input[@ng-model='formData.minConfidence']");

    public TransactionDataPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        pageUtils.waitTillElementVisible(transactionDataSectionTitle,15);
    }

    public void clickAddTransactionData(){
        pageUtils.clickElement(addTransactionDataButton);
        pageUtils.waitTillElementVisible(browseFile,15);
    }
    public void sendTransactionsFileLocation(String loc){
        pageUtils.clickElement(browseFile);
        pageUtils.uploadFileFromNativeWindow(loc);

    }

    public void clickStartButton() {
        pageUtils.sleep(1000);
        pageUtils.clickElement(startButton);
        pageUtils.waitTillElementVisible(viewResults,15);
    }

    public void selectSupport(String supportVal){
        pageUtils.clearField(support);
        pageUtils.sendKeys(support,supportVal);
    }

    public void selectMinConfidence(String minConfidenceVal){
        pageUtils.clearField(minConfidence);
        pageUtils.sendKeys(minConfidence,minConfidenceVal);
    }

    public ResultRulesPage clickViewResults(){
        pageUtils.clickElement(viewResults);
        return new ResultRulesPage(driver);
    }

}
