package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/resources/2024/day4/input.txt"));

        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }


        int numRows = lines.getFirst().length();
        int numCol = lines.size();

        char[][] map = new char[numRows][numCol];

        for (int i = 0; i < numRows; i++) {
            map[i] = lines.get(i).toCharArray();
        }

        System.out.println(part1(lines, map));
        System.out.println(part2(lines, map));
    }

    private static int part2(List<String> lines, char[][] map) {
        int result = 0;

        int numRows = lines.getFirst().length();
        int numCol = lines.size();

        for (int i = 1; i < numRows - 1; i++) {
            for (int j = 1; j < numCol - 1; j++) {
                if ('A' == map[i][j]) {
                    StringBuilder line = new StringBuilder();
                    String diagonal1 = line.append(map[i - 1][j - 1]).append(map[i][j]).append(map[i + 1][j + 1]).toString();
                    line = new StringBuilder();
                    String diagonal2 = line.append(map[i - 1][j + 1]).append(map[i][j]).append(map[i + 1][j - 1]).toString();

                    if ((diagonal1.equals("MAS") || diagonal1.equals("SAM")) && (diagonal2.equals("MAS") || diagonal2.equals("SAM"))) {
                        result++;
                    }
                }
            }
        }

        return result;
    }


    private static int part1(List<String> lines, char[][] map) {

        int result = 0;

        int numRows = lines.getFirst().length();
        int numCol = lines.size();

        Pattern xmasPattern = Pattern.compile("XMAS");
        Pattern samxPattern = Pattern.compile("SAMX");

        // check horizontally
//        System.out.println("Horizontal check");
        for (int i = 0; i < numRows; i++) {
            String line = lines.get(i);

//            System.out.println(line);

            Matcher xmasMatcher = xmasPattern.matcher(line);
            while (xmasMatcher.find()) {
                result++;
            }

            Matcher samxMatcher = samxPattern.matcher(line);
            while (samxMatcher.find()) {
                result++;
            }
        }

        // check vertically
//        System.out.println("Vertical check");

        for (int j = 0; j < numCol; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                line.append(map[i][j]);
            }

//            System.out.println(line);

            Matcher xmasMatcher = xmasPattern.matcher(line);
            while (xmasMatcher.find()) {
                result++;
            }

            Matcher samxMatcher = samxPattern.matcher(line);
            while (samxMatcher.find()) {
                result++;
            }

        }

        // 00,11,22
        // 10,21,32
        // 20,31,42
//        System.out.println("Low Diagonal");
        for (int i = 0; i < numRows - 3; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < numCol - i; j++) {
                line.append(map[i+j][j]);
            }

//            System.out.println(line);

            Matcher xmasMatcher = xmasPattern.matcher(line);
            while (xmasMatcher.find()) {
                result++;
            }

            Matcher samxMatcher = samxPattern.matcher(line);
            while (samxMatcher.find()) {
                result++;
            }
        }

        // 01,12,23
        // 02,13,24
        // 03,14,25
//        System.out.println("Up Diagonal");
        for (int j = 1; j < numCol - 3; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < numRows - j; i++) {
                line.append(map[i][j+i]);
            }

//            System.out.println(line);

            Matcher xmasMatcher = xmasPattern.matcher(line);
            while (xmasMatcher.find()) {
                result++;
            }

            Matcher samxMatcher = samxPattern.matcher(line);
            while (samxMatcher.find()) {
                result++;
            }
        }

        // REVERSE
//        System.out.println("Low Reverse Diagonal");
        for (int i = 0; i < numRows; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < numCol - i; j++) {
                line.append(map[i+j][numCol - j - 1]);
            }

//            System.out.println(line);

            Matcher xmasMatcher = xmasPattern.matcher(line);
            while (xmasMatcher.find()) {
                result++;
            }

            Matcher samxMatcher = samxPattern.matcher(line);
            while (samxMatcher.find()) {
                result++;
            }
        }

        // 01,12,23
        // 02,13,24
        // 03,14,25
//        System.out.println("Up Reverse Diagonal");
        for (int j = 1; j < numCol; j++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < numRows - j; i++) {
                line.append(map[i][numRows - j - i - 1]);
            }

//            System.out.println(line);

            Matcher xmasMatcher = xmasPattern.matcher(line);
            while (xmasMatcher.find()) {
                result++;
            }

            Matcher samxMatcher = samxPattern.matcher(line);
            while (samxMatcher.find()) {
                result++;
            }
        }

        return result;

    }


}
