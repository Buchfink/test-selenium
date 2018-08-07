import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ZonesTest {
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

    void goToOrigin(){
        WebElement block = driver.findElement(By.id("sidebar"));
        block.findElement(By.tagName("a")).click();
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
    public void checkAlphabethical() {

        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        WebElement subBlock = driver.findElement(By.id("content"));
        List<WebElement> countryList = subBlock.findElements(By.className("row"));
        int countrySize = countryList.size();
        System.out.println(countrySize);

        for (int i = 0; i<countrySize; i++){
            //go to the very beginning of countries list
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            subBlock = driver.findElement(By.id("content"));
            countryList = subBlock.findElements(By.className("row"));

            List<WebElement> columnList= countryList.get(i).findElements(By.tagName("td"));
            String linkAsText = columnList.get(4).findElement(By.tagName("a")).getAttribute("href");
            checkZonesAlph(linkAsText);
        }
    }

    public void checkZonesAlph(String linkAsText){
        String link = linkAsText;
        System.out.println(link);
        driver.get(link);
        WebElement table = driver.findElement(By.id("table-zones"));
        //rows with zones (states)
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        int numberZones = rows.size()-1;
        //get value of first Zone
        List<WebElement> cellsRow = rows.get(1).findElements(By.tagName("td"));
        WebElement cell = cellsRow.get(2).findElement(By.tagName("select"));
        List<WebElement> zonesList = cell.findElements(By.tagName("option"));
        System.out.println(zonesList.size());
        String zoneSelected = "";
        for (WebElement zoneIfSelected: zonesList){
            String attributeSelected = zoneIfSelected.getAttribute("selected");
            //System.out.println(attributeSelected);
            if (attributeSelected != null){
                //zoneSelected = zoneIfSelected.getText();
                zoneSelected = zoneIfSelected.getAttribute("textContent");
                System.out.println(zoneSelected);
            }
        }
        ///compare with other Zones names
        for (int i=2; i<numberZones; i++ ){
            cellsRow = rows.get(i).findElements(By.tagName("td"));
            cell = cellsRow.get(2).findElement(By.tagName("select"));
            zonesList = cell.findElements(By.tagName("option"));
            String zoneSelectedNext = "";
            for (WebElement zoneIfSelected: zonesList){
                String attributeSelected = zoneIfSelected.getAttribute("selected");
              //  System.out.println(attributeSelected);
                if (attributeSelected != null){
                    //zoneSelected = zoneIfSelected.getText();
                    zoneSelectedNext = zoneIfSelected.getAttribute("textContent");
                    System.out.println(zoneSelectedNext);
                }
            }
            if (zoneSelected.compareTo(zoneSelectedNext)<0){
                zoneSelected = zoneSelectedNext;
            }
            else {
                System.out.println("Zones not alphabetical");
                break;
            }
        }
        /*

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
        */
    }
    }
