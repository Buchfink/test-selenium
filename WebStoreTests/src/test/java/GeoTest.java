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

public class GeoTest {
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
 //   @Test(priority = 0)
    public void loginAdmin(){
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
        String title = driver.getTitle();
        System.out.println(title);
        wait.until(titleIs("My Store"));
    }

    @Test(priority = 1)
    public void checkAlphabethical(){

        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        WebElement subBlock = driver.findElement(By.id("content"));
        List<WebElement> countryList = subBlock.findElements(By.className("row"));
        int countrySize = countryList.size();
        System.out.println(countrySize);
        String linkText = countryList.get(0).findElement(By.tagName("a")).getText();
        String nextText;
        for (int i=1 ; i<countrySize; i++){
            nextText = countryList.get(i).findElement(By.tagName("a")).getText();
            if (linkText.compareTo(nextText)<0){
                linkText = nextText;
            }
            else {
                System.out.println("not alphabetical" );
                break;}
            System.out.println(linkText);
        }
    }

    @Test(priority = 2)
    public void checkZones(){
///just count countries
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        WebElement subBlock = driver.findElement(By.id("content"));
        List<WebElement> countryList = subBlock.findElements(By.className("row"));
        int countrySize = countryList.size();
        System.out.println(countrySize);
        ///get list of countries with several zones (otherwise tooo slow)
        ArrayList<Integer> severalZones = new ArrayList<Integer>();
        for (int i=0 ; i<countrySize; i++){
            List<WebElement> columnList= countryList.get(i).findElements(By.tagName("td"));
            WebElement zonesNumber = columnList.get(5);
            String linkAsText;
            int number = Integer.parseInt(zonesNumber.getText());
            if (number >0){
                System.out.println(number);
                severalZones.add(i);
            }
        }
        ///now check those with several zones
        for (Integer i : severalZones){
            //go to the very beginning of countries list
            driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            subBlock = driver.findElement(By.id("content"));
            countryList = subBlock.findElements(By.className("row"));

            List<WebElement> columnList= countryList.get(i).findElements(By.tagName("td"));
            String linkAsText = columnList.get(6).findElement(By.tagName("a")).getAttribute("href");
                checkZonesAlph(linkAsText);
        }
    }


    public void checkZonesAlph(String linkAsText){
        String link = linkAsText;
        System.out.println(link);
        driver.get(link);
        WebElement table = driver.findElement(By.id("table-zones"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<WebElement> cellsRow = rows.get(1).findElements(By.tagName("td"));
        WebElement cell = cellsRow.get(2).findElement(By.tagName("input"));
        String zone = cell.getAttribute("value");
        System.out.println(zone);

        int numberRows = rows.size()-1;
        for (int i=2 ; i<numberRows; i++){
            cellsRow = rows.get(i).findElements(By.tagName("td"));
            cell = cellsRow.get(2).findElement(By.tagName("input"));
            String zoneNext = cell.getAttribute("value");
            if (zone.compareTo(zoneNext)<0){
                zone = zoneNext;
            }
            else {
                System.out.println("Zones not alphabetical" );
                break;
            }
        }

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
