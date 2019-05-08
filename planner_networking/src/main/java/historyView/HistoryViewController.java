package historyView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import fx.planView.PlanViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import software_masters.planner_networking.Change;
import software_masters.planner_networking.Client;
import software_masters.planner_networking.Comment;
import software_masters.planner_networking.Log;
import software_masters.planner_networking.Main;
import software_masters.planner_networking.PlanNode;

public class HistoryViewController
{
	public PlanNode currNode;
	
	public Client testClient;
	
	public Stage primaryStage;
	
	public BorderPane mainView;
	
	@FXML
	private TextArea textSpot;
	
	@FXML
	private Button exit;
	

	/**
	 * @return the testClient
	 */	public Client getTestClient()
	{
		return testClient;
	}

	/**
	 * @param testClient the testClient to set
	 */
	public void setTestClient(Client testClient)
	{
		this.testClient = testClient;
	}

	/**
	 * @return the primaryStage
	 */
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	/**
	 * @param primaryStage the primaryStage to set
	 */
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

	public void exit() throws IOException
	{


		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fx/planView/planView.fxml"));
		BorderPane newMain = loader.load();
		
		PlanViewController cont = loader.getController();
		cont.setMainView(newMain);
		cont.setTestClient(testClient);
		cont.setPrimaryStage(primaryStage);
		
		primaryStage.setUserData(cont);
		primaryStage.getScene().setRoot(newMain);
		
	}

	public void setCurrNode(PlanNode currentNode)
	{

		this.currNode = currentNode;
		
	}
	
	
	
	public void setHistoryLog() 
	{
		String myLogText = "";
		
		
		Log thisLog = this.testClient.getCurrPlanFile().getMyLog();
		
		ArrayList<Change> logText = thisLog.getTheChanges();
		
		Collections.reverse(logText);

		for(Change i : logText)
		{
			String toAdd = i.getAuthor() + i.getAction() + i.getSection().getName();
			myLogText = myLogText + toAdd + "/n";
		}
		
		
	}



}
