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
 * Servlet implementation class Router
 */
@WebServlet("/router/*")
public class Router extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static String pkg="controller";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
        
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		System.out.println("we are in the Router...");		
		PrintWriter out=response.getWriter();
				
		String[] queryStringArr=request.getQueryString().toString().split("=|\\&");
		System.out.println("printing the query string...");
		for(String str: queryStringArr)
			System.out.println("<br>"+str);
		
		String[] pathInfoArr=request.getPathInfo().toString().split("/");
		System.out.println("printing the path info...");
		for(String str:pathInfoArr)
			System.out.println("<br>"+str);
		
		String controller=pathInfoArr[1];
		String action=pathInfoArr[2];
		//composing the controller name
		String controllerClassName=controller.substring(0,1).toUpperCase()+controller.substring(1)+"Controller";
		System.out.println("<br>"+"controller name->"+controllerClassName);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}