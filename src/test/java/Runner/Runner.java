package Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/java/resources/features/APISteps.feature",
    glue = "Steps",
    tags = "@API" 
)

public class Runner {
    
}

//borre <scope>test</scope> de cucumber-junit
