package DatePicker;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DatePicker {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://jqueryui.com/datepicker/");
        driver.manage().window().maximize();

        // Switch to the iframe containing the datepicker
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        // Open the calendar widget
        WebElement dateField = driver.findElement(By.id("datepicker"));
        dateField.click();

        // --- Your variables ---
        String year = "2023";      // Target year
        String month = "January";  // Target month (must match UI)
        String day = "15";         // Target day

        // Navigate to correct month & year
        while (true) {
            String currentYear = driver.findElement(By.className("ui-datepicker-year")).getText();
            String currentMonth = driver.findElement(By.className("ui-datepicker-month")).getText();

            if (currentYear.equals(year) && currentMonth.equals(month)) {
                break;
            }

            // Convert years for comparison
            int currYear = Integer.parseInt(currentYear);
            int tarYear = Integer.parseInt(year);

            if (tarYear > currYear ||
                    (tarYear == currYear && isTargetMonthGreater(currentMonth, month))) {
                // Target is in future
                driver.findElement(By.xpath("//a[@title='Next']")).click();
            } else {
                // Target is in past
                driver.findElement(By.xpath("//a[@title='Prev']")).click();
            }

            Thread.sleep(500);
        }

        // Select the day
        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//a"));
        for (WebElement date : allDates) {
            if (date.getText().equals(day)) {
                date.click();
                break;
            }
        }
    }

    // Helper method: check if target month > current month
    private static boolean isTargetMonthGreater(String currentMonth, String targetMonth) {
        String[] months = {"January","February","March","April","May","June","July",
                "August","September","October","November","December"};

        int currIndex = -1, targetIndex = -1;
        for (int i = 0; i < months.length; i++) {
            if (months[i].equalsIgnoreCase(currentMonth)) currIndex = i;
            if (months[i].equalsIgnoreCase(targetMonth)) targetIndex = i;
        }
        return targetIndex > currIndex;
    }
}
