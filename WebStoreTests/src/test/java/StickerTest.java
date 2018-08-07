import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class StickerTest {
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
    public void checkStickers(){
        driver.get("http://localhost/litecart/en/");

        ArrayList<WebElement> boxes = new ArrayList<WebElement>();
        boxes.add(driver.findElement(By.id("box-most-popular")));
        boxes.add(driver.findElement(By.id("box-campaigns")));
        boxes.add(driver.findElement(By.id("box-latest-products")));
        WebElement wrap ;
        for (WebElement box : boxes){
            int k = 0;
            List<WebElement> elementsBox = box.findElements(By.tagName("li"));
            for (WebElement elem : elementsBox){
                wrap = elem.findElement(By.className("image-wrapper"));
                wrap.findElement(By.xpath("//div[@class = 'sticker sale']"));
                k +=1;
            }
            System.out.println("part : " + k);
        }
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
