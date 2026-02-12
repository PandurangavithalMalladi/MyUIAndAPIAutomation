package factory;

import org.openqa.selenium.WebDriver;

public final class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        // prevent object creation
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverRef) {
        driver.set(driverRef);
    }

    public static void unload() {
        driver.remove();
    }
}
