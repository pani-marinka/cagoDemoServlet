package enums;

public enum RequestParameter {

    LOGOFF("logoff"),
    COUNT("count"),
    RESETCITY("resetCity"),
    TYPEFROM("typeFrom"),
    TYPETO("typeTo");

    RequestParameter(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
