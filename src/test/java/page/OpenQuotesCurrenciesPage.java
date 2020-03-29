package page;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.open;

public class OpenQuotesCurrenciesPage extends AbstractPage {
    private static final String HOMEPAGE_URL = "https://www.sberbank.ru/ru/quotes/currencies";

    public OpenQuotesCurrenciesPage(WebDriver driver) {
        super(driver);

    }

    public OpenQuotesCurrenciesPage openQuotesCurrenciesPage() {
        open(HOMEPAGE_URL);
        return this;
    }
}
