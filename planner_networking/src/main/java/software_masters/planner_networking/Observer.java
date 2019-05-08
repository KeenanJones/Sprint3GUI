package software_masters.planner_networking;

public abstract class Observer
{
	public Client mySubject;
	
	public abstract void update();
}
