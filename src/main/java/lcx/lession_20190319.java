package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/19 16:16.
 */
public class lession_20190319 {
    int[] data;
    int maxSize;
    int top;

    public lession_20190319(int maxSize) {
        this.maxSize = maxSize;
        data = new int[maxSize];
        top = -1;
    }

    /**
     * Description:依次加入数据
     * author: liuchangxin
     * Date: Created in 2019/3/19 16:18
     */
    public boolean push(int data) {
        if (top + 1 == maxSize) {
            System.out.println("栈已满！！");
            return false;
        }
        this.data[++top] = data;
        return true;
    }

    /**
     * Description:从栈中取出数据
     * author: liuchangxin
     * Date: Created in 2019/3/19 16:20
     */
    public int pop() throws Exception {
        if (top == -1) {
            throw new Exception("栈以空！！");
        }
        return this.data[top--];
    }

    public static void main(String[] args) throws Exception {
        lession_20190319 ll = new lession_20190319(10);
        ll.push(1);
        ll.push(2);
        ll.push(3);
        ll.push(4);
        ll.push(5);
        while (ll.top >= 0) {
            System.out.println(ll.pop());
        }
    }

}
