package org.mrtxee.playgrnd.sandbox.sender.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class RequestDto {
  String name;
  String bin;
  int age;
  boolean isPrivate;
}
