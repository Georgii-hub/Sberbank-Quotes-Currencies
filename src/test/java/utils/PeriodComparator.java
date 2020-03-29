package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.AbstractPage;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class PeriodComparator extends AbstractPage {

    public PeriodComparator(WebDriver driver) {
        super(driver);
    }

    public String getFromData() {
        String fromData = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@data-property='fromDate']"))).getAttribute("value");
        return fromData;
    }

    public String getToData() {
        String toData = new WebDriverWait(driver, 1000).until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@data-property='toDate']"))).getAttribute("value");
        return toData;
    }

    public Period getPeriod() {
        String fromData = getFromData();
        String toData = getToData();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate start = LocalDate.parse(fromData, dtf);
        LocalDate end = LocalDate.parse(toData, dtf);

        Period period = Period.between(start, end);
        return period;
    }
}
