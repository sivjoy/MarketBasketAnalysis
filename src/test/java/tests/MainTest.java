package tests;

import javafx.scene.layout.Priority;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.RecomendationsExplorer.RecommendationsPage;
import pages.administration.AdministrationBasePage;
import pages.administration.ProductsPage;
import pages.administration.ResultRulesPage;
import pages.administration.TransactionDataPage;
import utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siv on 2/16/15.
 */
public class MainTest extends BaseTest{
    private BasePage basePage;
    private AdministrationBasePage adminBasePage;
    private ProductsPage productsPage;
    private TransactionDataPage transactionDataPage;
    private ResultRulesPage resultRulesPage;
    private RecommendationsPage recommendationsPage;

    private String productsFileName="p3.csv";
    private List<String> allProductNames;
    private String productsFileLocation=System.getProperty("user.dir")+"/src/test/resources/DataFiles/"+productsFileName;
    private String txFileName="t3.csv";
    private String txFileLocation = System.getProperty("user.dir") + "/src/test/resources/DataFiles/" + txFileName;
    private List<String> allProductAssociationRules;

    private FileUtils fUtils = new FileUtils();
    @BeforeClass
    public void setUp(){

        System.out.println("\n\n ================Starting the test:===================");
        allProductNames = fUtils.getAllProductValues(productsFileLocation);
        //allProductDetails =fUtils.getAllProductIDsAndValues(productsFileLocation);
    }

    @Test(priority=1)
    public void validateDataTest(){
        basePage = new BasePage(driver);
        adminBasePage= basePage.clickOnAdminstrationLink();
        productsPage=adminBasePage.clickOnProductsLink();
        productsPage.sendProductsFileLocation(productsFileLocation);
        productsPage.clickUploadButton();
        transactionDataPage=productsPage.clickOnTransactionDataLink();
        transactionDataPage.clickAddTransactionData();
        transactionDataPage.selectSupport("2");
        transactionDataPage.selectMinConfidence("50");
        transactionDataPage.sendTransactionsFileLocation(txFileLocation);
        transactionDataPage.clickStartButton();
        resultRulesPage= transactionDataPage.clickViewResults();
        resultRulesPage.waitTillResultsAreLoaded();
        resultRulesPage.printResults();
        this.allProductAssociationRules = resultRulesPage.getAllProductAssociationRules();
        this.recommendationsPage=resultRulesPage.clickOnRecommendationExplorer();

    }

    @Test(priority=2)
    public void checkIfAddedProductsVisibleInRecommendationsPage(){
        List<String> productsBasket = allProductNames.subList(0,1);
        Assert.assertTrue(this.recommendationsPage.searchProducts(allProductNames),"Unable to see all products in search Products section");
        System.out.println("The products to be added are :"+productsBasket);
        Assert.assertTrue(this.recommendationsPage.AddProducts(productsBasket), "Unable to add selected Products.");
        List<String> associatedItems  = getAssociatedRule(this.allProductAssociationRules,productsBasket);
        recommendationsPage.checkIfAssociatedItemPresent(associatedItems);

    }

    private List<String> getAssociatedRule(List<String> rules,List<String> productsSubList){
        String subListProductsInString="";
        List<String> associatedProducts=null;
        for(String item:productsSubList) {
            subListProductsInString+=item+" ";
        }

        for(String rule:rules){
            for(String product:productsSubList){
                System.out.println("Rule is : " + rule);
                System.out.println("product is : "+product);
                if(rule.contains(product))
                    associatedProducts.add((rule.split("<="))[0].trim());
                else break;
            }
        }
        System.out.println("The associated products are :"+associatedProducts);
        return associatedProducts;
    }

    @AfterClass
    public void tearUp(){
        System.out.println("\n\n ================Stopping the test:===================");
    }
}
