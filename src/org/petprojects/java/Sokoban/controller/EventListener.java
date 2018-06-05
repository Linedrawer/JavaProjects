package org.petprojects.java.Sokoban.controller;

import org.petprojects.java.Sokoban.model.Direction;


public interface EventListener
{
    void move(Direction direction);

    void restart();

    void startNextLevel();

    void levelCompleted(int level);
}
