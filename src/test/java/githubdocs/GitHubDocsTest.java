package githubdocs;
import com.codeborne.selenide.CollectionCondition;
import githubdocs.data.Languages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class GitHubDocsTest extends BaseTest {

    static Stream<Arguments> GitHubDocsLanguagesDataProvider() {
        return Stream.of(
                Arguments.of(Languages.ENGLISH, List.of("Get started", "Migrations", "Account and profile", "Authentication",
                        "Billing and payments", "Site policy")),
                Arguments.of(Languages.CHINESE, List.of("入门", "迁移", "帐户和个人资料", "身份验证",
                        "计费与付款", "站点政策")),
                Arguments.of(Languages.SPANISH, List.of("Introducción", "Migraciones", "Cuenta y perfil", "Autenticación",
                        "Facturación y pagos", "Política del sitio")),
                Arguments.of(Languages.PORTUGUESE, List.of("Introdução", "Migrações", "Conta e perfil", "Autenticação",
                        "Cobrança e pagamentos", "Política do site")),
                Arguments.of(Languages.RUSSIAN, List.of("Начать", "Миграции", "Учетная запись и профиль", "Проверка подлинности",
                        "Выставление счетов и платежи", "Политика сайта")),
                Arguments.of(Languages.JAPANESE, List.of("はじめに", "移行", "アカウントとプロファイル", "認証",
                        "課金と支払い", "サイト ポリシー")),
                Arguments.of(Languages.FRENCH, List.of("Bien démarrer", "Migrations", "Compte et profil", "Authentification",
                        "Facturation et paiements", "Politique du site")),
                Arguments.of(Languages.GERMAN, List.of("Erste Schritte", "Migrationen", "Konto und Profil", "Authentifizierung",
                        "Abrechnung und Zahlungen", "Websiterichtlinie")),
                Arguments.of(Languages.KOREAN, List.of("시작하기", "마이그레이션", "계정 및 프로필", "인증",
                        "청구 및 결제", "사이트 정책"))
        );
    }
    @MethodSource("GitHubDocsLanguagesDataProvider")
    @ParameterizedTest(name = "Для языка {0} отображается информация {1}")
    @Tag("BLOCKER")
    void gitHubDocsShouldContainAllOfInformationForGivenLanguage(
            Languages languages,
            List<String> information
    ) {
        $("[type = button] .octicon-globe").click();
        $$("[role = menu] a").find(text(languages.getName())).click();
        $$x("//*[@id= 'main-content']/div/section[2]/div/div/div[1]/div/div[2]/ul/li")
                .filter(visible)
                .shouldHave(texts(information));
    }

    @CsvSource ({
            "Документация по GitHub,Русский",
            "GitHub Docs,English"
    })
    @ParameterizedTest(name = "Надпись {0} должна отображаться при смене языка на {1}")
    @Tags({@Tag("CRITICAL"),@Tag("UI_TEST")})
    void gitHubDocsShouldContainsTitleTextForGivenLanguage(
            String titleName,
            String languageType
    ) {
        $("[type = button] .octicon-globe").click();
        $$("[role = menu] a").find(text(languageType)).click();
        $("#title-h1").shouldHave(text(titleName));
    }

    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "Надпись {0} должна отображаться при смене языка на {1}")
    @Tags({@Tag("MEDIUM"), @Tag("UI_TEST")})
    void gitHubDocsShouldContainsTitleTextForGivenLanguageFileSourceMethod( String title, String languageName) {

        $("[type = button] .octicon-globe").click();
        $$("[role = menu] a").find(text(languageName)).click();
        $("#title-h1").shouldHave(text(title));

    }

    @ValueSource(
            strings = {"Allure testops", "Selenide"}
    )
    @ParameterizedTest(name = "Адрес {1} должен быть в выдаче гугла по запросу {0}")
    //
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})
    void searchResultsCountTest(String productName) {
        $("[name=q]").setValue(productName).pressEnter();
        $$("div[class='g']").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }


}