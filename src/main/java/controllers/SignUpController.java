package controllers;


import enums.RedirectPath;
import model.User;
import service.UserService;
import service.ValidationService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static enums.SessionAttributeNew.AUTHENTICATED;

@WebServlet(name = "SignUpController", urlPatterns = "/signUp")
public class SignUpController extends HttpServlet {

    private static UserService userService;
    private static ValidationService validationService;  // = ValidationServiceImpl();
    private ServletContext sc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
        userService = (UserService) sc.getAttribute("userService");
        validationService = (ValidationService) sc.getAttribute("validationService");
        // sc.setAttribute("users", new ArrayList<>());
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());  // "/cargoDemoServlet_war_exploded/index.jsp");
        forwardResponse(determineUrl(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());
        RequestCustomer customer = RequestCustomer.fromRequestParameters(request);
        customer.setAsRequestAttributes(request);
        List<String> violations = customer.validate();
        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
        } else {
            request.getSession().setAttribute(AUTHENTICATED.getValue(), userService.getByLogin(customer.login));
        }

        String url = determineUrl(violations);
        forwardResponse(url, request, response);
    }
/*
  String language = req.getParameter(RequestParameter.LANGUAGE.getValue());
   int language1= Integer.parseInt(language);

            if (cityService.checkAddCityParameters(name, language1)) {
                req.setCharacterEncoding("UTF-8");
                cityService.addNewCity(name ,language1);
            }
 */

    private String determineUrl(List<String> violations) {
        if (!violations.isEmpty()) {
            return RedirectPath.SIGNUP_PAGE.getValue(); //"/WEB-INF/views/signUp.jsp";
        } else {

            return RedirectPath.FIRST_PAGE.getValue(); //"/";
        }
    }

    private String determineUrl() {
        return RedirectPath.SIGNUP_PAGE.getValue(); // "/WEB-INF/views/signUp.jsp";

    }


    private void forwardResponse(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(url).forward(request, response);        // resp.sendRedirect(RedirectPath.AUTH_PAGE.getValue());

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class RequestCustomer {

        private final String firstName;
        private final String lastName;
        private final String email;
        private final String login;
        private final String pass;
        private final String pass2;
        private final String language;

        private RequestCustomer(String firstName, String lastName, String email, String login, String pass, String pass2, String language) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.login = login;
            this.pass = pass;
            this.pass2 = pass2;
            this.language = language;
        }

        public static RequestCustomer fromRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException {
            request.setCharacterEncoding("UTF-8");

            return new RequestCustomer(

                    request.getParameter("firstname"),
                    request.getParameter("lastname"),
                    request.getParameter("email"),
                    request.getParameter("login"),
                    request.getParameter("pass"),
                    request.getParameter("pass2"),
                    request.getParameter("language"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) throws UnsupportedEncodingException {
            request.setCharacterEncoding("UTF-8");
            request.setAttribute("firstname", firstName);
            request.setAttribute("lastname", lastName);
            request.setAttribute("email", email);
            request.setAttribute("login", login);
            request.setAttribute("pass", pass);
            request.setAttribute("pass2", pass2);
            request.setAttribute("language", language);
        }

        public List<String> validate() {
            List<String> violations = new ArrayList<>();
            if (!validationService.stringValidate(firstName)) {
                violations.add("First Name is mandatory");
            }

            if (!validationService.stringValidate(lastName)) {
                violations.add("Last Name is mandatory");
            }

            if (!validationService.emailValidate(email)) {
                violations.add("Email must be a well-formed address");
            }

            if (!validationService.passwordValidate(pass, pass2)) {
                violations.add("Password not null and repeating");
            }

            if (!validationService.loginValidate(login)) {
                violations.add("Login is not correct! Only letters and numbers");
            }

            if (userService.getByLoginStr(login) != null) {
                violations.add("Such login exist! Enter other login");
            }

            if (violations.isEmpty()) {
                User user = userService.addNewUser(login, pass, firstName, lastName, email, language);   //String login, String pass, String name, String lastName, String email
                if (user == null) {
                    violations.add("User not created. Call administrator!");
                }
            }
            return violations;
        }
    }
}
