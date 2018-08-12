import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class WindowsTest {
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
    public void checkCountry() {
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));
        WebElement currentSection = elementsTable.get(2);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();///goes to countries
        //on the countries page
        WebElement country = driver.findElement(By.id("content")).findElement(By.name("countries_form"));
        List<WebElement> listCountry = country.findElements(By.className("row"));
        List<WebElement> cellsCountry = listCountry.get(10).findElements(By.cssSelector("td"));
        cellsCountry.get(4).findElement(By.tagName("a")).click(); ///goes to a country
        //on the country page
        WebElement content = driver.findElement(By.id("content"));
        Set<String> oldOpenWins = driver.getWindowHandles();

        int[] iNums = {2, 3, 6, 8, 9, 10};
        for (int i : iNums) {
            String selector = "tr:nth-child(" + i + ")";

            content.findElement(By.cssSelector(selector)).findElement(By.tagName("td")).findElement(By.tagName("a")).click();
            openCloseWin(oldOpenWins);
        }

        content.findElement(By.cssSelector("tr:nth-child(7)")).findElement(By.cssSelector("a:nth-child(3)")).click();
        openCloseWin(oldOpenWins);
    }

    public void openCloseWin(final Set oldOpenWins){
        String oldWin = driver.getWindowHandle();
         Set<String> openWins = driver.getWindowHandles();
        openWins.removeAll(oldOpenWins);
        String newWin = openWins.iterator().next();
        driver.switchTo().window(newWin);
        driver.close();
        driver.switchTo().window(oldWin);
/*
        String newWindowHandle = wait.until(new ExpectedCondition<String>() {
                                public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldOpenWins);
                               return newWindowsSet.size() > 0 ?
                                       newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(newWindowHandle);
        driver.close();
        driver.switchTo().window(oldWin);
*/
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
