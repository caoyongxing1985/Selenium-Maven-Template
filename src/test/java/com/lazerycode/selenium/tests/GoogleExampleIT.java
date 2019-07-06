package com.lazerycode.selenium.tests;

import com.lazerycode.selenium.DriverBase;
import com.lazerycode.selenium.page_objects.BaiduHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

public class GoogleExampleIT extends DriverBase {

    private ExpectedCondition<Boolean> pageTitleStartsWith(final String searchString) {
        return driver -> driver.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
    }

    @Test
    public void googleMilkExample() throws Exception {
        // Create a new WebDriver instance
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebDriver driver = getDriver();

        // And now use this to visit Google
        driver.get("http://image.baidu.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        BaiduHomePage baiduHomePage = new BaiduHomePage();

        // Check the title of the page
        System.out.println("Page title is: " + driver.getTitle());

        long startTime=System.currentTimeMillis();

        baiduHomePage.enterSearchTerm("杨幂")
                .submitSearch();

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 10, 100);
        wait.until(pageTitleStartsWith("杨幂"));

        // Should see: "cheese! - Google Search"
        System.out.println("Page title is: " + driver.getTitle());


        List<WebElement> elements=driver.findElements(By.cssSelector("li.imgitem"));

        while (elements.size()<50){
            System.out.println("点击下一页......");
            WebElement webElement = driver.findElement(By.cssSelector("body"));
            // webElement.sendKeys(Keys.PAGE_DOWN);
            webElement.sendKeys(Keys.PAGE_DOWN);
            Thread.sleep(100);
            elements=driver.findElements(By.cssSelector("li.imgitem"));
        }

        for(WebElement webElement:elements){

            System.out.println("url: "+webElement.getAttribute("data-objurl"));
            System.out.println("text: "+webElement.getAttribute("data-title"));
        }

        System.out.println("耗时："+(System.currentTimeMillis()-startTime)/1000);

    }
}
