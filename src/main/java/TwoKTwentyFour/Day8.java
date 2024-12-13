package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day8/example2.txt"));

        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        int numRows = lines.getFirst().length();
        int numCol = lines.size();

        Position[][] map = new Position[numRows][numCol];

        Set<Position> antinodes = new HashSet<>();

        for (int i = 0; i < numRows; i++) {
            char[] charArray = lines.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                Position position = new Position(i, j, c);
                map[i][j] = position;
            }
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCol; j++) {
                Position currentPosition = map[i][j];
                if (currentPosition.element == '.') {
                    continue;
                }

                for (int k = i; k < numRows; k++) {
                    for (int l = 0; l < numCol; l++) {
                        if (k == i && l <= j) {
                            continue;
                        }
                        Position nextPosition = map[k][l];
                        if (currentPosition.element == nextPosition.element) {
                            int newX = currentPosition.x - nextPosition.x;
                            int newY = currentPosition.y - nextPosition.y;

                            int newUpX = currentPosition.x + newX;
                            int newUpY = currentPosition.y + newY;

                            int countPlusCounter = 0;
                            while ((newUpX >= 0 && newUpX < numRows)
                                    && (newUpY >= 0 && newUpY < numCol)) {
                                Position antinode = new Position(newUpX, newUpY, '#');
                                antinodes.add(antinode);
                                newUpX += newX;
                                newUpY += newY;

                                countPlusCounter++;
                            }

//                            if (countPlusCounter == 1) {
////                                antinodes.add(nextPosition);
//                            } else if (countPlusCounter >= 2) {
//                                antinodes.add(nextPosition);
//                                antinodes.add(currentPosition);
//                            }

                            int newDownX = nextPosition.x - newX;
                            int newDownY = nextPosition.y - newY;

                            countPlusCounter = 0;
                            while ((newDownX >= 0 && newDownX < numRows)
                                    && (newDownY >= 0 && newDownY < numCol)) {
                                Position antinode = new Position(newDownX, newDownY, '#');
                                antinodes.add(antinode);

                                newDownX -= newX;
                                newDownY -= newY;

                                countPlusCounter++;
                            }

                            antinodes.add(nextPosition);
                            antinodes.add(currentPosition);

//                            if (countPlusCounter == 1) {
////                                antinodes.add(currentPosition);
//                            } else if (countPlusCounter >= 2) {
//                                antinodes.add(currentPosition);
//                                antinodes.add(nextPosition);
//                            }
                        }
                    }
                }

            }
        }

        System.out.println(antinodes);
        System.out.println(antinodes.size());

    }

    private static class Position {
        private final int x;
        private final int y;

        private char element;

        public Position(int x, int y, char element) {
            this.x = x;
            this.y = y;
            this.element = element;
        }

        public void setElement(char element) {
            this.element = element;
        }

        public boolean isAntinode() {
            return element == '#';
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", element=" + element +
                    '}';
        }
    }

}
