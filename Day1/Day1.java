/**
 * Attempted answers:
 * 
 * 475 - too low
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    // private String fileName = "Day1/testData1.txt";
    private String fileName = "Day1/data1.txt";
    private int initialValue = 50;

    private List<String> readFile() {
        List<String> data = new ArrayList<>();

        try {
            String userDirectory = new File(fileName).getAbsolutePath();
            System.out.println(userDirectory);
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String dataElement = scanner.nextLine();
                data.add(dataElement);
            }
            scanner.close();
            System.out.println("Successfully read " + fileName);
            return data;
        } catch (Exception e) {
            System.out.println("Unable to read data");
            e.printStackTrace();
            return null;
        }
    }

    private int calculatePassword(List<String> data) {
        int dialPoints = initialValue;
        int password = 0;

        for(String dataElement : data) {
            String[] dataParts = dataElement.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            String direction = dataParts[0];
            String value = dataParts[1];

            if (direction.contains("R")) {
                dialPoints += Integer.valueOf(value);
            } else if (direction.contains("L")) {
                dialPoints -= Integer.valueOf(value);
            } else {
                System.out.println("There was a problem processing the data.");
            }

            // Use -ve safe mod
            dialPoints = ((dialPoints % 100) + 100) % 100;

            if (dialPoints == 0) {
                password += 1;
            }
        }

        return password;
    }

    private int calculatePassword2(List<String> data) {
        int dialPoints = initialValue;
        int password = 0;

        for(String dataElement : data) {
            String[] dataParts = dataElement.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            String direction = dataParts[0];
            int value = Integer.valueOf(dataParts[1]);
            
            int previousDialPoints = dialPoints;

            // update dial
            if (direction.contains("R")) {
                dialPoints += value;
            } else if (direction.contains("L")) {
                dialPoints -= value;
            } else {
                System.out.println("There was a problem processing the data.");
            }

            int wholeRotations = Math.floorDiv(value, 100);
            boolean partialRotation = false;

            dialPoints = ((dialPoints % 100) + 100) % 100;

            password += wholeRotations;

            if (dialPoints == 0) {
                password += 1;
            } else {
                if (direction.contains("R")) {
                    partialRotation = dialPoints < previousDialPoints;
                } else {
                    partialRotation = (dialPoints > previousDialPoints) && previousDialPoints != 0;
                }
            }

            if (partialRotation) {
                password += 1;
            }
        }

        return password;
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        
        List<String> data = day1.readFile();
        System.out.println("Password: " + day1.calculatePassword(data));
        System.out.println("Password2: " + day1.calculatePassword2(data));
        
    }
}