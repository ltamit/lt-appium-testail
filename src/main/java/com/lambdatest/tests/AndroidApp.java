package com.lambdatest.tests;

import com.lambdatest.library.utils.testrail.TestRailHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.List;

public class AndroidApp {

    String userName = "amitwalia"; //System.getenv("LT_USERNAME") == null ?
            //"username" : System.getenv("LT_USERNAME"); //Add username here
    String accessKey = "SjOTOTQikYHfho5r39aieNJACmUoiofHmhZB8jUyCUG439zMXW"; //System.getenv("LT_ACCESS_KEY") == null ?
            //"accessKey" : System.getenv("LT_ACCESS_KEY");//Add accessKey here

    private String Status = "failed";

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";

    AppiumDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) throws Exception {

        String testrailusername = "amitlambda3@gmail.com";//System.getenv("TESTRAIL_USERNAME") != null ? System.getenv("TESTRAIL_USERNAME")
                //: Configuration.readConfig("TESTRAIL_USERNAME");
        String testrailpassword = "%Amit1234";//System.getenv("TESTRAIL_PASSWORD") != null ? System.getenv("TESTRAIL_PASSWORD")
                //: Configuration.readConfig("TESTRAIL_PASSWORD");
        String testrailurl = "https://amitlambda03.testrail.io/"; //System.getenv("TESTRAIL_URL") != null ? System.getenv("TESTRAIL_URL")
                //: Configuration.readConfig("TESTRAIL_URL");

        TestRailHandler trh = new TestRailHandler(testrailusername,testrailpassword, testrailurl);

        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("build","Java TestNG Android");
            capabilities.setCapability("name",platform+" "+device+" "+version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion",version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            //AppURL (Create from Wikipedia.apk sample in project)
            capabilities.setCapability("app", "lt://APP1016046941678692547703428"); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("console", true);
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability("geoLocation", "HK");

            String hub = "https://" + userName + ":" + accessKey + gridURL;
            driver = new AppiumDriver(new URL(hub), capabilities);

            MobileElement color = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/color");
            //Changes color to pink
            color.click();
            Thread.sleep(1000);
            //Back to orginal color
            color.click();

            MobileElement text = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/Text");
            //Changes the text to "Proverbial"
            text.click();

            //toast will be visible
            MobileElement toast = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/toast");
            toast.click();

            //notification will be visible
            MobileElement notification = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/notification");
            notification.click();
            Thread.sleep(2000);

            //Opens the geolocation page
            MobileElement geo = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/geoLocation");
            geo.click();
            Thread.sleep(5000);

            //takes back to home page
            MobileElement home = (MobileElement) driver.findElementByAccessibilityId("Home");
            home.click();

            //Takes to speed test page
            MobileElement speedtest = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/speedTest");
            speedtest.click();
            Thread.sleep(5000);

            MobileElement Home = (MobileElement) driver.findElementByAccessibilityId("Home");
            Home.click();

            //Opens the browser
            MobileElement browser = (MobileElement) driver.findElementByAccessibilityId("Browser");
            browser.click();

            MobileElement url = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/url");
            url.sendKeys("https://www.lambdatest.com");

            MobileElement find = (MobileElement) driver.findElementById("com.lambdatest.proverbial:id/find");
            find.click();

            Status = "passed";
            driver.executeScript("lambda-status=" + Status);

            trh.updateResultToTestRail(4, "4335", "33");

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
            trh.updateResultToTestRail(4, "4335", "33");

            try{
                driver.quit();
            }catch(Exception e1){
                e.printStackTrace();
                trh.updateResultToTestRail(4, "4335", "33");

            }
        }


    }
}
