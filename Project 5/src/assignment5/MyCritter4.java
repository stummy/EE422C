/* CRITTERS MyCritter4.java
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

import java.util.*;

public class MyCritter4 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        walk(6);
    }

    @Override
    public boolean fight(String opponent) {
        return true;
    }

    public Color viewColor(){ return Color.RED; }

    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    public String toString() {
        return "4";
    }
}