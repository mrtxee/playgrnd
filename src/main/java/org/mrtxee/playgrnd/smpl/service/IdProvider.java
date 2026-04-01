package org.mrtxee.playgrnd.smpl.service;

import java.util.Objects;
import java.util.UUID;

public class IdProvider {

  public String getIdentifier(Object object) {
    if (Objects.isNull(object)) {
      return new UUID(0, 0).toString();
    }
    return UUID.randomUUID().toString();
  }

}
