package TwoKTwentyFour;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/2024/day9/input.txt"));

        String memory = scanner.nextLine();

        int[] blocks = memory.chars().map(c -> c - '0').toArray();

        List<MemoryBlock> memoryBlocks = new ArrayList<>();

        boolean freeMemory = false;
        long blockId = 0;
        for (int block : blocks) {
            for (int i = 0; i < block; i++) {
                memoryBlocks.add(new MemoryBlock(freeMemory ? null : blockId));
            }
            if (!freeMemory) {
                blockId++;
            }
            freeMemory = !freeMemory;
        }

        long result = 0;

        int length = memoryBlocks.size();

        // Part 2, part 2
        for (int i = length - 1; i >= 0 ; i--) {
            MemoryBlock currentBlock = memoryBlocks.get(i);
            if (currentBlock.isFree()) {
                continue;
            }
            int countFromBehind = 1;
            for (int j = i - 1; j >= 0 ; j--) {
                MemoryBlock nextBlock = memoryBlocks.get(j);
                if (nextBlock.isFree()) {
                    break;
                } else if (currentBlock.blockId.equals(nextBlock.blockId)) {
                    countFromBehind++;
                } else {
                    break;
                }
            }
            i -= countFromBehind - 1;

            for (int j = 0; j < i; j++) {
                int countFreeMemory = 0;
                for (int k = j; k < length; k++) { // -j, stupid
                    if (memoryBlocks.get(k).isFree()) {
                        countFreeMemory++;
                    } else {
                        break;
                    }
                }

                if (countFreeMemory >= countFromBehind) {
                    for (int k = 0; k < countFromBehind; k++) {
                        memoryBlocks.get(j + k).setBlockId(memoryBlocks.get(i + k).blockId);
                        memoryBlocks.get(i + k).setBlockId(null);
                    }
                    break;
                }
            }


        }

//        System.out.println(printDisk(memoryBlocks));

        // 6404056047358 Oliver
        // 6379677752410 Anton

        for (int i = 0; i < memoryBlocks.size(); i++) {
            if (!memoryBlocks.get(i).isFree()) {
                result += (long) i * memoryBlocks.get(i).blockId;
            }
        }

        // Part 1
//        for (int i = 0; i < length - takenFromBehind; i++) {
//            MemoryBlock memoryBlock = memoryBlocks.get(i);
//            if (!memoryBlock.isFree()) {
//                result += (long) i * memoryBlock.blockId;
//            } else {
//                for (int j = length - takenFromBehind - 1; j > i; j--) {
//                    takenFromBehind++;
//                    MemoryBlock temp = memoryBlocks.get(j);
//                    if (!temp.isFree()) {
//                        result += (long) i * temp.blockId;
//                        memoryBlock.setBlockId(temp.blockId);
//                        temp.setBlockId(null);
//                        break;
//                    }
//                }
//            }
//
////            System.out.println(i);
////            System.out.println(takenFromBehind);
////            System.out.println(memoryBlock);
//        }



        System.out.println(result);

    }

    private static String printDisk(List<MemoryBlock> blocks) {
        return String.join("", blocks.stream().map(b -> b.blockId == null ? "." : String.valueOf(b.blockId)).map(b -> "|" + b + "|").toList());
    }

    private static class MemoryBlock {
        private Long blockId;

        public MemoryBlock(Long blockId) {
            this.blockId = blockId;
        }

        public boolean isFree() {
            return blockId == null;
        }

        public void setBlockId(Long blockId) {
            this.blockId = blockId;
        }
    }
}
