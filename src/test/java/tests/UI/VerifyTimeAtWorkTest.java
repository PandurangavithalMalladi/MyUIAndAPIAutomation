package tests.UI;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import factory.DriverFactory;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelXLSXUtil;

public class VerifyTimeAtWorkTest {

    WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
	public void setup(ITestContext context) {

		DriverFactory.initDriver();
		driver = DriverFactory.getDriver();

		context.setAttribute("driver", driver);
	}
    
    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        String excelPath =
                System.getProperty("user.dir")
                + "/src/test/resources/testdata/TestData.xlsx";

        return ExcelXLSXUtil.getTestData(excelPath, "Login");
    }


    @Test(dataProvider = "loginData",retryAnalyzer = listeners.RetryAnalyzer.class)
    public void verifyTimeAtWorkTest(String url, String username, String password) {

        try {
            driver.get(url);
            loginPage = new LoginPage(driver);
            loginPage.login(username, password);

            dashboardPage = new DashboardPage(driver);

            Assert.assertTrue(dashboardPage.isDashboardDisplayed(),
                    "Dashboard not displayed");

            Assert.assertEquals(
                    dashboardPage.verifyTimeAtWork(),
                    "Time at Work",
                    "Time at Work is incorrect"
            );

        } catch (Throwable t) {   // ✅ MUST be Throwable
            //ScreenshotUtil.takeScreenshot(driver, "verifyTimeAtWorkTest");
            throw t; // rethrow to mark test as FAILED
        }
    }

    @AfterMethod
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
