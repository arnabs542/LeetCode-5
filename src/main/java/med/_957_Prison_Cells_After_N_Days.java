package med;

import org.testng.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by udaythota on 4/14/20.
 * <p>
 * There are 8 prison cells in a row, and each cell is either occupied or vacant.
 * Each day, whether the cell is occupied or vacant changes according to the following rules:
 * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
 * Otherwise, it becomes vacant.
 * </p>
 */
public class _957_Prison_Cells_After_N_Days {
    // first try to find the number of cycles after which the pattern repeats itself. then use this cycles to find the actual N (N%cycles) and then use that N to find the next day cells
    // as there are only 8 states there are utmost 256 combinations and the pattern has to somewhere repeat before itself
    // TC: O(1), SC: O(1) - because irrespective of n, we always gonna have only 256 combinations and same with space (doesn't grow with input)
    private static int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0 || N < 0) {
            return null;
        }
        Set<String> set = new HashSet<>();
        int cycles = 0;
        boolean cycleFound = false;
        for (int i = 0; i < N; i++) {
            int[] nextDayCells = nextDay(cells);
            String key = Arrays.toString(nextDayCells);
            if (!set.contains(key)) {
                set.add(key);
                cycles++;
            } else {
                cycleFound = true;
                break;
            }
            cells = nextDayCells;
        }
        if (cycleFound) {
            N %= cycles;
            for (int i = 0; i < N; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    // simple helper method to get the state of cells on the next day
    private static int[] nextDay(int[] cells) {
        int[] newCells = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            newCells[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
        }
        return newCells;
    }

    public static void main(String[] args) {
    //    Assert.assertEquals(prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7), new int[]{0, 0, 1, 1, 0, 0, 0, 0});
        Assert.assertEquals(prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000), new int[]{0, 0, 1, 1, 1, 1, 1, 0});
    }
}
