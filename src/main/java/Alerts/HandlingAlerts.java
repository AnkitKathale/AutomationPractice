package Alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HandlingAlerts {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.manage().window().maximize();
        //alert no 1
//        driver.findElement(By.xpath("//button[@onclick='jsAlert()']")).click();
//          Alert alr = wait.until(ExpectedConditions.alertIsPresent());

//        alr.accept();

        //alert no 2
        //driver.findElement(By.xpath("//button[@onclick='jsConfirm()']")).click();
        //Alert alr = wait.until(ExpectedConditions.alertIsPresent());
        //alr.accept();
        //alr.dismiss();

        //alert no 3
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Alert alr = wait.until(ExpectedConditions.alertIsPresent());//can also use switchToAlert() here instead of this
        alr.sendKeys("lawda");
        alr.accept();




    }
}
