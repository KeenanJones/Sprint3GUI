package software_masters.planner_networking;

public class Change
{
	private String author;
	private PlanNode section;
	private String action;
	
	
	public Change(String name, PlanNode section, String action)
	{

		super();
		this.author = name;
		this.section = section;
		this.action = action;
	}
	public String getAuthor()
	{
	
		return author;
	}
	public void setAuthor(String author)
	{
	
		this.author = author;
	}
	public PlanNode getSection()
	{
	
		return section;
	}
	public void setSection(PlanNode section)
	{
	
		this.section = section;
	}
	public String getAction()
	{
	
		return action;
	}
	public void setAction(String action)
	{
	
		this.action = action;
	}
	
	
}
