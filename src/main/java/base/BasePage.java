package base;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.JavaScriptUtil;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // 👈 explicit wait
        PageFactory.initElements(driver, this);
    }

    // Click with explicit wait + JS fallback
    protected void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            JavaScriptUtil.clickByJS(driver, element);
        }
    }

    // Type text with wait
    protected void type(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    // Get text with wait
    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    // Check displayed safely
    protected boolean isDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // Wait for page title and return it
    protected String getPageTitle() {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.titleIs("")));
        return driver.getTitle();
    }
}
