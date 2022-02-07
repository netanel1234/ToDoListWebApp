package model;

/**
 * IToDoListDAO interface that lists the methods through which the web application uses the database
 */
public interface IToDoListDAO {
	
	public void addItem(Item item) throws ToDoListException;
	public void deleteItem(Item item) throws ToDoListException;
	public void updateItem(Item item) throws ToDoListException;
	public Item[] getItems(int id) throws ToDoListException;
	
	public int addUser(User user) throws ToDoListException;
	public User[] getUsers() throws ToDoListException;

}
