package com.selenium.test.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestUtils {


    public static void changeInnerHTML(WebDriver driver, WebElement element, String content) {
        try {
            ((JavascriptExecutor) driver).executeScript(

                    "var ele=arguments[0]; ele.innerHTML = '" + content + "';", element);

        } catch (Exception e) {
        }

    }

    public static void removeElement(WebDriver driver, WebElement element) {
        if (driver instanceof JavascriptExecutor) {
            try {
                ((JavascriptExecutor) driver).executeScript(
                        "var ele=arguments[0]; ele.remove();", element);

            } catch (StaleElementReferenceException exception) {


            }

        }
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile = new File(fileWithPath);

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }

    public static void takeElementScreenshot(WebDriver driver, String fileWithPath, WebElement element) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);

        //Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        BufferedImage fullImage = ImageIO.read(SrcFile);
        Point point = element.getLocation();

        BufferedImage elementScreenshot = fullImage.getSubimage(point.getX(), point.getY(), element.getSize().getWidth(), element.getSize().getHeight());

        ImageIO.write(elementScreenshot, "png", SrcFile);


        //Move image file to new destination

        File DestFile = new File(fileWithPath);


        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);


    }

    public static void scroll(WebDriver driver, boolean down) {
        scroll(driver, down?300:-300);
    }

    public static void scroll(WebDriver driver, int scrollDistance) {
        String scrollScript = "window.scrollBy(0,"+scrollDistance+")";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            jsExecutor.executeScript(scrollScript, "");

    }

    public static void scrollToExtreme(WebDriver driver,boolean top){
        String scrollScript = top?"window.scrollTo(0,0":"window.scrollTo(0,document.body.scrollHeight))";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(scrollScript, "");

    }

}
