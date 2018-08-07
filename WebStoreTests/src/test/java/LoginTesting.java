import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class LoginTesting {
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

    @Test(enabled = false)
    public void loginMainStore(){
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("ab@ab");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).sendKeys(Keys.ENTER);
//        driver.findElement(By.name("login")).click();
        String title = driver.getTitle();
        System.out.println(title);
        wait.until(titleIs("Online Store | My Store"));
//        wait.until(ExpectedConditions.titleContains("My Store"));
//        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
     //   driver.findElement(By.linkText("Logout")).click();
    }

    @Test(priority = 0)
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
    void goToOrigin(){
        WebElement block = driver.findElement(By.id("sidebar"));
        block.findElement(By.tagName("a")).click();
    }

    @Test(priority = 1)
    public void clickFirst(){
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement table = block.findElement(By.className("list-vertical"));
        ///should be the same
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));
        int tableSize = elementsTable.size();
        System.out.println(elementsTable.size());

        int currentNum = 0; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end first Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-template"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-logotype"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 1)
    public void clickSecond(){
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 1; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-catalog"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-product_groups"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-option_groups"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-3 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-manufacturers"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-4 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-suppliers"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-5 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-delivery_statuses"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-6 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-sold_out_statuses"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-7 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-quantity_units"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-8 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-csv"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-9 Logotype
    }
    @Test(priority = 3)
    public void clickThird(){
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 2; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end Major link
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
    }
    @Test(priority = 4)
    public void clickForth(){
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 3; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end Major link
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
    }
    @Test(priority = 5)
    public void clickFifth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 4; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-customers"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-csv"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-newsletter"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 6)
    public void clickSixth(){
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 5; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end Major link
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
    }
    @Test(priority = 7)
    public void clickSeventh() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 6; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-languages"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-storage_encoding"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 8)
    public void clickEightn() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 7; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-jobs"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-customer"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-shipping"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-payment"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-order_total"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-order_success"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-order_action"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 9)
    public void clickNinth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 8; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-orders"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-order_statuses"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 10)
    public void clickTenth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 9; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
    }
    @Test(priority = 11)
    public void clickEleventh() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 10; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-monthly_sales"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-most_sold_products"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-most_shopping_customers"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 12)
    public void clickTwelfth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 11; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-store_info"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-defaults"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-general"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-listings"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-images"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-checkout"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-advanced"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-security"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 13)
    public void clickThirteenth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 12; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
    }
    @Test(priority = 14)
    public void clickFourteenth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 13; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-tax_classes"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-tax_rates"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 15)
    public void clickFifteenth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 14; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-search"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-scan"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
        tableId = driver.findElement(By.id("box-apps-menu"));
        subLink = tableId.findElement(By.id("doc-csv"));
        subLink.click();
        subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
//end 1-2 Logotype
    }
    @Test(priority = 16)
    public void clickSixteenth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 15; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
    }
    @Test(priority = 17)
    public void clickSeventeenth() {
        goToOrigin();
        WebElement block = driver.findElement(By.id("box-apps-menu-wrapper"));
        WebElement tableTag = block.findElement(By.tagName("ul"));
        List<WebElement> elementsTable = tableTag.findElements(By.tagName("li"));

        int currentNum = 16; ///make dinamic
        WebElement currentSection = elementsTable.get(currentNum);
        WebElement link = currentSection.findElement(By.tagName("a"));
        link.click();
        ////end second Major link
        WebElement tableId = driver.findElement(By.id("box-apps-menu"));
        WebElement subLink = tableId.findElement(By.id("doc-vqmods"));
        subLink.click();
        WebElement subBlock = driver.findElement(By.id("content"));
        subBlock.findElement(By.tagName("h1"));
///end 1-1 Template
    }

        @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
