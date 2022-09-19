package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LatestNewsPage {
    WebDriver driver;


    @FindBy(xpath = "//h1[contains(text(),'Latest news')]")
    WebElement latestNewsHeader;


    public LatestNewsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public WebElement getPageHeader() {
        return latestNewsHeader;
    }


}
