package org.petprojects.java.ATM.command;

import org.petprojects.java.ATM.CashMachine;
import org.petprojects.java.ATM.ConsoleHelper;
import org.petprojects.java.ATM.exception.InterruptOperationException;

import java.util.ResourceBundle;


class ExitCommand implements Command {

  private final ResourceBundle res = ResourceBundle
      .getBundle(CashMachine.RESOURCE_PATH + "exit_en");

  @Override
  public void execute() throws InterruptOperationException {
    ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));

    if (ConsoleHelper.readString().equals(res.getString("yes"))) {
      ConsoleHelper.writeMessage(res.getString("thank.message"));
    }
  }
}
