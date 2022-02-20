package servlet;

import enums.RedirectPath;
import enums.RequestParameter;
import service.RoutCostService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/setType")
public class SortCityServlet extends HttpServlet {

    private static RoutCostService routCostService;
    private ServletContext sc;


    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
       routCostService = (RoutCostService) sc.getAttribute("routCostService");
       //validationService = (ValidationService) sc.getAttribute("validationService");
        // sc.setAttribute("users", new ArrayList<>());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        //typeFrom typeTo
        if ( req.getParameter(RequestParameter.TYPEFROM.getValue()) != null )
        {
            httpSession.setAttribute("routsCost",
                    routCostService.getRoutsByCityFrom(req.getParameter(RequestParameter.TYPEFROM.getValue())));

        }

            if ( req.getParameter(RequestParameter.TYPETO.getValue()) != null ) {
                httpSession.setAttribute("routsCost",
                        routCostService.getRoutsByCityTo(req.getParameter(RequestParameter.TYPETO.getValue())));
            }

        if ( req.getParameter(RequestParameter.RESETCITY.getValue()) != null ) {
            httpSession.setAttribute("routsCost", routCostService.getAllRoutCosts());
        }


        String ref = req.getHeader("referer");
        if (ref == null || ref.isEmpty()) ref = RedirectPath.INDEX_PAGE.getValue();
        resp.sendRedirect(ref);
    }

}






















