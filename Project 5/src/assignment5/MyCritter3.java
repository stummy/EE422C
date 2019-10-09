/* CRITTERS MyCritter3.java
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

public class MyCritter3 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        int dir = getRandomInt(8);
        walk(dir);

        MyCritter3 offspring = new MyCritter3();
        reproduce(offspring,0);
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 10) return true;
        return false;
    }

    public Color viewColor(){ return Color.BLUEVIOLET; }

    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    public String toString() {
        return "3";
    }
}