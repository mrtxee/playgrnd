package org.mrtxee.playgrnd.sender.api;

public interface Sender<R> {
  Sender<R> setPrivate(boolean isPrivate);

  Sender<R> setBin(String bin);

  Sender<R> setAge(int age);

  Sender<R> setName(String name);

  R send();
}
