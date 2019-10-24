

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ServerController {
	ServerSocket1 sSocket;
	DatabaseManager db1;
	Thread serverThread ;
    @FXML
    private TextArea text;
    @FXML
    private Button serverOn;
    @FXML
    private Button closeBt;
	@FXML
	private void initialize()
	{
		closeBt.setOpacity(0.0f);
		serverOn.setOpacity(1.0f);

	}
	public void makeServerOn(ActionEvent event)
	{
		closeBt.setOpacity(1.0f);
		serverOn.setOpacity(0.0f);
		db1=new DatabaseManager();
		text.appendText("Server is On");
		sSocket= new ServerSocket1();


		getQues();


	}
	private void getQues()
	{
		try
		{
			serverThread = new Thread(new Runnable() {

				@Override
				public void run() {

					while(true) {
						String st=sSocket.getQuestion();
						text.appendText("\n"+sSocket.dp2.getAddress()+": "+st);
						System.out.print(st);
						//sSocket.giveAnswer("s");
						retrive(st);

					}
				}
			});
		}catch(Exception threadException){
			threadException.printStackTrace();
		}
		serverThread.start();


	}
	public void retrive(String queryCondition)
	{

		System.out.println(queryCondition);
		if(!queryCondition.equals("Connected")&&!queryCondition.equals("Disconnect"))
		{
			String queryCondition1="ques='"+queryCondition+"'";

			try{

				db1.statusData(queryCondition1);
				if(db1.resultSet.next())
				{
						System.out.print(db1.resultSet.getString(2));
						sSocket.giveAnswer(db1.resultSet.getString(2));
						text.appendText("\nServer:"+db1.resultSet.getString(2));
				}

				else
				{
					sSocket.giveAnswer("Sorry No Appropriate Ans");
					text.appendText("\nServer: Sorry No Appropriate Ans");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}



	}
	@FXML
	public void close(ActionEvent event)
	{
		Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		serverThread.stop();
		sSocket.close();
		stage.close();
	}
}
