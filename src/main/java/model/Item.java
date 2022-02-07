package model;

/**
 * This class represents task in the to-do list 
 * @author netan
 *
 */
public class Item {
	
	private int serialnumber;
	private int userid;
	private String task;
	
	public Item()
	{
		
	}
	
	public Item(int userid, String task) 
	{
		super();
		this.userid = userid;
		this.task = task;
	}
	
	public Item(int serialnumber, int userid, String task) 
	{
		super();
		this.serialnumber = serialnumber;
		this.userid = userid;
		this.task = task;
	}

	public int getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Item [serialnumber=" + serialnumber + ", userid=" + userid + ", task=" + task + "]";
	}
}
