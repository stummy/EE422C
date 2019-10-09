/* CRITTERS Controller.java
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

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class Controller {
    public TextField textInput1;
    public TextField textInput2;
    public ChoiceBox Critters;
    public Text output;


    public void processButton(ActionEvent event){
        try{
            String value = ((Button)event.getSource()).getText();
            if(value.equals("Quit")){
                Platform.exit();
            }

            else if(value.equals("Seed")){
                Critter.setSeed(Critter.getRandomInt(100));
            }

            else if(value.equals("Show")){
                Critter.displayWorld();
            }

            else if(value.equals("Make")){
                int amt = 0;
                String cr = "";
                cr += "assignment5." + Critters.getSelectionModel().getSelectedItem().toString();

                textInput1.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            textInput1.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });

                try{
                    amt = Integer.parseInt(textInput1.getText());
                } catch (NumberFormatException e) {
                    amt = 1;
                }

                try{
                    for (int i = 0; i < amt; i++) {
                        Critter.makeCritter(cr);
                    }
                } catch(InvalidCritterException e){
                    System.out.println("error");
                }
                Critter.displayWorld();
            }

            else if(value.equals("Stats")){
                List<Critter> list = new ArrayList<Critter>();
                String cr = "";
                cr += "assignment5." + Critters.getSelectionModel().getSelectedItem().toString();

                try {
                    list = Critter.getInstances(cr);
                    Class c = Class.forName(cr);
                    Method method = c.getMethod("runStats", List.class);
                    output.setText((method.invoke(c, list)).toString());

                } catch (InvalidCritterException | ClassNotFoundException |
                        NoSuchMethodException | IllegalAccessException | InvocationTargetException e){
                    System.out.println("error");
                }
            }

            else if(value.equals("Step")){
                int amt = 0;

                textInput2.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        if (!newValue.matches("\\d*")) {
                            textInput2.setText(newValue.replaceAll("[^\\d]", ""));
                        }
                    }
                });

                try{
                    amt = Integer.parseInt(textInput2.getText());
                } catch (NumberFormatException e) {
                    amt = 1;
                }

                for (int i = 0; i < amt; i++) {
                    for (int j = 0; j < Params.refresh_algae_count; j++) {
                        try{
                            Critter.makeCritter("assignment5.Algae");
                        }catch(InvalidCritterException ICE){
                            System.out.println("error in making algae");
                        }
                    }
                    
                    Critter.worldTimeStep();
                    Critter.displayWorld();
                }
            }

            else if(value.equals("Start")){
                if(Main.timerN == 1){
                    Main.timer.start();
                }
                else if(Main.timerN == 2){
                    Main.timer2.start();
                }
                else if(Main.timerN == 3){
                    Main.timer3.start();
                }
            }
            else if(value.equals("Stop")){
                Main.timer.stop();
                Main.timer2.stop();
                Main.timer3.stop();
            }
            else if(value.equals("Speed 1")){
                Main.timerN = 1;
            }
            else if(value.equals("Speed 2")){
                Main.timerN = 2;
            }
            else if(value.equals("Speed 3")){
                Main.timerN = 3;
            }

        } catch (ClassCastException e){
            System.out.println("error");
        }

    }

}
