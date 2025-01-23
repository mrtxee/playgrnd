package org.mrtxee.playgrnd.sandbox.generics;

import org.mrtxee.playgrnd.sandbox.generics.dto.Par;

public class Testy {

  <P>Par<P> doIt(){
    Par<P> p = new Par<P>();
    System.out.println("my type is "+p.getClass().getName());
    return p;
  }

  public void doItS(){
    this.<String>doIt().setValue("s");
  }

  public void doItP(){
    this.<Integer>doIt().setValue(9);
  }

}
