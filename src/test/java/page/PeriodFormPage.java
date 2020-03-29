package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PeriodFormPage extends AbstractPage {

    public PeriodFormPage(WebDriver driver) {
        super(driver);
    }

    public void selectMonthPeriod() {
        WebElement monthRadioButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@class='kit-radio kit-radio_size_m kit-radio-group__radio kit-radio-group__radio_month']/div[1]")));
        monthRadioButton.click();
    }

    public void selectHalfYearPeriod() {
        WebElement halfYearRadioButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@class='kit-radio kit-radio_size_m kit-radio-group__radio kit-radio-group__radio_halfyear']/div[1]")));
        halfYearRadioButton.click();
    }

    public void selectQuarterPeriod() {
        WebElement quarterRadioButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@class='kit-radio kit-radio_size_m kit-radio-group__radio kit-radio-group__radio_quarter']/div[1]")));
        quarterRadioButton.click();
    }

    public void selectYearPeriod() {
        WebElement yearRadioButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[@class='kit-radio kit-radio_size_m kit-radio-group__radio kit-radio-group__radio_year']/div[1]")));
        yearRadioButton.click();
    }

    public void selectFreePeriod() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,300)");

        WebElement DataFromButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='rates-aside__filter-block-line']/div[@class='rates-date-picker__wrapper rates-aside__datepicker rates-aside__datepicker_field_from-date']/button")));
        DataFromButton.click();
        WebElement SelectDataFromTwelfth = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'12')]")));
        SelectDataFromTwelfth.click();

        WebElement DataToButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='rates-aside__filter-block-line']/div[@class='rates-date-picker__wrapper rates-aside__datepicker rates-aside__datepicker_field_to-date']/button")));
        DataToButton.click();
        WebElement SelectDataToTwentySeventh = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'27')]")));
        SelectDataToTwentySeventh.click();
    }
}
