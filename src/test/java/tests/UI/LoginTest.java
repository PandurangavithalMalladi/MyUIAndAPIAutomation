package tests.UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelXLSXUtil;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setup(ITestContext context) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        context.setAttribute("driver", driver);   // 🔥 ADD THIS
    }
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        String excelPath =
                System.getProperty("user.dir")
                + "/src/test/resources/testdata/TestData.xlsx";

        return ExcelXLSXUtil.getTestData(excelPath, "Login");
    }


    @Test(dataProvider = "loginData",retryAnalyzer = listeners.RetryAnalyzer.class)
    public void loginTest(String url, String username, String password) {

        try {
            driver.get(url);
            loginPage = new LoginPage(driver);
            loginPage.login(username, password);

            dashboardPage = new DashboardPage(driver);

            Assert.assertTrue(
                    dashboardPage.isDashboardDisplayed(),
                    "Dashboard page is not displayed after login"
            );

            dashboardPage.logout();

        } catch (Throwable t) {   // ✅ MUST be Throwable
            //ScreenshotUtil.takeScreenshot(driver, "loginTest");
            throw t; // rethrow to mark test as FAILED
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
