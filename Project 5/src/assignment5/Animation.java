/* CRITTERS Animation.java
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

public abstract class Animation extends AnimationTimer {
    private long shleep = 0;
    long prevTime = 0;

    public Animation( long sleep) {
        this.shleep = sleep * 1_000_000;
    }

    @Override
    public void handle(long now) {
        if ((now - prevTime) < shleep) {
            return;
        }
        prevTime = now;
        handle();
    }

    public abstract void handle();
}
