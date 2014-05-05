import gnu.trove.TIntArrayList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 1/8/14
 * Time: 1:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem079Main {

    static boolean isValid(int passcode, int[] digits) {
        int index = digits.length;
        while (passcode > 0) {
            int i = 0;
            while (i < digits.length && digits[i] != passcode % 10) {
                ++i;
            }
            if (i > index) {
                return false;
            }
            index = i;
            passcode /= 10;
        }
        return true;
    }

    private static boolean isValid(TIntArrayList passcodes, int[] digits) {
        for(int passcode : passcodes.toNativeArray()) {
            if (!isValid(passcode, digits)) {
                return false;
            }
        }
        return true;
    }

    private static void recurse(int[] digits, int umask, TIntArrayList passcodes, int[] result) {
        if (result[0] != 0) {
        } else if (umask == 0) {
            if (isValid(passcodes, digits)) {
                int m = 0;
                int pow10 = 1;
                for (int i = 0; i < digits.length; ++i) {
                    m += digits[digits.length - i - 1] * pow10;
                    pow10 *= 10;
                }
                result[0] = m;
            }
        } else {
            for(int i = 0; i < 10; ++i) {
                if ((umask & (1 << i)) != 0) {
                    int[] newDigits = new int[digits.length + 1];
                    System.arraycopy(digits, 0, newDigits, 0, digits.length);
                    newDigits[digits.length] = i;
                    recurse(newDigits, umask ^ (1 << i), passcodes, result);
                }
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String filename) {
        int[] result = new int[1];
        try {
            File file = new File(filename);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;

            int umask = 0;
            TIntArrayList passcodes = new TIntArrayList();
            while ((line = br.readLine()) != null)  {
                int passcode = Integer.parseInt(line);
                passcodes.add(passcode);
                while(passcode > 0) {
                    umask |= 1 << (passcode % 10);
                    passcode /= 10;
                }
            }

            recurse(new int[0], umask, passcodes, result);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result[0];
    }

    public static void main(String[] args) {
        System.out.println(solve("data/keylog.txt"));
    }
}
