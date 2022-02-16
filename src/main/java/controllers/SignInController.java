package controllers;


import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SignInController", urlPatterns = "/signIn")
public class SignInController extends HttpServlet {

    private UserService service;
    private ServletContext sc;

    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
        service = (UserService) sc.getAttribute("userService");
        sc.setAttribute("users", new ArrayList<>());
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestCustomer customer = RequestCustomer.fromRequestParameters(request);
        customer.setAsRequestAttributes(request);
               String firstLogin = service.getByLogin("mnp").getLogin();
        System.out.println(firstLogin);
               request.setAttribute("firstname", firstLogin);
        //System.out.println("firstLogin = " + firstLogin);

        List<String> violations = customer.validate();

        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
        }

        String url = determineUrl(violations);
        forwardResponse(url, request, response);
      //  response.sendRedirect("/WEB-INF/views/customerinfo.jsp");
        //.sendRedirect(RedirectPath.MAIN_PAGE.getValue());
    }

    /*
        String login    = req.getParameter(RequestParameter.LOGIN.getValue());
        String pass1    = req.getParameter(RequestParameter.PASS1.getValue());
        String pass2    = req.getParameter(RequestParameter.PASS2.getValue());
        String name     = req.getParameter(RequestParameter.NAME.getValue());
        String lastname = req.getParameter(RequestParameter.LASTNAME.getValue());

        String email    = req.getParameter(RequestParameter.EMAIL.getValue());
        int race        =  Integer.parseInt(req.getParameter(RequestParameter.RACE.getValue()));

        if (validationService.validateRegistration(login, pass1, pass2)) {
             userService.addNewUser(login, pass1, name, lastname , race, email);
            req.getSession().setAttribute(AUTHENTICATED.getValue(), userService.getByLogin(login));
            userOnlineService.add(userService.getByLogin(login));
            resp.sendRedirect(RedirectPath.MAIN_REDIRECT.getValue());
            }
        UserStatus status = UserStatus.PASSWORD_FIELDS_MISMATCH;
        out.addObject("status", status.getValue());
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        out.addObject("races", raceService.getRaces());
        return out;
     */


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = determineUrl();
        forwardResponse(url, request, response);
      //response.sendRedirect("/WEB-INF/views/signInfo.jsp");
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        PrintWriter out = resp.getWriter();
//        out.println(getAddPage("Title.AUTHENTICATION.getValue()"));
//    }

//          String getAddPage(String title) {
//        StringBuilder out = new StringBuilder();
//        out.append(getHead(title));
//
//        out.append(getEnd());
//        return out.toString();
    private String getEnd() {
        return "</body></html>";
    }
    private String getHead(String title) {
        StringBuilder out = new StringBuilder();
        out.append("<html><head><title>");
        out.append(title);
        out.append("</title>");
        out.append("<style type='text/css'>" +
                "TABLE {border-collapse: collapse;}" +
                "TD, TH {padding: 3px;border: 1px solid black;}" +
                "TH {background: #b0e0e6;}</style>");
        out.append("</head><body>");
        return out.toString();
    }


    private String determineUrl(List<String> violations) {
            return "/WEB-INF/views/signInfo.jsp";

    }

    private String determineUrl() {
        return "/WEB-INF/views/signInfo.jsp";

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

        private RequestCustomer(String firstName, String lastName, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        public static RequestCustomer fromRequestParameters(HttpServletRequest request) {
            return new RequestCustomer(
                    request.getParameter("firstname"),
                    request.getParameter("lastname"),
                    request.getParameter("email"));
        }

        public void setAsRequestAttributes(HttpServletRequest request) {
            request.setAttribute("firstname", firstName);
            request.setAttribute("lastname", lastName);
            request.setAttribute("email", email);
        }

        public List<String> validate() {
            List<String> violations = new ArrayList<>();

            return violations;
        }
    }
}
