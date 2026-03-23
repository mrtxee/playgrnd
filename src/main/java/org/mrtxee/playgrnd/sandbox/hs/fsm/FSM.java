package org.mrtxee.playgrnd.sandbox.hs.fsm;

import java.util.Optional;
import java.util.logging.Logger;

public class FSM {
    private static Logger log;
    private State currentState = null;
    private State[] states;

    public FSM(State[] states) {
        this.setStates(states);
        this.currentState = states[0];
        log = Logger.getLogger(this.getClass().getName());
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void resetCurrentStatePointer() {
        setCurrentState(states[0]);
    }

    public void setStates(State[] states) {
        if (states.length < 1) throw new ExceptionInInitializerError("null length  states list provided");
        this.states = states;
    }

    public void printFSMScheme() {
        for (State st : states) {
            st.printTransitions();
        }
    }

    public Optional<State> getStateByString(String input) {
        this.resetCurrentStatePointer();
        StringBuilder logMessage = new StringBuilder(input + ": ");
        for (int i = 0; i < input.length(); i++) {
            logMessage.append(String.format("%s+", getCurrentState()));
            if (!this.currentState.hasNextStateByChar(input.charAt(i))) {
                log.info(logMessage + String.format("%c->NULL (no transition found); ", input.charAt(i)));
                return Optional.empty();
            }
            setCurrentState(this.currentState.getNextStateIDByChar(input.charAt(i)));
            logMessage.append(String.format("%c->%s; ", input.charAt(i), getCurrentState()));
        }
        log.info(logMessage.toString());
        return Optional.ofNullable(this.currentState);
    }

}
