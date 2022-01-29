package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

public class HibernateToDoListDAO implements IToDoListDAO{
	
	private static HibernateToDoListDAO dao=null;		
	private SessionFactory factory=null;
	
	private HibernateToDoListDAO()
	{
		factory=new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public static HibernateToDoListDAO getInstance()
	{
		if(dao==null)
			dao=new HibernateToDoListDAO();
		return dao;
	}

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

/*
public class HibernateToDoListDAO<T> implements IToDoListDAO<T> {

    @Override
    public void add(T ob) throws ToDoListException {
        Session session = factory.openSession();
        try {
        	// preparing session to "work"
            session.beginTransaction();
            session.save(ob);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
            	//not to lose data
                session.getTransaction().rollback();
            }
            throw new ToDoListException("Cant add the item into the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
    }

    @Override
    public void update(T ob) throws ToDoListException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.update(ob);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("Cant update the item in the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
    }

    @Override
    public void delete(T ob) throws ToDoListException {
        Session session = factory.openSession();
        try {
            session.beginTransaction();
            session.delete(ob);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("Cant delete the item from the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
    }

    /**
     * get all items from the DB of specific user with specific id
     *
     * @param id
     * @return array of items
     * @throws ToDoListException
     *
    @Override
    public Item[] getItems(Long id) throws ToDoListException {
        Session session = factory.openSession();
        //get with criteria to return every object that corresponds to the Item class
        Criteria criteria = session.createCriteria(Item.class);
        Item[] items = null;
        try {
            session.beginTransaction();
            // get items of specific user
            List<Item> itemList = criteria.add(Restrictions.eq("userID", id)).list();
            items = new Item[itemList.size()];
            items = itemList.toArray(items);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("cant get the items from the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
        return items;
    }

    /**
     * get a user from the DB
     *
     * @param id
     * @return User object
     * @throws ToDoListException
     *
    @Override
    public User getUser(Long id) throws ToDoListException {
        Session session = factory.openSession();
        User user = null;
        try {
            session.beginTransaction();
            user = (User) session.get(User.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new ToDoListException("cant get the items from the DB", e);
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.getMessage();
            }
        }

        return user;
    }

}

 */
