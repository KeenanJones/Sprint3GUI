package software_masters.planner_networking;

import java.util.ArrayList;

public class Log
{
	private ArrayList<Change> theChanges;
	
	
	
	

	public Log(ArrayList<Change> theChanges)
	{

		super();
		this.theChanges = theChanges;
	}

	public ArrayList<Change> getTheChanges()
	{
	
		return theChanges;
	}

	public void setTheChanges(ArrayList<Change> theChanges)
	{
	
		this.theChanges = theChanges;
	}
	
	public void addChange(Change toAdd)
	{
		this.theChanges.add(toAdd);
	}
}
