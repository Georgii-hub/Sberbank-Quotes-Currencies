package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CurrenciesForComparisonForm extends AbstractPage {

    public CurrenciesForComparisonForm(WebDriver driver) {
        super(driver);
    }

    public String addEuroCurrency() {
        WebElement euro = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//aside[@class]/div/div/div/div/label/div[@class='kit-checkbox__control']")));
        euro.click();
        String currency = new WebDriverWait(driver, 1000).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tbody/tr[@class='rates-current__table-row rates-current__table-row_odd']/td[@class='rates-current__table-cell rates-current__table-cell_column_name']/.")
        )).getText();
        return currency;
    }

    public String checkDKKCurrency() {
        selectDKKCurrency();
        String currencyFromSelectForm = new WebDriverWait(driver, 1000).until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@role='group']/label[3]/div[2]")
        )).getText();
        return currencyFromSelectForm;
    }

    public String removeCurrency() {
        selectDKKCurrency();
        WebElement checkboxDkkOff = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='group']/label[3]/div[1]")));
        checkboxDkkOff.click();
        String dkkFromCurrenciesFromComparisonForm = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tbody/tr[@class='rates-current__table-row']/td[1]"))).getText();
        return dkkFromCurrenciesFromComparisonForm;
    }

    public String checkDisplayGraph() {
        selectDKKCurrency();
        String graphName = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='details-item print-invisible'][2]/h2"))).getText();
        return graphName;
    }

    public String openQuotationTable() {
        selectDKKCurrency();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900)");
        WebElement buttonQuotationTable = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='details-item print-invisible'][2]/div/div/div/span[2]/span")));
        buttonQuotationTable.click();
        String quotationTableName = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']/div[@class='modal-dialog']/div/h2"))).getText();
        return quotationTableName;
    }

    public void selectDKKCurrency() {
        WebElement dropdown = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='select']")));
        dropdown.click();

        WebElement selectDKK = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='visible']/span[3]")));
        selectDKK.click();
    }
}