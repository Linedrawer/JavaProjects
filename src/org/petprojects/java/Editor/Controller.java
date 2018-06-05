package org.petprojects.java.Editor;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {

    private View view;
    private HTMLDocument document;

    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }


    public void init() {
        createNewDocument();
    }

    public void resetDocument() {
        if (document != null) {
            //Delete from current listener document listener of edits which can be cancelled/returned
            document.removeUndoableEditListener(view.getUndoListener());
        }
        //create new document by default and assign it document field
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        //Add edit listener to new document
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    //Write passed text with html tags to document
    public void setPlainText(String text) {
        resetDocument();
        //Create new StringReader based on passed text
        StringReader stringReader = new StringReader(text);

        try {
            new HTMLEditorKit().read(stringReader, document, 0);
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public String getPlainText() {
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0, document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

        return stringWriter.toString();
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML Editor");
        view.resetUndo();
        currentFile = null;

    }

    public void openDocument() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int n = jFileChooser.showOpenDialog(view);

        if (n == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());

            try (FileReader fileReader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(fileReader, document, 0);
                view.resetUndo();
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public void saveDocument() {
        if (currentFile == null) {
            saveDocumentAs();
        }
        else {
            view.selectHtmlTab();

            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocumentAs() {

        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        int n = jFileChooser.showSaveDialog(view);

        if (n == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());

            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(fileWriter, document, 0, document.getLength());
            }
            catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }


    public void exit() {
        System.exit(0);
    }


    //psvm
    public static void main(String[] args) {

        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();

    }


    //setters and getters
    public HTMLDocument getDocument() {
        return document;
    }


}
