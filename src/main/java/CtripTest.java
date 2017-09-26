package com.selenium.test;

import com.selenium.test.utils.TestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.DateUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class CtripTest {
    static WebDriver driver;
    static String baseUrl = "https://english.ctrip.com";
    //STATUS COMPLETE
    @FindBy(css = "#hotelsCity")
    WebElement myElement;

    public static void main(String[] args) throws Exception {
        File pathToBinary = new File("C:\\Users\\ygong1\\Mozilla Firefox\\firefox.exe");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        WebDriver driver = new FirefoxDriver(ffBinary, firefoxProfile);
        goCtrip(driver);
        // goDynWeb(driver);


    }


    public static void goCtrip(WebDriver driver) throws Exception {
        //get today date
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        dateFormat.applyPattern("YYYY-MM-dd");
        String dateToday = dateFormat.format(today);

        driver.get(baseUrl);

        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#hotelsCity")));


        WebElement hotelNameTextField = driver.findElement(By.cssSelector("#hotelsCity"));
        hotelNameTextField.click();

        TestUtils.takeSnapShot(driver, "C://Users//ygong1//IdeaProjects//SeleniumTest//hotelspopup.png");

        WebElement beijingLink = driver.findElement(By.linkText("Beijing"));

        beijingLink.click();
        WebElement checkInTextField = driver.findElement(By.cssSelector("#txtCheckInDisplay"));
        checkInTextField.click();


        String todayLinkSelector = "div[data-id='" + dateToday + "']";
        WebElement todayLink = driver.findElement(By.cssSelector(todayLinkSelector));
        System.err.println(todayLink.getAttribute("outerHTML"));
        WebElement previousMonth = driver.findElement(By.cssSelector("#prev-month"));
        WebElement nextMonth = driver.findElement(By.cssSelector("#next-month"));

        TestUtils.takeSnapShot(driver, "C://Users//ygong1//IdeaProjects//SeleniumTest//calendar.png");
        todayLink.click();

        List<WebElement> starCheckBoxes = driver.findElements(By.cssSelector("label[class='ui_checkbox']"));
        WebElement threeStarCheckbox = driver.findElement(By.cssSelector("label[value='" + "1,2" + "'] span[class='fake-checkbox']"));
        threeStarCheckbox.click();


        List<WebElement> adElements = driver.findElements(By.cssSelector("div[class^='fl_pop_']"));
        System.out.print(adElements.size() + "");
        for (WebElement element : adElements
                ) {
            TestUtils.removeElement(driver, element);
        }


        TestUtils.takeSnapShot(driver, "C://Users//ygong1//IdeaProjects//SeleniumTest//3starhotel.png");
        WebElement searchHotelsButton = driver.findElement(By.cssSelector("#homesearch-btn"));
        TestUtils.scroll(driver,true);
        searchHotelsButton.click();

    }

    public static void goDynWeb(WebDriver driver) {
        driver.get("http://www.dyn-web.com/tutorials/iframes/basics/demo.php");

        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#ifrm")));

        WebElement demoIframe = driver.findElement(By.cssSelector("#ifrm"));
        driver.switchTo().frame(demoIframe);
        WebElement iframeHeading = driver.findElement(By.cssSelector("h1"));
        TestUtils.changeInnerHTML(driver, iframeHeading, "French fries Mother");
        driver.switchTo().defaultContent();

    }


//    void methodAddedByOnkobaSamson() {
//
//        //This is the second commit done by onkoba sam
//
//    }

}





