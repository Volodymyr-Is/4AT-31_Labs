package aqa_course.task11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Sender {
    public static void sendKeys(WebDriver driver, String userName, String userEmail, String currentAddress, String permanentAddress) {
        driver.findElement(By.id("userName")).sendKeys(userName);
        driver.findElement(By.id("userEmail")).sendKeys(userEmail);
        driver.findElement(By.id("currentAddress")).sendKeys(currentAddress);
        driver.findElement(By.id("permanentAddress")).sendKeys(permanentAddress);
    }
}
