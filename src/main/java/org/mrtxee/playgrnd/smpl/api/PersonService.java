package org.mrtxee.playgrnd.smpl.api;

public interface PersonService {

  boolean isAdult(Person person);

  String getIdentifier(Person person);
}
