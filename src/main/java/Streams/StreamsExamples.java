package Streams;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExamples {

    public static void main(String[] args) {
        StreamsExamples streamsExamples = new StreamsExamples();
        //gather data from external file and count its rows
        //streamsExamples.charRemoveCount();

        //parsing values
        //streamsExamples.parseDataFromRows();

        //from rows to HashMap
        streamsExamples.hashMapStoring();
    }

    void charRemoveCount(){
        try {
            Stream<String> myRows = Files.lines(Paths.get("data.txt"));
            int rowCounter = (int)myRows
                    .map(x -> x.split(","))
                    .filter(x -> x.length >= 3)
                    .count(); //its counting only those rows where the length is >= 3
            System.out.println(rowCounter + " rows.");
            myRows.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void parseDataFromRows(){
        try {
            Stream<String> myRows = Files.lines(Paths.get("data.txt"));
            myRows
                    .map(x -> x.split(","))
                    .filter(x -> x.length == 3)
                    .filter(x -> Double.parseDouble(x[2]) >= 3)
                    .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));

            myRows.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hashMapStoring(){
        try {
            Stream<String> myRow = Files.lines(Paths.get("data.txt"));
            Map<String, Double> myMap = new HashMap<>();
            myMap = myRow
                        .map(x -> x.split(","))
                        .filter(x -> x.length == 3)
                        .filter(x -> Double.parseDouble(x[2]) >= 2)
                        .collect(Collectors.toMap(
                                x -> x[0],
                                x -> Double.parseDouble(x[2])
                        ));
            myRow.close();
            for (String key : myMap.keySet()){
                System.out.println(key + " " + myMap.get(key));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
