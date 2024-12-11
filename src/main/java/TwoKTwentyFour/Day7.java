package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day7/input.txt"));

        long result = 0L;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            long endResult = Long.parseLong(line.split(":")[0]);
            List<Long> numbers = Arrays.stream(line.split(":")[1].split(" ")).skip(1).map(Long::parseLong).toList();

            if (isValid(endResult, numbers.getFirst(), numbers.subList(1, numbers.size()))) {
                result += endResult;
            }
        }

        System.out.println(result);
    }

    private static boolean isValid(long result, long acc, List<Long> numbers) {
        if (numbers.isEmpty()) {
            return acc == result;
        } else {
            Long first = numbers.getFirst();

            boolean valid1 = isValid(result, acc + first, numbers.subList(1, numbers.size()));

            boolean valid2 = isValid(result,acc * first, numbers.subList(1, numbers.size()));

            boolean valid3 = isValid(result, (long) (acc * Math.pow(10, first.toString().length())) + first, numbers.subList(1, numbers.size()));

            return valid1 || valid2 || valid3;
        }
    }

}
