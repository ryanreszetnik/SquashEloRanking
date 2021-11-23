package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Main extends Application {
	StackPane newPlayerStack = new StackPane();
	GridPane newPlayer = new GridPane();
	GridPane challenges = new GridPane();
	Pane newGame = new Pane();
	Pane newplayerss = new Pane();
	ArrayList<String> newplayers = new ArrayList<>();
	ArrayList<String> newranks = new ArrayList<>();
	static ArrayList<Player> players = new ArrayList<>();
	Scene createPlayer, challenge;
	static Label firstPlayerName, secondPlayerName;
	static String p1percent, p2percent;

	@Override

	public void start(Stage primaryStage) {
		try {

			BorderPane root = new BorderPane();
			createPlayer = new Scene(newPlayerStack, 400, 400);

			createPlayer.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			challenge = new Scene(challenges, 800, 400);
			challenge.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			newPlayerStack.getChildren().addAll(newplayerss, newPlayer);
			newPlayer(primaryStage);

			primaryStage.setScene(createPlayer);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void newPlayer(Stage primaryStage) {
		newPlayer.setVgap(5);
		newPlayer.setHgap(5);
		// Defining the Name text field
		final TextField name = new TextField();
		name.setPromptText("Enter your first name.");
		name.setPrefColumnCount(10);

		GridPane.setConstraints(name, 0, 0);
		newPlayer.getChildren().add(name);
		// Defining the Last Name text field
/*		final TextField lastName = new TextField();
		lastName.setPromptText("Enter your last name.");
		GridPane.setConstraints(lastName, 0, 1);
		newPlayer.getChildren().add(lastName);

		final TextField rankinitial = new TextField();
		rankinitial.setPromptText("Enter starting rank.");
		GridPane.setConstraints(rankinitial, 0, 2);
		newPlayer.getChildren().add(rankinitial);
*/
		// Defining the Submit button
		Button submit = new Button("Add Player");
		GridPane.setConstraints(submit, 1, 0);
		newPlayer.getChildren().add(submit);
		Label playerss = new Label();
		newplayerss.getChildren().add(playerss);
		playerss.setTranslateY(90);
		submit.setOnAction(event -> {
			String player = name.getText();
			////System.out.printprintln(player);
	//		newranks.add(rankinitial.getText());
			newranks.add("1000");
			newplayers.add(player);
			playerss.setText(playerss.getText() + "\n " + newplayers.size() + ": " + player);

		});
		Button done = new Button("Finished");
		GridPane.setConstraints(done, 1, 1);
		newPlayer.getChildren().add(done);
		done.setOnAction(event -> {
			int temp = newplayers.size();
			for (int i = 0; i < temp; i++) {
				Player local = new Player(newplayers.get(0), Integer.valueOf(newranks.get(i)));

				players.add(local);
				//System.out.println(local.name);
				newplayers.remove(0);
			}
			//System.out.println("before");
			for (int i = 0; i < players.size(); i++) {
				// players.get(i).setElo(Integer.valueOf(newranks.get(0));
				//System.out.println(newranks.get(i) + "Testing thing: " + players.get(i).getElo());
			}
			//System.out.println("end");
			primaryStage.setScene(challenge);
			Challenge(primaryStage);

		});
	}

	public void Challenge(Stage primaryStage) {
		//System.out.println("Thing " + players.get(1));
		players = Player.sort(players);

		//System.out.println("Thing 2 " + players.get(1));
		challenges.setVgap(5);
		challenges.setHgap(5);
		Button[] player1 = new Button[players.size()];
		Button[] player2 = new Button[players.size()];
		//Button addPlayers = new Button("Add Players");
		//GridPane.setConstraints(addPlayers, 1, 1);
	//	challenges.getChildren().add(addPlayers);

		for (int i = 0; i < players.size(); i++) {
			//System.out.println("Place 4" + players.size());
			player1[i] = new Button(players.get(i).name + ": " + players.get(i).getElo());
			player2[i] = new Button(players.get(i).name + ": " + players.get(i).getElo());
			GridPane.setConstraints(player1[i], 1, i + 2);
			GridPane.setConstraints(player2[i], 5, i + 2);

			challenges.getChildren().addAll(player1[i], player2[i]);
		}
		firstPlayerName = new Label("");
		secondPlayerName = new Label("");
		Label firstPlayerpercent = new Label("");
		Label secondPlayerpercent = new Label("");
		GridPane.setConstraints(firstPlayerName, 2, 2);
		GridPane.setConstraints(secondPlayerName, 4, 2);
		GridPane.setConstraints(firstPlayerpercent, 2, 1);
		GridPane.setConstraints(secondPlayerpercent, 4, 1);
		challenges.getChildren().addAll(firstPlayerName, secondPlayerName, firstPlayerpercent, secondPlayerpercent);
		for (int i = 0; i < players.size(); i++) {
			int place = i;
			player1[i].setOnMouseClicked(event -> {
				
				firstPlayerName.setText(players.get(place).name);
				if(secondPlayerName.getText() != ""){
					double percentChancep1=Player.getPercent(getPlayer(firstPlayerName.getText()), getPlayer(secondPlayerName.getText()));
					double p1per=Math.round(percentChancep1*10000)/100.0;
					p1percent = "(" +(p1per) + "%)";
					p2percent ="(" +(Math.round((100.0-p1per)*100)/100.0) + "%)";
				}
				firstPlayerpercent.setText(p1percent);
				secondPlayerpercent.setText(p2percent);
			});
			player2[i].setOnMouseClicked(event -> {
				secondPlayerName.setText(players.get(place).name);
				if(firstPlayerName.getText() != ""){
					double percentChancep1=Player.getPercent(getPlayer(firstPlayerName.getText()), getPlayer(secondPlayerName.getText()));
					double p1per=Math.round(percentChancep1*10000)/100.0;
					p1percent = "(" +(p1per) + "%)";
					p2percent ="(" +(Math.round((100.0-p1per)*100)/100.0) + "%)";
				}
				firstPlayerpercent.setText(p1percent);
				secondPlayerpercent.setText(p2percent);
			});
		}
	/*	addPlayers.setOnAction(event -> {
			challenges.getChildren().removeAll(firstPlayerName, secondPlayerName);
			primaryStage.setScene(createPlayer);
		});*/
		final TextField p1scoretext = new TextField();
		p1scoretext.setPromptText("Enter Score.");
		GridPane.setConstraints(p1scoretext, 2, 3);
		challenges.getChildren().add(p1scoretext);
		final TextField p2scoretext = new TextField();
		p2scoretext.setPromptText("Enter Score.");
		GridPane.setConstraints(p2scoretext, 4, 3);
		challenges.getChildren().add(p2scoretext);
		
		Button submitGame = new Button("SubmitGame");
		GridPane.setConstraints(submitGame, 3, 3);
		challenges.getChildren().add(submitGame);
		submitGame.setOnAction(event -> {
			String p1nam =firstPlayerName.getText();
			String p2nam =secondPlayerName.getText();
			int scorep1 = Integer.valueOf(p1scoretext.getText());
			int scorep2 = Integer.valueOf(p2scoretext.getText());
			Game p1game=new Game(getPlayer(p1nam),getPlayer(p2nam),"Today", scorep1,scorep2);
		//	Game p2game=new Game(getPlayer(p2nam),getPlayer(p1nam),"Today", scorep2,scorep1);
			//getPlayer(p1nam).games.add(p1game);
			//getPlayer(p2nam).games.add(p2game);
			players = Player.sort(players);
			for (int i = 0; i < players.size(); i++) {
				////System.out.println("Place 4" + players.size());
				
				player1[i].setText(players.get(i).name + ": " + players.get(i).getElo());
				player2[i].setText(players.get(i).name + ": " + players.get(i).getElo());
			}
			if(firstPlayerName.getText() != ""){
				double percentChancep1=Player.getPercent(getPlayer(firstPlayerName.getText()), getPlayer(secondPlayerName.getText()));
				double p1per=Math.round(percentChancep1*10000)/100.0;
				p1percent = "(" +(p1per) + "%)";
				p2percent ="(" +(Math.round((100.0-p1per)*100)/100.0) + "%)";
			}
			firstPlayerpercent.setText(p1percent);
			secondPlayerpercent.setText(p2percent);
		});
	}
	public static Player getPlayer(String name){
		for(int i = 0; i < players.size(); i++){
			if(name == players.get(i).name){
				return players.get(i);
			}
		}
		//System.out.println("Player Not Found");
	
		return null;
	}
}
