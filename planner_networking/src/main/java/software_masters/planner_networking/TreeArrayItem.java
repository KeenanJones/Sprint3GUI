package software_masters.planner_networking;

import java.util.ArrayList;

public class TreeArrayItem
{

	PlanNode node;
	ArrayList<TreeArrayItem> kids;
	
	public TreeArrayItem(PlanNode node, ArrayList<TreeArrayItem> kids)
	{

		super();
		this.node = node;
		this.kids = kids;
	}
	
	public PlanNode getNode()
	{
	
		return node;
	}
	public void setNode(PlanNode node)
	{
	
		this.node = node;
	}
	public ArrayList<TreeArrayItem> getKids()
	{
	
		return kids;
	}
	public void setKids(ArrayList<TreeArrayItem> kids)
	{
	
		this.kids = kids;
	}
	
	
	
}
