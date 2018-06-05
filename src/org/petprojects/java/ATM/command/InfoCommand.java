package org.petprojects.java.ATM.command;

import org.petprojects.java.ATM.CashMachine;
import org.petprojects.java.ATM.ConsoleHelper;
import org.petprojects.java.ATM.CurrencyManipulator;
import org.petprojects.java.ATM.CurrencyManipulatorFactory;

import java.util.ResourceBundle;


class InfoCommand implements Command {

  private final ResourceBundle res = ResourceBundle
      .getBundle(CashMachine.RESOURCE_PATH + "info_en");

  @Override
  public void execute() {
    boolean money = false;
    ConsoleHelper.writeMessage(res.getString("before"));
    for (CurrencyManipulator cur : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
      if (cur.hasMoney()) {
        if (cur.getTotalAmount() > 0) {
          ConsoleHelper.writeMessage(cur.getCurrencyCode() + " - " + cur.getTotalAmount());
          money = true;
        }
      }
    }
    if (!money) {
      ConsoleHelper.writeMessage(res.getString("no.money"));
    }
  }
}
