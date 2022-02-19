package controllers;


import enums.RedirectPath;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import service.ValidationService;
import utils.VerifyRecaptcha;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static enums.SessionAttributeNew.AUTHENTICATED;
import static java.lang.Integer.parseInt;

@WebServlet(name = "SignUpController", urlPatterns = "/signUp")
public class SignUpController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(SignUpController.class.getName());

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
        request.setAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());
        request.getRequestDispatcher(RedirectPath.SIGNUP_PAGE.getValue()).forward(request, response);


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
            User user = userService.getByLogin(customer.login);
            request.getSession().setAttribute(AUTHENTICATED.getValue(), user);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("login", user.getLogin());
            httpSession.setAttribute("locale", userService.getLocaleUser(user));
        }
        forwardResponse(violations, request, response);
    }

    private String determineUrl(List<String> violations) {
        if (!violations.isEmpty()) {
            return RedirectPath.SIGNUP_PAGE.getValue(); //"/WEB-INF/views/signUp.jsp";
        } else {

            return RedirectPath.FIRST_PAGE.getValue(); //"/";
        }
    }


    private void forwardResponse(List<String> violations, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!violations.isEmpty()) {
            try {
                request.getRequestDispatcher(RedirectPath.SIGNUP_PAGE.getValue()).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect(RedirectPath.TOMAIN_PAGE.getValue());
        }
    }

    private static class RequestCustomer {

        private final String firstName;
        private final String lastName;
        private final String email;
        private final String login;
        private final String pass;
        private final String pass2;
        private int language;
        String gRecaptchaResponse ;

        private RequestCustomer(String firstName, String lastName, String email, String login, String pass, String pass2, int language, String gRecaptchaResponse) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.login = login;
            this.pass = pass;
            this.pass2 = pass2;
            this.language = language;
            this.gRecaptchaResponse = gRecaptchaResponse;
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
                    Integer.parseInt(request.getParameter("language")),
                    request.getParameter("g-recaptcha-response"));
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

        public List<String> validate() throws IOException {
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

          //  boolean result = VerifyRecaptcha.verify(gRecaptchaResponse);
           if(!VerifyRecaptcha.verify(gRecaptchaResponse)){
               violations.add("You missed the Captcha!");
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
