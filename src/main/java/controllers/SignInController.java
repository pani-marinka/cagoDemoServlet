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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static enums.SessionAttributeNew.AUTHENTICATED;

@WebServlet(name = "SignInController", urlPatterns = "/signIn")
public class SignInController extends HttpServlet {

    private static UserService userService;
    private static ValidationService validationService;  // = ValidationServiceImpl();
    private ServletContext sc;
    List<String> violations = new ArrayList<>();
    private static String refSignIn = null;

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
        refSignIn = request.getHeader("referer");
        request.getRequestDispatcher(RedirectPath.SIGNIN_PAGE.getValue()).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());
        SignInController.RequestCustomer customer = SignInController.RequestCustomer.fromRequestParameters(request);
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


    private static class RequestCustomer {

        private final String login;
        private final String pass;

        private RequestCustomer(String login, String pass) {
            this.login = login;
            this.pass = pass;

        }

        public static SignInController.RequestCustomer fromRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException {
            request.setCharacterEncoding("UTF-8");

            return new SignInController.RequestCustomer(

                    request.getParameter("login"),
                    request.getParameter("pass"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) throws UnsupportedEncodingException {
            request.setCharacterEncoding("UTF-8");

            request.setAttribute("login", login);
            request.setAttribute("pass", pass);


        }

        public List<String> validate() {
            List<String> violations = new ArrayList<>();


            if (!validationService.loginValidate(login)) {
                violations.add("Login is not correct! Only letters and numbers");
            }

            if (pass == null || pass.isEmpty()) {
                violations.add("Password not null");
            }

            if (userService.getByLoginStr(login) == null) {
                violations.add("Such login does not exist! Enter other login or register");
            }

            if (violations.isEmpty()) {
                boolean checkPass = userService.checkUserPassword(userService.getByLogin(login), pass);
                if (!checkPass) {
                    violations.add("Password not correct");
                }
            }
            return violations;
        }
    }

    private void forwardResponse(List<String> violations, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!violations.isEmpty()) {
            try {
                request.getRequestDispatcher(RedirectPath.SIGNIN_PAGE.getValue()).forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
                 if (refSignIn == null || refSignIn.isEmpty()) refSignIn = RedirectPath.INDEX_PAGE.getValue();
            response.sendRedirect(refSignIn);
           //response.sendRedirect(RedirectPath.TOMAIN_PAGE.getValue());
        }
    }
}
