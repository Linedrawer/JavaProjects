package org.petprojects.java.Tetris;

import java.awt.event.KeyEvent;

/**
 *  Tetris class contains main game functionality.
 */
public class Tetris
{

    private Field field;                //Cell filed
    private Figure figure;              //Figure

    private boolean isGameOver;         //Game over

    public Tetris(int width, int height)
    {
        field = new Field(width, height);
        figure = null;
    }

    /**
     * Field getter.
     */
    public Field getField()
    {
        return field;
    }

    /**
     * Figure getter.
     */
    public Figure getFigure()
    {
        return figure;
    }

    /**
     *  Game main loop
     *  All main key game actions take place here
     */
    public void run() throws Exception
    {
        //Create keyboardObserver object and start it.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //initialise isGameOver boolean to false
        isGameOver = false;
        //Create first figure on the top in the middle: x - half of field width, y - 0.
        figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0);

        while (!isGameOver)
        {
            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();
                //If 'q' is pressed - quit game
                if (event.getKeyChar() == 'q') return;
                //If 'left arrow' is pressed - move figure left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    figure.left();
                //If 'right arrow' is pressed - move figure right
                else if (event.getKeyCode() ==  KeyEvent.VK_RIGHT)
                    figure.right();
                //If 'r' is pressed - rotate figure
                else if (event.getKeyCode() ==  KeyEvent.VK_R)
                    figure.rotate();
                //'space bar' - drop figure down
                else if (event.getKeyCode() ==  KeyEvent.VK_SPACE)
                    figure.downMaximum();
            }

            step();             //make step
            field.print();      //print field current status
            Thread.sleep(300);  //pause 300 ms - 0.3 s
        }

        System.out.println("Game Over");
    }

    private void step()
    {
        //move figure down
        figure.down();

        //if its not possible to move figure down
        if (!figure.isCurrentPositionAvailable())
        {
            figure.up();                    //move figure up
            figure.landed();                //land figure

            isGameOver = figure.getY() <= 1;//if figure is landed on top of the field the game is over

            field.removeFullLines();

            figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0); //create new figure
        }
    }

    public static Tetris game;
    public static void main(String[] args) throws Exception
    {
        game = new Tetris(10, 20);
        game.run();
    }
}
