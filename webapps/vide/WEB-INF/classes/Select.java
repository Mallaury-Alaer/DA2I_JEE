import java.sql.*;
import java.util.Properties;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Select")
public class Select extends HttpServlet{
    public void service( HttpServletRequest req, HttpServletResponse res )
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();
	res.setContentType( "text/html" );
	String param = req.getParameter("table");
	String table;
        if(param==null){
            table="table_test";
        }else{
            table=param;
        }
		
	out.println("<!doctype html>");
	out.println("<head>");
	out.println("<meta charset=\"utf-8\">");
	
	out.println("<title>Table "+ table +"</title><link rel=\"stylesheet\" href=\"../styleTable.css\"></head><body><center> ");
	out.println("<h1>Contenu de la table : "+ table +"</h1>");

        Connection con=null;
	try{
	    Statement stmt;
        int cpt=0;
		
	    // enregistrement du driver
	    Class.forName("org.postgresql.Driver");
		
	    // connexion à la base
	    String url = "jdbc:postgresql://psqlserv/da2i";
	    String nom = "alaerm";
	    String mdp = "moi";
	    con = DriverManager.getConnection(url,nom,mdp);	
	
	    stmt = con.createStatement();
	    String query = "select * from "+ table;
	    ResultSet rs = stmt.executeQuery(query);
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int nbCols = rsmd.getColumnCount();
			
	    //out.println("<TABLE BORDER=\"1\">");
	    out.println("<table border=1>");
	    
	    out.println("<TR>");
	    for(int i=1; i<=nbCols; i++){
		out.println("<TH>"+rsmd.getColumnName(i)+"</TH>");
	    }
	    out.println("</TR");
			
	    while(rs.next()){
            if(cpt%2==0)
		          out.println("<TR class=\"pair\">");
            else
                out.println("<TR class=\"impair\">");
            cpt++;
		for(int i=1; i<=nbCols; i++){
		    out.println("<TD>"+rs.getString(rsmd.getColumnName(i))+"</TD>");
		}
		out.println("</TR>");
	    }
		
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