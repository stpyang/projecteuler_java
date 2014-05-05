import gnu.trove.TIntHashSet;
import gnu.trove.TIntObjectHashMap;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/9/13
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem093Main {

    private static final String[] TEMPLATES = "DDoDoDo DDoDDoo DDDoDoo DDDooDo DDDDooo".split(" ");
    private static final String DIGITS = "0123456789";
    private static final String OPERATIONS = "+-*/";

    private static String makeString(String template, char[] digits, char[] operations) {
        StringBuilder sbr = new StringBuilder();
        int d = 0;
        int o = 0;
        for (int i = 0; i < template.length(); ++i) {
            if (template.charAt(i) == 'D') {
                sbr.append(digits[d++]);
            } else if (template.charAt(i) == 'o') {
                sbr.append(operations[o++]);
            }
        }
        return sbr.toString();
    }

    private static double process(String input) {
        Stack<Double> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push((double)Integer.parseInt(Character.toString(c)));
            } else if (c == '+') {
                stack.push(stack.pop() + stack.pop());
            } else if (c == '-') {
                stack.push(stack.pop() - stack.pop());
            } else if (c == '*') {
                stack.push(stack.pop() * stack.pop());
            } else if (c == '/') {
                stack.push(stack.pop() / stack.pop());
            }
        }
        return stack.pop();
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve() {
        TIntObjectHashMap<TIntHashSet> outputs = new TIntObjectHashMap<>();
        for (char d0 : DIGITS.toCharArray()) {
            for (char d1 : DIGITS.toCharArray()) {
                if (d1 == d0) continue;
                for (char d2 : DIGITS.toCharArray()) {
                    if (d2 == d0 || d2 == d1) continue;
                    for (char d3 : DIGITS.toCharArray()) {
                        if (d3 == d0 || d3 == d1 || d3 == d2) continue;
                        char[] digits = new char[] {d0, d1, d2, d3};
                        char[] hashDigits = digits.clone();
                        Arrays.sort(hashDigits);
                        int hash = 1000 * Integer.parseInt(Character.toString(hashDigits[0])) +
                                100 * Integer.parseInt(Character.toString(hashDigits[1])) +
                                10 * Integer.parseInt(Character.toString(hashDigits[2])) +
                                Integer.parseInt(Character.toString(hashDigits[3]));
                        if (!outputs.contains(hash)) {
                            outputs.put(hash, new TIntHashSet());
                        }

                        for (String template :TEMPLATES) {
                            for (char o0 : OPERATIONS.toCharArray()) {
                                for (char o1 : OPERATIONS.toCharArray()) {
                                    for (char o2 : OPERATIONS.toCharArray()) {
                                        char[] operations = new char[] { o0, o1, o2 };
                                        String input = makeString(template, digits, operations);
                                        double d = process(input);
                                        if (Math.abs(d % 1) < 10E-8 && d > 0) {
                                            outputs.get(hash).add((int) d);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int max_N = 0;
        int result = 0;
        for (int hash : outputs.keys()) {
            TIntHashSet outputsN = outputs.get(hash);
            int n = 1;
            while (outputsN.contains(n)) {
                ++n;
            }
            if (n > max_N) {
                max_N = n;
                result = hash;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve());
    }
}
