package fx.editComment;

import java.io.IOException;

import fx.planView.PlanViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import software_masters.planner_networking.Client;
import software_masters.planner_networking.Comment;
import software_masters.planner_networking.Main;
import software_masters.planner_networking.PlanNode;

public class EditCommentController
{
	@FXML
	private Button removeComment;
	
	@FXML
	private Button exit;
	
	@FXML
	private TextField thisText;
	
	public Comment thisComment;
	
	public PlanNode currNode;
	
	public Client testClient;
	
	public Stage primaryStage;
	
	public BorderPane mainView;

	
	
	public Comment getThisComment()
	{
	
		return thisComment;
	}

	public void setThisComment(Comment thisComment)
	{
	
		this.thisComment = thisComment;
	}

	public PlanNode getCurrNode()
	{
	
		return currNode;
	}

	public void setCurrNode(PlanNode currNode)
	{
	
		this.currNode = currNode;
	}

	public Client getTestClient()
	{
	
		return testClient;
	}

	public void setTestClient(Client testClient)
	{
	
		this.testClient = testClient;
	}

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

	
	public void removeComment() throws IOException
	{
		PlanNode commentNode = thisComment.getParentNode();

		

		
		for(int i = 0; i < commentNode.getComments().size(); i++)
		{

			
			if(commentNode.getComments().get(i).getData() == thisComment.getAuthor())
			{

				commentNode.getComments().remove(i);
			}
		}
		
		
		exit();
	}
	
	public void generateComment()
	{
		thisText.setText(thisComment.getData());
	}
}
