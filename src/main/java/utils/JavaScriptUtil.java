package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

    public static void clickByJS(
            WebDriver driver, WebElement element) {

        JavascriptExecutor js =
            (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public static void scrollDown(WebDriver driver) {

        JavascriptExecutor js =
            (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
