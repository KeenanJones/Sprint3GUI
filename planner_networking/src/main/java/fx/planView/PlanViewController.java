package fx.planView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import fx.addComment.addCommentController;
import fx.checkSave.CheckSaveController;
import fx.editComment.EditCommentController;
import fx.homePageView.HomePageViewController;
import historyView.HistoryViewController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import loginView.LoginViewController;
import software_masters.planner_networking.Change;
import software_masters.planner_networking.Client;
import software_masters.planner_networking.Comment;
import software_masters.planner_networking.FXTreeView;
import software_masters.planner_networking.Log;
import software_masters.planner_networking.Main;
import software_masters.planner_networking.PlanFile;
import software_masters.planner_networking.PlanNode;
import software_masters.planner_networking.tableComment;

public class PlanViewController
{
	Stage primaryStage;
	BorderPane mainView;
	Client testClient;
	PlanNode currentNode;
	
	Boolean builtTree = false;
	
	@FXML 
	private Button logoutButton;
	
	@FXML
	private Button HistoryBtn;
	
	@FXML
	private ScrollPane scroll;
	
	@FXML 
	private Button commentBtn;
	
	@FXML
	private Label nodeLabel;
	
	@FXML 
	private Button homepageButton;
	
	@FXML
	private TreeView tree;

	@FXML
	private TextArea contents;
	
	@FXML
	Label user;
	
	@FXML
	Label dept;
	
	@FXML
	private Text comments;

	/**
	 * @return the user
	 */
	public Label getUser()
	{
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String userName)
	{
		user.setText(userName);
	}

