import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: syang
 * Date: 2/19/14
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem059Main {

    static int scoreCipherChar(int[] ints, char c, int modulus) {
        int result = 0;
        for (int i = 0; i < ints.length; ++i) {
            if (i % 3 == modulus) {
                int decoded = ints[i] ^ c;
                if (decoded < 32 || decoded > 126) { // if the character is unprintable
                    return -1;
                } else if (Character.isLetterOrDigit(decoded)) {
                    ++result;
                }
            }
        }
        return result;
    }

    static String decodeLine(int[] ints, char c0, char c1, char c2) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < ints.length; ++i) {
            if (i % 3 == 0) {
                sbr.append((char)(ints[i] ^ c0));
            } else if (i % 3 == 1) {
                sbr.append((char)(ints[i] ^ c1));
            } else {
                sbr.append((char)(ints[i] ^ c2));
            }
        }
        return sbr.toString();
    }

    public static long solve(String fileName) {
        long result = 0;

        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] text = line.split(",");
                int[] ints = new int[text.length];
                for (int i = 0; i < text.length; ++i) {
                    ints[i] = Integer.parseInt(text[i]);
                }

                int score;

                score = 0;
                int s;

                char c0 = 'a';
                for (int i = 0; i < 26; ++i) {
                    char temp = (char)('a' + i);
                    if ((s = scoreCipherChar(ints, temp, 0)) > score) {
                        c0 = temp;
                        score = s;
                    }
                }

                score = 0;
                char c1 = 'a';
                for (int i = 0; i < 26; ++i) {
                    char temp = (char)('a' + i);
                    if ((s = scoreCipherChar(ints, temp, 1)) > score) {
                        c1 = temp;
                        score = s;
                    }
                }

                score = 0;
                char c2 = 'a';
                for (int i = 0; i < 26; ++i) {
                    char temp = (char)('a' + i);
                    if ((s = scoreCipherChar(ints, temp, 2)) > score) {
                        c2 = temp;
                        score = s;
                    }
                }

                String solution = decodeLine(ints, c0, c1, c2);

                for (char c : solution.toCharArray()) {
                    result += c;
                }

                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/cipher1.txt"));
    }
}
