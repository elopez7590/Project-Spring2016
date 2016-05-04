/**
 * 
 * @filename WebPoster.java
 * @author Anthony Cali, Edde Lopez
 */
package edu.marist.metrics_collector.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.marist.metrics_collector.database.accessor.SampleJavaSQL;

/**
 * MetricsCollector class definition and implementation.
 * 
 */
public class WebPoster extends HttpServlet {
	
    public static final String javascriptFunctions = "\\Project-Spring2016\\metrics_collector\\src\\main\\java\\edu\\marist\\metrics_collector\\WebContent\\pages";

    /** 
     * The doGet method of the servlet. <br> 
     *  
     * This method will post each row of the database to the GUI.
     *  
     * @param request 
     *            the request send by the client to the server 
     * @param response 
     *            the response send by the server to the client 
     * @throws ServletException 
     *             if an error occurred 
     * @throws IOException 
     *             if an error occurred 
     */  
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        
        doPost(request, response);  
    }  
	
    /**
     * This will post rows to the GUI.
     * 
     * @param request The HTTP request
     * @param response The HTTP response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
            //Call Chris's JSP page here
            request.getRequestDispatcher("metrics.jsp").forward(request, response);
            
            SampleJavaSQL sj = new SampleJavaSQL();
            ArrayList<String> al = sj.getAllData();
            //TODO: Transfer this data to Table
            
            /*DataTable dt = (DataTable)Session[""];
            for (String row : al)
            {
                String[] data = row.split(",");
                DataRow dr = dt.NewRow();
                dr["pidCell"] = Integer.parseInt(data[0]);
                dr["nameCell"] = data[1];
                dr["machineCell"] = data[2];
                dr["parentCell"] = Integer.parseInt(data[3]);
                dr["sizeCell"] = Long.parseLong(data[4])
                dr["dateCell"] = data[5];
                dt.Rows.Add(dr);
            }*/
            
	}
        
        public void addMetric(String s)
        {
            
        }

}
