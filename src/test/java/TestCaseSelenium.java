import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


import org.openqa.selenium.chrome.ChromeDriver;
import pages.LatestNewsPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.LatestNewsSection;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


@TestInstance(value = Lifecycle.PER_CLASS)
public class TestCaseSelenium {
    private WebDriver driver;
    static Logger log = Logger.getLogger(String.valueOf(TestCaseSelenium.class));

    @BeforeEach
    public void setup() throws URISyntaxException {
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }



    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1() throws InterruptedException {
        LatestNewsSection latestNewsPageSection = new LatestNewsSection(driver);
        LatestNewsPage latestNewsPage = new LatestNewsPage(driver);

        driver.navigate().to("https://www.nordea.fi/en/personal/get-help/");
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.wscrOk"))).click();

        log.info("Print out header of the card");
        System.out.println(String.format("Section header text: %s", latestNewsPageSection.getSectionHeader()));

        log.info("Printout  count of card links");
        System.out.println(String.format("Number of card links: %s", latestNewsPageSection.getCountOfSectionLinks()));

        log.info("Printout out link nr 2 href");
        System.out.println(String.format("Link with position %s: %s", 1, latestNewsPageSection.getLinkText(1)));

        log.info("Come out with 2 assertions for elements in the card");
        assertTrue(latestNewsPageSection.getLinkElement(1).isDisplayed());
        assertTrue(latestNewsPageSection.getLinkText(1).equals("Open an ASP account via digital channel "));

        List<WebElement> linkElements = latestNewsPageSection.getSectionLinksList();
        Optional<WebElement> result = linkElements.stream()
                .filter(s -> s.getText().contains("See all current news"))
                .findAny();
        assertTrue(result.get().isDisplayed());

        log.info("Displaying all links from section");
        linkElements.stream().forEach(s -> System.out.println(s.getText()));


        log.info("Click on link nr 2");
        latestNewsPageSection.clickOnLink(2);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").toString().equals("complete");

        log.info("Add assertion that you were redirected correctly");
        assertTrue(latestNewsPage.getPageHeader().isDisplayed());

    }

    private String getChromeDriverPath() {
        return Paths.get("src", "main", "resources", "chromedriver.exe").toString();
    }
}
