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

@WebServlet(name = "SignInController", urlPatterns = "/signIn")
public class SignInController extends HttpServlet {

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
        SignInController.RequestCustomer customer = SignInController.RequestCustomer.fromRequestParameters(request);
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


    private String determineUrl(List<String> violations) {
        if (!violations.isEmpty()) {
            return RedirectPath.SIGNIN_PAGE.getValue(); //"/WEB-INF/views/signUp.jsp";
        } else {
            return RedirectPath.FIRST_PAGE.getValue(); //"/";
        }
    }

    private String determineUrl() {
        return RedirectPath.SIGNIN_PAGE.getValue(); // "/WEB-INF/views/signUp.jsp";
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
}
