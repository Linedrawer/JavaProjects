package org.petprojects.java.Editor.actions;


import org.petprojects.java.Editor.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * UndoAction class
 */
public class UndoAction extends AbstractAction {

    private View view;

    public UndoAction(View view) {
        this.view = view;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }
}

