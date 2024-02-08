package githubdocs.data;

public enum Languages {

    ENGLISH ("English"),
    CHINESE("简体中文"),
    SPANISH("Español"),
    PORTUGUESE("Português do Brasil"),
    RUSSIAN ("Русский"),
    JAPANESE("日本語"),
    FRENCH("Français"),
    GERMAN("Deutsch"),
    KOREAN("한국어");

    private String name;

    Languages(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}