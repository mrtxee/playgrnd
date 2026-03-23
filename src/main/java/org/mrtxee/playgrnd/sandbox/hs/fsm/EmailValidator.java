package org.mrtxee.playgrnd.sandbox.hs.fsm;

import java.util.HashMap;
import java.util.Map;

public class EmailValidator extends FSM {
  public EmailValidator() {
    super(getStatesForEmailFSM());
  }

  public EmailValidator(State[] config) {
    super(config);
  }

  protected static State[] getStatesForEmailFSM() {
    Map<String, String> dictionary = new HashMap<>();
    dictionary.put("letters", "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");
    dictionary.put("digits", "1234567890");
    dictionary.put("symbols", "-_");
    dictionary.put("at", "@");
    dictionary.put("dot", ".");

    State[] states = State.getEmptyStateArr(9);
    for (char ch : dictionary.get("letters").toCharArray()) {
      states[0].addTransition(ch, states[1]);
      states[2].addTransition(ch, states[3]);
      states[4].addTransition(ch, states[5]);
      states[5].addTransition(ch, states[6]);
      states[6].addTransition(ch, states[7]);
      states[7].addTransition(ch, states[8]);
    }
    for (char ch : dictionary.get("dot").toCharArray()) {
      states[1].addTransition(ch, states[0]);
    }
    for (char ch : (dictionary.get("letters") + dictionary.get("digits")
        + dictionary.get("symbols")).toCharArray()) {
      states[1].addTransition(ch, states[1]);
      states[3].addTransition(ch, states[3]);
    }
    for (char ch : dictionary.get("at").toCharArray()) {
      states[1].addTransition(ch, states[2]);
    }
    for (char ch : dictionary.get("dot").toCharArray()) {
      states[3].addTransition(ch, states[4]);
      states[5].addTransition(ch, states[4]);
      states[6].addTransition(ch, states[4]);
      states[7].addTransition(ch, states[4]);
      states[8].addTransition(ch, states[4]);
    }
    for (char ch : (dictionary.get("digits") + dictionary.get("symbols")).toCharArray()) {
      states[4].addTransition(ch, states[3]);
      states[5].addTransition(ch, states[3]);
      states[6].addTransition(ch, states[3]);
      states[7].addTransition(ch, states[3]);
      states[8].addTransition(ch, states[3]);
    }
    states[5].setFin(true);
    states[6].setFin(true);
    states[7].setFin(true);
    states[8].setFin(true);

    return states;
  }

  public boolean validate(String emailCandidate) {
    return (getStateByString(emailCandidate).orElse(new State(0))).isFin();
  }
}
