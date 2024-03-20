package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/resources/features/APISteps.feature",
    glue = "Steps",
    tags = "@API" 
    // AGREGAR ESTE PLUGIN PARA GENERAR LOS REPORTES pluggin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", json:target/cucumber-reports.json}
)

public class Runner {
    
}

//borre <scope>test</scope> de cucumber-junit
