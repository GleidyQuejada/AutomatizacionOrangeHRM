package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actions.Upload;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import tasks.Login;

import java.io.File;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

public class RecruitmentSteps
{
    Actor user = Actor.named("Tester");

    @Given("the user is on the OrangeHRM login page")
    public void theUserIsOnTheOrangeHRMLoginPage()
    {
        user.attemptsTo(Login.withCredentials("Admin","admin123"));
    }

    @Given("the user is on the Recruitment page")
    public void theUserIsOnTheRecruitmentPage()
    {
       user.attemptsTo(Click.on(
               By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[5]/a/span")));
    }

    @When("the user clicks the Add button to start the hiring process")
    public void theUserClicksTheAddButtonToStartTheHiringProcess()
    {
        user.attemptsTo(Click.on(
                By.xpath("//button[contains(text(), '+ Add')]")));
    }

    @And("the user fills out the candidate's information with the following details:")
    public void theUserFillsOutTheCandidateSInformationWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable)
    {
        java.util.Map<String, String> data = dataTable.asMap(String.class, String.class);

        user.attemptsTo(
                Enter.theValue(data.get("firstName")).into(By.name("Oscar")),
                Enter.theValue(data.get("middleName")).into(By.name("Andres")),
                Enter.theValue(data.get("lastName")).into(By.name("Roa")),
                SelectFromOptions.byVisibleText(data.get("vacancy")).from(By.id("Payroll Administrator")),
                Enter.theValue(data.get("email")).into(By.name("oscarandres@gmail.com")),
                Enter.theValue(data.get("contactNumber")).into(By.name("453465464347")),
                Upload.theFile(new File(data.get("")).toPath()).to((WebElement) By.name("")),
                Click.on(By.xpath("//button[contains(text(), 'Save')]"))
        );
    }

    @Then("the candidate should be listed under the Recruitment section")
    public void theCandidateShouldBeListedUnderTheRecruitmentSection(String candidateName)
    {
        user.should(seeThat(WebElementQuestion.the
                (By.xpath("//td[contains(text(), '" + candidateName + "')]")),
                WebElementStateMatchers.isVisible())
        );
    }

    @Then("the candidate \\{string} should be listed under the Recruitment section with status")
    public void theCandidateStringShouldBeListedUnderTheRecruitmentSectionWithStatus(String candidateName, String status)
    {
        user.should(
                seeThat(WebElementQuestion.the(By.xpath("//td[contains(text(),'" + candidateName + "')]")), WebElementStateMatchers.isVisible()),
                seeThat(WebElementQuestion.the(By.xpath("//td[contains(text(), '" + status + "')]")), WebElementStateMatchers.isVisible())
        );
    }
}
