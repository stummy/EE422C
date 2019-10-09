/* CRITTERS Main.java
 * EE422C Project 5 submission by
 * Mircea Antonescu
 * mca2357
 * 15500
 * Zahra Atzuri
 * zfa84
 * 15500
 * Slip days used: <0>
 * Spring 2018
 */

package assignment5;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.omg.CORBA.DynAnyPackage.Invalid;

public class Main extends Application{

	static GridPane worldGrid = new GridPane();
	static GridPane critGrid = new GridPane();
	public static int timerN = 1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		primaryStage.setTitle("Critters");
		WorldView.setTileSize();

		Stage world = new Stage();
		world.setTitle("World");
		StackPane stackPane = new StackPane();
		ObservableList list = stackPane.getChildren();
		list.addAll(worldGrid, critGrid);
		Scene worldScene = new Scene(stackPane, Params.world_width*(WorldView.tileSize+2), Params.world_height*(WorldView.tileSize+2)); //Fix later
		world.setScene(worldScene);
		world.show();

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	static Animation timer = new Animation(500) {
		@Override
		public void handle() {
			for (int i = 0; i < Params.refresh_algae_count; i++) {
				try{
					Critter.makeCritter("assignment5.Algae");
				}catch(InvalidCritterException ICE){
					System.out.println("error in making algae");
				}
			}

			Critter.worldTimeStep();
			Critter.displayWorld();
		}
	};

	static Animation timer2 = new Animation(250) {
		@Override
		public void handle() {
			for (int i = 0; i < Params.refresh_algae_count; i++) {
				try{
					Critter.makeCritter("assignment5.Algae");
				}catch(InvalidCritterException ICE){
					System.out.println("error in making algae");
				}
			}

			Critter.worldTimeStep();
			Critter.displayWorld();
		}
	};

	static Animation timer3 = new Animation(100) {
		@Override
		public void handle() {
			for (int i = 0; i < Params.refresh_algae_count; i++) {
				try{
					Critter.makeCritter("assignment5.Algae");
				}catch(InvalidCritterException ICE){
					System.out.println("error in making algae");
				}
			}

			Critter.worldTimeStep();
			Critter.displayWorld();
		}
	};
}
