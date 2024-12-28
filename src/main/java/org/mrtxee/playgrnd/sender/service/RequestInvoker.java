package org.mrtxee.playgrnd.sender.service;

import lombok.RequiredArgsConstructor;
import org.mrtxee.playgrnd.sender.api.Sender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestInvoker {

  private final DataBin dataBin;

  public <R> Sender<R> invoke(Class<R> responseType) {
    return new SenderImpl<R>(responseType, dataBin);
  }
}
