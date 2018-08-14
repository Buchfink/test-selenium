import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MenuTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void start(){
        try{
            System.setProperty("webdriver.chrome.driver", "C:\\Study\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 8);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            loginAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void loginAdmin(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        // driver.findElement(By.name("login")).click();
        String title = driver.getTitle();
        System.out.println(title);
        wait.until(titleIs("My Store"));
    }
    void goToOrigin(){
        WebElement block = driver.findElement(By.id("sidebar"));
        block.findElement(By.tagName("a")).click();
    }

    @Test(priority = 1)
    public void clickFirst(){
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
       /// WebElement table = block.findElement(By.className("list-vertical"));
        ///should be the same
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));
        int tableSize = elementsTable.size();
        System.out.println(elementsTable.size());

        for (int i=0; i< tableSize; i++){
            goToOrigin();
            block = driver.findElement(By.id("box-apps-menu-wrapper"));
            tableTag = block.findElement(By.tagName("ul"));
            elementsTable = tableTag.findElements(By.tagName("li"));
            WebElement currentSection = elementsTable.get(i);
            WebElement link = currentSection.findElement(By.tagName("a"));
            link.click();
            ////on the reloaded page
            block = driver.findElement(By.id("box-apps-menu-wrapper"));
            tableTag = block.findElement(By.tagName("ul"));
            String parentSelector = "li:nth-child(" + (i+1) + ")";
            WebElement parent = tableTag.findElement(By.cssSelector(parentSelector));
            List<WebElement> subMenu = parent.findElements(By.cssSelector("ul > li"));
            int subSize = subMenu.size();
            System.out.println(i + " has sub menu points : " + subSize);
            if (subSize > 0) {
                for (int j=0; j<subSize; j++){
                    block = driver.findElement(By.id("box-apps-menu-wrapper"));
                    tableTag = block.findElement(By.tagName("ul"));
                    parentSelector = "li:nth-child(" + (i+1) + ")";
                    parent = tableTag.findElement(By.cssSelector(parentSelector));
                    WebElement childTag = parent.findElement(By.tagName("ul"));
                    String childSelector ="li:nth-child(" + (j+1) + ")";
                    WebElement child = childTag.findElement(By.cssSelector(childSelector));
                    WebElement subLink = child.findElement(By.tagName("a"));
                    subLink.click();
                    ///check header
                    WebElement subBlock = driver.findElement(By.id("content"));
                    subBlock.findElement(By.tagName("h1"));
                }
            }
            else {
                ///check header
                WebElement subBlock = driver.findElement(By.id("content"));
                subBlock.findElement(By.tagName("h1"));
            }
            goToOrigin();

        }
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
