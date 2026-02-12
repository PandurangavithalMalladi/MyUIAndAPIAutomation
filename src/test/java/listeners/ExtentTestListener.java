package listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class ExtentTestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getExtentReport();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    // ===========================
    // TEST START
    // ===========================
    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());

        test.set(extentTest);
    }

    // ===========================
    // TEST SUCCESS
    // ===========================
    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
        test.remove();
    }

    // ===========================
    // TEST FAILURE
    // ===========================
    @Override
    public void onTestFailure(ITestResult result) {

        if (isRetrying(result)) {
            test.get().info("Test Failed - Retrying...");
            return;
        }

        test.get().fail(result.getThrowable());

        attachScreenshot(result);

        test.remove();
    }

    // ===========================
    // TEST SKIPPED
    // ===========================
    @Override
    public void onTestSkipped(ITestResult result) {

        if (isRetrying(result)) {
            return;
        }

        test.get().skip(result.getThrowable());
        test.remove();
    }

    // ===========================
    // SUITE FINISH
    // ===========================
    @Override
    public void onFinish(ITestContext context) {

        extent.flush();
    }

    // =====================================================
    // 🔥 ADD HELPER METHODS BELOW (INSIDE SAME CLASS)
    // =====================================================

    private boolean isRetrying(ITestResult result) {

        if (result.getMethod().getRetryAnalyzer(result) == null) {
            return false;
        }

        return result.getMethod()
                .getRetryAnalyzer(result)
                .retry(result);
    }

    private void attachScreenshot(ITestResult result) {

        try {
            Object testClass = result.getInstance();

            java.lang.reflect.Field driverField =
                    testClass.getClass()
                            .getDeclaredField("driver");

            driverField.setAccessible(true);

            WebDriver driver =
                    (WebDriver) driverField.get(testClass);

            if (driver != null) {

                String path =
                        ScreenshotUtil.takeScreenshot(driver,
                                result.getName());

                test.get().addScreenCaptureFromPath(path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
