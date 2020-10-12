/*
Benjamin Godwin 2020
CSC1052 - Printer Queue Project
EnumExtender.java

Info: static class to extend enum. Currently just used to select a random enum value
 */

import java.util.Random;

public class EnumExtender {

    public static final Random random = new Random();

    /**
     * Selects a random enum of the given class
     * @return
     */
    public static <T extends Enum<?>> T randomEnum(Class<T> enumClass) {
        T[] array = enumClass.getEnumConstants();
        return array[random.nextInt(array.length)];
    }
}
