package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/1/4 17:02.
 */

public class lession2 {
    //递归函数
    int fact(int n) {
        if (n == 1) {
            return 1;
        } else {
            return fact(n - 1) * n;
        }
    }

    public void main(String[] args) {
        System.out.println("factorial(10)=" + fact(4));
        int i = 7;
        System.out.println("兔子第" + i + "个月的总数为:" + f(i));
        System.out.println(num(100));
        System.out.println(y(100));

    }

    public int f(int x) {
        if (x == 1 || x == 2) {
            return 1;
        } else {
            return f(x - 1) + f(x - 2);
        }
    }

    public int num(int i) {
        if (i == 1) {
            return 1;
        } else {
            return i + num(i - 1);
        }
    }

    public static int y(int i) {
        int z = 0;
        for (int j = 0; j <= i; j++) {
            z += j;
        }
        return z;
    }

}
