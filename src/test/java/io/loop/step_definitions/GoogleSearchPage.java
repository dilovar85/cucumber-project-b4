package io.loop.step_definitions;

import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

    public GoogleSearchPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement searchBox;

    @FindBy(xpath = "//input[@id='gbgfbb'//preceding-sibling::input")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='recaptcha-checkbox-border']")
    public WebElement captcha;

    @FindBy (xpath = "//div[@class='wvKXQ']")
    public WebElement capital;


    public void handleReCaptcha(WebDriver driver, WebElement captchaElement) {
        try {


            WebElement iframe = driver.findElement(By.xpath("//iframe[@title='reCAPTCHA']"));
            driver.switchTo().frame(iframe);

            if (captchaElement.isDisplayed()) {
                captchaElement.click();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

            driver.switchTo().defaultContent();


    }
}


