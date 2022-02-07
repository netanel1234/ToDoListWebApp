package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * This servlet works like router. it's get requests from the clients, 
 * and according to each request, by reflaction, creates the requested controller and sends
 * the information this controller need to activate the right action.
 * it's done by invoking the right method. 
 * once the action done it's forwarding the execution to the jsp file 
 * represents the response to the client.
 */
@WebServlet("/router/*")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static String pkg="controller";
   
    public Router() {
        super();
    }
    
	/**
	 * the method get the request, analyzing the query string, and uses reflaction to create the right
	 * controller and to invoke the right action in it.
	 * once the action done, it's forwarding the execution to the right jsp file 
	 * represents the response.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
				
		String[] pathInfoArr=request.getPathInfo().toString().split("/");
		String controller=pathInfoArr[1];
		String action=pathInfoArr[2];
		/*composing the controller name*/
		String controllerClassName=controller.substring(0,1).toUpperCase()+controller.substring(1)+"Controller";
		try
		{
			Class clazz=Class.forName(pkg+"."+controllerClassName);
			Object object=clazz.getDeclaredConstructor().newInstance();
			Method method=clazz.getMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(object,request,response);
			getServletContext().getRequestDispatcher("/"+action+".jsp").forward(request, response);
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) 
		{
			//sending the user to error message screen.
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}