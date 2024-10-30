package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(

        features = "src/test/resources/features",
        glue = "src/test/java/stepdefinitions",
        plugin = {"pretty"},
        tags = "@Login"
)

public class LoginTest
{
}
