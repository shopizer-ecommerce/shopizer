package com.salesmanager.shop.admin.model.userpassword;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserReset
{
  final static String CHAR_LIST_WITHNUM = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
  final static String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-";

  final static int RANDOM_STRING_LENGTH = 10;
  
  private static final Logger LOGGER = LoggerFactory.getLogger(UserReset.class);

  public static String generateRandomString()
  {
    StringBuilder randStr = new StringBuilder();
    for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
      int number = getRandomNumber();
      char ch = CHAR_LIST_WITHNUM.charAt(number);
      randStr.append(ch);
    }
    return randStr.toString();
  }
  
  public static String generateRandomString(int length)
  {
    StringBuilder randStr = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int number = getRandomNumber();
      char ch = CHAR_LIST.charAt(number);
      randStr.append(ch);
    }
    return randStr.toString();
  }

  private static int getRandomNumber()
  {
    int randomInt = 0;

      UniformRandomProvider randomGenerator = RandomSource.create(RandomSource.MT);
      randomInt = randomGenerator.nextInt(CHAR_LIST.length());

    return randomInt;
  }

  
}