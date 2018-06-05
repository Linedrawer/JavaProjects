package org.petprojects.java.Editor;


import org.petprojects.java.Editor.listeners.FrameListener;
import org.petprojects.java.Editor.listeners.TabbedPaneChangeListener;
import org.petprojects.java.Editor.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener  {

    private Controller controller;

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);


    //two tab panel
    private JTabbedPane tabbedPane = new JTabbedPane();
    //visual html editing tool
    private JTextPane htmlTextPane = new JTextPane();
    // html editing text component
    private JEditorPane plainTextPane = new JEditorPane();


    //constructor
    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        } catch (ClassNotFoundException e) {
            ExceptionHandler.log(e);
        }
    }



    //methods
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {

            case "New":
                controller.createNewDocument();
                break;
            case "Open":
                controller.openDocument();
                break;
            case "Save":
                controller.saveDocument();
                break;
            case "Save as...":
                controller.saveDocumentAs();
                break;
            case "Exit":
                controller.exit();
                break;
            case "About":
                showAbout();
                break;
        }
    }


    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        setVisible(true);

    }

    public void initMenuBar() {
        //menu bar
        JMenuBar jMenuBar = new JMenuBar();
        //menu initialisation
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);

        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    public void initEditor() {

        htmlTextPane.setContentType("text/html");

        tabbedPane.addTab("HTML", new JScrollPane(htmlTextPane));

        tabbedPane.addTab("Text", new JScrollPane(plainTextPane));

        tabbedPane.setPreferredSize(new Dimension(800, 600));

        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));

        getContentPane().add(tabbedPane,BorderLayout.CENTER);


    }

    public void initGui() {
        initMenuBar();
        initEditor();

        pack();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void undo() {

        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab() {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void selectedTabChanged() {
        if (isHtmlTabSelected()) {
            controller.setPlainText(plainTextPane.getText());
        }
        else {
            plainTextPane.setText(controller.getPlainText());
        }
        resetUndo();
    }

    public void update() {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this, "HTML Editor created by Linedrawer", "About", JOptionPane.INFORMATION_MESSAGE);
    }



    public void exit() {
        controller.exit();
    }

    public Controller  getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }
}
