package application;


import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class ClientController {
	ClientSocket cSocket;
    @FXML
    private TextArea text1;
	@FXML
	private TextField text;

	// Event Listener on Button.onAction
	@FXML
	private void initialize()
	{
		cSocket= new ClientSocket();
	}
	@FXML
	public void getAnswer(ActionEvent event) {
		String ques=text.getText();
		text1.appendText("\n127.0.0.3: "+ques);
		cSocket.giveQues(ques);
		String ans=cSocket.getAns();
		text1.appendText("\nServer: "+ans);

		//initialize();
	}
	@FXML
	public void close(ActionEvent event)
	{
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		cSocket.close();
		stage.close();
	}
}
