/* CRITTERS View.java
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

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class View {

    public View(){

    }

    public static BorderPane drawBorderPane(){
        BorderPane border = new BorderPane();
        HBox hbox = addHBox();
        border.setBottom(hbox);
        border.setLeft(addVBox());
        //addStackPane(hbox);         // Add stack to HBox in top region

        border.setCenter(drawGridPane());
        //border.setRight(addFlowPane());

        return border;

    }

    public static HBox addHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);

        hbox.setStyle("-fx-background-color: #336699;");


        Button buttonCurrent = new Button("Stats");
        buttonCurrent.setPrefSize(100, 20);

        hbox.setAlignment(Pos.CENTER);
        Button buttonProjected = new Button("Quit");
        buttonProjected.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonCurrent, buttonProjected);

        return hbox;
    }

    public static VBox addVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        Text title = new Text("Data");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vbox.getChildren().add(title);

        return vbox;
    }

    private static GridPane drawGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0,0,0,0));
        // char world[][] = new Critter.displayWorld();

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                Rectangle rec = new Rectangle();
                rec.setWidth(5);
                rec.setHeight(5);

                rec.setFill(Color.DARKOLIVEGREEN);
                GridPane.setRowIndex(rec, row);
                GridPane.setColumnIndex(rec, col);
                gridPane.getChildren().addAll(rec);

            }
        }

        /*
        for (Critter cr : population) {
            Critter.CritterShape shape = cr.viewShape();
            if(shape.equals(Critter.CritterShape.CIRCLE)){
                Circle c = new Circle();
                c.setRadius(10);
                c.setCenterX(cr.x_coord-10);
                c.setCenterY(cr.y_coord-10);
                c.setFill(Color.GREEN);
                GridPane.setRowIndex(c, 100);
                GridPane.setColumnIndex(c, 100);
                gridPane.getChildren().addAll(c);
            }
        }
        */

        return gridPane;
    }

    /*
    private static StackPane createCell(Critter cr) {
        // find the shape of he
        Rectangle rec = new Rectangle();
        rec.setWidth(50);
        rec.setHeight(50);

        Critter.CritterShape cs = cr.viewShape();

        if (cs.equals(Critter.CritterShape.CIRCLE)) {

        }

        StackPane cell = new StackPane();

        //cell.getChildren().add(s);
        cell.getStyleClass().add("cell");
        return cell;
    }*/
}
