package pages.administration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.RecomendationsExplorer.RecommendationsPage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siv on 2/16/15.
 */
public class ResultRulesPage extends BasePage {

    private WebDriver driver;
    protected By resultsTable = By.xpath("(.//ul[@class='list-group'])[2]/li");
    protected By goToRecommendationExplorer = By.xpath("(.//a[contains(@href,'#/recommendation-explorer/')])[2]");
    protected List<String> allResultsValues;
    protected String baseResultsXpath = "(.//ul[@class='list-group'])[2]/li";

    public ResultRulesPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        pageUtils.waitTillElementVisible(goToRecommendationExplorer, 15);
    }

    public void waitTillResultsAreLoaded() {
        System.out.println("Waiting till the element be loaded ...");
        long startTime = System.currentTimeMillis();

        while ((startTime - System.currentTimeMillis()) < 30000) {
            if (!pageUtils.safeWaitTillElementVisible(resultsTable, 1)) {
                pageUtils.sleep(1000);
                driver.navigate().refresh();
            } else {
                System.out.println("The results are loaded successfully..");
                pageUtils.sleep(1000);
                break;
            }
        }
    }

    public void printResults() {
        String associatedItem;
        String itemsBasket;
        String secondColumnXpath;

        allResultsValues = new ArrayList<String>();
        for (int i = 1; true; i++) {
            associatedItem="";
            itemsBasket="";
            /*System.out.println("\t\t\n\n i is "+i);*/
            String firstColumnXpath = "(" + baseResultsXpath + "/div)[" + i + "]/div[1]";
            /*System.out.println("The xpath is :" + firstColumnXpath);*/

            try {
                associatedItem = driver.findElement(By.xpath(firstColumnXpath)).getText();
                /*System.out.println("The associated item is :"+associatedItem);*/

            } catch (Exception e) {
                break;
            }

            for (int j = 1; true; j++) {
                {
                    /*System.out.println("\t\t j is "+j);*/
                    secondColumnXpath = "(" + baseResultsXpath + "/div)[" + i + "]/div[2]/ul/li[" + j + "]";
                    /*System.out.println("\tThe inner xpath is :" + secondColumnXpath);*/

                    try {
                        itemsBasket+=driver.findElement(By.xpath(secondColumnXpath)).getText() + " ";
                        /*System.out.println("The itemsBasket is :"+itemsBasket);*/
                    } catch (Exception e) {
                        break;
                    }
                }
            }

            allResultsValues.add(i - 1,  associatedItem+ "   <=   "+itemsBasket);
            /*System.out.println("The rules are till now : "+allResultsValues);*/
        }
        System.out.println("\n\nThe rules are ...\n\n");

        for (int k = 0; k < allResultsValues.size(); k++) {
            System.out.println("\t\t" + allResultsValues.get(k));
        }
    }

    public List<String> getAllProductAssociationRules(){
        System.out.println("The association rules are : "+this.allResultsValues);
        return this.allResultsValues;
    }



    public RecommendationsPage clickOnRecommendationExplorer() {
        pageUtils.clickElement(goToRecommendationExplorer);
        return new RecommendationsPage(driver);
    }




}





