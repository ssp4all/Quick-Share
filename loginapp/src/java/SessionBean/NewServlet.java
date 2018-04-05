package SessionBean;

// program : File transfer using TCP protocol
// authoe : Suraj S. Pawar
// server sidepackage SessionBean;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
 
/**
 *
 * @author ssp
 */
public class NewServlet extends HttpServlet {
    @EJB
    private NewSessionBeanLocal newSessionBean;
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
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            
            String a = (request.getParameter("uname"));
            String b = (request.getParameter("pass"));
            if (newSessionBean.login(a, b) == 1) {
                out.println("<div>Login is successful</div>");
            
                out.print("ready to file transfer...!");
                
                FileInputStream fileIS;
                DatagramSocket clientSocket;
                InetAddress ipAddress;
                byte[] sendData;
                DatagramPacket sendPacket;
                File file = new File("/home/ssp/nature.txt");
                fileIS = new FileInputStream("/home/ssp/nature.txt");
                clientSocket = new DatagramSocket();
                ipAddress = InetAddress.getByName("localhost");
                sendData = new byte[1024];
                out.println("Sending...");
                int bytesRead = fileIS.read(sendData);
                while (bytesRead != -1) 
                {
                    sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);
                    clientSocket.send(sendPacket);
                    bytesRead = fileIS.read(sendData);
                }
                out.println(file.length() + " total bytes sent.");
                out.println("...done");
                fileIS.close();
                clientSocket.close();
             }
            
            else {
                    out.println("Login Failed");
                }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
