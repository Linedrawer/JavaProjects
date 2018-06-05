package org.petprojects.java.Snake;

import java.util.ArrayList;

/**
 * Класс змея
 */
public class Snake
{
    //Snake movement direction
    private SnakeDirection direction;
    //Boolean to indicate whether snake is alive
    private boolean isAlive;
    //List of all snake sections.
    private ArrayList<SnakeSection> sections;

    public Snake(int x, int y)
    {
        sections = new ArrayList<>();
        sections.add(new SnakeSection(x, y));
        isAlive = true;
    }

    public boolean isAlive()
    {
        return isAlive;
    }

    public int getX()
    {
        return sections.get(0).getX();
    }

    public int getY()
    {
        return sections.get(0).getY();
    }

    public void setDirection(SnakeDirection direction)
    {
        this.direction = direction;
    }

    public ArrayList<SnakeSection> getSections()
    {
        return sections;
    }

    /**
     * Method moves snake by one step.
     * Snake movement depends on direction variable
     */
    public void move()
    {
        if (!isAlive) return;

        if (direction == SnakeDirection.UP)
            move(0, -1);
        else if (direction == SnakeDirection.RIGHT)
            move(1, 0);
        else if (direction == SnakeDirection.DOWN)
            move(0, 1);
        else if (direction == SnakeDirection.LEFT)
            move(-1, 0);
    }

    /**
     * Method moves snake to next cell of field
     * Cell coordinates are set relatively to its head - dx, dy variable
     */
    private void move(int dx, int dy)
    {
        //create new snake head - new snake section
        SnakeSection head = sections.get(0);
        head = new SnakeSection(head.getX() + dx, head.getY() + dy);

        //check whether head is out of field border
        checkBorders(head);
        if (!isAlive) return;

        //check whether snake intersects itself

        checkBody(head);
        if (!isAlive) return;

        //Check whether snake has eaten mouse
        Mouse mouse = Room.game.getMouse();
        if (head.getX() == mouse.getX() && head.getY() == mouse.getY()) //has eaten
        {
            sections.add(0, head);                  //add new head
            Room.game.eatMouse();                   //Tail remains, new mouse is created
        }
        else //snake moves
        {
            sections.add(0, head);                  //add new head
            sections.remove(sections.size() - 1);   //delete last element from snake tail
        }
    }

    /**
     *  Method checks whether new head is in bounds of field.
     */
    private void checkBorders(SnakeSection head)
    {
        if ((head.getX() < 0 || head.getX() >= Room.game.getWidth()) || head.getY() < 0 || head.getY() >= Room.game.getHeight())
        {
            isAlive = false;
        }
    }

    /**
     *  Method checks whether head intersects with another section of snake.
     */
    private void checkBody(SnakeSection head)
    {
        if (sections.contains(head))
        {
            isAlive = false;
        }
    }
}
