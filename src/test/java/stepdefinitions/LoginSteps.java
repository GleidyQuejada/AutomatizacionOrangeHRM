package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tasks.Login;

import static org.junit.Assert.assertEquals;


public class LoginSteps
{

    private final Actor actor = Actor.named("User");

    @Given("the user is on the OrangeHRM login page")
    public void theUserIsOnTheOrangeHRMLoginPage()
    {

        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        actor.can(BrowseTheWeb.with(driver));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("the user enters a valid username and password")
    public void theUserEntersAValidUsernameAndPassword()
    {
        actor.attemptsTo(Login.withCredentials("Admin", "admin123"));
    }

    @Then("the user should be redirected to the home page")
    public void theUserShouldBeRedirectedToTheHomePage()
    {
        String url = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        String actualUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        assertEquals("The user cannot enter in the page", url, actualUrl);

        //Validate emergent window "Accept"
        actor.attemptsTo(Click.on(By.xpath("//button[text()='Aceptar']")));
    }
}
