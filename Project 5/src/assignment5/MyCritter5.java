/* CRITTERS MyCritter5.java
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

public class MyCritter5 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int dir = getRandomInt(8);
        run(dir);
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 15) return true;
        return false;
    }

    public Color viewColor(){ return Color.PEACHPUFF; }

    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    public String toString() {
        return "5";
    }
}
