package racingcar.util;

public class JavaUtilRandom implements RandomUtil {
    private static final JavaUtilRandom INSTANCE = new JavaUtilRandom();
    private final java.util.Random random;

    private JavaUtilRandom() {
        random = new java.util.Random();
    }

    public static JavaUtilRandom getInstance() {
        return INSTANCE;
    }

    public int getInt(int bound) {
        return random.nextInt(bound);
    }
}
