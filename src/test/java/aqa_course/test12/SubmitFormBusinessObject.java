package aqa_course.test12;

import aqa_course.task11.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SubmitFormBusinessObject {
    public void submitForm(WebDriver driver){
        driver.get("https://demoqa.com/elements");

        SubmitFormPageObject submitFormPageObject = new SubmitFormPageObject(driver);
        submitFormPageObject.clickTextBoxMenuButton();
        submitFormPageObject.waitForSubmitForm();

        String userName = Generator.generateText(0, 10);
        String userEmail = Generator.generateText(0, 10) + "@mail.com";
        String currentAddress = Generator.generateText(0, 10);
        String permanentAddress = Generator.generateText(0, 10);

        submitFormPageObject.inputUserName(userName);
        submitFormPageObject.inputUserEmail(userEmail);
        submitFormPageObject.inputCurrentAddressInput(currentAddress);
        submitFormPageObject.inputPermanentAddress(permanentAddress);
        submitFormPageObject.clickSubmitButton();
        submitFormPageObject.validateSubmitMessage(userName, userEmail, currentAddress, permanentAddress);


//        // Step 1: Open site and click Sign up
//        TextBoxPage submitPage = new TextBoxPage();
//        submitPage.clickSubmit(driver);
//
//        // Step 2: Fill form
//        String userName = Generator.generateText(0, 10);
//        String userEmail = Generator.generateText(0, 10) + "@mail.com";
//        String currentAddress = Generator.generateText(0, 10);
//        String permanentAddress = Generator.generateText(0, 10);
//
//        Sender.sendKeys(driver, userName, userEmail, currentAddress, permanentAddress);
//
//        // Step 3: Submit
//        Sender.submit(driver);
//
//        // Step 4: Validate success alert
//        String userNameOutput = OutputForm.getOutputText(driver, "name");
//        String userEmailOutput = OutputForm.getOutputText(driver, "email");
//        String currentAddressOutput = OutputForm.getOutputText(driver, "currentAddress");
//        String permanentAddressOutput = OutputForm.getOutputText(driver, "permanentAddress");
//
//        Assert.assertEquals(userNameOutput, "Name:"+userName, "Invalid Name");
//        Assert.assertEquals(userEmailOutput, "Email:"+userEmail, "Invalid Email");
//        Assert.assertEquals(currentAddressOutput, "Current Address :"+currentAddress, "Invalid Current Address");
//        Assert.assertEquals(permanentAddressOutput, "Permananet Address :"+permanentAddress, "Invalid Permanent Address");
    }
}
