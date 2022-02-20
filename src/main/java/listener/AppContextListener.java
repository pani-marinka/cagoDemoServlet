package listener;

import dao.DistanceDAO;
import dao.TariffCostDAO;
import dao.UserDAO;

import factorySession.DaoMyFactorySession;
import service.*;

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
        DistanceDAO distanceDao = DaoMyFactorySession.getInstance().getDistanceDao();
        TariffCostDAO tariffCostDao = DaoMyFactorySession.getInstance().getTariffCostDao();
        ServiceTariffCost serviceTariffCost = new ServiceTariffCostImpl(tariffCostDao);
        RoutCostService routCostService = new RoutCostServiceImpl(distanceDao,tariffCostDao);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("validationService", validationService);
        servletContext.setAttribute("routCostService", routCostService);
        servletContext.setAttribute("routsCost",routCostService.getAllRoutCosts());
        servletContext.setAttribute("tariffAny",serviceTariffCost.getTariffAny());
    //    servletContext.setAttribute("language", "0"); // En default
       // System.out.println(sce.getServletContext().getAttribute("userService"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
         //  servletContext.setAttribute("language", "0"); // En default
    }

}
