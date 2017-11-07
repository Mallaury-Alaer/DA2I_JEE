import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Hello")
public class Hello extends HttpServlet{
	public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		res.setContentType( "text/html" );
		out.println( "<head><title>Select</title></head><body><center>" );
		out.println( "<h2>Hello "+req.getParameter("nom")+"</h2>" );
		out.println( "</center> </body>" );
	}
}