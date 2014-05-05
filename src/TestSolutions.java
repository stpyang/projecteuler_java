import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: stpyang
 * Date: 12/2/13
 * Time: 12:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSolutions {

    // todo: 60 62 63 65 76 85 89 92 120 b138 203 206

    private static Map<Integer, Integer> time;
    private static Map<Integer, Integer> sorted;

    private static int[][] sampleMatrix;
    private static int[][] matrix;

    @Deprecated
    void checkDoubleSolution(int problemNumber, double expected, double epsilon, Object... args) {
        try {
            String className = "Problem" + String.format("%03d", problemNumber) + "Main";
            Class problem = Class.forName(className);
            for (Method method : problem.getMethods()) {
                if (method.getName().contains("solve")) {
                    if (method.getParameterTypes().length == args.length) {
                        long startMillis = System.currentTimeMillis();
                        assertTrue(Math.abs(expected - (double)method.invoke(null, args)) < epsilon);
                        long endMillis = System.currentTimeMillis();
                        time.put(problemNumber, (int)(endMillis - startMillis));
                        sorted.put((int)(endMillis - startMillis), problemNumber);
                        return;
                    }
                }
            }
            assertTrue(false);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Deprecated
    void checkLongSolution(int problemNumber, long expected, Object... args) {
        try {
            String className = "Problem" + String.format("%03d", problemNumber) + "Main";
            Class problem = Class.forName(className);
            for (Method method : problem.getMethods()) {
                if (method.getName().contains("solve")) {
                    if (method.getParameterTypes().length == args.length) {
                        long startMillis = System.currentTimeMillis();
                        assertEquals(expected, method.invoke(null, args));
                        long endMillis = System.currentTimeMillis();
                        time.put(problemNumber, (int)(endMillis - startMillis));
                        sorted.put((int)(endMillis - startMillis), problemNumber);
                        return;
                    }
                }
            }
            assertTrue(false);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    void checkSolution(int problemNumber, MySolution solution, long expected) {
        long startMillis = System.currentTimeMillis();
        assertEquals(expected, solution.solve());
        long endMillis = System.currentTimeMillis();
        time.put(problemNumber, (int)(endMillis - startMillis));
        sorted.put((int)(endMillis - startMillis), problemNumber);

    }

    void assertEqualsAndTime(int problemNumber, String expected, Object... args) {
        try {
            String className = "Problem" + String.format("%03d", problemNumber) + "Main";
            Class problem = Class.forName(className);
            for (Method method : problem.getMethods()) {
                if (method.getName().contains("solve")) {
                    if (method.getParameterTypes().length == args.length) {
                        long startMillis = System.currentTimeMillis();
                        assertEquals(expected, method.invoke(null, args));
                        long endMillis = System.currentTimeMillis();
                        time.put(problemNumber, (int)(endMillis - startMillis));
                        sorted.put((int)(endMillis - startMillis), problemNumber);
                        return;
                    }
                }
            }
            assertTrue(false);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @BeforeClass
    public static void setUpBefore() {
        time = new ConcurrentSkipListMap<>();
        sorted = new ConcurrentSkipListMap<>();

        sampleMatrix = new int[][] {
                new int[] {131, 673, 234,103, 18 },
                new int[] {201, 96, 342, 965, 150 },
                new int[] {630, 803, 746, 422, 111 },
                new int[] {537, 699, 497, 121, 956 },
                new int[] {805, 732, 524, 37, 331 },
        };
        matrix = Problem081Main.readMatrixFromFile("data/matrix.txt");
    }

    @Test
    public void testProblem001() {
        checkSolution(1, new Problem001(10, 3, 5), 23);
        checkSolution(1, new Problem001(1000, 3, 5), 233168);
    }

    @Test
    public void testProblem002() {
        checkSolution(2, new Problem002(100), 44);
        checkSolution(2, new Problem002(4000000), 4613732);
    }

    @Test
    public void testProblem003() {
        checkSolution(3, new Problem003(13195), 29);
        checkSolution(3, new Problem003(600851475143L), 6857);
    }

    @Test
    public void testProblem004() {
        assertEquals(true, EulerUtil.isPalindrome(91 * 99));
        checkSolution(4, new Problem004(2), 9009);
        checkSolution(4, new Problem004(3), 906609);
    }

    @Test
    public void testProblem005() {
        checkSolution(5, new Problem005(10), 2520);
        checkSolution(5, new Problem005(20), 232792560);
    }

    @Test
    public void testProblem006() {
        checkSolution(6, new Problem006(10), 2640);
        checkSolution(6, new Problem006(100), 25164150);
    }

    @Test
    public void testProblem007() {
        checkSolution(7, new Problem007(6), 13);
        checkSolution(7, new Problem007(10001), 104743);
    }

    @Test
    public void testProblem008() {
        checkSolution(8, new Problem008(5), 40824);
    }

    @Test
    public void testProblem009() {
        checkSolution(9, new Problem009(1000), 31875000);
    }

    @Test
    public void testProblem010() {
        checkSolution(17, new Problem010(10), 17);
        checkSolution(17, new Problem010(2000000), 142913828922L);
    }

    @Test
    public void testProblem011() {
        checkSolution(11, new Problem011(4), 70600674);
    }

    @Test
    public void testProblem012() {
        checkSolution(12, new Problem012(500), 76576500);
    }

    @Test
    public void testProblem013() {
        checkSolution(13, new Problem013(10), 5537376230L);
    }

    @Test
    public void testProblem014() {
        assertEquals(10, Problem014.chainLength(13));
        checkSolution(14, new Problem014(1000000), 837799);
    }

    @Test
    public void testProblem015() {
        checkSolution(15, new Problem015(20), 137846528820L);
    }

    @Test
    public void testProblem016() {
        checkSolution(16, new Problem016(1000), 1366);
    }

    @Test
    public void testProblem017() {
        checkSolution(17, new Problem017(1000), 21124);
    }

    @Test
    public void testProblem018() {
        checkSolution(18, new Problem018(Problem018.sample), 23);
        checkSolution(18, new Problem018(Problem018.input), 1074);
    }

    @Test
    public void testProblem019() {
        assertEquals(1, Problem019.dayOfWeek(1900, 1, 1));
        checkSolution(19, new Problem019(1901, 2001), 171);
    }

    @Test
    public void testProblem020() {
        checkSolution(20, new Problem020(10), 27);
        checkSolution(20, new Problem020(100), 648);
    }

    @Test
    public void testProblem021() {
        int[] sumOfDivisors = EulerUtil.getSumsOfDivisorsUpTo(284);
        assertEquals(220, sumOfDivisors[284]);
        assertEquals(284, sumOfDivisors[220]);
        checkSolution(21, new Problem021(10000), 31626);
    }

    @Test
    public void testProblem022() {
        assertEquals(53, Problem022.wordScore("COLIN"));
        checkSolution(22, new Problem022("data/names.txt"), 871198282);
    }

    @Test
    public void testProblem023() {
        checkSolution(23, new Problem023(), 4179871);
    }

    @Test
    public void testProblem024() {
        checkSolution(24, new Problem024(1, 3), 12);
        checkSolution(24, new Problem024(2, 3), 21);
        checkSolution(24, new Problem024(3, 3), 102);
        checkSolution(24, new Problem024(4, 3), 120);
        checkSolution(24, new Problem024(5, 3), 201);
        checkSolution(24, new Problem024(6, 3), 210);
        checkSolution(24, new Problem024(1000000, 10), 2783915460L);
    }

    @Test
    public void testProblem025() {
        checkSolution(25, new Problem025(1000), 4782);
    }

    @Test
    public void testProblem026() {
        assertEquals(0, Problem026.getSequenceLength(1));
        assertEquals(0, Problem026.getSequenceLength(2));
        assertEquals(1, Problem026.getSequenceLength(3));
        assertEquals(0, Problem026.getSequenceLength(4));
        assertEquals(0, Problem026.getSequenceLength(5));
        assertEquals(1, Problem026.getSequenceLength(6));
        assertEquals(6, Problem026.getSequenceLength(7));
        assertEquals(0, Problem026.getSequenceLength(8));
        assertEquals(1, Problem026.getSequenceLength(9));
        assertEquals(0, Problem026.getSequenceLength(10));
        checkSolution(26, new Problem026(1000), 983);
    }

    @Test
    public void testProblem027() {
        checkSolution(27, new Problem027(1000), -59231);
    }

    @Test
    public void testProblem028() {
        checkSolution(28, new Problem028(5), 101);
        checkSolution(28, new Problem028(1001), 669171001);
    }

    @Test
    public void testProblem029() {
        checkSolution(29, new Problem029(5), 15);
        checkSolution(29, new Problem029(100), 9183);
    }

    @Test
    public void testProblem030() {
        checkSolution(30, new Problem030(4), 19316);
        checkSolution(30, new Problem030(5), 443839);
    }

    @Test
    public void testProblem031() {
        checkLongSolution(31, 73682, 200);
    }

    @Test
    public void testProblem032() {
        assertEquals(true, Problem032Main.isPandigitalWithProduct(39, 186));
        checkLongSolution(32, 45228);
    }

    @Test
    public void testProblem033() {
        checkLongSolution(33, 100);
    }

    @Test
    public void testProblem034() {
        assertEquals(145, Problem034Main.digitFactorial(145));
        checkLongSolution(34, 40730);
    }

    @Test
    public void testProblem035() {
        assertEquals(719, Problem035Main.rotate(197));
        assertEquals(197, Problem035Main.rotate(971));
        assertEquals(971, Problem035Main.rotate(719));
        assertEquals(13, Problem035Main.solve(100));
        checkLongSolution(35, 55, 1000000);
    }

    @Test
    public void testProblem036() {
        assertTrue(EulerUtil.isPalindrome(585));
        assertTrue(EulerUtil.isPalindrome(585, 2));
        checkLongSolution(36, 872187, 1000000);
    }

    @Test
    public void testProblem037() {
        checkLongSolution(37, 748317);
    }

    @Test
    public void testProblem038() {
        assertEquals(192384576, Problem038Main.calculatePandigitalConcatenatedProduct(192));
        assertEquals(918273645, Problem038Main.calculatePandigitalConcatenatedProduct(9));
        checkLongSolution(38, 932718654);
    }

    @Test
    public void testProblem039() {
        assertEquals(120, Problem039Main.solve(120));
        checkLongSolution(39, 840, 1000);
    }

    @Test
    public void testProblem040() {
        checkLongSolution(40, 210, 7);
    }

    @Test
    public void testProblem041() {
        assertEquals(true, Problem041Main.isPanDigital(2143, 4));
        checkLongSolution(41, 7652413);
    }

    @Test
    public void testProblem042() {
        assertEquals(55, Problem042Main.score("SKY"));
        assertEquals(true, EulerUtil.isGeometricNumber(55, 3));
        checkLongSolution(42, 162, "data/words.txt");
    }

    @Test
    public void testProblem043() {
        assertEquals(true, Problem043Main.hasProperty(new int[] { 1, 4, 0, 6, 3, 5, 7, 2, 8, 9 }));
        checkLongSolution(43, 16695334890L);
    }

    @Test
    public void testProblem044() {
        checkLongSolution(44, 5482660);
    }

    @Test
    public void testProblem045() {
        assertEquals(true, EulerUtil.isGeometricNumber(40755, 3));
        assertEquals(true, EulerUtil.isGeometricNumber(40755, 5));
        assertEquals(true, EulerUtil.isGeometricNumber(40755, 6));
        checkLongSolution(45, 1533776805);
    }

    @Test
    public void testProblem046() {
        checkLongSolution(46, 5777);
    }

    @Test
    public void testProblem047() {
        assertEquals(14, Problem047Main.solve(2));
        assertEquals(644, Problem047Main.solve(3));
        checkLongSolution(47, 134043, 4);
    }

    @Test
    public void testProblem048() {
        assertEquals(10405071317L, Problem048Main.solve(10, Long.MAX_VALUE));
        checkLongSolution(48, 9110846700L, 1000, 10000000000L);
    }

    @Test
    public void testProblem049() {
        checkLongSolution(49, 296962999629L);
    }

    @Test
    public void testProblem050() {
        assertEquals(41, Problem050Main.solve(100));
        assertEquals(953, Problem050Main.solve(1000));
        checkLongSolution(50, 997651, 1000000);
    }

    @Test
    public void testProblem051() {
        assertEquals(13, Problem051Main.solve(1, 6));
        assertEquals(56003, Problem051Main.solve(2, 7));
        checkLongSolution(51, 121313, 3, 8);
    }

    @Test
    public void testProblem052() {
        checkLongSolution(52, 142857, 6);
    }

    @Test
    public void testProblem053() {
        checkLongSolution(53, 4075, 100, 1000000);
    }

    @Test
    public void testProblem054() {
        assertTrue(Problem054Main.compareHands("5H 5C 6S 7S KD", "2C 3S 8S 8D TD") > 0);
        assertTrue(Problem054Main.compareHands("5D 8C 9S JS AC", "2C 5C 7D 8S QH") < 0);
        assertTrue(Problem054Main.compareHands("2D 9C AS AH AC", "3D 6D 7D TD QD") > 0);
        assertTrue(Problem054Main.compareHands("4D 6S 9H QH QC", "3D 6D 7H QD QS") < 0);
        assertTrue(Problem054Main.compareHands("2H 2D 4C 4D 4S", "3C 3D 3S 9S 9D") < 0);
        checkLongSolution(54, 376, "data/poker.txt");
    }

    @Test
    public void testProblem055() {
        assertEquals(false, Problem055Main.isLychrel(47));
        assertEquals(false, Problem055Main.isLychrel(349));
        assertEquals(true, Problem055Main.isLychrel(196));
        checkLongSolution(55, 249, 10000);

    }

    @Test
    public void testProblem056() {
        checkLongSolution(56, 972, 100);
    }

    @Test
    public void testProblem057() {
        checkLongSolution(57, 153, 1000);
    }

    @Test
    public void testProblem058() {
        checkLongSolution(58, 26241);
    }

    @Test
    public void testProblem059() {
        checkLongSolution(59, 107359, "data/cipher1.txt");
    }

    @Test
    public void testProblem061() {
        checkLongSolution(61, 28684);
    }

    @Test
    public void testProblem064() {
        checkLongSolution(64, 1322);
    }

    @Test
    public void testProblem066() {
        checkLongSolution(66, 661);
    }

    @Test
    public void testProblem067() {
        checkLongSolution(67, 7273, "data/triangle.txt");
    }

    @Test
    public void testProblem068() {
        checkLongSolution(68, 6531031914842725L, 5);
    }

    @Test
    public void testProblem069() {
        assertEquals(6, Problem069Main.solve(10));
        checkLongSolution(69, 510510, 1000000);
    }

    @Test
    public void testProblem070() {
        assertEquals(true, Problem070Main.isPermutationOf(87109, 79180));
        checkLongSolution(70, 8319823, 10000000);
    }

    @Test
    public void testProblem071() {
        assertEquals(2, Problem071Main.solve(3, 7, 10));
        checkLongSolution(71, 428570, 3, 7, 1000000);
    }

    @Test
    public void testProblem072() {
        assertEquals(21, Problem072Main.solve(8));
        checkLongSolution(72, 303963552391L, 1000000);
    }

    @Test
    public void testProblem073() {
        assertEquals(3, Problem073Main.solve(1, 2, 1, 3, 8));
        checkLongSolution(73, 7295372, 1, 2, 1, 3, 12000);
    }

    @Test
    public void testProblem074() {
        checkLongSolution(74, 402, 1000000, 60);
    }

    @Test
    public void testProblem075() {
        checkLongSolution(75, 161667, 1500000, 1);
    }

    @Test
    public void testProblem077() {
        checkLongSolution(77, 71, 5000) ;
    }

    @Test
    public void testProblem078() {
        checkLongSolution(78, 55374, 1000000);
    }

    @Test
    public void testProblem079() {
        assertEquals(true, Problem079Main.isValid(317, new int[] { 3, 1, 9, 6, 8, 0, 9 }));
        checkLongSolution(79, 73162890, "data/keylog.txt");
    }

    @Test
    public void testProblem080() {
        checkLongSolution(80, 40886, 100, 100);
    }

    @Test
    public void testProblem081() {
        assertEquals(2427, Problem081Main.solve(sampleMatrix, 5, 5));
        checkLongSolution(81, 427337, matrix, 80, 80);
    }                          

    @Test
    public void testProblem082() {
        assertEquals(994, Problem082Main.solve(sampleMatrix, 5, 5));
        checkLongSolution(82, 260324, matrix, 80, 80);
    }

    @Test
    public void testProblem083() {
        assertEquals(2297, Problem083Main.solve(sampleMatrix, 5, 5));
        checkLongSolution(83, 425185, matrix, 80, 80);
    }

    @Test
    public void testProblem084() {
        checkLongSolution(84, 101524);
    }

    @Test
    public void testProblem086() {
        checkLongSolution(86, 1818, 1000000);
    }

    @Test
    public void testProblem087() {
        assertEquals(4, Problem087Main.solve(50));
        checkLongSolution(87, 1097343, 50000000);
    }

    @Test
    public void testProblem088() {
        checkLongSolution(88, 7587457, 2, 12000);
    }

    @Test
    public void testProblem090() {
        checkLongSolution(90, 1217);
    }

    @Test
    public void testProblem091() {
        checkLongSolution(91, 14234, 50);
    }

    @Test
    public void testProblem093() {
        checkLongSolution(93, 1258);
    }

    @Test
    public void testProblem094() {
        checkLongSolution(94, 518408346, 1000000000L);
    }

    @Test
    public void testProblem095() {
        checkLongSolution(95, 14316, 1000000);
    }

    @Test
    public void testProblem096() {
        checkLongSolution(96, 24702, "data/sudoku.txt");
    }

    @Test
    public void testProblem097() {
        checkLongSolution(97, 8739992577L);
    }

    @Test
    public void testProblem098() {
        checkLongSolution(98, 18769, "data/words.txt");
    }

    @Test
    public void testProblem099() {
        checkLongSolution(99, 709, "data/base_exp.txt");
    }

    @Test
    public void testProblem100() {
        checkLongSolution(100, 756872327473L, 1000000000000L);
    }

    @Test
    public void testProblem101() {
        checkLongSolution(101, 37076114526L, 10);
    }

    @Test
    public void testProblem102() {
        Problem102Main.Triangle t;
        t = new Problem102Main.Triangle(-340,495,-153,-910,835,-947);
        assertTrue(t.containsOrigin());

        t = new Problem102Main.Triangle(-175,41,-421,-714,574,-645);
        assertFalse(t.containsOrigin());

        checkLongSolution(102, 228, "data/triangles.txt");
    }

    @Test
    public void testProblem103() {
        checkLongSolution(103, 20313839404245L);
    }

    @Test
    public void testProblem104() {
        checkLongSolution(104, 329468);
    }

    @Test
    public void testProblem105() {
        assertEquals(false, Problem105Main.isSpecial(new int[] {81, 88, 75, 42, 87, 84, 86, 65}));
        assertEquals(true, Problem105Main.isSpecial(new int[] {157, 150, 164, 119, 79, 159, 161, 139, 158}));
        checkLongSolution(105, 73702, "data/sets.txt");
    }

    @Test
    public void testProblem106() {
        assertEquals(1, Problem106Main.solve(4));
        assertEquals(70, Problem106Main.solve(7));
        checkLongSolution(106, 21384, 12);
    }

    @Test
    public void testProblem107() {
        assertEquals(150, Problem107Main.solve(Problem107Main.sample, 7));
        checkLongSolution(107, 259679, "data/network.txt", 40);
    }

    @Test
    public void testProblem108() {
        checkLongSolution(108, 180180, 1000);
    }

    @Test
    public void testProblem109() {
        assertEquals(42336, Problem109Main.solve(Integer.MAX_VALUE));
        checkLongSolution(109, 38182, 100);
    }

    @Test
    public void testProblem110() {
        checkLongSolution(110, 9350130049860600L, 4000000);
    }

    @Test
    public void testProblem111() {
        assertEquals(273700, Problem111Main.solve(4));
        checkLongSolution(111, 612407567715L, 10);
    }

    @Test
    public void testProblem112() {
        assertEquals(538, Problem112Main.solve(0.50));
        assertEquals(21780, Problem112Main.solve(0.90));
        checkLongSolution(112, 1587000, 0.99);
    }

    @Test
    public void testProblem113() {
        assertEquals(12951, Problem113Main.solve(6));
        assertEquals(277032, Problem113Main.solve(10));
        checkLongSolution(113, 51161058134250L, 100);
    }

    @Test
    public void testProblem114() {
        assertEquals(17, Problem114Main.solve(7));
        checkLongSolution(114, 16475640049L, 50);
    }

    @Test
    public void testProblem115() {
        Problem114Main.initializeMaps(3);
        assertEquals(673135, Problem114Main.fillCount(3, 29));
        assertEquals(1089155, Problem114Main.fillCount(3, 30));
        Problem114Main.initializeMaps(10);
        assertEquals(880711, Problem114Main.fillCount(10, 56));
        assertEquals(1148904, Problem114Main.fillCount(10, 57));
        Problem114Main.initializeMaps(50);
        checkLongSolution(115, 168, 50, 1000000);
    }

    @Test
    public void testProblem116() {
        assertEquals(12, Problem116Main.solve(5));
        checkLongSolution(116, 20492570929L, 50);
    }

    @Test
    public void testProblem117() {
        assertEquals(15, Problem117Main.solve(5));
        checkLongSolution(117, 100808458960497L, 50);
    }

    @Test
    public void testProblem118() {
        checkLongSolution(118, 44680);
    }

    @Test
    public void testProblem119() {
        assertEquals(512, Problem119Main.solve(2));
        assertEquals(614656, Problem119Main.solve(10));
        checkLongSolution(119, 248155780267521L, 30);
    }

    @Test
    public void testProblem121() {
        assertEquals(10, Problem121Main.solve(4));
        checkLongSolution(121, 2269, 15);
    }

    @Test
    public void testProblem122() {
        checkLongSolution(122, 1582, 200);
    }

    @Test
    public void testProblem123() {
        assertEquals(7037, Problem123Main.solve(100000000L));
        checkLongSolution(123, 21035, 10000000000L);
    }

    @Test
    public void testProblem124() {
        assertEquals(8, Problem124Main.solve(4, 10));
        assertEquals(9, Problem124Main.solve(6, 10));
        checkLongSolution(124, 21417, 10000, 100000);
    }

    @Test
    public void testProblem125() {
        assertTrue(EulerUtil.isPalindrome(12 * 13 * 25 / 6 - 5 * 6 * 13 / 6));
        assertEquals(4164, Problem125Main.solve(1000));
        checkLongSolution(125, 2906969179L, 100000000);
    }

    @Test
    public void testProblem126() {
        assertEquals(2, Problem126Main.C(22));
        assertEquals(4, Problem126Main.C(46));
        assertEquals(5, Problem126Main.C(78));
        assertEquals(8, Problem126Main.C(118));
        assertEquals(154, Problem126Main.solve(10));
        checkLongSolution(126, 18522, 1000);
    }

    @Test
    public void testProblem127() {
        assertEquals(12523, Problem127Main.solve(1000));
        checkLongSolution(127, 18407904, 120000);
    }

    @Test
    public void testProblem128() {
        assertEquals(271, Problem128Main.solve(3, 10));
        checkLongSolution(128, 14516824220L, 3, 2000);
    }

    @Test
    public void testProblem129() {
        assertEquals(6, Problem129Main.A(7));
        assertEquals(5, Problem129Main.A(41));
        assertEquals(17, Problem129Main.solve(10));
        checkLongSolution(129, 1000023, 1000000);
    }

    @Test
    public void testProblem130() {
        assertEquals(91 + 259 + 451 + 481 + 703, Problem130Main.solve(5));
        checkLongSolution(130, 149253, 25);
    }

    @Test
    public void testProblem131() {
        assertEquals(4, Problem131Main.solve(100));
        checkLongSolution(131, 173, 1000000);
    }

    @Test
    public void testProblem132() {
        assertEquals(9414, Problem132Main.solve(10, 4));
        checkLongSolution(132, 843296, 1000000000, 40);
    }

    @Test
    public void testProblem133() {
        assertEquals(true, Problem133Main.fastTest(11));
        assertEquals(true, Problem133Main.fastTest(17));
        assertEquals(true, Problem133Main.fastTest(41));
        assertEquals(true, Problem133Main.fastTest(73));
        checkLongSolution(133, 453647705, 100000);
    }

    @Test
    public void testProblem134() {
        assertEquals(1219, Problem134Main.S(19, 23));
        checkLongSolution(134, 18613426663617118L, 5, 1000000);
    }

    @Test
    public void testProblem135() {
        checkLongSolution(135, 4989, 1000000, 10);
    }

    @Test
    public void testProblem136() {
        assertEquals(25, Problem136Main.solve(100));
        checkLongSolution(136, 2544559, 50000000);
    }

    @Test
    public void testProblem139() {
        checkLongSolution(139, 10057761, 100000000);
    }

    @Test
    public void testProblem141() {
        assertEquals(124657, Problem141Main.solve(100000));
        checkLongSolution(141, 878454337159L, 1000000000000L);
    }

    @Test
    public void testProblem142() {
        checkLongSolution(142, 1006193);
    }

    @Test
    public void testProblem143() {
        checkLongSolution(143, 30758397, 120000);
    }

    @Test
    public void testProblem145() {
        assertTrue(Problem145Main.isReversible(36));
        assertTrue(Problem145Main.isReversible(63));
        assertEquals(120, Problem145Main.solve(3));
        checkLongSolution(145, 608720, 9);
    }

    @Test
    public void testProblem146() {
        assertEquals(1242490, Problem146Main.solve(1000000));
        checkLongSolution(146, 676333270, 150000000);
    }

    @Test
    public void testProblem162() {
        assertEqualsAndTime(162, "3D58725572C62302", 16);
    }

    @Test
    public void testProblem164() {
        checkLongSolution(164, 378158756814587L, 20, 9);
    }

    @Test
    public void testProblem173() {
        assertEquals(41, Problem173Main.solve(100));
        checkLongSolution(173, 1572729, 1000000);
    }

    @Test
    public void testProblem174() {
        checkLongSolution(174, 209566, 1000000, 10);
    }

    @Test
    public void testProblem179() {
        checkLongSolution(179, 986262, 10000000);
    }

    @Test
    public void testProblem187() {
        assertEquals(10, Problem187Main.solve(30));
        checkLongSolution(187, 17427258, 100000000);
    }

    @Test
    public void testProblem188() {
        checkLongSolution(188, 95962097, 1777, 1855, 100000000);
    }

    @Test
    public void testProblem191() {
        assertEquals(43, Problem191Main.solve(4));
        checkLongSolution(191, 1918080160, 30);
    }

    @Test
    public void testProblem197() {
        checkDoubleSolution(197, 1.710637717, 10e-10);
    }

    @Test
    public void testProblem204() {
        assertEquals(1105, Problem204Main.solve(5, 100000000));
        checkLongSolution(204, 2944730, 100, 1000000000L);
    }

    @Test
    public void testProblem205() {
        checkLongSolution(205, 5731441, 9, 6);
    }

    @Test
    public void testProblem231() {
        assertEquals(14, Problem231Main.solve(10, 3));
        checkLongSolution(231, 7526965179680L, 20000000, 15000000);
    }

    @Test
    public void testProblem235() {
        checkLongSolution(235, 1002322108633L, -600000000000L, 12);
    }

    @Test
    public void testProblem243() {
        assertEquals(12, Problem243Main.solve(4, 10));
        checkLongSolution(243, 892371480L, 15499, 94744);
    }

    @AfterClass
    public static void tearDown() {
        Integer[] sortedSolvedProblems = time.keySet().toArray(new Integer[time.size()]);
        Integer[] sortedSolvedTimes = sorted.keySet().toArray(new Integer[sorted.size()]);

        for(int i = 0; i < Math.max(sortedSolvedProblems.length, sortedSolvedTimes.length); ++i) {
            System.out.print(String.format("%5d", sortedSolvedProblems[i]) + " " + String.format("%10d", time.get(sortedSolvedProblems[i])));
            if (i < sortedSolvedTimes.length) {
                System.out.print("         ");
                System.out.print(String.format("%5d", sortedSolvedTimes[i]) + " " + String.format("%10d", sorted.get(sortedSolvedTimes[i])));
            }
            System.out.println();
        }
    }
}
