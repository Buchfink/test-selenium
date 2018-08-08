import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class NewProductTest {
    WebDriver driver;
    WebDriverWait wait;


    @BeforeClass
    public void start(){
        try{
            System.setProperty("webdriver.chrome.driver", "C:\\Study\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 8);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            loginAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(enabled = false)
    public void loginMainStore(){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("ab@ab");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        String title = driver.getTitle();
        System.out.println(title);
        wait.until(titleIs("Online Store | My Store"));
    }

    public void loginAdmin(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        // driver.findElement(By.name("login")).click();
        String title = driver.getTitle();
        System.out.println(title);
        wait.until(titleIs("My Store"));
    }
    @Test
    public void addProduct(){
        WebElement linkToCatalog = driver.findElement(By.cssSelector("#box-apps-menu li:nth-child(2)"));
        linkToCatalog.click();
        WebElement addButton = driver.findElement(By.cssSelector("#content a:nth-child(2)"));
        addButton.click();
        List<WebElement> generalFields= driver.findElements(By.cssSelector(("div#tab-general > table > tbody > tr")));
        System.out.println(generalFields.size());


        WebElement statusLabel = generalFields.get(0).findElement(By.tagName("td")).findElement(By.tagName("label"));
        WebElement status = statusLabel.findElement(By.tagName("input"));
        System.out.println(status.getAttribute("value"));
        status.click();

        WebElement nameLabel = generalFields.get(1).findElement(By.tagName("td")).findElement(By.tagName("input"));
        nameLabel.sendKeys("Star");

        generalFields.get(2).findElement(By.tagName("td")).findElement(By.tagName("input")).sendKeys("111");

        WebElement genderLabel = generalFields.get(6).findElement(By.cssSelector("td"));
        List<WebElement> genderOptions = genderLabel.findElements(By.cssSelector("div > table > tbody > tr"));
        genderOptions.get(3).findElement(By.tagName("input")).click();

        WebElement quantityLabel = generalFields.get(7).findElement(By.cssSelector( "td > table > tbody > tr > td:nth-child(1) > input"));
        quantityLabel.sendKeys("10");

        WebElement soldOutLabel = generalFields.get(7).findElement(By.cssSelector( "td > table > tbody > tr > td:nth-child(4) > select"));
        Select sold = new Select(soldOutLabel);
        sold.selectByIndex(0);

        WebElement dateFromLabel = generalFields.get(9).findElement(By.cssSelector("td > input"));
        dateFromLabel.sendKeys("01012018");
        dateFromLabel = generalFields.get(10).findElement(By.cssSelector("td > input"));
        dateFromLabel.sendKeys("01012019");

        WebElement imageLabel = generalFields.get(8).findElement(By.cssSelector("td > table > tbody > tr:nth-child(1) > td > input"));
        imageLabel.sendKeys("https://github.com/Buchfink/test-selenium/blob/master/Star.jpg");
        //*[@id="tab-general"]/table/tbody/tr[9]/td/table/tbody/tr[1]/td/input
       // #tab-general > table > tbody > tr:nth-child(9) > td > table > tbody > tr:nth-child(1) > td > input[type="file"]

        ///next tab
        driver.findElement(By.cssSelector("#content > form > div > ul > li:nth-child(2) > a")).click();

        WebElement manId = driver.findElement(By.name("manufacturer_id"));
        Select man = new Select(manId);
        man.selectByIndex(1);

        driver.findElement(By.name("keywords")).sendKeys("Star");

        driver.findElement(By.name("short_description[en]")).sendKeys("Small Star");

        driver.findElement(By.name("description[en]")).sendKeys("Not very small Star");

        driver.findElement(By.name("head_title[en]")).sendKeys("Famous Star");

        driver.findElement(By.name("meta_description[en]")).sendKeys("meta Star");

        ///price tab
        driver.findElement(By.cssSelector("#content > form > div > ul > li:nth-child(4) > a")).click();

        WebElement price = driver.findElement(By.name("purchase_price"));
        price.clear();
        price.sendKeys("20");

        price = driver.findElement(By.name("gross_prices[USD]"));
        price.clear();
        price.sendKeys("20");
/*
        price = driver.findElement(By.name("gross_prices[EUR]"));
        price.clear();
        price.sendKeys("15");
*/
        driver.findElement(By.name("save")).click();

    }


    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
