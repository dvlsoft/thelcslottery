package com.dvlsoft;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {

    public static List<Person> readFile(String filename) throws IOException {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            Map<String, List<String>> personsMap = new HashMap<>();
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                putPersonToMap(personsMap, currentLine);
            }
            persons.addAll(personsMap.entrySet().stream().map(Parser::getPersonFromEntry).collect(Collectors.toList()));
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("File " + filename + " not found");
        } catch (IOException ex) {
            throw new IOException("File " + filename + " has error during reading");
        }
        return persons;
    }

    // Create person
    private static Person getPersonFromEntry(Map.Entry<String, List<String>> entry) {
        String[] personFields = entry.getKey().split(",");
        List<String> ticketsNumbers = entry.getValue();
        String firstName = personFields[1];
        String lastName = personFields[0];
        String country = personFields[2];
        return new Person(firstName, lastName, country, ticketsNumbers);
    }

    // Help map to find distinct person
    private static void putPersonToMap(Map<String, List<String>> personMap, String line) {
        String person = line.substring(0, line.lastIndexOf(","));
        String ticketNumbers = line.substring(line.lastIndexOf(",") + 1, line.length());
        if (personMap.containsKey(person)) {
            personMap.get(person).add(ticketNumbers);
        } else {
            personMap.put(person, new ArrayList<String>() {{
                        add(ticketNumbers);
                    }}
            );
        }
    }

}
