package software_masters.planner_networking;

public class Comment
{
	private Integer ID;
	private String data;
	private String author;
	
	public Comment(Integer ID, String data, String author)
	{
		this.ID = ID;
		this.data = data;
		this.author = author;
		
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
