package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/resources/2024/day1/test.txt"));

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        while (scanner.hasNext()) {
            left.add(scanner.nextInt());
            right.add(scanner.nextInt());
        }

        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        int result = 0;

        for (int i = 0; i < left.size(); i++) {
            Integer theNumber = left.get(i);

            int countOccurrence = 0;
            for (int j = 0; j < right.size(); j++) {
                if (theNumber.equals(right.get(j))) {
                    countOccurrence++;
                } else if (theNumber > right.get(j) && countOccurrence != 0) {
                    break;
                }
            }


            result += theNumber * countOccurrence;
        }

        System.out.println(result);
    }
}
