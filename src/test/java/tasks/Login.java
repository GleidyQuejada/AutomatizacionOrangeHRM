package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import org.openqa.selenium.By;
import utils.Tasks;


public class Login implements Task
{
    private final String username;
    private final String password;


    public Login (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public static Login withCredentials(String username, String password)
    {
        return Tasks.instrumented(Login.class,username,password);
    }

    @Override
    public <T extends Actor> void performAs (T actor)
    {
        actor.attemptsTo(
                Enter.theValue(username).into(By.name("username")),
                Enter.theValue(password).into(By.name("password")),
                Click.on(By.xpath("//button[@type='submit']"))
        );
    }

}
