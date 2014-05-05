import gnu.trove.TIntArrayList;
import gnu.trove.TIntIntHashMap;

/**
 * Created by stpyang on 12/26/13.
 */
public class Problem122Main {

    private static void recurse(int upperLimit, TIntArrayList generatedNumbers, TIntIntHashMap minimalAdditions) {
        int biggest = generatedNumbers.get(generatedNumbers.size() - 1);
        for(int i = 0; i < generatedNumbers.size(); ++i) {
            int newNumber = biggest + generatedNumbers.get(generatedNumbers.size() - i - 1);
            if (newNumber <= upperLimit &&
                    (!minimalAdditions.containsKey(newNumber) || minimalAdditions.get(newNumber) > generatedNumbers.size() - 1) &&
                    !generatedNumbers.contains(newNumber)) {
                minimalAdditions.put(newNumber, generatedNumbers.size());
                TIntArrayList newGeneratedNumbers = (TIntArrayList)generatedNumbers.clone();
                newGeneratedNumbers.add(newNumber);
                newGeneratedNumbers.sort();
                recurse(upperLimit, newGeneratedNumbers, minimalAdditions);
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static long solve(int N) {
        long result = 0;

        TIntIntHashMap minimalAdditions = new TIntIntHashMap();
        minimalAdditions.put(1, 0);
        TIntArrayList seed = new TIntArrayList();
        seed.add(1);

        recurse(N, seed, minimalAdditions);

        for (int key : minimalAdditions.keys()) {
            result += minimalAdditions.get(key);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(200));
    }
}
