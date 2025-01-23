package org.mrtxee.playgrnd.sandbox.generics.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Par<P> {
  private P value;
}
