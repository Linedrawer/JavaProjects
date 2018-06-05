package org.petprojects.java.Sokoban.view;

import org.petprojects.java.Sokoban.controller.EventListener;
import org.petprojects.java.Sokoban.model.Direction;
import org.petprojects.java.Sokoban.model.GameObject;
import org.petprojects.java.Sokoban.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Field extends JPanel
{
    private View view;
    private EventListener eventListener;

    public void setEventListener(EventListener eventListener)
    {
        this.eventListener = eventListener;
    }

    public Field(View view)
    {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        addKeyListener(keyHandler);
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);
        GameObjects gameObjects = view.getGameObjects();
        for (GameObject gameObject : gameObjects.getAll())
        {
            gameObject.draw(g);
        }
    }

    public class KeyHandler extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_R:
                    eventListener.restart();
                    break;
            }
        }
    }
}
