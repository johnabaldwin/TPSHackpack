public class SegmentTree {

    int size;
    int[] low, high, min, delta;

    SegmentTree(int n) {
        this.size = n;
        //4*n + 1 is the number of nodes in a tree of size n, a good proof to do yourself
        //Also easily derivable in contest
        low = new int[4*size + 1];
        high = new int[4*size + 1];
        min = new int[4*size + 1];
        delta = new int[4*size + 1];

        init(1, 0, size-1);
    }


    //a & b are high & low of interval
    void increment(int a, int b, int value) {
        increment(1, a, b, value);
    }

    void increment(int i, int a, int b, int value) {
        if(b < low[i] || a > high[i])
            return;
        if(low[i] >= a && b <= high[i]) {
            delta[i] += value;
            return;
        }
        propogate(i);
        increment(2*i,a,b,value);
        increment(2*i + 1,a,b,value);
        update(i);
    }

    void init(int i, int a, int b) {
        low[i] = a;
        high[i] = b;

        if (a == b)
            return;
        int m = (a+b)/2;
        init(2*i,a,m);
        init(2*i + 1,m+1,b);
    }

    int minimum(int i, int a, int b) {
        if(a > high[i] || b < low[i])
            return Integer.MAX_VALUE;
        if(low[i] >= a && b <= high[i])
            return min[i] + delta[i];
        propogate(i);
        int leftMin = minimum(2*i,a,b);
        int rightMin = minimum(2*i+1,a,b);
        update(i);
        return Math.min(leftMin, rightMin);
    }

    int minimum(int a, int b) {
        return minimum(1, a, b);
    }

    //typically code that changes
    void propogate(int i) {
        delta[2*i] += delta[i];
        delta[2*i + 1] += delta[i];
        delta[i] = 0;
    }

    //typically code that changes
    void update(int i) {
        min[i] = Math.min(min[2*i] + delta[2*i], min[2*i+1] + delta[2*i+1]);
    }
}
