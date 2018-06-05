package org.petprojects.java.Editor.listeners;

import org.petprojects.java.Editor.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;

/**
 * TextEditMenuListener class
 */
public class TextEditMenuListener implements MenuListener {

    private View view;

    public TextEditMenuListener(View view) {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        //From passed parameter receive object upon which action was taken
        JMenu jMenu = (JMenu) e.getSource();
        //Get list of components (menu items) from received menu
        Component[] components = jMenu.getMenuComponents();
        //For each menu item calls method setEnabled passing method isHtmlTabSelected() call result as a result
        for (Component component : components) {
            component.setEnabled(view.isHtmlTabSelected());
        }

    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}