	/**
	 * @return the dept
	 */
	public Label getDept()
	{
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(String deptName)
	{
		dept.setText(deptName);
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
	
	@FXML
	Button saveBtn;
	@FXML
	Button removeBtn;
	@FXML
	Button addBtn;
	private Object PlanNode;
	
	// lets never touch this again... it works
	public void logout() throws IOException {
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/loginView/loginView.fxml"));
		//this.mainView = loader.load();
		//assertThat(mainView!=null);
		BorderPane newMain = loader.load();
		
		LoginViewController cont = loader.getController();
		cont.setMainView(newMain);
		cont.setTestClient(testClient);
		cont.setPrimaryStage(primaryStage);
		
		primaryStage.setUserData(cont);
		primaryStage.getScene().setRoot(newMain);
		
	}
	
	public void homepage() throws IOException {
		
		
		if(saveBtn.isDisabled())
		{
		
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fx/homePageView/homePageView.fxml"));
			this.mainView = loader.load();
		
			HomePageViewController cont = loader.getController();
			cont.setTestClient(testClient);
			cont.setPrimaryStage(primaryStage);
			this.testClient = cont.getTestClient();
			
			
			cont.setDept(dept.getText());
			cont.setUser(user.getText());
		
		
			primaryStage.getScene().setRoot(mainView);
		}
		
		else
		{
			
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fx/checkSave/checkSave.fxml"));
			//this.mainView = loader.load();
			BorderPane newMain = loader.load();
		
			CheckSaveController cont = loader.getController();
			cont.setTestClient(testClient);
			cont.setPrimaryStage(primaryStage);
			cont.setDept(dept.getText());
			cont.setUser(user.getText());
		
			primaryStage.setWidth(500);
			primaryStage.getScene().setRoot(newMain);
		}
			
			
			
			
			
		
		
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void buildTree() throws RemoteException {
		
		
		if(!builtTree) {
			
			removeBtn.setDisable(true);
			addBtn.setDisable(true);
			saveBtn.setDisable(true);

		
			contents.setText("");

			TreeItem<PlanNode> theRoot = makeTree();

			tree.setRoot(theRoot);

			
			tree.getSelectionModel().selectedItemProperty()
	        .addListener((observable, oldValue, newValue) -> handleTreeClick((TreeItem<PlanNode>) newValue));

			builtTree = true;

		}
	}
	
	private void handleTreeClick(TreeItem<PlanNode> newValue)
	{
		
		try {
			
		this.scroll.setContent(new Text("There are no comments for this section."));
			
		removeBtn.setDisable(false);

		addBtn.setDisable(false);

		this.currentNode = newValue.getValue();
		
		
		loadComments(currentNode);
		
		nodeLabel.setText(currentNode.getName());
		
		setContents(currentNode.getData());
		}
		catch (Exception E){
			
		}
		
		
		
	}
	



	private void removeComments()
	{
		comments.setText("");
		
		
	}

	public TreeItem<PlanNode> makeTree() throws RemoteException
	{

		TreeItem<PlanNode> rootItem = getProducts(testClient.getCurrPlanFile().getPlan().getRoot());

		
		

		return rootItem;

	}

	public TreeItem<PlanNode> getProducts(PlanNode root) throws RemoteException
	{

		// This will be the final ArrayList passed back to FXTreeView.java for build
		// (should only hold Mission for centre)
		//ArrayList<TreeItem<PlanNode>> FinalNodeList = new ArrayList<TreeItem<PlanNode>>();

		TreeItem<PlanNode> currentTreeItem = new TreeItem<PlanNode>(root);

		//FinalNodeList.add(currentTreeItem);

		getKids(root, currentTreeItem);

		return currentTreeItem;
	}
	
	private void getKids(PlanNode parentNode, TreeItem<PlanNode> parentTreeItem)
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
	
	public void removeNode() throws RemoteException
	{
		
		Change thisChange = new Change(testClient.getUsername(), currentNode, "Removed a node");
		ArrayList<Change> newList = new ArrayList<Change>();
		testClient.getCurrPlanFile().setMyLog(new Log(newList));
		Log thisLog = testClient.getCurrPlanFile().getMyLog();
		thisLog.addChange(thisChange);
		testClient.getCurrPlanFile().setMyLog(thisLog);
		
		
		
		this.testClient.setCurrNode(currentNode);
		
		this.testClient.removeBranch();
		
		
		builtTree = false;
		buildTree();
		
		saveBtn.setDisable(false);
		removeBtn.setDisable(true);
		addBtn.setDisable(true);

			
	}
	
	public void addNode() throws RemoteException
	{
		
		
		this.testClient.setCurrNode(currentNode);
		this.testClient.addBranch();
		
		Change thisChange = new Change(testClient.getUsername(), currentNode, "Added a node");
		ArrayList<Change> newList = new ArrayList<Change>();
		testClient.getCurrPlanFile().setMyLog(new Log(newList));
		Log thisLog = testClient.getCurrPlanFile().getMyLog();
		thisLog.addChange(thisChange);
		testClient.getCurrPlanFile().setMyLog(thisLog);
		
		
		builtTree = false;
		buildTree();
		saveBtn.setDisable(false);
		removeBtn.setDisable(true);
		addBtn.setDisable(true);
		
		
		
	}
	
	public void save() throws IllegalArgumentException, RemoteException 
	{
		this.testClient.setCurrNode(currentNode);
		this.testClient.pushPlan(testClient.getCurrPlanFile());
		removeBtn.setDisable(true);
		addBtn.setDisable(true);
		saveBtn.setDisable(true);
		
	}

	public void setContents(String stringContent) {
		
		contents.setText(stringContent);
	}
	
	public void changeContent() {
		
		if(this.currentNode != null) {
			
		saveBtn.setDisable(false);
		String contentValue = contents.getText();
		currentNode.setData(contentValue);
		
		}
		
	}
	
	public void comment() throws IOException
	{
		
	
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fx/addComment/addComment.fxml"));
		BorderPane newMain = loader.load();
		
		addCommentController cont = loader.getController();
		cont.setMainView(newMain);
		cont.setTestClient(testClient);
		cont.setPrimaryStage(primaryStage);
		
		
		
		
		cont.setCurrNode(this.currentNode);
	
		
		primaryStage.setUserData(cont);
		primaryStage.getScene().setRoot(newMain);
			
	}
	
	@SuppressWarnings("unchecked")
	public void loadComments(PlanNode currentNode)
	{
		
		Integer size = currentNode.getComments().size();
		String finalComment = "";
		ObservableList<Comment> data = FXCollections.observableArrayList();
		
		if(size != 0) 
		{
			
			
			for(int i = 0; i < size; i++) 
			{
				
				
				finalComment = "";
				
				String authorName = currentNode.getComments().get(i).getAuthor();
				
				String comment = currentNode.getComments().get(i).getData();
				
				String toComment = authorName + ": " + comment;
				finalComment = finalComment + toComment;

				data.add(new Comment(i, finalComment, comment, currentNode));
				
			
		        
			}
			
			@SuppressWarnings("rawtypes")
			TableView table = new TableView();
		
	        
	        TableColumn<String, Comment> col1 = new TableColumn<>("Comments:");
	        col1.setCellValueFactory(new PropertyValueFactory<>("data"));
	        col1.setMinWidth(353);
	        
	        table.getColumns().addAll(col1);
	        table.getItems().addAll(data);
	        
	        table.getSelectionModel().selectedItemProperty()
	        .addListener((observable, oldValue, newValue) -> {
				try
				{
					handleTableClick((Comment) newValue);
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

	        
	        data.clear();

	        this.scroll.setContent(table);
			
			
		}
	}

	private Object handleTableClick(Comment newValue) throws IOException
	{	
		editComment(newValue);
		
		return null;
	}

	private void editComment(Comment newValue) throws IOException
	{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/fx/editComment/editComment.fxml"));
		BorderPane newMain = loader.load();
		
		EditCommentController cont = loader.getController();
		cont.setMainView(newMain);
		cont.setTestClient(testClient);
		cont.setPrimaryStage(primaryStage);
		cont.setThisComment(newValue);
		
		
		
		
		cont.setCurrNode(this.currentNode);
	
		
		primaryStage.setUserData(cont);
		primaryStage.getScene().setRoot(newMain);
		
		
	}
	
	public void getHistory() throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("/historyView/historyView.fxml"));
		BorderPane newMain = loader.load();
		
		HistoryViewController cont = loader.getController();
		cont.setMainView(newMain);
		cont.setTestClient(testClient);
		cont.setPrimaryStage(primaryStage);
		
		
		
		
		cont.setCurrNode(this.currentNode);
	
		
		primaryStage.setUserData(cont);
		primaryStage.getScene().setRoot(newMain);
		
	}
	
	

}