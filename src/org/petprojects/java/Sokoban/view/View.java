package org.petprojects.java.Sokoban.view;

import org.petprojects.java.Sokoban.controller.Controller;
import org.petprojects.java.Sokoban.controller.EventListener;
import org.petprojects.java.Sokoban.model.GameObjects;

import javax.swing.*;

public class View extends JFrame
{
    private Controller controller;
    private Field field;

    public void setEventListener(EventListener eventListener) {
        field.setEventListener(eventListener);
    }

    public View(Controller controller)
    {
        this.controller = controller;
    }

    public void init()
    {
        field = new Field(this);
        add(field);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Sokoban");
        setVisible(true);
    }

    public void update()
    {
        field.repaint();
    }

    public GameObjects getGameObjects()
    {
        return controller.getGameObjects();
    }

    public void completed(int level)
    {
        update();
        JOptionPane.showMessageDialog(field, null, "Level " + level + " completed!", JOptionPane.INFORMATION_MESSAGE);
        controller.startNextLevel();
    }
}
