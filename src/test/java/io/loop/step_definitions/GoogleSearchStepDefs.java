package io.loop.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.loop.utilities.BrowserUtils;
import io.loop.utilities.ConfigurationReader;
import io.loop.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GoogleSearchStepDefs {

    GoogleSearchPage googleSearchPage = new GoogleSearchPage();
    @Given("user is on Google search page")
    public void user_is_on_google_search_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("google.url"));
        BrowserUtils.takeScreenshot();

    }
    @When("user types Loop Academy in the google search box and clicks enter")
    public void user_types_loop_academy_in_the_google_search_box_and_clicks_enter() throws InterruptedException {
        googleSearchPage.searchBox.sendKeys("Loop Academy"+ Keys.ENTER);
        WebElement element = Driver.getDriver().findElement(By.xpath("//iframe[@title='reCAPTCHA']"));

        Driver.getDriver().switchTo().frame(element);

        if(googleSearchPage.captcha.isDisplayed()){
            googleSearchPage.captcha.click();
        }

        Driver.getDriver().switchTo().defaultContent();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Then("user should see Loop Academy - Google search in the google title")
    public void user_should_see_loop_academy_google_search_in_the_google_title() {

        String actualTitle = Driver.getDriver().getTitle();
        assertEquals("Expected result does not match the actual", "Loop Academy - Google Search", actualTitle);

    }

    @Then("user searches the following items")
    public void user_searches_the_following_items (List <String> items) {
        /*
        for (String item : items){
            googleSearchPage.searchBox.clear();
            googleSearchPage.searchBox.sendKeys(item + Keys.ENTER);
            googleSearchPage.handleReCaptcha(Driver.getDriver(), googleSearchPage.captcha);
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs(item + " - Google Search"));

            assertEquals("Expected does not match the actual", item + " - Google Search", Driver.getDriver().getTitle());
        }

         */

        items.forEach(item -> {
            googleSearchPage.searchBox.clear();
            googleSearchPage.searchBox.sendKeys(item + Keys.ENTER);
            googleSearchPage.handleReCaptcha(Driver.getDriver(), googleSearchPage.captcha);
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.titleIs(item + " - Google Search"));

            assertEquals("Expected does not match the actual", item + " - Google Search", Driver.getDriver().getTitle());

        });




    }
    @Then("we love Loop Academy")
    public void we_love_loop_academy() {
        System.out.println("we love loop academy");

    }

    @When("user search for {string}")
    public void user_search_for(String country) throws InterruptedException {

        googleSearchPage.searchBox.sendKeys("What is the capital of " + country +Keys.ENTER);


    }
    @Then("user should see the {string} in the results")
    public void user_should_see_the_in_the_results(String capital) {
        googleSearchPage.handleReCaptcha(Driver.getDriver(), googleSearchPage.captcha);
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10) );
       // wait.until(ExpectedConditions.visibilityOf(googleSearchPage.capital));
        assertEquals("Expected capital city: " + capital + " does not match with actual one: " + googleSearchPage.capital.getText(), capital, googleSearchPage.capital.getText());

    }



}
