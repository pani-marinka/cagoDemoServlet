package enums;

public enum RedirectPath {

    SIGNUP_PAGE("/WEB-INF/views/signUp.jsp"),
    MAIN_PAGE("/cargoDemoServlet_war_exploded/index.jsp"),
    FIRST_PAGE("/"),
    SIGNIN_PAGE("/WEB-INF/views/signIn.jsp"),
    ADD_PAGE("/cargoDemoServlet_war_exploded/add"),
    DETAIL_PAGE("/cargoDemoServlet_war_exploded/detail");

    private RedirectPath(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
