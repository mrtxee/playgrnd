package org.mrtxee.playgrnd.sender.service;

import com.google.common.base.MoreObjects;
import lombok.extern.slf4j.Slf4j;
import org.mrtxee.playgrnd.sender.api.Sender;
import org.mrtxee.playgrnd.sender.dto.RequestDto;
import org.apache.commons.lang3.SerializationUtils;

@Slf4j
public class SenderImpl<R> implements Sender<R> {
  private final DataBin dataBin;
  private final RequestDto request;

  public SenderImpl(DataBin dataBin) {
    this.dataBin = dataBin;
    this.request = getInitialRequest();
  }

  @Override
  public R send() {
    String response = request.toString();
    log.info("we've sent the following message: {}", response);
    byte[] data = SerializationUtils.serialize(response);
    return (R) SerializationUtils.deserialize(data);
  }

  @Override
  public Sender<R> setPrivate(boolean isPrivate){
    request.setPrivate(isPrivate);
    return this;
  }

  @Override
  public Sender<R> setBin(String bin){
    request.setBin(bin);
    return this;
  }

  @Override
  public Sender<R> setAge(int age){
    request.setAge(age);
    return this;
  }

  @Override
  public Sender<R> setName(String name){
    request.setName(name);
    return this;
  }

  private RequestDto getInitialRequest(){
    return RequestDto.builder()
      .age(MoreObjects.firstNonNull(dataBin.getAge(), 10))
      .name("Fedor")
      .bin(dataBin.getFromBin())
      .build();
  }

}
