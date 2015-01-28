package org.neoevolution.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jonathan D'Orleans <jonathan.dorleans@gmail.com>
 * @since 1.0
 */
public final class Randomizer {

    private Randomizer() { }


    private static ThreadLocalRandom current() {
        return ThreadLocalRandom.current();
    }


    public static int randomInt(int value) {
        return current().nextInt(value);
    }

    public static int randomIntInclusive(int value) {
        return current().nextInt(value+1);
    }

    public static int randomInt(int min, int max) {
        return current().nextInt(min, max);
    }

    public static int randomIntInclusive(int min, int max) {
        return current().nextInt(min, max+1);
    }


    public static long randomLong(long value) {
        return current().nextLong(value);
    }

    public static long randomLongInclusive(long value) {
        return current().nextLong(value+1);
    }

    public static long randomLong(long min, long max) {
        return current().nextLong(min, max);
    }

    public static long randomLongInclusive(long min, long max) {
        return current().nextLong(min, max+1);
    }


    public static boolean randomBoolean() {
        return randomBoolean(0.5);
    }

    public static boolean randomBoolean(double value) {
        return (random() < value);
    }


    public static double random() {
        return current().nextDouble();
    }

    public static double randomInclusive() {
        double value = random();
        return (randomBoolean() ? 1-value : value);
    }

    public static double random(double value) {
        return current().nextDouble(value);
    }

    public static double randomInclusive(double value) {
        return (value * randomInclusive());
    }

    public static double random(double min, double max) {
        return current().nextDouble(min, max);
    }

    public static double randomInclusive(double min, double max) {
        return (min + randomInclusive(max-min));
    }


    public static <T> T random(T a, T b) {
        return (randomBoolean() ? a : b);
    }

}
