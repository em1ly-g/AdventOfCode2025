import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4 {
    // String fileName = "Day4/testData.txt";
    String fileName = "Day4/data.txt";
    List<char[]> data;
    int gridHeight = 0;
    int gridWidth = 0;


    private List<char[]> readData(String fileName, boolean printLines) {
        List<char[]> data = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                char[] lineList = line.toCharArray();
                data.add(lineList);
                if (printLines) {
                    System.out.println(line);
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Unable to read file" + fileName);
            e.printStackTrace();
        }
        return data;
    }


    private boolean checkLocaitonForRoll(int row, int col) {
        try {
            char location = data.get(col)[row];
            return location == '@' || location == 'X';
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private int getAvailableRolls() {
        int availableRolls = 0;
        for (int row=0; row<gridHeight; row++) {
            for (int col=0; col<gridWidth; col++){
                int surroundingRolls = 0;

                if (data.get(col)[row] == '@') {
                    if(checkLocaitonForRoll(row - 1, col - 1)){
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row - 1, col)) {
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row - 1, col + 1)) {
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row, col - 1)) {
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row, col + 1)) {
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row + 1, col - 1)) {
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row + 1, col)) {
                        surroundingRolls ++;
                    }
                    if (checkLocaitonForRoll(row + 1, col + 1)) {
                        surroundingRolls ++;
                    }

                    if (surroundingRolls < 4) {
                        availableRolls ++;
                        data.get(col)[row] = 'X';
                    }
                }
            }
        }
        return availableRolls;
    }

    private void part1() {
        this.data = readData(fileName, false);
        this.gridHeight = data.size();
        this.gridWidth = data.get(0).length;

        int availableRolls = getAvailableRolls();

        System.out.println("Part 1: " + availableRolls);
    }

    private boolean removeAvailableRolls() {
        boolean isUpdating = false;
         for (int row=0; row<gridHeight; row++) {
            for (int col=0; col<gridWidth; col++){
                if (data.get(row)[col] == 'X') {
                    data.get(row)[col] = 'O';
                    isUpdating = true;
                }
            }
        }
        return isUpdating;
    }

    private int countRemovedRolls() {
        int removedRolls = 0;
        for (int row=0; row<gridHeight; row++) {
            for (int col=0; col<gridWidth; col++){
                if (data.get(row)[col] == 'O') {
                    removedRolls ++;
                }
            }
        }
        return removedRolls;
    }

    private void part2() {
        boolean isUpdating = false;
        do {
            getAvailableRolls();
            isUpdating = removeAvailableRolls();
        } while (isUpdating);

        int removedRolls = countRemovedRolls();

        System.out.println("Part 2: " + removedRolls);
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();

        day4.part1();
        day4.part2();
    }

}
