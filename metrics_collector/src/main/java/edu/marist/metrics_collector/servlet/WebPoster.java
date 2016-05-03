/**
 * 
 * @filename WebPoster.java
 * @author Hongchao Young
 * @editor Anthony Cali, Edde Lopez
 */
package edu.marist.metrics_collector.servlet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.script.Invocable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;



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
    * 
    *
    */
    public void refreshTable()
    {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        
        try
        {
            engine.eval(Files.newBufferedReader(Paths.get(javascriptFunctions), StandardCharsets.UTF_8));
            Invocable inv = (Invocable) engine;
            String refreshScript = "function refresh()"; //may need to change this, more detail
            inv.invokeFunction("refreshScript");
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
        
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
				
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");

            SampleJavaSQL sj = new SampleJavaSQL();
            ArrayList<String> al = sj.getAllData();
            //TODO: Transfer this data to Table
            refreshTable();
	}

}
