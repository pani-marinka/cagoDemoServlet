package listener;

import dao.UserDAO;

import factorySession.DaoMyFactorySession;
import service.UserService;
import service.UserServiceImpl;
import service.ValidationService;
import service.ValidationServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener, ServletContextAttributeListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Configure app ==> bootstrap


        UserDAO userDao = DaoMyFactorySession.getInstance().getUserDao();
        UserService userService = new UserServiceImpl(userDao);
        ValidationService validationService = new ValidationServiceImpl();
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("validationService", validationService);
        servletContext.setAttribute("language", "1"); // En default
       // System.out.println(sce.getServletContext().getAttribute("userService"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Destroy resources
    }

}
