import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3 {
    // String fileName = "Day3/testData.txt";
    String fileName = "Day3/data.txt";

    private List<List<Integer>> readData() {
        List<List<Integer>> data = new ArrayList<>();
        List<String> rawData = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                rawData.add(scanner.nextLine());
            }
            scanner.close();
        } catch(Exception e) {
            System.out.println("Problem reading data.");
            e.printStackTrace();
        }

        for (String line : rawData) {
            List<Integer> dataLine = new ArrayList<>();
            for (String charStr : line.split("(?!^)")) {
                dataLine.add(Integer.parseInt(charStr));
            }
            data.add(dataLine);
        }

        return data;
    }

    private int getLargestJoltage(List<Integer> bank) {
        // initalise to nonsensical value
        int value10s = -1;
        int value1s = -1;

        int value10sLocation = -1;

        for (int i=0; i<bank.size(); i++) {
            int batteryJoltage = bank.get(i);
            if (batteryJoltage > value10s && i < bank.size()-1) {
                value10s = batteryJoltage;
                value10sLocation = i;
                value1s = -1; // reset 1s when 10s is updated
            } else if (batteryJoltage > value1s && i > value10sLocation) {
                value1s = batteryJoltage;
            }
        }

        return value10s * 10 + value1s;
    }

    private void part1(List<List<Integer>> data) {

        List<Integer> joltageList = new ArrayList<>();

        for (List<Integer> bank : data) {
            int joltage = getLargestJoltage(bank);
            joltageList.add(joltage);
        }

        int sum = joltageList.stream().mapToInt(i -> i.intValue()).sum();

        System.out.println("Part 1: " + sum);

    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        List<List<Integer>> data = day3.readData();
        day3.part1(data);

    }
    
}
