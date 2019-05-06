package software_masters.planner_networking;

import java.io.Serializable;

public class Comment implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4635517100749284720L;
	private Integer ID;
	private String data;
	private String author;
	private PlanNode parentNode;
	
	public Comment(Integer ID, String data, String author, PlanNode parentNode)
	{
		this.ID = ID;
		this.data = data;
		this.author = author;
		this.parentNode = parentNode;
		
	}
	
	public PlanNode getParentNode()
	{
	
		return parentNode;
	}

	public void setParentNode(PlanNode parentNode)
	{
	
		this.parentNode = parentNode;
	}

	public Integer getID()
	{
	
		return ID;
	}
	public void setID(Integer iD)
	{
	
		ID = iD;
	}
	public String getData()
	{
	
		return data;
	}
	public void setData(String data)
	{
	
		this.data = data;
	}
	public String getAuthor()
	{
	
		return author;
	}
	public void setAuthor(String author)
	{
	
		this.author = author;
	}
	
	
}
