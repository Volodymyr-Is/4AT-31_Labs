package aqa_course.test12;

import aqa_course.task11.OutputForm;
import aqa_course.task11.TextBoxPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SubmitFormPageObject {
    WebDriver driver;

    @FindBy(xpath="//ul/li[@id=\"item-0\"]")
    WebElement textBoxMenuButton;

    @FindBy(id="userName")
    WebElement userNameInput;

    @FindBy(id="userEmail")
    WebElement userEmailInput;

    @FindBy(id="currentAddress")
    WebElement currentAddressInput;

    @FindBy(id="permanentAddress")
    WebElement permanentAddressInput;

    @FindBy(xpath="//button[text()='Submit']")
    WebElement SubmitFormButton;

    WebDriverWait wait;

    public void waitForSubmitForm()
    {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName")));
    }

    public SubmitFormPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickTextBoxMenuButton() {
        textBoxMenuButton.click();
    }

    public void inputUserName(String userName) {
        userNameInput.sendKeys(userName);
    }

    public void inputUserEmail(String userEmail) {
        userEmailInput.sendKeys(userEmail);
    }

    public void inputCurrentAddressInput(String currentAddress) {
        currentAddressInput.sendKeys(currentAddress);
    }

    public void inputPermanentAddress(String permanentAddress) {
        permanentAddressInput.sendKeys(permanentAddress);
    }

    public void clickSubmitButton() {
        SubmitFormButton.click();
    }

    public void validateSubmitMessage(String expectedUserName, String expectedUserEmail, String expectedCurrentAddress, String expectedPermanentAddress) {
        String userNameOutput = OutputForm.getOutputText(driver, "name");
        String userEmailOutput = OutputForm.getOutputText(driver, "email");
        String currentAddressOutput = OutputForm.getOutputText(driver, "currentAddress");
        String permanentAddressOutput = OutputForm.getOutputText(driver, "permanentAddress");

        Assert.assertEquals(userNameOutput, "Name:"+expectedUserName, "Invalid Name");
        Assert.assertEquals(userEmailOutput, "Email:"+expectedUserEmail, "Invalid Email");
        Assert.assertEquals(currentAddressOutput, "Current Address :"+expectedCurrentAddress, "Invalid Current Address");
        Assert.assertEquals(permanentAddressOutput, "Permananet Address :"+expectedPermanentAddress, "Invalid Permanent Address");
    }
}
