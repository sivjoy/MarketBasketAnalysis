package pages.RecomendationsExplorer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.Collections;
import java.util.List;

/**
 * Created by siv on 2/27/15.
 */
public class RecommendationsPage extends BasePage{

    private By searchProducts = By.xpath(".//input[@placeholder='Search Products']");
    private By allActualAssociatedProductNames= By.xpath("//div[@class='product']/b");
    WebDriver driver;
    public RecommendationsPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
        pageUtils.waitTillElementVisible(searchProducts,15);
    }

    public Boolean searchProducts(List<String> prods){
        Boolean allProductsAvailable=true;
        for(String product:prods){
            try {
                pageUtils.clearField(searchProducts);
                pageUtils.sendKeys(searchProducts,product);
                pageUtils.waitTillElementVisible(By.xpath(".//span[text()='" + product.toLowerCase() + "']"), 3);
                pageUtils.sleep(1000);
            }catch(Exception e){
                allProductsAvailable=false;
                e.printStackTrace();
            }
        }
        return allProductsAvailable;
    }

    public Boolean AddProducts(List<String> prods){
        Boolean allProductsAdded=true;
        for(String product:prods){
            try {
                pageUtils.clearField(searchProducts);
                pageUtils.sendKeys(searchProducts,product);
                pageUtils.waitTillElementVisible(By.xpath(".//span[text()='" + product.toLowerCase() + "']/preceding-sibling::input"),5);
                pageUtils.clickElement(By.xpath(".//span[text()='" + product.toLowerCase() + "']/preceding-sibling::input"));
                pageUtils.waitTillElementVisible(By.xpath(".//span[text()='" + product.toLowerCase() + "']/following-sibling::button"),5);
                pageUtils.sleep(1000);
            }catch(Exception e){
                allProductsAdded=false;
                e.printStackTrace();
            }
        }
        return allProductsAdded;
    }

    public Boolean checkIfAssociatedItemPresent(List<String> expectedAssociatedItems){
        List<String> actualAssocicatedItems = null;
        List<WebElement> allActualProducts = driver.findElements(allActualAssociatedProductNames);
        for(WebElement ele:allActualProducts){
            actualAssocicatedItems.add(ele.getText());
        }
        Collections.sort(expectedAssociatedItems);
        Collections.sort(actualAssocicatedItems);
        System.out.println("expectedAssociatedItems :"+expectedAssociatedItems);
        System.out.println("actualAssocicatedItems :"+actualAssocicatedItems);
        if( expectedAssociatedItems.equals(actualAssocicatedItems))
            return true;
        else return false;
    }

}
