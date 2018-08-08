import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class NewUserTest {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createNewUser(){
        driver.get("http://localhost/litecart/en/");

        WebElement box = driver.findElement(By.id("box-account-login"));
        box.findElement(By.tagName("a")).click();

        driver.findElement(By.name("firstname")).sendKeys("Ab");
        driver.findElement(By.name("lastname")).sendKeys("Ab");
        driver.findElement(By.name("address1")).sendKeys("Ab");
        driver.findElement(By.name("city")).sendKeys("Ab");
        driver.findElement(By.name("postcode")).sendKeys("11111");

        /// for unique email
        int i = (int)((new Date().getTime())%1000000);
       // System.out.println(i + "  repeat possible in min  " + 1000000/1000/60);
        String email = "ab" + i + "@ab";
        driver.findElement(By.name("email")).sendKeys(email);

        driver.findElement(By.name("phone")).sendKeys("+1111111");

        WebElement dropdown  = driver.findElement(By.name("country_code"));//the select
        Select countries = new Select(dropdown);
        //countries.deselectAll(); is not mmultiple choice so it doesn't work
        countries.selectByValue("US");

        dropdown  = driver.findElement(By.cssSelector("select[name = zone_code]"));//the select
        Select state = new Select(dropdown);
        //countries.deselectAll(); is not mmultiple choice so it doesn't work
        state.selectByValue("PA");

        String password = "admin";
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);

        driver.findElement(By.cssSelector("button[name = create_account]")).click();

        ///logout
        wait.until(titleIs("Online Store | My Store"));
        WebElement account = driver.findElement(By.id("box-account"));
        account.findElement(By.linkText("Logout")).click();


        //new login + logout
        loginMainStore(email, password);
        driver.findElement(By.id("box-account")).findElement(By.linkText("Logout")).click();

    }

    public void loginMainStore(String email, String password){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        String title = driver.getTitle();
        wait.until(titleIs("Online Store | My Store"));
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
