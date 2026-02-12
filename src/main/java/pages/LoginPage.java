package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);   // 👈 important
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginBtn);
    }
}
