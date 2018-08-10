import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CartTest {
    WebDriver driver;
    WebDriverWait wait;
    Integer commonWait = 5;


    @BeforeClass
    public void start(){
        try{
            System.setProperty("webdriver.chrome.driver", "C:\\Study\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(commonWait, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 8);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkCart() {
        for (int i = 1; i < 4; i++) {
            driver.get("http://localhost/litecart/en/");
            //List<WebElement> boxes = driver.findElements(By.cssSelector("li.product"));
            String duckNum = "li.product:nth-child(" + i + ") > a";
          //  WebElement box = driver.findElement(By.cssSelector("li.product:nth-child(i) > a"));
            WebElement box = driver.findElement(By.cssSelector(duckNum));
            //////  check for sale property
            try {
                driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                box.findElement(By.cssSelector(".sale"));
                box.click();
                WebElement yellow = driver.findElement(By.name("options[Size]"));
                Select qty = new Select(yellow);
                qty.selectByIndex(1);
                driver.findElement(By.name("add_cart_product")).click();
                driver.manage().timeouts().implicitlyWait(commonWait, TimeUnit.SECONDS);
            } catch (NoSuchElementException e) {
                box.click();
                driver.findElement(By.name("add_cart_product")).click();

            }
            WebElement cart = driver.findElement(By.id("cart"));
            WebElement qtyChanged = cart.findElement(By.cssSelector("a:nth-child(2) > .quantity"));
            wait.until(textToBePresentInElement(qtyChanged, String.valueOf(i)));
//        String number = qtyChanged.getText();
//        System.out.println(number);
//        driver.get(driver.findElement(By.cssSelector("#logotype-wrapper > a")).getAttribute("href"));
            driver.get("http://localhost/litecart/en/");
        }

        WebElement toCart = driver.findElement(By.id("cart-wrapper"));
        toCart.findElement(By.cssSelector("a")).click();

        ////// in the cart
        WebElement table= driver.findElement(By.id("order_confirmation-wrapper")).findElement(By.cssSelector("table > tbody"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        Integer inTheCart = rows.size();
        System.out.println(inTheCart);
        String qty3 = rows.get(3).findElement(By.cssSelector("td:nth-child(1)")).getText();
        System.out.println(qty3);

        while (inTheCart > 6){
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(rows.get(3)));
            table= driver.findElement(By.id("order_confirmation-wrapper")).findElement(By.cssSelector("table > tbody"));
            rows = table.findElements(By.tagName("tr"));
            inTheCart = rows.size();
            System.out.println(inTheCart);
        }

        driver.findElement(By.name("remove_cart_item")).click();
        wait.until(stalenessOf(rows.get(3)));

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}

