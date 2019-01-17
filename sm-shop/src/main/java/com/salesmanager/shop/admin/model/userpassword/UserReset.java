package com.salesmanager.shop.admin.model.userpassword;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class UserReset {

  static final String CHAR_LIST_WITHNUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-";
  static final int RANDOM_STRING_LENGTH = 10;

  private UserReset() {}

  public static String generateRandomString() throws NoSuchAlgorithmException {
    StringBuilder randStr = new StringBuilder();
    for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
      int number = getRandomNumber();
      char ch = CHAR_LIST_WITHNUM.charAt(number);
      randStr.append(ch);
    }
    return randStr.toString();
  }
  
  public static String generateRandomString(int length) throws NoSuchAlgorithmException {
    StringBuilder randStr = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int number = getRandomNumber();
      char ch = CHAR_LIST.charAt(number);
      randStr.append(ch);
    }
    return randStr.toString();
  }

  private static int getRandomNumber() throws NoSuchAlgorithmException {
    Random randomGenerator = SecureRandom.getInstanceStrong();
    int randomInt = randomGenerator.nextInt(CHAR_LIST.length());
    if (randomInt - 1 == -1) {
      return randomInt;
    }
    return randomInt - 1;
  }

  
}