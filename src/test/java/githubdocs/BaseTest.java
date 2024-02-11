package githubdocs;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {

    @BeforeAll
    public static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        open("https://docs.github.com/");

    }
}