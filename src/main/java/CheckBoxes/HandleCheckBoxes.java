package CheckBoxes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HandleCheckBoxes {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 1. Open the website
        driver.get("https://testautomationpractice.blogspot.com");
        driver.manage().window().maximize();
        //2.Select Specific CheckBox

        List<WebElement> cb = driver.findElements(By.xpath("//input[@class='form-check-input']"));
        for(WebElement c:cb){ //clicking all
            c.click();
            if(c.isSelected()) {
	        		System.out.println("Selected checkbox: " + c.getAttribute("id"));
	        	}
        }

//



    }
}
