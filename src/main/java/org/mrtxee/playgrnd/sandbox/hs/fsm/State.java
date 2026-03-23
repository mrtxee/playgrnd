package org.mrtxee.playgrnd.sandbox.hs.fsm;

import java.util.HashMap;

public class State {
    private final int id;
    private final HashMap<Character, State> transitions = new HashMap<>();
    private boolean fin;

    public State(int id, boolean fin) throws ExceptionInInitializerError {
        //if (id==0) throw new ExceptionInInitializerError("id=0 is forbidden");
        this.id = id;
        this.fin = fin;
    }

    public State(int id) {
        this(id, false);
    }

    public static State[] getEmptyStateArr(int len) {
        State[] states = new State[len];
        for (int i = 0; i < len; i++) {
            states[i] = new State(i);
        }
        return states;
    }

    public boolean isFin() {
        return fin;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public void makeFinal() {
        this.setFin(true);
    }

    public int getId() {
        return id;
    }

    public HashMap<Character, State> getTransitions() {
        return transitions;
    }

    @Override
    public String toString() {
        return "S" + id + ((this.fin) ? "F" : "");
    }

    public void addTransition(char ch, State nextState) {
        this.transitions.put(ch, nextState);
    }

    public State getNexState(char ch) {
        return this.transitions.getOrDefault(ch, null);
    }

    public void printTransitions() {
        System.out.print(this + ": ");
        for (Object x : transitions.keySet()) {
            System.out.print(x + "->" + transitions.get(x) + "; ");
        }
        System.out.println();
    }

    public boolean hasNextStateByChar(char ch) {
        return null != this.transitions.get(ch);
    }

    public State getNextStateIDByChar(char ch) {
        return this.transitions.get(ch);
    }

}
