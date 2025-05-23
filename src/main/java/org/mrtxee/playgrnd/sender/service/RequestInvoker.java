package org.mrtxee.playgrnd.sender.service;

import lombok.RequiredArgsConstructor;
import org.mrtxee.playgrnd.sender.api.Sender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestInvoker {

  private final DataProvider dataProvider;

  public <R> Sender<R> invoke() {
    return new <R> SenderImpl<R>(dataProvider);
  }

}
