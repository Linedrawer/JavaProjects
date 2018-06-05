package org.petprojects.java.ATM.command;


import org.petprojects.java.ATM.exception.InterruptOperationException;

interface Command {

  void execute() throws InterruptOperationException;
}
