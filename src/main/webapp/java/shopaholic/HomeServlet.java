// HomeServlet.java
package main.webapp.java.shopaholic;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


//@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
   
    // Public no-argument constructor
    public HomeServlet() {
        // Optional: You can initialize any resources or perform setup tasks here
    }
    
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        JDBC product = HomeController.getProduct();
//        req.setAttribute("product", product);
        req.getRequestDispatcher("./WEB-INF/views/home.jsp").forward(req, resp);
    }
}
