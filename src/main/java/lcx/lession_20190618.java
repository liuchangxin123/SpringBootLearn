package lcx;

/**
 * @Descpription: TODO()
 * @Author : liuChangXin
 * @Created in 2019/6/18 11:11.
 */
public class lession_20190618 {
    public static void main(String[] args){
        int a=1;
        try {
            lession_20190618 l=new lession_20190618();
            l.a(a);
        }catch (Exception e){
            System.out.println("bbbb");
        }
        System.out.println("cccc");
    }
    public int a(int a)throws Exception{
       int i= a/0;
        System.out.println("aaaa");
       return i;
    }
}
