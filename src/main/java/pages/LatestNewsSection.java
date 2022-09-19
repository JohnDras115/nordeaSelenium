package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LatestNewsSection {
	WebDriver driver;


	@FindBy(xpath="//section[./h2[text()='Latest news']]")
	WebElement latestNews;

	@FindBy(xpath="//section[./h2[text()='Latest news']]//a")
	List<WebElement> latestNewsLinks;



	public LatestNewsSection(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public String getSectionHeader(){
		return latestNews.findElement(By.tagName("h2")).getText();
	}

	public List<WebElement> getSectionLinksList(){
		return latestNews.findElements(By.tagName("a"));
	}
	public int getCountOfSectionLinks(){
		return latestNews.findElements(By.tagName("a")).size();
	}


	public String getLinkText(int index){
		return latestNewsLinks.get(index).getText();
	}

	public WebElement getLinkElement(int index){
		return latestNewsLinks.get(index);
	}


	public void clickOnLink(int index){
		 latestNewsLinks.get(index).click();
	}





	//List<WebElement> linkElements = latestNewsPageObj.getSectionLinksList();
	//linkElements.get(2).getText()






	//ublic void setQuery(String q) {
	//	q = q == null ? "" : q;
	//	queryInputText.sendKeys(q);
//	}
	
	//public void search() {
	//	queryInputText.sendKeys(Keys.ENTER);
	//}
	
	//public List<WebElement> getResults() {
	//	return searchResultsContainer.findElements(By.tagName("a"));
	//}
}
