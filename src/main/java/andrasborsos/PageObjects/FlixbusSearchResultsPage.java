package andrasborsos.PageObjects;

import andrasborsos.resources.ChooseInitializeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class FlixbusSearchResultsPage extends ChooseInitializeDriver {

    public FlixbusSearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    By departureHeaderLocator =new By.ByCssSelector(".TripTitle__titleWrapper___2wsf0>div");
    By resultsLocator= new By.ByCssSelector(".ResultsList__resultsList___3yIIM>div");
    By departureTimeLocator=new By.ByCssSelector(".LocationsHorizontal__departureTime___1_wvV");
    By priceLocator=new By.ByCssSelector("[data-e2e='search-result-prices'][class*='rice']");
    By reservationErrorMessageLocator=new By.ByCssSelector(".SearchResult__bookingMessage___10x2O");
    By reserveBTNLocator=new By.ByCssSelector("[data-e2e='button-reserve-trip']");
    By deleteReservationBTNLocator=new By.ByCssSelector(".hcr-btn-2-5-0.hcr-btn--tertiary-2-5-0");
    By returnWayBackTabLocator=new By.ByCssSelector("[data-e2e='search-results-selected-departure']");
    By noTicketsMessageLocator=new By.ByCssSelector(".flix-h2.Message__messageTitle___2SiYU");

    public ArrayList<ArrayList<WebElement>> getResults(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,8);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(departureTimeLocator));

        ArrayList<ArrayList<WebElement>> resultsOrganized=new ArrayList<>();

        ArrayList<WebElement> resultsRowsOnPage=(ArrayList<WebElement>)driver.findElements(resultsLocator);
        for(int i=0;i< resultsRowsOnPage.size();i++){
            ArrayList<WebElement> resultContainer=new ArrayList<>();
            addResultSubtypeToContainer(i,departureTimeLocator, driver,resultsRowsOnPage,resultContainer);
            addResultSubtypeToContainer(i,priceLocator, driver,resultsRowsOnPage,resultContainer);
            addResultSubtypeToContainer(i,reservationErrorMessageLocator, driver,resultsRowsOnPage,resultContainer);
            resultsOrganized.add(resultContainer);
        }
        return resultsOrganized;
    }

    public WebElement getreserveBTN(){
        return driver.findElement(reserveBTNLocator);
    }

    public void waitToLeaveResultsPage(){
        WebDriverWait webDriverWait=new WebDriverWait(driver,8);
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementLocated(departureHeaderLocator,"Odaút")));
    }

    public WebElement getdeleteReservationBTN(){
        return driver.findElement(deleteReservationBTNLocator);
    }

    public WebElement getNoTicketsMessage(){
        if(driver.findElements(noTicketsMessageLocator).size()>0) {
            return driver.findElement(noTicketsMessageLocator);
        }
        else return null;
    }

    private ArrayList<WebElement> addResultSubtypeToContainer(int i, By resultSubtypeLocator,WebDriver driver, ArrayList<WebElement> resultsRowsOnPage, ArrayList<WebElement> resultContainer){
        if(resultsRowsOnPage.get(i).findElements(resultSubtypeLocator).size()>0){
            resultContainer.add(resultsRowsOnPage.get(i).findElement(resultSubtypeLocator));
        }
        else{
            resultContainer.add(null);
        }
        return resultContainer;
    }

}
