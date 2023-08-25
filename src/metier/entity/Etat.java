package metier.entity;

public enum Etat {
    PERDU("perdu"),
    RENDU("rendu"),
    TOUS("tous"),
    NULL(null);

    private final String value;

    Etat(String value) {
        this.value = value;
    }

    String getValue() { return value; }

    @Override
    public String toString() {
        return value;
    }
}
