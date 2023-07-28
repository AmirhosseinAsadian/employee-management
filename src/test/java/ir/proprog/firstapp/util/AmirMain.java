package ir.proprog.firstapp.util;

public class AmirMain {
    public static void main(String[] args) {

    }

    private int pow(int a, int b) {
        Math.pow(a, b);
        int result = 0;
        int idx = 1;
        while (idx <= b) {
            result = a * idx;
            idx++;
        }
        return result;
    }
}
