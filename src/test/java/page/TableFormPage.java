package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PeriodComparator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TableFormPage extends AbstractPage {
    public TableFormPage(WebDriver driver) {
        super(driver);
    }

    public String totalGradationValues() {
        String gradationValues;
        WebElement selectTotalGradation = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@data-code='840']")));
        selectTotalGradation.click();
        WebElement fromThousand = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@data-amount-from='1000']")));
        String fromThousandValue = fromThousand.getText();

        WebElement fromZero = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@data-amount-from='0']")));
        String fromZeroValue = fromZero.getText();
        gradationValues = fromThousandValue + " И " + fromZeroValue;

        return gradationValues;
    }

    public void openPrintForm() {
        WebElement pushPrintLink = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='rates-details__link rates-details__link_action_print']/span")));
        pushPrintLink.click();
    }

    public String printButton() {
        WebElement PrintButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='rates-button rates-details__print-preview-button'][2]")));

        String namePrintButton = PrintButton.getText();
        return namePrintButton;
    }

    public boolean showButton() {
        boolean present;
        try {
            driver.findElement(By.xpath("//button[@class='rates-button']"));
            return present = true;
        } catch (NoSuchElementException e) {
            return present = false;
        }
    }

    public boolean closeButtonWork() {
        WebElement closeButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@class='rates-button rates-details__print-preview-button']")));
        closeButton.click();

        boolean present;
        try {
            driver.findElement(By.xpath("//h1[@class='header_widget']"));
            return present = true;
        } catch (NoSuchElementException e) {
            return present = false;
        }
    }

    public String calculatorButtonWork() {
        WebElement calculatorButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@class='rates-current__link']")));
        calculatorButton.click();

        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    public boolean downloadQuotationTable() {
        WebElement buttonQuotationTable = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='details-item print-invisible']/div/div/div//span[contains(text(),'Таблица изменения котировок')]")));
        buttonQuotationTable.click();

        WebElement downloadButton = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='document']/a")));
        downloadButton.click();

        WebElement closeButtonClick = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='rates-table-view-close']/span")));
        closeButtonClick.click();

        String fromData = new PeriodComparator(driver).getFromData();
        String toData = new PeriodComparator(driver).getToData();


        /** Перед использованием данного метода необходимо изменить путь downloadPath на локальный **/
        String downloadPath = "C:\\Users\\Georgii_Stetsiur-Mov\\Downloads\\Доллар США с " + fromData + " по " + toData + ".xls";

        Path path = Paths.get(downloadPath);

        if (Files.exists(path)) {
            File file = new File(downloadPath);
            file.delete();
            return true;
        } else {
            return false;
        }
    }
}

