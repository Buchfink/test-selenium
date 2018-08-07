import org.openqa.selenium.By;
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

public class DuckTest {
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
    public void checkDuck(){
        driver.get("http://localhost/litecart/en/");

        WebElement box = driver.findElement(By.id("box-campaigns"));
        WebElement duckMain = box.findElement(By.cssSelector("li.product")) ;
        String duckText = duckMain.findElement(By.className("name")).getText();
        System.out.println("name main : " + duckText);
        String duckRegPrice = duckMain.findElement(By.className("regular-price")).getText();
        System.out.println("reg price main : " + duckRegPrice);
        String duckActionPrice = duckMain.findElement(By.className("campaign-price")).getText();
        System.out.println("action price main : " + duckActionPrice);

        ////get and check price color
        String regPriceColor = duckMain.findElement(By.className("regular-price")).getCssValue("color");
        System.out.println("color reg price main : " + regPriceColor);
        String[] colorValues = regPriceColor.split(", ");
        colorValues[0] = colorValues[0].substring(5);
        for (int i=1; i<3 ; i++){
          //  System.out.println(colorValues[i]);
            if (colorValues[i].compareTo(colorValues[i-1]) !=0 ){
                System.out.println("Color not grey!!"); break;
            }
        }
        String actionPriceColor = duckMain.findElement(By.className("campaign-price")).getCssValue("color");
        System.out.println("color action price main : " + actionPriceColor);
        colorValues = actionPriceColor.split(", ");
        colorValues[0] = colorValues[0].substring(5);
        for (int i=1; i<3 ; i++){
            //  System.out.println(colorValues[i]);
            if (colorValues[i].compareTo("0") !=0 ){
                System.out.println("Color not red!!"); break;
            }
        }
        ////get and compare price size
        String regPriceSize = duckMain.findElement(By.className("regular-price")).getCssValue("font-size");
        System.out.println("size reg price main : " + regPriceSize);
        String[] twoValues = regPriceSize.split("px");
        Double justValueReg = Double.parseDouble(twoValues[0]);

        String actionPriceSize = duckMain.findElement(By.className("campaign-price")).getCssValue("font-size");
        System.out.println("size action price main : " + actionPriceSize);
        twoValues = actionPriceSize.split("px");
        Double justValueAction = Double.parseDouble(twoValues[0]);
      ///  System.out.println(justValueAction);
        if (justValueAction <= justValueReg) {
            System.out.println("price size NOK");
        }
        else  {
            System.out.println("Action price is bigger. OK! ");
        }

        /////////////goto product page
        duckMain.findElement(By.tagName("a")).click();
        String nameProduct = driver.findElement(By.tagName("h1")).getText();
        System.out.println("name Product : " + nameProduct);
        System.out.println("name main : " + duckText);
        if (nameProduct.compareTo(duckText) !=0){
            System.out.println("Names differ");
        }
        else {
            System.out.println("Names are the same. OK!");
        }
        String regPriceProd = driver.findElement(By.className("regular-price")).getText();
        if (duckRegPrice.compareTo(regPriceProd) != 0){
            System.out.println("regular prices differ!");
        }
        else {
            System.out.println("regular prices are the same. OK!");
        }
        String actionPriceProd = driver.findElement(By.className("campaign-price")).getText();
        if (actionPriceProd.compareTo(duckActionPrice) != 0){
            System.out.println("action prices differ!");
        }
        else {
            System.out.println("action prices are the same. OK!");
        }
        ////color
        String regPriceProdColor = driver.findElement(By.className("regular-price")).getCssValue("color");
        System.out.println("color reg price product : " + regPriceProdColor);
        colorValues = regPriceProdColor.split(", ");
        colorValues[0] = colorValues[0].substring(5);
        for (int i=1; i<3 ; i++){
            if (colorValues[i].compareTo(colorValues[i-1]) !=0 ){
                System.out.println("Color not grey!!"); break;
            }
        }
        String actionPriceProdColor = driver.findElement(By.className("campaign-price")).getCssValue("color");
        System.out.println("color action price product : " + actionPriceProdColor);
        colorValues = actionPriceProdColor.split(", ");
        colorValues[0] = colorValues[0].substring(5);
        for (int i=1; i<3 ; i++){
            //  System.out.println(colorValues[i]);
            if (colorValues[i].compareTo("0") !=0 ){
                System.out.println("Color not red!!"); break;
            }
        }
        /////sizes
        ////get and compare price size
        String regPriceSizeProd = driver.findElement(By.className("regular-price")).getCssValue("font-size");
        System.out.println("size reg price prod : " + regPriceSizeProd);
        twoValues = regPriceSizeProd.split("px");
        justValueReg = Double.parseDouble(twoValues[0]);

        String actionPriceSizeProd = driver.findElement(By.className("campaign-price")).getCssValue("font-size");
        System.out.println("size action price prod : " + actionPriceSizeProd);
        twoValues = actionPriceSizeProd.split("px");
        justValueAction = Double.parseDouble(twoValues[0]);
      ///  System.out.println(justValueAction);
        if (justValueAction <= justValueReg) {
            System.out.println("price size NOK");
        }
        else  {
            System.out.println("Action price is bigger. OK! ");
        }
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
