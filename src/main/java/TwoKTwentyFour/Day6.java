package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static TwoKTwentyFour.Day6.Direction.*;


public class Day6 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day6/input.txt"));

        List<String> lines = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        int numRows = lines.getFirst().length();
        int numCol = lines.size();

        Position[][] map = new Position[numRows][numCol];

        int xStaring = 0;
        int yStarting = 0;

        for (int i = 0; i < numRows; i++) {
            char[] charArray = lines.get(i).toCharArray();
            for (int j = 0; j < charArray.length; j++) {
                char c = charArray[j];
                if (c == '^') {
                    xStaring = i;
                    yStarting = j;
                }
                map[i][j] = new Position(i, j, c);
            }
        }

        boolean shouldWalk = true;
        Direction direction = UP;
        int x = xStaring;
        int y = yStarting;

        Set<Position> visitedPositions = new HashSet<>();

        while (shouldWalk) {

            Position currentPosition = map[x][y];
            visitedPositions.add(currentPosition);

            switch (direction) {
                case UP -> {
                    if (x - 1 < 0) {
                        shouldWalk = false;
                        break;
                    } else {
                        if (map[x - 1][y].element == '#') {
                            direction = RIGHT;
                            continue;
                        }
                    }
                    x--;

                }
                case RIGHT -> {
                    if (y + 1 >= numCol) {
                        shouldWalk = false;
                        break;
                    } else {
                        if (map[x][y + 1].element == '#') {
                            direction = DOWN;
                            continue;
                        }
                    }
                    y++;
                }
                case DOWN -> {
                    if (x + 1 >= numRows) {
                        shouldWalk = false;
                        break;
                    } else {
                        if (map[x + 1][y].element == '#') {
                            direction = LEFT;
                            continue;
                        }
                    }
                    x++;

                }
                case LEFT -> {
                    if (y - 1 < 0) {
                        shouldWalk = false;
                        continue;
                    } else {
                        if (map[x][y - 1].element == '#') {
                            direction = UP;
                            continue;
                        }
                    }
                    y--;

                }
                default -> {
                    return;
                }

            }

//            System.out.printf("x=%d,y=%d%n", x, y);

        }

        System.out.println(visitedPositions.size());

        int countLoops = 0;

        for (Position visitedPosition : visitedPositions) {

            // clear map
            for (int i = 0; i < numRows; i++) {
                char[] charArray = lines.get(i).toCharArray();
                for (int j = 0; j < charArray.length; j++) {
                    char c = charArray[j];
                    if (c == '^') {
                        xStaring = i;
                        yStarting = j;
                    }
                    map[i][j] = new Position(i, j, c);
                }
            }

            visitedPosition.isVisited = false;
            visitedPosition.directions = new ArrayList<>();

            direction = UP;
            shouldWalk = true;
            x = xStaring;
            y = yStarting;

            if (visitedPosition.x == xStaring && visitedPosition.y == yStarting) {
                continue;
            } else {
                map[visitedPosition.x][visitedPosition.y].element = '#';
            }

            boolean isDirectionChanged = false;

            while (shouldWalk) {

                Position currentPosition = map[x][y];

                if (!isDirectionChanged && currentPosition.isVisited && currentPosition.directions.contains(direction)) {
                    countLoops++;
                    break;
                }

                if (isDirectionChanged) {
                    isDirectionChanged = false;
                }

                currentPosition.setVisitedAndDirection(direction);

                switch (direction) {
                    case UP -> {
                        if (x - 1 < 0) {
                            shouldWalk = false;
                            break;
                        } else {
                            if (map[x - 1][y].element == '#') {
                                map[x - 1][y].setVisitedAndDirection(UP);
                                direction = RIGHT;
                                isDirectionChanged = true;
                                continue;
                            }
                        }
                        x--;

                    }
                    case RIGHT -> {
                        if (y + 1 >= numCol) {
                            shouldWalk = false;
                            break;
                        } else {
                            if (map[x][y + 1].element == '#') {
                                map[x][y + 1].setVisitedAndDirection(RIGHT);
                                direction = DOWN;
                                isDirectionChanged = true;
                                continue;
                            }
                        }
                        y++;
                    }
                    case DOWN -> {
                        if (x + 1 >= numRows) {
                            shouldWalk = false;
                            break;
                        } else {
                            if (map[x + 1][y].element == '#') {
                                map[x + 1][y].setVisitedAndDirection(DOWN);
                                direction = LEFT;
                                isDirectionChanged = true;
                                continue;
                            }
                        }
                        x++;

                    }
                    case LEFT -> {
                        if (y - 1 < 0) {
                            shouldWalk = false;
                            continue;
                        } else {
                            if (map[x][y - 1].element == '#') {
                                map[x][y - 1].setVisitedAndDirection(LEFT);
                                direction = UP;
                                isDirectionChanged = true;
                                continue;
                            }
                        }
                        y--;

                    }
                    default -> {
                        return;
                    }

                }
            }



        }

        System.out.println(countLoops);


    }

    enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    static class Position {
        int x;
        int y;

        char element;

        boolean isVisited = false;
        Direction direction;
        List<Direction> directions = new ArrayList<>();

        Position(int x, int y, char element) {
            this.x = x;
            this.y = y;
            this.element = element;
        }

        void setVisitedAndDirection(Direction direction) {
            this.isVisited = true;
            this.direction = direction;
            this.directions.add(direction);
        }
    }

}
