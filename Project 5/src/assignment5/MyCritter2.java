/* CRITTERS MyCritter2.java
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

public class MyCritter2 extends Critter.TestCritter {

    @Override
    public void doTimeStep() {
        walk(2);
    }

    @Override
    public boolean fight(String opponent) {
        if (getEnergy() > 5) return true;
        return false;
    }

    @Override
    public CritterShape viewShape() {
        return CritterShape.TRIANGLE;
    }

    public Color viewColor(){ return Color.YELLOW; }

    public String toString() {
        return "2";
    }
}