import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.testng.annotations.*;

public class Mytest
{
    WebDriver driver;

//        Creating a new FirefoxDriver
    @BeforeSuite
        public void createDriver()
    {
        driver = new FirefoxDriver();
    }


    @Test(priority = 1)
    public void accessAmazon()
    {
//      accessing amazon website
        driver.get("https://www.amazon.com/");
    }

    @Test(priority = 2)
    public void searchAndSubmitStanley()
    {
//      searches for the search box and enters text stanley cup and clicks submit button
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        enterText(driver, 10, "Stanley cup", searchBox);
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        clickButton(driver, 10, searchButton);
    }

    @Test(priority = 3)
    public void filterProducts()
    {
//      filters the products with only products having ratings 3 stas and more and clicks on the first product
        WebElement threeStar = driver.findElement(By.xpath("//i[@class='a-icon a-icon-star-medium a-star-medium-3']"));
        clickButton(driver, 10, threeStar);
        WebElement productOne = driver.findElement(By.xpath("//img[@alt='Stanley Quencher H2.0 FlowState Stainless Steel Vacuum Insulated Tumbler with Lid and Straw for Water, Iced Tea or Coffee']"));
        clickButton(driver, 10, productOne);
    }

    @Test(priority = 4)
    public void addProduct()
    {
//        adds the product to cart and enters cart page
        WebElement addToCart = driver.findElement(By.id("add-to-cart-button"));
        clickButton(driver, 10, addToCart);
        WebElement goToCart = driver.findElement(By.xpath("//a[@href='/cart?ref_=sw_gtc']"));
        clickButton(driver, 10, goToCart);
    }

    @Test(priority = 5)
    public void accessYoutube()
    {

//        suddenly goes to youtube and searches for a cinnamon latte receipe
        driver.navigate().to("https://www.youtube.com/");
        WebElement youtubeSearch = driver.findElement(By.name("search_query"));
        enterText(driver, 10, "cinnamon latte receipe", youtubeSearch);
        WebElement youtubeSearchButton = driver.findElement(By.xpath("//button[@id='search-icon-legacy']"));
        clickButton(driver, 10, youtubeSearchButton);
    }

    @Test(priority = 6)
    public void gobackAmazon()
    {
//      goes back to amazon cart and deletes the cart and thus ending the testing phase.
        driver.navigate().back();
        WebElement deleteIcon =  driver.findElement(By.className("a-button a-button-base a-button-small"));
        clickButton(driver, 10, deleteIcon);
    }


    @AfterSuite
    public void quitDriver()
    {
        driver.quit();
    }



    public static void enterText(WebDriver driver, int timeout, String text, WebElement element)
    {
        Duration timeoutSec = Duration.ofSeconds(timeout);
        new WebDriverWait(driver, timeoutSec).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }

    public static void clickButton(WebDriver driver, int timeout, WebElement element)
    {
        Duration timeoutSec = Duration.ofSeconds(timeout);
        new WebDriverWait(driver, timeoutSec).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
