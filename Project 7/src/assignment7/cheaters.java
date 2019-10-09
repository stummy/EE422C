/* cheaters.java
 * EE422C Project 7 submission by
 * Mircea Antonescu
 * mca2357
 * 15500
 * Zahra Atzuri
 * zfa84
 * 15500
 * Slip days used: <0>
 * Spring 2018
 */
package assignment7;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.List;

public class cheaters extends Application{
    private static Map<Set<String>, Integer> compMap = new HashMap<>();
    static int lengthWord = 0;
    static int threshold = 200;

    public static void main(String[] args) throws IOException {
        long startTime = System.nanoTime();
        ArrayList<Set<String>> fileSets = new ArrayList<>();

        if(args.length > 0){
            try{
                lengthWord = Integer.parseInt(args[0]);
                threshold = Integer.parseInt(args[1]);
            } catch (NumberFormatException e){}
        }
        String args0 = "/Users/hyalithus/Documents/School/Year 2/Spring 2018/EE 422C - Project 7/big_doc_set";

        File dir = new File(args0);
        File[] folder = dir.listFiles();

        // create the word sequences for each file
        for (File f: folder) {
            StringBuilder longAssString = readFile(f);
            Set<String> words = sequenceWords(lengthWord,longAssString);
            fileSets.add(words);
        }

        // find similarities between the files (sets)
        for (int i = 0; i < fileSets.size() - 1; i++) {
            Set<String> file1 = fileSets.get(i);

            for (int j = i + 1; j < fileSets.size(); j++) {
                Set<String> file2 = new HashSet<>(fileSets.get(j));

                file2.retainAll(file1); // compare files

                // add files to map if there's any stuff in common
                if(file2.size() != 0) {
                    Set<String> key = new HashSet<>();
                    key.add(folder[i].toString().replaceAll(args0 + "/",""));
                    key.add(folder[j].toString().replaceAll(args0 + "/",""));
                    compMap.put(key, file2.size());
                }
            }
        }
        compMap = sortMap(compMap);

        // Display Testing
        for (Set<String> key : compMap.keySet()) {
            if(compMap.get(key) >= threshold) {
                System.out.println(compMap.get(key) + ":" + key);
            }
        }


        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Main: " + duration/1000000);

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Cheaters");
        GridPane grid = new GridPane();
        Canvas canvas = new Canvas(1000,1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Map<String, Integer[]> locations = new HashMap<>();
        Random random = new Random();

        for(Set<String> key : compMap.keySet()){
            if(compMap.get(key) >= threshold){

                String[] fileNames = new String[2];
                int i = 0;
                for (String s : key){
                    fileNames[i] = s;
                    i++;
                }

                Integer x1,y1,x2,y2;

                if(!locations.keySet().contains(fileNames[0])){
                    x1 = random.nextInt(700);
                    y1 = random.nextInt(700);
                    locations.put(fileNames[0], new Integer[]{x1,y1,1} );
                }
                else{
                    x1 = locations.get(fileNames[0])[0];
                    y1 = locations.get(fileNames[0])[1];
                    locations.get(fileNames[0])[2] += 1;
                }

                if(!locations.keySet().contains(fileNames[1])){
                    x2 = random.nextInt(700);
                    y2 = random.nextInt(700);
                    locations.put(fileNames[1], new Integer[]{x2,y2,1} );
                }
                else{
                    x2 = locations.get(fileNames[1])[0];
                    y2 = locations.get(fileNames[1])[1];
                    locations.get(fileNames[1])[2] += 1;
                }

                gc.setStroke(Color.CORNFLOWERBLUE);
                gc.setLineWidth(compMap.get(key)/200);
                gc.strokeLine(x1,y1,x2,y2);
                gc.fillText("" + compMap.get(key), (x1+x2)/2 + 10, (y1+y2)/2 + 10 );

                int multiplier1 = locations.get(fileNames[0])[2];
                int multiplier2 = locations.get(fileNames[1])[2];
                gc.setFill(Color.LIGHTCORAL);
                gc.fillRect(x1,y1,20*multiplier1,20*multiplier1);
                gc.fillRect(x2,y2,20*multiplier2,20*multiplier2);

                gc.setStroke(Color.LIGHTCORAL);
                gc.setFont(Font.font("Verdana", 10));
                if(multiplier1 > 2){
                    gc.fillText( fileNames[0], x1, y1-5 );
                }

                if(multiplier2 > 2){
                    gc.fillText( fileNames[1], x2, y2-5 );
                }

            }
        }

        grid.getChildren().add(canvas);

        Scene scene = new Scene(grid, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Helper function to sort the map for a nice display!
     * @param map that is unsorted
     * @return a sorted map
     */
    private static Map<Set<String>, Integer> sortMap(Map<Set<String>, Integer> map) {
        Map<Set<String>, Integer> sorted = new LinkedHashMap<>();
        List<Map.Entry<Set<String>, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<Set<String>, Integer> e : list) {
            sorted.put(e.getKey(), e.getValue());
        }
        return sorted;
    }


    /**
     * Helper method that reads a file line by line and puts in a really long string
     * @param f File to read
     * @return looong string with words from the file
     * @throws IOException
     */
    public static StringBuilder readFile(File f) throws IOException {
        String currentLine; // current line of the string
        StringBuilder longAssString = new StringBuilder();
        BufferedReader inFile = new BufferedReader(new FileReader(f));

        // read file and add to the long string
        while ((currentLine = inFile.readLine()) != null) {
            longAssString.append(currentLine);
        } // while

        inFile.close();
        return longAssString;
    } // readFile


    /**
     * Helper method that breaks up the inputted long string into sequences
     * @param lengthWord length of the word sequence
     * @param longAssString String with all the words in the file
     * @return a set of strings with sequences of words
     */
    public static Set<String> sequenceWords(int lengthWord, StringBuilder longAssString){
        String[] temp = (longAssString.toString()).split(" "); // convert String to String[]
        Set<String> sequences = new HashSet<>();

        // get rid of all the non alphanumeric chars and also uppercase it
        for (int i = 0; i < temp.length; i++) {
            temp[i] = temp[i].replaceAll("\\W","").toUpperCase();
        }

        // create sequences of words and put into set
        for (int i = 0; i < temp.length - lengthWord - 1; i++) {
            StringBuilder seq = new StringBuilder();
            seq.append(temp[i]);
            for (int j = i + 1; j < lengthWord + i - 1 ; j++) {
                seq.append(temp[j]);
            }
            sequences.add(seq.toString());
        }
        return sequences;
    } // sequenceWords

} // cheaters