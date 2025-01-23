package org.mrtxee.playgrnd.sender.controller;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.lang3.SerializationUtils;

public class Stuff {
  public static void main(String[] args) {

    Serializable[] data = new Serializable[9];
    byte[] bb = SerializationUtils.serialize(data);

    System.out.println(Arrays.toString(bb));

  }
}
