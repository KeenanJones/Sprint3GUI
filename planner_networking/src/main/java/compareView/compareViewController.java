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
import software_masters.planner_networking.Client;
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
	
	
	
	
	
	PlanFile selectedPlan;
	Stage primaryStage;
	BorderPane mainView;
	Client testClient;
	PlanNode currentNode;	
	int count = 0;
	
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
	
	private void comparePlans(PlanFile firstPlan1, PlanFile secondPlan2)
	{
		
		makeTree(firstPlan1);
		textSpot.setText("Going to compare " + firstPlan1.getYear() + " and " + secondPlan2.getYear());
		
	}
	
	public ArrayList<PlanNode> makeTree(PlanFile firstPlan1) throws RemoteException
	{

		ArrayList<TreeArrayItem> finalArray = getProducts(firstPlan1.getPlan().getRoot());
		
		return finalArray;

	}

	// This method creates an ArrayList of TreeItems (Products)
	public ArrayList<TreeArrayItem> getProducts(PlanNode root) throws RemoteException
	{
		//Makes a new array and adds the root
		ArrayList<TreeArrayItem> thisLevel = new ArrayList<TreeArrayItem>();
		
		TreeArrayItem rootItem = new TreeArrayItem(root, new ArrayList<TreeArrayItem>());
		thisLevel.add(rootItem);
		
		getKids(root);

		return thisLevel;
	}
	

	private void getKids(PlanNode parentNode)
	{

		if (parentNode.getChildren().isEmpty())
		{
			return;
		}

		for (int i = 0; i < parentNode.getChildren().size(); i++)
		{
			TreeItem<PlanNode> newChild = new TreeItem<PlanNode>(parentNode.getChildren().get(i));

			parentTreeItem.getChildren().add(newChild);
			getKids(parentNode.getChildren().get(i), newChild);

		}

	}

	

	
}
