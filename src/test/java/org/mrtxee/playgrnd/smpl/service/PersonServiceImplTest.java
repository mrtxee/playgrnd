package org.mrtxee.playgrnd.smpl.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mrtxee.playgrnd.smpl.api.Person;
import org.mrtxee.playgrnd.smpl.dto.PersonDto;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

  @Mock
  private IdProvider idProviderMock;

  @InjectMocks
  private PersonServiceImpl sut;

  @BeforeEach
  void setUp() {
    sut = new PersonServiceImpl(idProviderMock);
  }

  @Test
  void givenAge18_whenIsAdult_thanFalse() {
    // given
    Person person = new PersonDto("John", 18);
    // when
    boolean actual = sut.isAdult(person);
    // then
    assertTrue(actual);
  }

  @Test
  @DisplayName("Тест совершеннолетия")
  void givenBelow18_whenIsAdult_thanFalse() {
    // given
    Person person = new PersonDto("John", 1);
    // when
    boolean actual = sut.isAdult(person);
    // then
    assertFalse(actual);
  }

  @Test
  void givenPerson_whenGetIdentifier_thanMockedValue() {
    // given
    final Person person = new PersonDto("John", 90);
    final String expected = "expectedIdentifier";
    when(idProviderMock.getIdentifier(any())).thenReturn(expected);
    // when
    String actual = sut.getIdentifier(person);
    // then
    assertEquals(expected, actual);
  }

  @Test
  void givenPerson_whenGetIdentifier_thanSpecialCaseValue() {
    // given
    final Person person = null;
    final String expected = "00000000-0000-0000-0000-000000000000";
    PersonServiceImpl sut = new PersonServiceImpl(new IdProvider());
    // when
    String actual = sut.getIdentifier(person);
    // then
    assertEquals(expected, actual);
  }

}