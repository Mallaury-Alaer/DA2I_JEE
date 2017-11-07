import java.sql.*;
import java.util.Properties;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/InitForm")
public class InitForm extends HttpServlet{
    public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();
	res.setContentType( "text/html" );
	String table;
    String param = req.getParameter("table");
    if(param==null){
        table="table_test";
    }else{
        table=param;
    }

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
        out.println("<!doctype html>");
	   out.println("<head>");
	   out.println("<meta charset=\"utf-8\">");
	
	   out.println("<title>Saisie "+ table +"</title><link rel=\"stylesheet\" href=\"../styleTable.css\"></head><body><center> ");
	   out.println("<h1>Formulaire de saisie de la table "+ table +"</h1>");
        
	    String query = "select * from "+table+";";
	    ResultSet rs = stmt.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int nbCols = rsmd.getColumnCount();
        
        out.print("<form method=\"POST\" action=\"Insert\">");
        out.print("<input type=\"hidden\" name=\"table\" value=\""+table+"\">");
        for(int i=1;i<=nbCols; i++){
            out.print("<label>"+rsmd.getColumnName(i)+"</label> : <INPUT type=\"text\" size=20 name=\""+rsmd.getColumnName(i)+"\"><br><br>");
        }
        out.print("<INPUT type=\"submit\" value=\"Valider\">");
        out.print("</form>");
        
		
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