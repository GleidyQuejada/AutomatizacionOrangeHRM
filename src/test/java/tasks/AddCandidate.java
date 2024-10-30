package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actions.Upload;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.questions.WebElementQuestion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static utils.Tasks.instrumented;

public class AddCandidate implements Task
{
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String vacancy;
    private final String email;
    private final String contactNumber;
    private final String resumePath;

    public AddCandidate(String firstName, String middleName, String lastName, String vacancy,
                        String email, String contactNumber, String resumePath)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.vacancy = vacancy;
        this.email = email;
        this.contactNumber = contactNumber;
        this.resumePath = resumePath;
    }

    public static AddCandidate withDetails(String firstName, String middleName, String lastName, String vacancy,
                                           String email, String contactNumber, String resumePath)
    {
        return instrumented(AddCandidate.class, firstName, middleName, lastName, vacancy, email, contactNumber, resumePath);
    }


    @Override
    public <T extends Actor> void performAs(T actor)
    {

        actor.attemptsTo(

                Click.on(By.linkText("Recruitment")),
                Click.on(By.xpath("//button[contains(text(), '+ Add')]")),

                Enter.theValue(firstName).into(By.name("firstName")),
                Enter.theValue(middleName).into(By.name("middleName")),
                Enter.theValue(lastName).into(By.name("lastName")),
                SelectFromOptions.byVisibleText(vacancy).from(By.id("vacancy")),
                Enter.theValue(email).into(By.name("email")),
                Enter.theValue(contactNumber).into(By.name("contactNumber")),
                Upload.theFile(new File(resumePath).toPath()).to((WebElement) By.name("resume")),

                Click.on(By.xpath("//button[contains(text(), 'Save')]"))

        );

                actor.should(
                        seeThat(WebElementQuestion.the(By.xpath("//td[contains(text(),'" + firstName + " " + lastName + "')]")),
                                WebElementStateMatchers.isVisible()),
                        seeThat(WebElementQuestion.the(By.xpath("//td[contains(text(), 'Hired')]")),
                                WebElementStateMatchers.isVisible())
                );

    }
}
