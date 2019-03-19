
import java.util.*;


/*
This data structure solves the problem of how to quickly get a range sum (sum of elements from n to m) quickly
and how to update values quickly. It does both operations in O(log(n)) time. How it does this is relatively unimportant
to understand, but a brief explanation is that it uses binary numbers to determine how many indicies each index should keep
track of.
 */
public class FenwickTree //Also known as a Binary Index Tree (BIT)
{
    //This code is infamous for being black boxed because it can be very hard to understand.
    //I would recommend you gain some slight understanding of how it functions and then just learn how to use it.
    static class BITree
    {
        final int max = 1000; // max size of tree in order to allow insertion
        // should use max bounds of problem

        int[] bit = new int[max];

        /**
         *
         * @param size - the size of the fenwick tree, incremented by 1 in order to avoid using 0 index
         * @param arr - original values to store in the BIT
         */
        public BITree(int size, int[] arr) {
            Arrays.fill(bit, 1, size + 1, 0);
            for (int i = 0; i < size; i++) {
                updateBIT(i, size, arr[i]);
            }
        }

        /**
         *
         * @param i - starting index
         * @param n - size of the BIT
         * @param val - value to add into the BIT
         */
        public void updateBIT(int i, int n, int val) {
            i += 1;
            while (i <= n) {
                bit[i] += val;
                i += i & (-i);
            }
        }

        /**
         * Gets the sum from index 1 to i in log(i) time
         * @param i - index to end summation
         * @return - the sum of all elements in the range 1 to i
         */
        public int getSum(int i) {
            i += 1;
            int sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & (-i);
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int size = in.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = in.nextInt();
        BITree b = new BITree(size, arr);
        System.out.println(b.getSum(5));
    }
}
/*
 12 2 1 1 3 2 3 4 5 6 7 8 9
 */
