package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day3/input.txt"));

        StringBuilder program = new StringBuilder();

        while (scanner.hasNextLine()) {
            program.append(scanner.nextLine());
        }

        String programInput = program.toString();

//        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)"); FOR PART 1!
        Pattern pattern = Pattern.compile("mul\\([0-9]{1,3},[0-9]{1,3}\\)|don't\\(\\)|do\\(\\)");
        Matcher matcher = pattern.matcher(programInput);

        int result = 0;

        boolean shouldCount = true;
        while (matcher.find()) {
            String group = matcher.group();

            if ("don't()".equals(group)) {
                shouldCount = false;
                continue;
            } else if ("do()".equals(group)) {
                shouldCount = true;
                continue;
            }

            if (shouldCount) {
                result += solvePart1(group);
            }
        }

        System.out.println(result);
    }

    private static int solvePart1(String group) {
        String[] parts = group.replace("mul(", "").replace(")", "").split(",");

        return Integer.parseInt(parts[0]) * Integer.parseInt(parts[1]);
    }
}
