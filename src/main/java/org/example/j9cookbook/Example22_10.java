package org.example.j9cookbook;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Example22_10 {
}
class BuzzInServlet extends HttpServlet {
    public static void main(String[] args) {
        System.out.println("ththth");
    }
    private final static String WINNER = "buzzin.winner";
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletContext application = getServletContext();
        boolean iWon = false;
        String user = request.getRemoteHost() + '@' + request.getRemoteAddr();
        synchronized (application){
            if (application.getAttribute(WINNER)==null){
                application.setAttribute(WINNER, user);
                application.log("BuzzInServlet: WINNER "+user);
                iWon = true;
            }
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Thanks for playing</title></head>");
        out.println("<body bgcoler=\"white\">");
        if(iWon){
            out.println("<b>YOU GOT IT</b>");
        }else {
            out.println("Thanks for playing, "+request.getRemoteAddr());
            out.println(", but  "+application.getAttribute(WINNER)+ " buzzed in first");
        }
        out.println("</body></html>");

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ServletContext application = getServletContext();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        boolean iWon = false;
        String user = request.getRemoteHost() + '@' + request.getRemoteAddr();
        if (request.isUserInRole("host")) {
            out.println("<html><head><title>Welcome back, " + request.getUserPrincipal().getName() + "</title><head>");
            out.println("<body bgcolor=\"white\">");
            String command = request.getParameter("command");
            if (command.equals("reset")) {
                // Synchronize what you need, no more, no less.
                synchronized (application) {
                    session.setAttribute("buzzin.message", "RESET");
                }
                application.setAttribute(WINNER, null);
            }  else if (command.equals("show")) {
                String winner = null;
                synchronized(application) {
                    winner = (String)application.getAttribute(WINNER);
                }
                if (winner == null) {
                    session.setAttribute("buzzin.message", "<b>No winner yet!</b>");
                } else {
                    session.setAttribute("buzzin.message", "<b>Winner is: </b>" + winner);
                }

            } else {
                session.setAttribute("buzzin.message", "ERROR: Command " + command + " invalid.");
            }
            RequestDispatcher rd = application.getRequestDispatcher( "/hosts/index.jsp");
            rd.forward(request, response);
        } else {
            out.println("<html><head><title>Nice try, but... </title><head>");
            out.println("<body bgcolor=\"white\">");
            out.println( "I'm sorry, Dave, but you know I can't allow you to do that.");
            out.println("Even if you are " + request.getUserPrincipal());
        }
        out.println("</body></html>");
    }

}