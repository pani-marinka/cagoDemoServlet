package controllers;


import enums.RedirectPath;
import enums.RequestParameter;
import model.User;
import service.ServiceDistanace;
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

//@WebServlet(name = "SignInController", urlPatterns = "/signIn")
@WebServlet(name = "CalculationController", urlPatterns = "/calculation")
public class CalculationController extends HttpServlet {

    private static UserService userService;
    public static ServiceDistanace serviceDistanace;
    private static ValidationService validationService;  // = ValidationServiceImpl();
    private ServletContext sc;
    List<String> violations = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
        userService = (UserService) sc.getAttribute("userService");
        serviceDistanace = (ServiceDistanace) sc.getAttribute("serviceDistanace");
        validationService = (ValidationService) sc.getAttribute("validationService");
        // sc.setAttribute("users", new ArrayList<>());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());
        request.setAttribute("cityTo",
                serviceDistanace.getCitiesRouters());
        request.setAttribute("cityFrom",
                serviceDistanace.getCitiesRouters());
        HttpSession httpSession = request.getSession();
        if (request.getParameter(RequestParameter.COUNT.getValue()) != null) {
            CalculationController.RequestCustomer customer = CalculationController.RequestCustomer.fromRequestParameters(request);
            customer.setAsRequestAttributes(request);
            List<String> violations = customer.validate();
            if (!violations.isEmpty()) {
                request.setAttribute("violations", violations);
            } else {
                //User user = userService.getByLogin(customer.wide);
             //   request.getSession().setAttribute(AUTHENTICATED.getValue(), user);
                //HttpSession httpSession = request.getSession();
                //httpSession.setAttribute("login", user.getLogin());
               // httpSession.setAttribute("locale", servicerDistance.);
            }


        }


        request.getRequestDispatcher("/WEB-INF/views/calculation.jsp").forward(request, response);
        //request.getRequestDispatcher(RedirectPath.CALCULAT_PAGE.getValue()).forward(request, response);
        // request.getRequestDispatcher(RedirectPath.CALCULATION_PAGE.getValue()).forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());
        CalculationController.RequestCustomer customer = CalculationController.RequestCustomer.fromRequestParameters(request);
        customer.setAsRequestAttributes(request);
        List<String> violations = customer.validate();
        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
        } else {
            User user = userService.getByLogin(customer.wide);
            request.getSession().setAttribute(AUTHENTICATED.getValue(), user);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("login", user.getLogin());
            httpSession.setAttribute("locale", userService.getLocaleUser(user));
        }
        forwardResponse(violations, request, response);
    }


    private static class RequestCustomer {

        private final String hight;
        private final String wide;
        private final String place;
        private final String lenght;


        public RequestCustomer(String hight, String wide, String place, String lenght) {
            this.hight = hight;
            this.wide = wide;
            this.place = place;
            this.lenght = lenght;
        }

        public static CalculationController.RequestCustomer fromRequestParameters(HttpServletRequest request) throws UnsupportedEncodingException {
            request.setCharacterEncoding("UTF-8");

            return new CalculationController.RequestCustomer(

                    request.getParameter("hight"),
                    request.getParameter("wide"),
                    request.getParameter("place"),
                    request.getParameter("lenght"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) throws UnsupportedEncodingException {
            request.setCharacterEncoding("UTF-8");

            request.setAttribute("hight", hight);
            request.setAttribute("wide", wide);
            request.setAttribute("lenght", lenght);
            request.setAttribute("place", place);


        }

        public List<String> validate() {
            List<String> violations = new ArrayList<>();


            if (!validationService.loginValidate(hight)) {
                violations.add("Login is not correct! Only letters and numbers");
            }

            if (hight == null || wide.isEmpty()) {
                violations.add("Password not null");
            }

            if (userService.getByLoginStr(wide) == null) {
                violations.add("Such login does not exist! Enter other login or register");
            }

            if (violations.isEmpty()) {
                boolean checkPass = userService.checkUserPassword(userService.getByLogin(wide), lenght);
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
            response.sendRedirect(RedirectPath.TOMAIN_PAGE.getValue());
        }
    }
}
