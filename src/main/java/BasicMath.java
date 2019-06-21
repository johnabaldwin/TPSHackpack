import java.util.Arrays;

public class BasicMath {

    /**
     * These next 2 methods are just definitions, you don't
     * need to understand exactly how they work just know what they do.
     * When initially calling these methods {@code a} and {@code b} are
     * reflexive.
     */
    public static int GreatestCommonDivisor(int a, int b) {
        return b == 0 ? a : GreatestCommonDivisor(b, a % b);
    }

    public static int LeastCommonMultiple(int a, int b) {
        return (a * b) / GreatestCommonDivisor(a, b);
    }

    /*
    Modular Exponentiation is a fast way to get the remainder of a small number raised to a huge number (ie 2^5823723042302)
    mod another huge number (in this case 1000000007) quickly.
     */
    static int MOD = 1000000007;

    public static long modPow(int base, long exp) {
        if (exp == 0) return 1 % MOD;
        if (exp % 2 == 0) {
            long half = modPow(base, exp / 2);
            return (half * half) % MOD;
        }

        return (base * modPow(base, exp - 1)) % MOD;
    }

    /*
        This method finds all possible permutations of a set of numbers. Becareful because I believe this runs in O(n!)
        because thats the minimum needed to find all possible permutations. The input is an empty array (perm) that this
        method will build as it creates each permutation, an empty boolean array (used) to determine which numbers have
        been used in each permutation, the length of the permutations (k), and the numbers that are being used (nums).
     */
    public static void permute(int[] perm, boolean[] used, int k, int[] nums) {
        if (k == perm.length) {
            System.out.println(Arrays.toString(perm));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    perm[k] = nums[i];
                    used[i] = true;
                    permute(perm, used, k + 1, nums);
                    used[i] = false;
                }
            }
        }
    }

    public static void PrimeSieve(int n) {
        // initially assume all integers >= 2 are prime by marking them as true in this array
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        /*
        Starting from 2 all numbers that are multiples of two can be marked as not prime, then you can move on to 3,
        3 has not been marked as not prime, so it is prime but all of its multiples aren't. This logic can be applied
        ad infinitum. The (factor*factor <= n) aspect is a minor improvement that can be made to improve the runtime by
        a little bit, but I have been told this is not very significant.
         */

        for (int factor = 2; factor * factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor * j <= n; j++) {
                    isPrime[factor * j] = false;
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes++;
        }
        System.out.println("The number of primes <= " + n + " is " + primes);
    }

    /*
        A powerset in the set of all possible subsets of a give set (ie for the set {1,2,3} the powerset is
        {{ },{ 1 },{ 2 },{ 1 2 },{ 3 },{ 1 3 },{ 2 3 },{ 1 2 3 }}). This method takes in both the set and the size of the
        set. Don't be too concerned about how this code operates. I have never needed it for competitive programming, but
        it has come in handy for smaller projects. The runtime of the method is O(2^n).
     */
    public static void Powerset(int n, int[] set) {
        // Run a loop for printing all 2^n
        // subsets one by one
        for (int i = 0; i < (1 << n); i++) {
            System.out.print("{ ");

            // Print current subset
            for (int j = 0; j < n; j++)

                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get which numbers
                // are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0)
                    System.out.print(set[j] + " ");

            System.out.println("}");
        }
    }

}
