package org.petprojects.java.Editor.actions;

import org.petprojects.java.Editor.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *  RedoAction class
 */
public class RedoAction extends AbstractAction {

    private View view;

    public RedoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.redo();
    }
}


