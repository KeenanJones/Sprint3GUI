package software_masters.planner_networking;

import java.util.ArrayList;

public class LogObserver extends Observer
{

	Client mySubject;
	PlanFile currPlanFile;
	Log planLog;
	ArrayList<Change> theChanges;
	
	
	public LogObserver(Client mySubject, PlanFile currPlanFile, Log planLog, ArrayList<Change> theChanges)
	{

		super();
		this.mySubject = mySubject;
		this.currPlanFile = currPlanFile;
		this.planLog = planLog;
		this.theChanges = theChanges;
		
		
	}



	@Override
	public void update()
	{
		Change mostRecentChange = mySubject.getMyChange();
		this.theChanges.add(mostRecentChange);
		this.planLog.setTheChanges(theChanges);
		this.currPlanFile.setMyLog(planLog);

	}



	public Client getMySubject()
	{
	
		return mySubject;
	}



	public void setMySubject(Client mySubject)
	{
	
		this.mySubject = mySubject;
	}



	public PlanFile getCurrPlanFile()
	{
	
		return currPlanFile;
	}



	public void setCurrPlanFile(PlanFile currPlanFile)
	{
	
		this.currPlanFile = currPlanFile;
	}



	public Log getPlanLog()
	{
	
		return planLog;
	}



	public void setPlanLog(Log planLog)
	{
	
		this.planLog = planLog;
	}



	public ArrayList<Change> getTheChanges()
	{
	
		return theChanges;
	}



	public void setTheChanges(ArrayList<Change> theChanges)
	{
	
		this.theChanges = theChanges;
	}

}
