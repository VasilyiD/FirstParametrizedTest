package githubdocs;
import com.codeborne.selenide.Condition;
import githubdocs.data.Languages;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

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
    void GitHubDocsShouldContainAllOfInformationForGivenLanguage(
            Languages languages,
            List<String> information
    ) {
        open("https://docs.github.com/");
        $("#\\:Racr6\\:").click();
        $$("#__primerPortalRoot__ > div > div > div > ul").find(Condition.text(languages.getName())).click();
        $$x("//*[@id= 'main-content' ]/div/section[2]/div/div/div[1]/div/div[2]/ul")
                .filter(visible)
                .shouldHave(texts(information));
    }
}