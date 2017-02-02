package com.dvlsoft;

import java.io.IOException;
import java.util.List;

public class Lottery {

    public static void main(String[] args) {
        if (validateInput(args)) {
            try {
                String fileName = args[0];
                String winNumbers = args[1];
                List<Person> persons = Parser.readFile(fileName);
                LotteryProcessor processor = new LotteryProcessor(winNumbers);
                processor.calculateCredits(persons);
                processor.printResult(persons);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean validateInput(String[] args) {
        if (args == null || args.length != 2) {
            System.out.println("Incorrect arguments - <file name> <win numbers> (ex. testData.txt 456000123)");
            return false;
        }
        return true;
    }
}
