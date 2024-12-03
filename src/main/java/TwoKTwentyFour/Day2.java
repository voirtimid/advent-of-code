package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day2/input.txt"));

        List<List<Integer>> reports = new ArrayList<>();
        List<List<Integer>> forFiltering = new ArrayList<>();

        while (scanner.hasNext()) {
            ArrayList<Integer> report = new ArrayList<>();
            String line = scanner.nextLine();

            for (String l : line.split(" ")) {
                report.add(Integer.valueOf(l));
            }

            reports.add(report);
        }

        int result = 0;

        for (List<Integer> report : reports) {
            if (isGoodReport(report)) {
                result++;
            } else {
                forFiltering.add(report);
            }
        }

        for (List<Integer> report : forFiltering) {
            int size = report.size();
            for (int i = 0; i < size; i++) {
                List<Integer> temp = new ArrayList<>();
                for (int j = 0; j < report.size(); j++) {
                    if (i != j) {
                        temp.add(report.get(j));
                    }
                }
                if (isGoodReport(temp)) {
                    result++;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    private static boolean isGoodReport(List<Integer> report) {
        boolean allGood = true;

        int asc = 0;
        int desc = 0;
        for (int i = 0; i < report.size() - 1; i++) {
            boolean isAsc = report.get(i) - report.get(i + 1) < 0;
            if (isAsc) {
                asc++;
            } else {
                desc++;
            }
        }
        boolean ascending = desc <= asc;

        if (asc > 1 && desc > 1) {
            allGood = false;
        }

        for (int i = 0; i < report.size() - 1; i++) {
            if (ascending == report.get(i) - report.get(i + 1) < 0) {
                if (Math.abs(report.get(i) - report.get(i+1)) < 1 || Math.abs(report.get(i) - report.get(i+1)) > 3) {
                    allGood = false;
                    break;
                }
            } else {
                allGood = false;
                break;
            }
        }

        if (allGood) {
            System.out.print("All good=");
            System.out.println(report);
        } else {
            System.out.print("Not good=");
            System.out.println(report);
        }

        return allGood;

    }

}
