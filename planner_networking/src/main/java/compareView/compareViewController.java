package compareView;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import software_masters.planner_networking.Change;
import software_masters.planner_networking.Client;
import software_masters.planner_networking.Log;
import software_masters.planner_networking.PlanFile;
import software_masters.planner_networking.PlanNode;
import software_masters.planner_networking.TreeArrayItem;

public class compareViewController 
{
	@FXML
	private ComboBox<String> SelectPlan;
	
	@FXML
	private Label firstPlan;
	
	@FXML
	private Button compareButton;
	
	@FXML
	private TextArea textSpot;
	
	@FXML
	private Button exit;
	
	
	
	
	private TreeArrayItem secondCurrNode;
	PlanFile selectedPlan;
	Stage primaryStage;
	BorderPane mainView;
	Client testClient;
	PlanNode currentNode;	
	int count = 0;
	private String myDifferences;

	private String plan1Year;

	private String plan2Year;
	
	public Stage getPrimaryStage()
	{
	
		return primaryStage;
	}
	public void setPrimaryStage(Stage primaryStage)
	{
	
		this.primaryStage = primaryStage;
	}
	public BorderPane getMainView()
	{
	
		return mainView;
	}
	public void setMainView(BorderPane mainView)
	{
	
		this.mainView = mainView;
	}
	public Client getTestClient()
	{
	
		return testClient;
	}
	public void setTestClient(Client testClient)
	{
	
		this.testClient = testClient;
	}
	public PlanNode getCurrentNode()
	{
	
		return currentNode;
	}
	public void setCurrentNode(PlanNode currentNode)
	{
	
		this.currentNode = currentNode;
	}
	
	public PlanFile getSelectedPlan()
	{
	
		return selectedPlan;
	}
	
	public void setSelectedPlan(PlanFile selectedPlan)
	{
	
		this.selectedPlan = selectedPlan;
	}
	
	public void makeMenu() throws RemoteException
	{	
        List<String> list = new ArrayList<String>();
        
        ArrayList<PlanFile> plans = testClient.getPlans();

        ObservableList<String> thisArray = FXCollections.observableList(list);
		
        for (PlanFile p : plans)
        {
        	if(!p.getYear().equals(selectedPlan.getYear()))
        	{
        		thisArray.add(p.getYear());
        	}
        	
        }
		 

            
	if (count==0)
	{
		SelectPlan.setItems(thisArray);
		count ++;
	}

	}
	
	public void setSelectedPlanText()
	{
		firstPlan.setText(testClient.getCurrPlanFile().getYear());
	}
	
	public void compare() throws RemoteException
	{
		PlanFile firstPlan = testClient.getCurrPlanFile();
		String secondPlanString = SelectPlan.getValue();
		
		PlanFile secondPlan = findPlan(secondPlanString);
		
		comparePlans(firstPlan, secondPlan);
	}
	
	private PlanFile findPlan(String secondPlanString) throws RemoteException
	{
		ArrayList<PlanFile> plans = testClient.getPlans();
		PlanFile whatToReturn = null;
		
        for (PlanFile p : plans)
        {
        	if(p.getYear().equals(secondPlanString))
        	{
        		whatToReturn = p;
        	}
        }    
        
		return whatToReturn;
        
		
	}
	
	private void comparePlans(PlanFile firstPlan1, PlanFile secondPlan2) throws RemoteException
	{
		this.myDifferences = "";
		this.plan1Year = firstPlan1.getYear();
		this.plan2Year = secondPlan2.getYear();
		compareTrees(firstPlan1.getPlan().getRoot(), secondPlan2.getPlan().getRoot());
		
		
		textSpot.setText(this.myDifferences);
		
	}
	
	private void compareTrees(PlanNode firstPlan1, PlanNode secondPlan2)
	{
		String string1 = firstPlan1.getData();
		String string2 = secondPlan2.getData();
		
		int lengthOfChildren1 = firstPlan1.getChildren().size();
		int lengthOfChildren2 = secondPlan2.getChildren().size();

		//See if they are both null to get rid of null error
		if(string1 == null && string2 == null)
		{
			return;
		}
		
		//See if they are different
		else if((string1 == null && string2 != null) || (string1 != null && string2 == null) || (!string1.equals(string2)))
		{
			//If different, we want to note the difference
			String update = firstPlan1.getName() + " " + this.plan1Year + " and " + secondPlan2.getName() + " " + this.plan2Year + " " + "have different data.";
			this.myDifferences = this.myDifferences + update + "\n";
			
		}
		
		//See if they have uneven children
		if (lengthOfChildren1 < lengthOfChildren2)
		{
			for (int i = 0; i < lengthOfChildren1; i++)
			{
				 compareTrees(firstPlan1.getChildren().get(i), secondPlan2.getChildren().get(i));
			} 
			
			for (int i = lengthOfChildren1 - 1; i < lengthOfChildren2; i++)
			{
				String update = "Plan " + this.plan2Year + " has a " + secondPlan2.getName() + " section that " + this.plan1Year + " doesn't.";
				this.myDifferences = this.myDifferences + update + "\n";
			}
		}
		
		else if(lengthOfChildren1 > lengthOfChildren2)
		{			
			for (int i = 0; i<lengthOfChildren2; i++)
			{
				 compareTrees(firstPlan1.getChildren().get(i), secondPlan2.getChildren().get(i));
			}
			
			for (int i = lengthOfChildren2-1; i < lengthOfChildren1; i++)
			{
				String update = "Plan " + this.plan1Year + " has a " + firstPlan1.getName() + " section that " + this.plan2Year + " doesn't.";
				this.myDifferences = this.myDifferences + update + "\n";
			}

		}
		
		//Else the lengths of children are the same
		else
		{
			for (int i = 0; i<lengthOfChildren1; i++)
			{
				compareTrees(firstPlan1.getChildren().get(i), secondPlan2.getChildren().get(i));
			}	 
		}
	}
		
}
	

	

	

