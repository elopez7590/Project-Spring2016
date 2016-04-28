/**
 * 
 * @filename MetricsCollector.java
 * @author Hongchao Young
 * @editor Anthony Cali, Edde Lopez
 */
package edu.marist.metrics_collector.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.marist.metrics_collector.dao.MetricsQueryDao;

/**
 * MetricsCollector class definition and implementation.
 * 
 */
public class MetricsCollector extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2734313777055681639L;

	/** 
     * The doGet method of the servlet. <br> 
     *  
     * This method is called when a form has its tag value method equals to get. 
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
    	System.out.println("servlet started");
        doPost(request, response);  
    }  
	
	/**
	 * Handles an HTTP POST request from Plupload.
	 * 
	 * @param request The HTTP request
	 * @param response The HTTP response
    * @throws javax.servlet.ServletException
    * @throws java.io.IOException
	 */
   @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String metrics = MetricsQueryDao.getAllMetrics();
      try (PrintWriter out = response.getWriter()) {
         out.println(metrics);
         out.flush();
      }
	}

}
