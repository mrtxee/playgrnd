package org.mrtxee.playgrnd.sandbox.hs.fsm;

/*
* {
    states:[1, 2,...],
    final:[1, 2,...],
    transitions : [
        {
            start: 1
            ch:  'a'
            end : 2
        },
        {...}
    ]
* */

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

public class FSMBuilder {


//    protected static State[] parseStatesFromJSONFile(String fileName) throws FileNotFoundException {
//        StateTransitionSet stt = (new Gson()).fromJson(new FileReader(fileName), StateTransitionSet.class);
//        State[] states = State.getEmptyStateArr(stt.getStatesCount());
//        stt.getTransitions().forEach((transition) -> states[transition.start].addTransition(transition.ch, states[transition.end]));
//        stt.getFinalStates().forEach((finalStateID) -> states[finalStateID].makeFinal());
//        return states;
//    }
//
//    private static void objectToJSONFile(Object obj, String fileName) {
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(fileName);
//            BufferedWriter bw = new BufferedWriter(fw);
//            (new GsonBuilder().setPrettyPrinting().create()).toJson(obj, bw);
//            bw.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fw != null) {
//                    fw.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    protected void statesToJSONFile(String fileName, State[] states) {
//        StateTransitionSet stt = new StateTransitionSet();
//        Arrays.stream(states).forEach((st) -> {
//            stt.addState(st.getId());
//            if (st.isFin()) stt.addFinalState(st.getId());
//            st.getTransitions().keySet().forEach((ch) -> stt.addTransition(st.getId(), st.getTransitions().get(ch).getId(), ch));
//        });
//        objectToJSONFile(stt, fileName);
//    }
//
//    private static class StateTransitionSet {
//        private final List<Integer> states = new LinkedList<>();
//        private final List<Integer> finalStates = new LinkedList<>();
//        private final List<Transition> transitions = new LinkedList<>();
//
//        public int getStatesCount() {
//            return states.size();
//        }
//
//        public List<Transition> getTransitions() {
//            return transitions;
//        }
//
//        private void addState(int state) {
//            states.add(state);
//        }
//
//        private void addFinalState(int state) {
//            finalStates.add(state);
//        }
//
//        private void addTransition(int start, int end, char ch) {
//            transitions.add(new Transition(start, end, ch));
//        }
//
//        public List<Integer> getFinalStates() {
//            return finalStates;
//        }
//
//        private static class Transition {
//            int start;
//            int end;
//            char ch;
//
//            public Transition(int start, int end, char ch) {
//                this.start = start;
//                this.end = end;
//                this.ch = ch;
//            }
//        }
//    }
}
