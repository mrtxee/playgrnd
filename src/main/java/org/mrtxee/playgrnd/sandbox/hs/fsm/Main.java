package org.mrtxee.playgrnd.sandbox.hs.fsm;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;

public class Main {
//    public static boolean initFlag = false;
//
//    public static void emailFSMCommonTest(String input, State correctOutput, FSM fsa) {
//        State output = fsa.getStateByString(input).orElse(new State(1));
//        System.out.printf("test %s for %s: %s ? %s%n", (correctOutput == output) ? "PASSED" : "FAILED", input, output, correctOutput);
//    }
//
//    public static void emailValidatorCommonTest(String input, boolean correctOutput, EmailValidator emailValidator) {
//        boolean output = emailValidator.validate(input);
//        System.out.printf("test %s for %s: %s ? %s%n", (correctOutput == output) ? "PASSED" : "FAILED", input, output, correctOutput);
//    }
//
//    public static void appInit() {
//        // disable mandatory console output
//        LogManager.getLogManager().reset();
//        // set standard log message format
//        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
//        initFlag = true;
//    }
//
//    public static void startLogger(String loggerName, String fileName) {
//        if (!initFlag) appInit();
//        Logger logger = Logger.getLogger(loggerName);
//        try {
//            // This block configure the logger with handler and formatter
//            FileHandler fileHandler = new FileHandler(fileName, true);
//            fileHandler.setFormatter(new SimpleFormatter());
//            fileHandler.setLevel(Level.INFO);
//            logger.addHandler(fileHandler);
//
//            Handler consoleHandler = new ConsoleHandler();
//            consoleHandler.setLevel(Level.WARNING);
//            logger.addHandler(consoleHandler);
//
//            // the following statement is used to log any messages
//            logger.info(String.format(" - - %s start - - ", loggerName));
//        } catch (SecurityException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void emailFSMTestList() {
//        State[] states = EmailValidator.getStatesForEmailFSM();
//        FSM fsa = new FSM(states);
//        emailFSMCommonTest("rvanat@mail.ru", states[6], fsa);
//        emailFSMCommonTest("rvanat@mail.mail.mail.mail.ru", states[6], fsa);
//        emailFSMCommonTest("rvanat@mail.mail.ma-____il.ma4134il.ruw", states[7], fsa);
//        emailFSMCommonTest("rvanat@mail.ruwe", states[8], fsa);
//        emailFSMCommonTest("r.v.a_--___.nat@mail.ruwerxx", null, fsa);
//        emailFSMCommonTest("", states[0], fsa);
//        emailFSMCommonTest("@", null, fsa);
//        emailFSMCommonTest(".ЫЫ", null, fsa);
//        emailFSMCommonTest("rvanaЫt@mail.ru", null, fsa);
//        emailFSMCommonTest("rvanat@ma!il.ru", null, fsa);
//    }
//
//    public static void emailValidatorTestList() {
//        EmailValidator emailValidator = new EmailValidator();
//        emailValidatorCommonTest("rvanat@mail.ru", true, emailValidator);
//        emailValidatorCommonTest("rvanat@mail.mail.mail.mail.ru", true, emailValidator);
//        emailValidatorCommonTest("rvanat@mail.mail.ma-____il.ma4134il.ruw", true, emailValidator);
//        emailValidatorCommonTest("rvanat@mail.ruwe", true, emailValidator);
//        emailValidatorCommonTest("r.v.a_--___.nat@mail.ruwerxx", false, emailValidator);
//        emailValidatorCommonTest("", false, emailValidator);
//        emailValidatorCommonTest("@", false, emailValidator);
//        emailValidatorCommonTest(".ЫЫ", false, emailValidator);
//        emailValidatorCommonTest("rvanaЫt@mail.ru", false, emailValidator);
//        emailValidatorCommonTest("rvanat@ma!il.ru", false, emailValidator);
//    }
//
//    public static void statesToJSONConfigFileTest(String fsmStatesFileName) {
//        FSMBuilder fb = new FSMBuilder();
//        fb.statesToJSONFile(fsmStatesFileName, EmailValidator.getStatesForEmailFSM());
//    }
//
//    public static void emailValidatorJsonConfigTestList(String fsmStatesFileName) throws FileNotFoundException {
//        EmailValidator emailValidator = new EmailValidator(FSMBuilder.parseStatesFromJSONFile(fsmStatesFileName));
//        emailValidatorCommonTest("rvanat@mail.ru", true, emailValidator);
//        emailValidatorCommonTest("rvanat@mail.mail.mail.mail.ru", true, emailValidator);
//        emailValidatorCommonTest("rvanat@mail.mail.ma-____il.ma4134il.ruw", true, emailValidator);
//        emailValidatorCommonTest("rvanat@mail.ruwe", true, emailValidator);
//        emailValidatorCommonTest("r.v.a_--___.nat@mail.ruwerxx", false, emailValidator);
//        emailValidatorCommonTest("", false, emailValidator);
//        emailValidatorCommonTest("@", false, emailValidator);
//        emailValidatorCommonTest(".ЫЫ", false, emailValidator);
//        emailValidatorCommonTest("rvanaЫt@mail.ru", false, emailValidator);
//        emailValidatorCommonTest("rvanat@ma!il.ru", false, emailValidator);
//    }
//
//    public static void objectToJSONFile(Object obj, String fileName) {
//        // pretty print
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        //Gson gson = new Gson();
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(fileName);
//            BufferedWriter bw = new BufferedWriter(fw);
//            gson.toJson(obj, bw);
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
//    public static State[] getStatesFromFile(String fileName) throws FileNotFoundException {
//        Type listType = new TypeToken<State[]>() {
//        }.getType();
//        return (new Gson()).fromJson(new FileReader(fileName), listType);
//    }
//
//    public static void printStates(State[] states) {
//        for (State st : states) {
//            st.printTransitions();
//        }
//    }
//
//    public static void gsonTest() {
//        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        appInit();
//        String fsmStatesFileName = System.getProperty("user.dir") + "\\emailValidatorFSM.json";
//
//        statesToJSONConfigFileTest(fsmStatesFileName);
//        emailValidatorJsonConfigTestList(fsmStatesFileName);
//
//        //        emailValidatorTestList();
//        //        emailFSMTestList();
//    }
}
