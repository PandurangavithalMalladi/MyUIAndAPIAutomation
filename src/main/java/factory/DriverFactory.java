package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

public final class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverFactory() {
        // prevent object creation
    }

    public static void initDriver() {

        String browser = ConfigReader.get("browser");
        String headless = ConfigReader.get("headless");

        WebDriver driverInstance;

        switch (browser.toLowerCase()) {

            case "chrome":

                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                if (headless.equalsIgnoreCase("true")) {
                    chromeOptions.addArguments("--headless=new");
                    chromeOptions.addArguments("--window-size=1920,1080");
                }

                driverInstance = new ChromeDriver(chromeOptions);
                break;

            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (headless.equalsIgnoreCase("true")) {
                    firefoxOptions.addArguments("--headless");
                }

                driverInstance = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":

                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                if (headless.equalsIgnoreCase("true")) {
                    edgeOptions.addArguments("--headless=new");
                }

                driverInstance = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new RuntimeException("Browser not supported: " + browser);
        }

        driverInstance.manage().window().maximize();

        driverInstance.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Long.parseLong(ConfigReader.get("implicitWait"))
                )
        );

        driver.set(driverInstance);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}