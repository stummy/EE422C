/* CRITTERS WorldView.java
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

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class WorldView {
	protected static int tileSize = 10;

	public static void setTileSize(){
		if(Params.world_width >= 50){
			tileSize = 10;
		}
		else if(Params.world_width > 30 && Params.world_width <= 50){
			tileSize = 20;
		}
		else if(Params.world_width > 10 && Params.world_width <= 30){
			tileSize = 40;
		}
		else if(Params.world_width > 0 && Params.world_width <= 10){
			tileSize = 50;
		}
	}


	/*
	 * Returns a square or a circle, according to shapeIndex
	 */
	static Shape getIcon(Critter.CritterShape shape) {
		Shape s = null;
		int shapeIndex = 0;

		if(shape.equals(Critter.CritterShape.CIRCLE)){
			shapeIndex = 0;
		}
		else if(shape.equals(Critter.CritterShape.SQUARE)){
			shapeIndex = 1;
		}
		else if(shape.equals(Critter.CritterShape.TRIANGLE)){
			shapeIndex = 2;
		}
		else if(shape.equals(Critter.CritterShape.DIAMOND)){
			shapeIndex = 3;
		}
		else if(shape.equals(Critter.CritterShape.STAR)){
			shapeIndex = 4;
		}
		
		switch(shapeIndex) {
			case 0: // Circle
				s = new Circle(tileSize/2);
				break;
			case 1: // square
				s = new Rectangle(tileSize,tileSize);
				break;
			case 2: // triangle
				s = drawTriangle();
				break;
			case 3: // diamond
				s = drawDiamond();
				break;
			case 4: // star
				s = drawStar();
				break;
			default:
				break;

		}
		// set the outline of the shape
		s.setStroke(javafx.scene.paint.Color.WHITE); // outline
		return s;
	}

	public static void drawCritter(int x_coord, int y_coord, Critter.CritterShape shape, Color outline, Color fill){

		Shape sh = getIcon(shape);
		sh.setFill(fill);
		sh.setStroke(outline);

		if(x_coord == 0 && y_coord == 0){
			Main.critGrid.add(sh, x_coord,y_coord );
		}
		else if(x_coord == 0){
			Main.critGrid.add(sh, x_coord,y_coord -1);
		}
		else if(y_coord == 0) {
			Main.critGrid.add(sh, x_coord-1,y_coord);
		}
		else{
			Main.critGrid.add(sh, x_coord-1,y_coord -1);
		}
	}

	public static void drawGrid(){
		for(int i = 0; i < Params.world_height*tileSize; i+=tileSize){
			for (int j = 0; j < Params.world_width*tileSize; j+=tileSize) {
				Rectangle tile = new Rectangle(tileSize, tileSize);
				tile.setStroke(Color.GREY);
				tile.setFill(Color.BLACK);
				Main.worldGrid.add(tile, i, j);
			}
		}
	}

	/* Paints the shape on a grid. */
	public static void paint() {
		Main.critGrid.getChildren().clear();
		Main.worldGrid.getChildren().clear();
		drawGrid();
	}

	private static Shape drawTriangle(){
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(
				1.0, 1.0,
				(double)tileSize - 1.0, 1.0,
				(double)(tileSize/2), (double)tileSize - 1.0);
		return triangle;
	}

	private static Shape drawDiamond(){
		Polygon diamond = new Polygon();
		diamond.getPoints().addAll(
				1.0, (double)(tileSize/2),
				(double)(tileSize/2), (double)tileSize - 1.0,
				(double)tileSize - 1.0, (double)(tileSize/2),
				(double)(tileSize/2), 1.0);
		return diamond;
	}

	private static Shape drawStar(){
		Polygon star = new Polygon();
		star.getPoints().addAll(
				1.0, (double)tileSize/2.0,
				(double)tileSize/3.0, (double)tileSize*(2.0/3.0),
				(double)tileSize/3.0, (double)tileSize-1.0,
				(double)tileSize*(2.0/3.0), (double)tileSize*(2.0/3.0),
				(double)tileSize-1.0, (double)tileSize-1.0,
				(double)tileSize*(2.0/3.0), (double)tileSize/2.0,
				(double)tileSize-1.0, 1.0,
				(double)tileSize*(2.0/3.0), (double)tileSize/3.0,
				(double)tileSize/3.0, 1.0,
				(double)tileSize/3.0,(double)tileSize/3.0);
		return star;
	}
}
