package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day11 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day11/input.txt"));

        List<Long> numbers = new ArrayList<>();

        List<Stone> stones = new ArrayList<>();

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            numbers.add(number);
            stones.add(new Stone(number, 1));
        }


        // part 1
//        for (int i = 0; i < 25; i++) {
//            numbers = blinkNumber(numbers);
//            System.out.println(numbers.size());
//            System.out.println(new HashSet<>(numbers).size());
//        }

        // part 2
        for (int i = 0; i < 75; i++) {
            List<Stone> blinked = blink(stones);

            Map<Stone, Long> memoization = new HashMap<>();

            for (Stone stone : blinked) {
                if (memoization.containsKey(stone)) {
                    memoization.compute(stone, (k, currentQuantity) -> currentQuantity + stone.quantity);
                } else {
                    memoization.put(stone, stone.quantity);
                }
            }

            stones = memoization.entrySet()
                    .stream()
                    .map(stone -> new Stone(stone.getKey().number, stone.getValue()))
                    .toList();

            System.out.println(i);
            System.out.println(stones.size());
        }

        long result = 0;

        for (Stone stone : stones) {
            result += stone.quantity;
        }

        System.out.println(result);

    }

    private static List<Long> blinkNumber(List<Long> numbers) {
        List<Long> result = new ArrayList<>();

        for (Long number : numbers) {
            List<Long> longs = blinkOne(number);
            result.addAll(longs);
        }

        return result;
    }

    private static List<Long> blinkOne(Long number) {
        List<Long> result = new ArrayList<>();

        if (number.equals(0L)) {
            result.add(1L);
        } else if (number.toString().length() % 2 == 0) {
            int size = number.toString().length() / 2;
            result.add((long) (number / Math.pow(10, size)));
            result.add((long) (number % Math.pow(10, size)));
        } else {
            result.add(number * 2024);
        }

        return result;
    }

    private static List<Stone> blink(List<Stone> stones) {
        List<Stone> result = new ArrayList<>();

        for (Stone stone : stones) {
            List<Stone> longs = blinkOne(stone);
            result.addAll(longs);
        }

        return result;
    }

    private static List<Stone> blinkOne(Stone stone) {
        List<Stone> result = new ArrayList<>();

        long number = stone.number;

        long quantity = stone.quantity;

        if (number == 0L) {
            result.add(new Stone(1L, quantity));
        } else if (String.valueOf(number).length() % 2 == 0) {
            int size = String.valueOf(number).length() / 2;
            result.add(new Stone((long) (number / Math.pow(10, size)), quantity));
            result.add(new Stone((long) (number % Math.pow(10, size)), quantity));
        } else {
            result.add(new Stone(number * 2024, quantity));
        }

        return result;
    }

    // Map<Long, Map<Long, List<Long>>> memoization

    private static List<Long> blink(List<Long> numbers, Map<Long, Map<Long, List<Long>>> memoization, long i) {
        List<Long> result = new ArrayList<>();

        for (Long number : numbers) {
            List<Long> longs = blinkOne(number, memoization, i);
            result.addAll(longs);
        }

        return result;
    }

    private static List<Long> blinkOne(Long number, Map<Long, Map<Long, List<Long>>> memoization, long i) {
        List<Long> result = new ArrayList<>();

        if (memoization.containsKey(number)) {
            if (memoization.get(number).containsKey(i)) {
                return memoization.get(number).get(i);
            }
        }

        if (number.equals(0L)) {
            result.add(1L);
        } else if (number.toString().length() % 2 == 0) {
            int size = number.toString().length() / 2;
            result.add((long) (number / Math.pow(10, size)));
            result.add((long) (number % Math.pow(10, size)));
        } else {
            result.add(number * 2024);
        }

        return result;
    }

    static class Stone {
        final long number;

        long quantity;


        Stone(long number, long quantity) {
            this.number = number;
            this.quantity = quantity;
        }

        void setQuantity(long quantity) {
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Stone stone = (Stone) o;
            return number == stone.number;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(number);
        }
    }

}
