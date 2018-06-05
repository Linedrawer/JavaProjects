package org.petprojects.java.Editor.listeners;

import org.petprojects.java.Editor.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * TabbedPaneChangeListener class responsible for listening and processing changes of states of tab panel.
 **/
public class TabbedPaneChangeListener implements ChangeListener {

    private View view;


    public TabbedPaneChangeListener(View view) {
        this.view = view;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        view.selectedTabChanged();
    }
}
