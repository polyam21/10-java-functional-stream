
package com.example.task03;

import java.util.Comparator;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) {

        findMinMax(
                Stream.of(2, 9, 5, 4, 8, 1, 3),
                Integer::compareTo,
                (min, max) ->
                        System.out.println("min: " + min + " / max: " + max)
        );

    }

    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {

        class MinMax {
            T min;
            T max;
            boolean initialized;
            boolean hasNull;
        }

        MinMax result = new MinMax();

        stream.forEach(element -> {
            result.initialized = true;

            if (element == null) {
                result.hasNull = true;
                return;
            }

            if (result.min == null || order.compare(element, result.min) < 0) {
                result.min = element;
            }

            if (result.max == null || order.compare(element, result.max) > 0) {
                result.max = element;
            }
        });

        if (!result.initialized) {
            minMaxConsumer.accept(null, null);
        } else {
            minMaxConsumer.accept(result.hasNull ? null : result.min, result.max);
        }
    }
}
