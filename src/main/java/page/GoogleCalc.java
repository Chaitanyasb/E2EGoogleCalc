package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoogleCalc {

    private WebDriver driver;
    private By result = By.id("cwos");

    public GoogleCalc(WebDriver driver) {
        this.driver = driver;
    }

    //method to click the button that provide in the calculator
    public void buttonClick(String buttonText){

        WebElement button = driver.findElement(By.xpath("//div[text()='" + buttonText + "']"));
        button.click();
    }

    //Method to take multiple numbers and operator as input
    public void calculations(List<String> inputs){
      for (String input : inputs) {
            buttonClick(input);
        }
    }

    // method to perform calculation and get result
    public String getResult() {
        buttonClick("=");
        WebElement resultElement = driver.findElement(result);
        return resultElement.getText();
    }

    // method to perform CE action
    public void clearEntry() {
        buttonClick("CE");
    }

    //method to perform AC action
    public void allClear() {
        buttonClick("AC");
    }

}

