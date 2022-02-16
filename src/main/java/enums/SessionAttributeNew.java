package enums;

public enum SessionAttributeNew {
    AUTHENTICATED("authenticated");

    private String value;

    SessionAttributeNew(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
