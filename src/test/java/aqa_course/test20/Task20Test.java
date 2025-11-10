package aqa_course.test20;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.Test;
import org.openqa.selenium.By;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Task20Test {
    @Test
    public void task20Test() throws MalformedURLException {
        File apkFile = new File("apk/calc.apk");
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Pixel_6")
                .setApp(apkFile.getAbsolutePath());
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/session/9c6906ce-d134-4fa5-922e-190947182703"), String.valueOf(options));
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"App\"]")).click();
        driver.quit();
    }
}
