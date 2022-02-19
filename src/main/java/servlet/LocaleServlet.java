package servlet;

import enums.RedirectPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/setLocale")
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        String localeName = req.getParameter("locale");
        httpSession.setAttribute("locale", localeName);
        String ref = req.getHeader("referer");
        if (ref == null || ref.isEmpty()) ref = RedirectPath.INDEX_PAGE.getValue();
        resp.sendRedirect(ref);
    }
}






















