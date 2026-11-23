
package com.example.task04;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task04Main {

    public static void main(String[] args) {

        String result = new java.io.BufferedReader(
                new java.io.InputStreamReader(System.in, java.nio.charset.StandardCharsets.UTF_8)
        )
                .lines()
                .flatMap(line -> Arrays.stream(line.toLowerCase().split("[^\\p{L}\\p{N}]+")))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(
                        Comparator.<Map.Entry<String, Long>>comparingLong(Map.Entry::getValue)
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining("\n"));

        System.out.print(result);
    }
}

