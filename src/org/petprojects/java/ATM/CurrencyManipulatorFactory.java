package org.petprojects.java.ATM;

import java.util.Collection;
import java.util.HashMap;

@SuppressWarnings("ALL")
public final class CurrencyManipulatorFactory {

  static final HashMap<String, CurrencyManipulator> map = new HashMap<>();
  static boolean isExist = false;

  private CurrencyManipulatorFactory() {
  }

  public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
    isExist = false;
    CurrencyManipulator current;

    if (map.containsKey(currencyCode)) {
      return map.get(currencyCode);
    } else {
      current = new CurrencyManipulator(currencyCode);
      map.put(currencyCode, current);
      return current;
    }
  }

  public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
    return map.values();
  }


}
