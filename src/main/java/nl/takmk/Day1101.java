package nl.takmk;

public class Day1101 {

    /**
     * @See https://adventofcode.com/2018/day/11
     * <p>
     */


    public static void main(String[] args) {

        final int serial = 4842;

        int highestLevel = 0;
        int xh = 0;
        int yh = 0;
        int bh = 0;


        // part 1

        int blockSize = 3;

        for (int x = 1; x < 300 - blockSize; x++) {
            for (int y = 1; y < 300 - blockSize; y++) {
                if (calcPowerLevelBlock(x, y, blockSize, serial) > highestLevel) {
                    highestLevel = (calcPowerLevelBlock(x, y, blockSize, serial));
                    xh = x;
                    yh = y;
                }
            }
        }

        log("Highest level is " + highestLevel + " at (" + xh + "," + yh + ") with block size " + bh);

        // part 2

        for (blockSize = 1; blockSize < 300; blockSize++) {
            for (int x = 1; x < 300 - blockSize; x++) {
                for (int y = 1; y < 300 - blockSize; y++) {
                    if (calcPowerLevelBlock(x, y, blockSize, serial) > highestLevel) {
                        highestLevel = (calcPowerLevelBlock(x, y, blockSize, serial));
                        xh = x;
                        yh = y;
                        bh = blockSize;
                    }

                }
            }
        }

        log("Highest level is " + highestLevel + " at (" + xh + "," + yh + ") with block size " + bh);


    }

    /**
     * Calculate powerlevel for 3x3 block
     */
    private static int calcPowerLevelBlock(int startX, int startY, int blockSize, int serial) {

        int blockPowerLevel = 0;
        for (int x = startX; x < startX + blockSize; x++) {
            for (int y = startY; y < startY + blockSize; y++) {
                blockPowerLevel = blockPowerLevel + calcPowerLevel(x, y, serial);
            }
        }

        return blockPowerLevel;
    }


    /**
     * Find the fuel cell's rack ID, which is its X coordinate plus 10.
     * Begin with a power level of the rack ID times the Y coordinate.
     * Increase the power level by the value of the grid serial number (your puzzle input).
     * Set the power level to itself multiplied by the rack ID.
     * Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
     * Subtract 5 from the power level.
     * <p>
     * eg
     * The rack ID is 3 + 10 = 13.
     * The power level starts at 13 * 5 = 65.
     * Adding the serial number produces 65 + 8 = 73.
     * Multiplying by the rack ID produces 73 * 13 = 949.
     * The hundreds digit of 949 is 9.
     * Subtracting 5 produces 9 - 5 = 4.
     */
    private static int calcPowerLevel(int x, int y, int serial) {

        final int rackId = x + 10;

        int powerLevel = rackId * y;
        powerLevel = powerLevel + serial;
        powerLevel = powerLevel * rackId;
        powerLevel = ((powerLevel / 100) % 10);
        powerLevel = powerLevel - 5;

        return powerLevel;
    }


    //-----


    private static void log(String logLine) {
        System.out.println(logLine);
    }


}

