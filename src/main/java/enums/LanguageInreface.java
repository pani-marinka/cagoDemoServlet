package enums;

public enum LanguageInreface {
    en(0),
    ukr(1);

    private LanguageInreface(int value) {
        this.value = value;
    }

    private int value;

    public int getValue() {
        return value;
    }
}
