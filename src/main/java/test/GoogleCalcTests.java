package test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.GoogleCalc;

import java.util.Arrays;


public class GoogleCalcTests {

    private WebDriver driver;
    private GoogleCalc googleCalc;

    @BeforeClass
    public void setup(){
    System.setProperty("webdriver.chrome.driver","C:/Users/chromedriverwin32/chromedriver.exe");
    driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.google.com");
    driver.findElement(By.name("q")).sendKeys("calculator");
    driver.findElement(By.name("q")).submit();

    googleCalc = new GoogleCalc(driver);

    }

    @BeforeMethod
    public void clearCalc(){
        googleCalc.allClear();
    }

    // To verify addition of numbers
    @Test(priority = 1)
    public void additionTest(){
        googleCalc.calculations(Arrays.asList("1","+","2","+","3"));
        String result = googleCalc.getResult();
        Assert.assertEquals(result,"6", "Addition Test Failed");
    }

    // Test to verify subtraction of 3 numbers giving positive number result
    @Test(priority = 2)
    public void subtractionPositiveAnsTest(){
        googleCalc.calculations(Arrays.asList("6","−","2","−","3"));
        String result = googleCalc.getResult();
        Assert.assertEquals(result,"1", "Subtraction Test Failed");
    }

    // Test to verify subtraction of 3 numbers giving negative number result
    @Test(priority = 3)
    public void subtractionNegativeAnsTest(){
        googleCalc.calculations(Arrays.asList("3","−","2","−","6"));
        String result = googleCalc.getResult();
        Assert.assertEquals(result,"-5", "Subtraction Test Failed");
    }

    // Test to verify multiplication of multiple numbers including single and two-digit numbers
    @Test(priority = 4)
    public void multiplicationTest(){
        googleCalc.calculations(Arrays.asList("6","×","2","×","3","×","5","0"));
        String result = googleCalc.getResult();
        Assert.assertEquals(result,"1800", "multiplication Test Failed");
    }

    // Test to verify division of two two-digits numbers
    @Test(priority = 5)
    public void divisionTest(){
        googleCalc.calculations(Arrays.asList("2","4","÷","1","2"));
        String result = googleCalc.getResult();
        Assert.assertEquals(result,"2", "Division Test Failed");
    }

    //Test to verify multiple number calculations using different operators
    @Test(priority = 6)
    public void combinationOfOperatorsTest(){
        googleCalc.calculations(Arrays.asList("7",".","2","+","0",".","8","×","1","0","+","5","÷","5","−","2"));
        String result = googleCalc.getResult();
        Assert.assertEquals(result,"14.2", "Combination of operators Test Failed");
    }

    //Test to verify CE button clearing the recent entry and AC button clear all
    @Test(priority = 7)
    public void clearTest() {
        googleCalc.calculations(Arrays.asList("7", "1", "2"));
        googleCalc.clearEntry();
        String result = googleCalc.getResult();
        Assert.assertEquals(result, "71", "CE Test Failed");
        googleCalc.allClear();
        String result1 = googleCalc.getResult();
        Assert.assertEquals(result1, "0", "AC Test Failed");
    }

    @AfterClass
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }
}
