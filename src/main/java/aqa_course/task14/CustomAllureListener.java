package aqa_course.task14;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static aqa_course.task11.DriverProvider.getDriver;

public class CustomAllureListener implements ITestListener {
    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Success: " + result.getName());
        ITestListener.super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failure: " + result.getName());
        ITestListener.super.onTestFailure(result);
        makeScreenshotAttachment();
        makeDOMAttachment();
    }

    @Attachment(value="Page screenshot", type="image/png")
    private byte[] makeScreenshotAttachment(){
        System.out.println("makeScreenShot");
        return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value="{0}", type="text/plain")
    private String makeDOMAttachment() {
        return getDriver().getPageSource();
    }
}
