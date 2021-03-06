package servlet;


import enums.RedirectPath;
import enums.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/view")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter(RequestParameter.LOGOFF.getValue()) != null) {
            req.getSession().invalidate();
        }
        resp.sendRedirect(RedirectPath.MAIN_PAGE.getValue());
    }

}