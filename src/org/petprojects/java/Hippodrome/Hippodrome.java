package org.petprojects.java.Hippodrome;

import java.util.ArrayList;


public class Hippodrome {

    private static ArrayList<Horse> horses = new ArrayList<>();

    private ArrayList<Horse> getHorses() {
        return horses;
    }

    public static Hippodrome game;

    public static void main(String[] args) throws InterruptedException {

        game = new Hippodrome();

        Horse horse1 = new Horse("horse1", 3, 0);
        Horse horse2 = new Horse("horse2", 3, 0);
        Horse horse3 = new Horse("horse3", 3, 0);

        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        game.run();

    }

    public void run() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(500);
        }

        printWinner();

    }

    public void move() {

        for (Horse horse : horses) {
            horse.move();
        }

    }

    private void print() {

        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();

    }

    private Horse getWinner() {

        Horse winner = null;

        for (Horse h : getHorses()) {
            if (winner == null) {
                winner = h;
            } else {
                if (winner.getDistance() < h.getDistance()) {
                    winner = h;
                }
            }
        }

        return winner;
    }

    private void printWinner() {

        System.out.println("Winner is " + getWinner().getName() + "!");

    }
}
