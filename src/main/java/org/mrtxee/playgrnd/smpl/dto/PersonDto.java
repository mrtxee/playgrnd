package org.mrtxee.playgrnd.smpl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.mrtxee.playgrnd.smpl.api.Person;

@Data
@AllArgsConstructor
public class PersonDto implements Person {
  private String name;
  private int age;
}
