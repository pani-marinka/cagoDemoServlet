package enums;

public enum RequestParameter {
    LOGIN("login"),
    PASS("pass"),
    PASS1("pass1"),
    PASS2("pass2"),
    LASTNAME("lastname"),
    EMAIL("email"),
    NAME("name"),
    DESCR("descr"),
    PICTYPE("pictype"),
    RACE("race"),
    LOGOFF("logoff"),
    PIC("pic");


    RequestParameter(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
