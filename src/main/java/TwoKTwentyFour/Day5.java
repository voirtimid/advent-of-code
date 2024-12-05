package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day5/input.txt"));

        Map<Integer, List<Integer>> pageOrderingRules = new HashMap<>();

        List<List<Integer>> pageUpdates = new ArrayList<>();

        boolean readingPageOrderingRules = true;

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            if (line.isEmpty()) {
                readingPageOrderingRules = false;
                continue;
            }

            if (readingPageOrderingRules) {
                List<Integer> pages = Arrays.stream(line.split("\\|")).map(Integer::parseInt).toList();
                if (pageOrderingRules.containsKey(pages.getFirst())) {
                    List<Integer> list = pageOrderingRules.get(pages.getFirst());
                    list.add(pages.getLast());
                    pageOrderingRules.put(pages.getFirst(), list);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(pages.getLast());
                    pageOrderingRules.put(pages.getFirst(), list);
                }
            } else {
                pageUpdates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
            }
        }

        List<Integer> middlePages = new ArrayList<>();

        List<List<Integer>> faultyPages = new ArrayList<>();

        for (List<Integer> pageUpdate : pageUpdates) {
            List<Integer> checkPage = new ArrayList<>();
            boolean isFaulty = false;
            for (Integer page : pageUpdate) {
                for (Integer i : checkPage) {
                    if (pageOrderingRules.getOrDefault(page, List.of()).contains(i)) {
                        isFaulty = true;
                        break;
                    }
                }

                checkPage.add(page);

                if (isFaulty) {
                    break;
                }

            }

            if (!isFaulty) {
                middlePages.add(pageUpdate.get((int) Math.ceil(pageUpdate.size() / 2)));
            } else {
                faultyPages.add(pageUpdate);
            }
        }

        System.out.println(middlePages.stream().reduce(0, Integer::sum));

//        System.out.println(pageOrderingRules);
//        System.out.println(pageUpdates);
//        System.out.println(faultyPages);

        middlePages = new ArrayList<>();

        for (List<Integer> faultyPage : faultyPages) {
            List<Integer> updatedPage = new ArrayList<>();
            boolean isFaulty = false;
            for (Integer page : faultyPage) {
                int countFaulty = 0;
                for (Integer cPage : updatedPage) {
                    if (pageOrderingRules.getOrDefault(page, List.of()).contains(cPage)) {
                        isFaulty = true;
                        countFaulty++;
                    }
                }

                if (isFaulty) {
                    updatedPage.add(updatedPage.size() - countFaulty, page);
                } else {
                    updatedPage.addLast(page);
                }
                isFaulty = false;
            }

            middlePages.add(updatedPage.get((int) Math.ceil(updatedPage.size() / 2)));
        }

        System.out.println(middlePages.stream().reduce(0, Integer::sum));
    }
}
