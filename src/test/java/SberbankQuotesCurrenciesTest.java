import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import page.CurrenciesForComparisonForm;
import page.OpenQuotesCurrenciesPage;
import page.TableFormPage;
import utils.PeriodComparator;
import page.PeriodFormPage;
import utils.CustomWebDriver;
import utils.WebDriverCreator;

import java.time.Period;

public class SberbankQuotesCurrenciesTest {
    private static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        WebDriverCreator creator = new CustomWebDriver();
        driver = creator.createWebDriver();
        WebDriverRunner.setWebDriver(driver);
        driver.manage().window().maximize(); //открытие браузера в полноэкранном режиме
        new OpenQuotesCurrenciesPage(driver).openQuotesCurrenciesPage();
    }

    @Test(description = "Check add Euro currency in comparison form", groups = "Currency select form")
    public void checkAddNewCurrencies() {
        String addEuro = new CurrenciesForComparisonForm(driver).addEuroCurrency();
        Assert.assertEquals(addEuro, "EUR / RUB", "EUR currency was not added");
    }

    @Test(description = "Check select DKK currency in dropdown and add on Select Form", groups = "Currency select form")
    public void checkSelectNewCurrencies() {
        String newCurrencyFromSelectForm = new CurrenciesForComparisonForm(driver).checkDKKCurrency();
        Assert.assertEquals(newCurrencyFromSelectForm, "Датская крона", "Not correct currencies!");
    }

    @Test(description = "Check remove DDK currency from comparison form after remove flag", groups = "Currency select form")
    public void checkRemoveCurrenciesFromComparisonForm() {
        String checkDkkInComparisonForm = new CurrenciesForComparisonForm(driver).removeCurrency();
        Assert.assertFalse(false, "DKK currency not removed");
    }

    @Test(description = "Check display graph DDK currency after select in dropdown", groups = "Currency select form")
    public void checkAddCurrenciesGraph() {
        String graphName = new CurrenciesForComparisonForm(driver).checkDisplayGraph();
        Assert.assertEquals(graphName, "Датская крона", "Graph does not match with added currency");
    }

    @Test(description = "Check Open Quotation Table DDK", groups = "Currency select form")
    public void checkOpenQuotationTable() {
        String quotationTableName = new CurrenciesForComparisonForm(driver).openQuotationTable();
        Assert.assertEquals(quotationTableName, "Таблица изменения котировок, DKK", "QuotationTable was not open");
    }

    @Test(description = "Check one month period of time", groups = "Time period form")
    public void checkMonthPeriodOfTime() {
        new PeriodFormPage(driver).selectMonthPeriod();
        Period months = new PeriodComparator(driver).getPeriod();
        Assert.assertEquals(months.getMonths(), 1, "Period of time not one month");
    }

    @Test(description = "Check half year period of time", groups = "Time period form")
    public void checkHalfYearPeriodOfTime() {
        new PeriodFormPage(driver).selectHalfYearPeriod();
        Period halfYear = new PeriodComparator(driver).getPeriod();
        Assert.assertEquals(halfYear.getMonths(), 6, "Period of time not a half year");
    }

    @Test(description = "Check quarter period of time", groups = "Time period form")
    public void checkQuarterPeriodOfTime() {
        new PeriodFormPage(driver).selectQuarterPeriod();
        Period quarter = new PeriodComparator(driver).getPeriod();
        Assert.assertEquals(quarter.getMonths(), 3, "Period of time not a quarter");
    }

    @Test(description = "Check year period of time", groups = "Time period form")
    public void checkYearPeriodOfTime() {
        new PeriodFormPage(driver).selectYearPeriod();
        Period year = new PeriodComparator(driver).getPeriod();
        Assert.assertEquals(year.getYears(), 1, "Period of time not a year");
    }

    @Test(description = "Check free period of time", groups = "Time period form")
    public void checkFreePeriodOfTime() {
        new PeriodFormPage(driver).selectFreePeriod();
        Period free = new PeriodComparator(driver).getPeriod();
        Assert.assertEquals(free.getDays(), 15, "Period of time not correct");
    }

    @Test(description = "Check download Quotation Table", groups = "Buttons and other features")
    public void checkDownloadQuotationTable() {
        boolean download = new TableFormPage(driver).downloadQuotationTable();
        Assert.assertEquals(download, true, "Download was not successful");
    }

    @Test(description = "Check print button exist", groups = "Buttons and other features")
    public void checkPrintButton() {
        TableFormPage tableFormPage = new TableFormPage(driver);
        tableFormPage.openPrintForm();
        String namePrintButton = tableFormPage.printButton();
        Assert.assertEquals(namePrintButton, "Печать", "Error with Print button");
    }

    @Test(description = "Check Total Gradation Values", groups = "Buttons and other features")
    public void checkTotalGradationValues() {
        String totalGradationValues = new TableFormPage(driver).totalGradationValues();
        Assert.assertEquals(totalGradationValues, "от 1000 И 0-1000", "Not correct gradation values");
    }

    @Test(description = "Check show button exist", groups = "Buttons and other features")
    public void checkShowButton() {
        boolean showButton = new TableFormPage(driver).showButton();
        Assert.assertTrue(showButton, "Error with Show button");
    }

    @Test(description = "Check how work close button", groups = "Buttons and other features")
    public void checkCloseButton() {
        TableFormPage tableFormPage = new TableFormPage(driver);
        tableFormPage.openPrintForm();
        boolean closeButton = tableFormPage.closeButtonWork();
        Assert.assertTrue(closeButton, "Error with Close button");
    }

    @Test(description = "Check how work calculator button", groups = "Buttons and other features")
    public void checkCalculatorButton() {
        String currentUrl = new TableFormPage(driver).calculatorButtonWork();
        Assert.assertEquals(currentUrl, "https://www.sberbank.ru/ru/quotes/converter", "Calculator button ot correct work");
    }

    @AfterTest(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
