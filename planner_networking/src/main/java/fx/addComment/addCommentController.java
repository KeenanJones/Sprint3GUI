package fx.addComment;

import java.io.IOException;

import fx.planView.PlanViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import software_masters.planner_networking.Client;
import software_masters.planner_networking.Comment;
import software_masters.planner_networking.Main;
import software_masters.planner_networking.PlanNode;

public class addCommentController
{
	public PlanNode currNode;
	
	public Client testClient;
	
	public Stage primaryStage;
	
	public BorderPane mainView;
	
	@FXML
	private Button addComment;
	
	@FXML
	private Button exit;
	
	@FXML
	private TextArea commentField;
	
	/**
	 * @return the testClient
	 */
	public Client getTestClient()
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
	
	
	public void writeComment() throws IOException
	{
		String comment = this.commentField.getText();

		Integer size = currNode.getComments().size();
		Integer place = 0;
		Integer id = 1;
		
		if(size == 0)
		{
			
			place = 0;
			id = 1;
		}
		else
		{
			
			place = size - 1;
			id = currNode.getComments().get(place).getID() + 1;
		}
		
		Comment thisComment = new Comment(id, comment, testClient.getUsername(), currNode);
		
		currNode.addComment(thisComment);
		
		
		exit();
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
	
	
	
	

}
