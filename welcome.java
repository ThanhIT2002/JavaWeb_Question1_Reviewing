/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 * @author Thành Nguyễn
 */
@WebServlet(urlPatterns = {"/welcome"})
public class welcome extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet welcome</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet welcome at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean Test = (Boolean)session.getAttribute("TestLogged");
        
        if(Test){
            try {
               Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/onthi", "root", "");
                PreparedStatement statement = connect.prepareStatement("SELECT * FROM Product");
                ResultSet rs = statement.executeQuery();
               
                request.setAttribute("ListProduct", rs);
                request.getRequestDispatcher("display.jsp").forward(request, response);

              
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            response.sendRedirect("login.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String action = request.getParameter("action");
      request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
        if ("Total".equals(action)) {
            try {
                // Thực hiện tính tổng
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/onthi", "root", "");
                PreparedStatement stSum = connect.prepareStatement("SELECT SUM(Quantity * Price) AS total FROM Product");
                ResultSet rsSum = stSum.executeQuery();

                if (rsSum.next()) {
                    double total = rsSum.getDouble("total");
                    request.setAttribute("total", total);
                }
                PreparedStatement statement = connect.prepareStatement("SELECT * FROM Product");
                ResultSet rs = statement.executeQuery();
               
                request.setAttribute("ListProduct", rs);
                request.getRequestDispatcher("display.jsp").forward(request, response);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if ("search".equals(action)) {
            try {
                // Thực hiện tìm kiếm
                    String productName = (String)request.getParameter("search");
                  
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/onthi", "root", "");
                    PreparedStatement statement = connect.prepareStatement("SELECT * FROM Product WHERE Name LIKE ?");
                    statement.setString(1, "%" + productName + "%");
                    ResultSet rs = statement.executeQuery();

                    request.setAttribute("ListProduct", rs);
                    request.setAttribute("search", productName);
                    request.getRequestDispatcher("display.jsp").forward(request, response);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(welcome.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
           
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
