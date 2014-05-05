import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/11/13
 * Time: 9:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class Problem098Main {

    private static final Map<String, List<String>> sortedWordsMap = new HashMap<>();
    private static final Map<String, List<Integer>> sortedSquaresMap = new TreeMap<>();

    private static boolean populateMap(Map<Character, Integer> map, String word, Integer n) {
        char[] charArray = word.toCharArray();
        char[] intArray = n.toString().toCharArray();
        if (charArray.length != intArray.length) {
            throw new IllegalArgumentException("word and n must have same number of letters/digits");
        }
        for (int i = 0; i < word.length(); ++i) {
            char key = charArray[i];
            int value = Integer.parseInt(Character.toString(intArray[i]));
            if ((map.get(key) != null && map.get(key) != value) || map.containsValue(value)) {
                return false;
            } else {
                map.put(key, value);
            }
        }
        return true;
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(String fileName) {
        int result = 0;
        try {
            // read in data
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace("\"","");
                String[] words = line.split(",");
                for (String word : words) {
                    char[] letters = word.toCharArray();
                    Arrays.sort(letters);
                    String hash = String.copyValueOf(letters);
                    if (!sortedWordsMap.containsKey(hash)) {
                        sortedWordsMap.put(hash, new ArrayList<String>());
                    }
                    sortedWordsMap.get(hash).add(word);
                }
            }

            // get maximum anagram size
            int maxSize = 0;
            for (List<String> list : sortedWordsMap.values()) {
                if (list.size() > 1) {
                    maxSize = Math.max(maxSize, list.get(0).length());
                }
            }

            // calculate numerical anagrams
            for (int i = 1; i < Math.sqrt(EulerUtil.longPow(10, maxSize)); ++i) {
                String word = Integer.toString(i * i);
                char[] letters = word.toCharArray();
                Arrays.sort(letters);
                String hash = String.copyValueOf(letters);
                if (!sortedSquaresMap.containsKey(hash)) {
                    sortedSquaresMap.put(hash, new ArrayList<Integer>());
                }
                sortedSquaresMap.get(hash).add(Integer.parseInt(word));
            }

            Map<Character, Integer> tempMap = new HashMap<>();
            for (List<String> list1 : sortedWordsMap.values()) {
                if (list1.size() > 1) {
                    for(List<Integer> list2 : sortedSquaresMap.values()) {
                        if (list1.size() < 2 || list2.size() < 2 || list1.get(0).length() != list2.get(0).toString().length()) {
                            continue;
                        }
                        for (int i = 0; i < list1.size(); ++i) {
                            for(int j = 0; j < list2.size(); ++j) {
                                tempMap.clear();
                                if (populateMap(tempMap, list1.get(i), list2.get(j))) {
                                    for (int k = i + 1; k < list1.size(); ++k) {
                                        String anagram = list1.get(k);
                                        for (char c : tempMap.keySet()) {
                                            anagram = anagram.replace(Character.toString(c), Integer.toString(tempMap.get(c)));
                                        }
                                        if (list2.contains(Integer.parseInt(anagram))) {
                                            result = Math.max(result, Integer.parseInt(anagram));
                                            result = Math.max(result, list2.get(j));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve("data/words.txt"));
    }
}
