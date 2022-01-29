package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Response;

import model.*;

public class UserController {
	
	private HibernateToDoListDAO dao=HibernateToDoListDAO.getInstance();
	
	public void register(HttpServletRequest request, HttpServletResponse response) 
	{
		System.out.println("register in my UserController...");
		System.out.println("request.getQueryString()->"+request.getQueryString());
		String[] queryStringArr = request.getQueryString().toString().split("=|\\&");
		for(String str: queryStringArr)
			System.out.println("<br>"+str);
		int id=-1;
		try 
		{
			id=dao.addUser(new User(queryStringArr[1],queryStringArr[3]));
			System.out.println("user id in register method->"+id);
			request.setAttribute("id", String.valueOf(id));
		} 
		catch (ToDoListException e) 
		{
			System.out.println("problem in addUser()..... ");
			e.printStackTrace();
		}
		Cookie c=new Cookie("userid",String.valueOf(id));
		c.setMaxAge(60*60*24*30);
		response.addCookie(c);
	}
	
	public void congrat(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("in method congrat() in UserController");
		System.out.println("request.getQueryString()->"+request.getQueryString());
		String[] queryStringArr = request.getQueryString().toString().split("=|\\&");
		for(String str:queryStringArr)
			System.out.println(str);
		String id=null;
		try
		{
			Cookie[] cookies=request.getCookies();
			for(Cookie c:cookies)
			{
				if(c.getName().equals("userid"))
					id=c.getValue();
			}
			dao.addItem(new Item(Integer.valueOf(id),queryStringArr[1]));
			request.setAttribute("list", dao.getItems(Integer.valueOf(id)));
		}
		catch(ToDoListException e)
		{
			System.out.println("problem in add()..... ");
			e.printStackTrace();
		}
	}

	public void add(HttpServletRequest request, HttpServletResponse response) 
	{
		System.out.println("in method add() in UserController");
		System.out.println("request.getQueryString()->"+request.getQueryString());
		String[] queryStringArr = request.getQueryString().toString().split("=|\\&");
		for(String str:queryStringArr)
			System.out.println(str);
		String id=" ";
		try
		{
			Cookie[] cookies=request.getCookies();
			for(Cookie c:cookies)
			{
				if(c.getName().equals("userid"))
					id=c.getValue();
			}
			dao.addItem(new Item(Integer.valueOf(id),queryStringArr[1]));
			request.setAttribute("list", dao.getItems(Integer.valueOf(id)));
		}
		catch(ToDoListException e)
		{
			System.out.println("problem in add()..... ");
			e.printStackTrace();
		}
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("in method delete() in UserController");
		System.out.println("request.getQueryString()->"+request.getQueryString());
		String[] queryStringArr = request.getQueryString().toString().split("=|\\&");
		for(String str:queryStringArr)
			System.out.println(str);
		String id=" ";
		try
		{
			Cookie[] cookies=request.getCookies();
			for(Cookie c:cookies)
			{
				if(c.getName().equals("userid"))
					id=c.getValue();
			}
			Item i=new Item(Integer.parseInt(queryStringArr[1]),Integer.valueOf(id),dao.getStringTask(Integer.parseInt(queryStringArr[1])));
			dao.deleteItem(i);
			request.setAttribute("list", dao.getItems(Integer.valueOf(id)));
		}
		catch(ToDoListException e)
		{
			System.out.println("problem in delete()..... ");
			e.printStackTrace();
		}
	}

	public void update(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("in method update() in UserController");
		System.out.println("request.getQueryString()->"+request.getQueryString());
		String[] queryStringArr = request.getQueryString().toString().split("=|\\&");
		for(String str:queryStringArr)
			System.out.println(str);
		String id=" ";
		try
		{
			Cookie[] cookies=request.getCookies();
			for(Cookie c:cookies)
			{
				if(c.getName().equals("usreid"))
					id=c.getValue();
			}
			Item i=new Item(Integer.parseInt(queryStringArr[1]),Integer.valueOf(id),queryStringArr[3]);
			dao.updateItem(i);
			request.setAttribute("list", dao.getItems(Integer.valueOf(id)));
		}
		catch(ToDoListException e)
		{
			System.out.println("problem in delete()..... ");
			e.printStackTrace();
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) 
	{
		System.out.println("in method login() in UserController");
		System.out.println("request.getQueryString()->"+request.getQueryString());
		String[] queryStringArr = request.getQueryString().toString().split("=|\\&");
		for(String str:queryStringArr)
			System.out.println(str);
		String id=" ";
		try
		{
			id=dao.getUserIdByUsernameAndPassword(queryStringArr[1], queryStringArr[3]);
			request.setAttribute("list", dao.getItems(Integer.valueOf(id)));
		}
		catch(ToDoListException e)
		{
			System.out.println("problem in delete()..... ");
			e.printStackTrace();
		}
		Cookie c=new Cookie("userid",String.valueOf(id));
		c.setMaxAge(60*60*24*30);
		response.addCookie(c);
	}
}