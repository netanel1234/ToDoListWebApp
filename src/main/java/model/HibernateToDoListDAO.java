package model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

/**
 * This class implements IToDoListDAO. It's use Hibernate. 
 * This class implements the DAO pattern. Every communication with the data base, done from here. 
 * To ensure there's only one instance of SesseinFactory class, it's also implements Singleton pattern.  
 */
public class HibernateToDoListDAO implements IToDoListDAO{
	
	private static HibernateToDoListDAO dao=null;		
	private SessionFactory factory=null;
	
	/**
	 * constructor
	 */
	private HibernateToDoListDAO()
	{
		factory=new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	/**
	 * Singleton pattern - get instance of HibernateToDoListDAO
	 * @return instance of HibernateToDoListDAO
	 */
	public static HibernateToDoListDAO getInstance()
	{
		if(dao==null)
			dao=new HibernateToDoListDAO();
		return dao;
	}

	/**
	 * Adding task to the Items table
	 */
	@Override
	public void addItem(Item item) throws ToDoListException 
	{
		Session session=factory.openSession();
		Transaction transaction=null;
		try
		{
			transaction=session.beginTransaction();
			session.save(item);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with adding the item.....", e);
		}
		finally
		{
			session.close();
		}
	}

	/**
	 * Deleting task from Items table
	 */
	@Override
	public void deleteItem(Item item) throws ToDoListException 
	{
		Session session=factory.openSession();
		Transaction transaction=null;
		try
		{
			transaction=session.beginTransaction();
			session.delete(item);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with deleting the item.....", e);
		}
		finally
		{
			session.close();
		}
	}
	
	/**
	 * get user id by username and password
	 * @param username 
	 * @param password
	 * @return user id as String
	 * @throws ToDoListException
	 */
	public String getUserIdByUsernameAndPassword(String username,String password) throws ToDoListException
	{
		Session session=factory.openSession();
		Transaction transaction=null;
		String id=" ";
		List<User> items=null;
		try
		{
			transaction=session.beginTransaction();
			items=session.createQuery("FROM User U WHERE U.password="+password).list();
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with getUserIdByUsernameAndPassword.....", e);
		}
		finally
		{
			session.close();
		}
		User[] arr=new User[1];
		items.toArray(arr);
		id=String.valueOf(arr[0].getUserid());
		return id;
	}
	
	/**
	 * get the text of the task by serial number of the task 
	 * @param serial - serial number of the task 
	 * @return - the text of the task
	 * @throws ToDoListException
	 */
	public String getStringTask(int serial) throws ToDoListException 
	{
		Session session=factory.openSession();
		Transaction transaction=null;
		String task=" ";
		List<Item> items=null;
		try
		{
			transaction=session.beginTransaction();
			items=session.createQuery("FROM Item I WHERE I.serialnumber="+serial).list();
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with deleting the item.....", e);
		}
		finally
		{
			session.close();
		}
		Item[] arr=new Item[1];
		items.toArray(arr);
		task=arr[0].getTask();
		return task;
	}

	/**
	 * change the text of a task
	 */
	@Override
	public void updateItem(Item item) throws ToDoListException 
	{
		Session session=factory.openSession();
		Transaction transaction=null;
		try
		{
			transaction=session.beginTransaction();
			session.update(item);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with updating the item.....", e);
		}
		finally
		{
			session.close();
		}
	}

	/**
	 * get array of task using user id
	 */
	@Override
	public Item[] getItems(int id) throws ToDoListException 
	{
		Session session=factory.openSession();
		Criteria criteria=session.createCriteria(Item.class);
		List<Item> list=null;
		Transaction transaction=null;
		try
		{
			transaction=session.beginTransaction();
			list=criteria.add(Restrictions.eq("userid", id)).list();
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with displaying (getItems()) the items.....", e);
		}
		finally
		{
			session.close();
		}
		Item[] items=new Item[list.size()];
		list.toArray(items);
		return items;
	}

	/**
	 * add user to USERS table
	 */
	@Override
	public int addUser(User user) throws ToDoListException 
	{
		Session session=factory.openSession();
		Transaction transaction=null;
		int id;
		try
		{
			transaction=session.beginTransaction();
			session.save(user);
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with adding the item.....", e);
		}
		finally
		{
			session.close();
		}
		return user.getUserid();
	}

	/**
	 * get all users in the data base
	 */
	@Override
	public User[] getUsers() throws ToDoListException 
	{
		Session session=factory.openSession();
		List<User> list=null;
		Transaction transaction=null;
		try
		{
			transaction=session.beginTransaction();
			list=session.createQuery("from User").list();
			transaction.commit();
		}
		catch(HibernateException e)
		{
			transaction.rollback();
			throw new ToDoListException("problen with displaying (getUsers()) the users.....", e);
		}
		finally
		{
			session.close();
		}
		User[] users=new User[list.size()];
		list.toArray(users);
		return users;
	}

}
