import java.sql.*;
import java.util.Properties;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Insert")
public class Insert extends HttpServlet{
    public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();
	res.setContentType( "text/html" );
    String table = req.getParameter("table");

    Connection con=null;
	try{
	    Statement stmt;
		
	    // enregistrement du driver
	    Class.forName("org.postgresql.Driver");
		
	    // connexion à la base
	    String url = "jdbc:postgresql://psqlserv/da2i";
	    String nom = "alaerm";
	    String mdp = "moi";
	    con = DriverManager.getConnection(url,nom,mdp);	
	
	    stmt = con.createStatement();
        
	    String query = "select * from "+table+";";
	    ResultSet rs = stmt.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int nbCols = rsmd.getColumnCount();
        String inser = "insert into "+table+" values (";

        for(int i=1; i<=nbCols; i++){
            if(i!=nbCols){
                inser+="'"+req.getParameter(rsmd.getColumnName(i))+"',";
            }else{
                inser+="'"+req.getParameter(rsmd.getColumnName(i))+"');";
			}
        }
        
        stmt.executeUpdate(inser);
        
        res.sendRedirect("Select?table="+table);
        
	}catch(Exception e){
	    out.println(e.getMessage());
	}
        finally{
	    try{
		con.close();
	    }catch(Exception e){
		System.out.println("Error finally");
	    }
	}

    }
}