package fx.addComment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import software_masters.planner_networking.Client;

public class addCommentController
{
	
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
	
	
	

}
