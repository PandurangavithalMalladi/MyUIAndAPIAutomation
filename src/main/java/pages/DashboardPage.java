package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class DashboardPage extends BasePage {

    // Locators
    @FindBy(xpath = "//h6[text()='Dashboard']")
    private WebElement dashboardHeader;

    @FindBy(className = "oxd-userdropdown-tab")
    private WebElement profileIcon;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutBtn;
    
    @FindBy(xpath = "//p[text()='Time at Work']")
    private WebElement verifyTimeAtWork;
    

    // Constructor
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Actions
    public boolean isDashboardDisplayed() {
        return isDisplayed(dashboardHeader);
    }

    public void logout() {
        click(profileIcon);
        click(logoutBtn);
    }
    
    public String verifyTimeAtWork() {
        return getText(verifyTimeAtWork);
    }
}
