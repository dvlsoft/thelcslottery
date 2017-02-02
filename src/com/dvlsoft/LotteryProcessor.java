package com.dvlsoft;

import java.util.*;

public class LotteryProcessor {
    private String winNumbers;

    public LotteryProcessor(String winNumbers) {
        this.winNumbers = winNumbers;
    }

    public void calculateCredits(List<Person> persons) {
        persons.stream()
                .forEach(person -> person.addCredits(calculateSumOfCredits(person)));
    }

    public void printResult(List<Person> persons) {
        persons.stream()
                .filter(person -> person.getCredits() != 0)
                .sorted()
                .forEach(
                        System.out::println
                );
    }

    private int calculateSumOfCredits(Person person) {
        int sum = 0;
        for (String ticketNumber : person.getAllTicketsNumbers()) {
            int m = ticketNumber.length();
            int n = winNumbers.length();

            int[][] matrix = new int[m + 1][n + 1];

            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    if (ticketNumber.charAt(i) == winNumbers.charAt(j)) {
                        matrix[i][j] = matrix[i + 1][j + 1] + 1;
                    } else {
                        matrix[i][j] = Math.max(matrix[i + 1][j], matrix[i][j + 1]);
                    }
                }
            }
            sum += matrix[0][0];
        }
        return sum;
    }
}
