package aqa_course.task11;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.UUID;

public class Task11Test {
    @BeforeClass
    public static void setUp() {
        ChromeDriverManager.getInstance().setup();
        DriverProvider.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        DriverProvider.getDriver().manage().window().maximize();
    }

    @Test
    public void task11() {
        WebDriver driver = DriverProvider.getDriver();
        driver.get("https://demoqa.com/elements");

        // Step 1: Open site and click Sign up
        TextBoxPage submitPage = new TextBoxPage();
        submitPage.clickSubmit(driver);

        // Step 2: Fill form
        String userName = Generator.generateText(0, 10);
        String userEmail = Generator.generateText(0, 10) + "@mail.com";
        String currentAddress = Generator.generateText(0, 10);
        String permanentAddress = Generator.generateText(0, 10);

        Sender.sendKeys(driver, userName, userEmail, currentAddress, permanentAddress);

        // Step 3: Submit
        driver.findElement(By.xpath("//button[text()='Submit']")).click();

        // Step 4: Validate success alert
        String userNameOutput = OutputForm.getOutputText(driver, "name");
        String userEmailOutput = OutputForm.getOutputText(driver, "email");
        String currentAddressOutput = OutputForm.getOutputText(driver, "currentAddress");
        String permanentAddressOutput = OutputForm.getOutputText(driver, "permanentAddress");

        Assert.assertEquals(userNameOutput, "Name:"+userName, "Invalid Name");
        Assert.assertEquals(userEmailOutput, "Email:"+userEmail, "Invalid Email");
        Assert.assertEquals(currentAddressOutput, "Current Address :"+currentAddress, "Invalid Current Address");
        Assert.assertEquals(permanentAddressOutput, "Permananet Address :"+permanentAddress, "Invalid Permanent Address");
    }
}
