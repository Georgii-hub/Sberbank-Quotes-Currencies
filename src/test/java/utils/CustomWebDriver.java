package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CustomWebDriver implements WebDriverCreator  {
    private static WebDriver driver;

    public WebDriver createWebDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }
}
