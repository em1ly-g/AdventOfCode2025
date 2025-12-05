import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day2 {
    // String fileName = "Day2/testData.txt";
    String fileName = "Day2/data.txt";

    private String[] readFile() {
        try{
            File dataFile = new File(fileName);
            Scanner scanner = new Scanner(dataFile);
            // data comes in one line
            String data = scanner.nextLine();
            scanner.close();
            return data.split(",");
        } catch (Exception e) {
            System.out.println("There was a problem reading the data");
            e.printStackTrace();
        }
        return null;
    }

    private void part1(String[] data) {

        long invalidIdTotal = 0;

        for (String productId : data) {
            String[] ids = productId.split("-");

            long firstId = Long.valueOf(ids[0]);
            long lastId = Long.valueOf(ids[1]);

            List<String> idList = getAllStringIds(firstId, lastId);

            for(String id : idList) {
                if (!validateId(id)) {
                   invalidIdTotal += Long.valueOf(id);
                }
            }
        }
        System.out.println("Part 1: " + invalidIdTotal);
    }

    private boolean validateId(String id) {
        // invalid strings must be even
        if (id.length() % 2 != 0){
            return true;
        }

        int midpoint = id.length() / 2;
        String firstHalf = id.substring(0, midpoint);
        String secondHalf = id.substring(midpoint);

        // ids are valid if strings aren't equal
        return !firstHalf.equals(secondHalf);
    }

    private List<String> getAllStringIds(long firstId, long lastId) {
        List<String> idList = new ArrayList<>();

        for (long i=firstId; i<=lastId; i++) {
            idList.add(Long.toString(i));
        }

        return idList;
    }

    private int countOccurences(String string, String subString) {
        int count = 0;
        String testString = "";
        
        while (testString.length() < string.length()) {
            testString += subString;
            if (string.contains(testString)) {
                count ++;
                if (testString.equals(string)){
                    return count;
                }
            } else { 
                // don't care about the count in this scenario
                return 0;
            }
        }

        // dont care about count
        return 0;
    }

    private boolean validateId2(String id) {
        // max substring to be repeated twice
        int maxSubstring = id.length()/2;

        String substring;

        for (int i=maxSubstring; i>0; i--) {
            substring = id.substring(0, i);
            int substringRepititions = countOccurences(id, substring);

            if (substringRepititions > 1) {
                return false;
            }
        }
        return true;
    }

    private void part2(String[] data) {
        long invalidIdTotal = 0;

        for (String productId : data) {
            String[] ids = productId.split("-");

            long firstId = Long.valueOf(ids[0]);
            long lastId = Long.valueOf(ids[1]);

            List<String> idList = getAllStringIds(firstId, lastId);

            for(String id : idList) {
                if (!validateId2(id)) {
                   invalidIdTotal += Long.valueOf(id);
                }
            }
        }
        System.out.println("Part 2: " + invalidIdTotal);
    }

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        String[] data = day2.readFile();
        day2.part1(data);
        day2.part2(data);
    }
}
