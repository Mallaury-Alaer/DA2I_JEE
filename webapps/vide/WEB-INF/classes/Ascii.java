import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Ascii")
public class Ascii extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");
        out.println("<head><title>Ascii</title><link rel=\"stylesheet\" href=\"../styleAscii.css\"></head>");
        out.println("<body><center><h1>Table Ascii</h1>");
        
        out.println("<TABLE BORDER=\"1\">");
		
		int i = 32;
		while(i<=255){
            if(i%2==0)
			     out.println("<TR class=\"pair\">");
            else
                out.println("<TR class=\"impair\">");
            out.print("<TD>"+i+"</TD><TD>"+(char)i+"</TD> \n");
            i++;

			out.println("</TR>");
		}
        
        out.println("</TABLE>");
        out.println("</center></body>");    
    }
}