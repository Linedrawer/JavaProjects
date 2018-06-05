package org.petprojects.java.ATM.command;


import org.petprojects.java.ATM.CashMachine;
import org.petprojects.java.ATM.ConsoleHelper;
import org.petprojects.java.ATM.exception.InterruptOperationException;

import java.util.ResourceBundle;


public class LoginCommand implements Command {

  private final ResourceBundle validCreditCards = ResourceBundle
      .getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
  private final ResourceBundle res = ResourceBundle
      .getBundle(CashMachine.RESOURCE_PATH + "login_en");

  @Override
  public void execute() throws InterruptOperationException {
    ConsoleHelper.writeMessage(res.getString("before"));
    while (true) {
      ConsoleHelper.writeMessage(res.getString("specify.data"));
      String s1 = ConsoleHelper.readString();
      String s2 = ConsoleHelper.readString();
      if (validCreditCards.containsKey(s1)) {
        if (validCreditCards.getString(s1).equals(s2)) {
          ConsoleHelper.writeMessage(String.format(res.getString("success.format"), s1));
        } else {
          ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
          ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
          continue;
        }
      } else {
        ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), s1));
        ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
        continue;
      }

      break;
    }

  }
}
