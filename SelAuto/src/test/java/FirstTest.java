import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest    {
    private WebDriver driver;
        private WebDriverWait wait;


        @BeforeTest
        public void start(){
            System.setProperty("webdriver.chrome.driver", "C:\\Study\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, 10);

        }

        @Test
        public void myFirstTest(){
            driver.get("http://www.google.com");
            driver.findElement(By.name("q")).sendKeys("webdriver");
            driver.findElement(By.name("btnK")).sendKeys(Keys.ENTER);
           // driver.findElement(By.name("btnK")).click();
            wait.until(titleIs("webdriver - Поиск в Google"));
            System.out.println("First test!");
        }

        @Test
        public void mySecondTest(){
            System.out.println("Second test!");
        }

        @AfterTest
        public void stop(){
            driver.quit();
            driver = null;
        }

}
