package org.mrtxee.playgrnd.smpl.service;

import org.mrtxee.playgrnd.smpl.api.Person;
import org.mrtxee.playgrnd.smpl.api.PersonService;

public class PersonServiceImpl implements PersonService {

  private final IdProvider idProvider;

  public PersonServiceImpl(IdProvider idProvider) {
    this.idProvider = idProvider;
  }

  @Override
  public boolean isAdult(Person person) {
    return person.getAge() >= 18;
  }

  @Override
  public String getIdentifier(Person person) {
    return idProvider.getIdentifier(person);
  }

}
