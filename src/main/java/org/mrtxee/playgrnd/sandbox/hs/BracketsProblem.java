package org.mrtxee.playgrnd.sandbox.hs;

public class BracketsProblem {
    private static void printAllBracketsCombos(String output, int len) {
        int openBracketsCount = output.length() - output.replace("(", "").length();
        int closBracketsCount = output.length() - output.replace(")", "").length();
        if ((openBracketsCount == len) && (closBracketsCount == len)) {
            System.out.println(output);
        } else {
            if (openBracketsCount < len) {
                printAllBracketsCombos(output + "(", len);
            }
            if (closBracketsCount < openBracketsCount) {
                printAllBracketsCombos(output + ")", len);
            }
        }
    }

    public static void main(String[] args) {
        printAllBracketsCombos("", 3);

    }
}
