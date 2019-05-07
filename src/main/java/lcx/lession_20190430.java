package lcx;

/**
 * @Author : liuchangxin
 * @Descpription: TODO()
 * @Created in 2019/4/30 17:10.
 */
public class lession_20190430 {
    public static void main(String[] args) {
        String a=new String("a");
        String b=new String("b");
        String aa="aa";
        String bb="aa";
        System.out.println(aa==bb);

        System.out.println(args);
    }
}


class AA{
    public AA(){

    }
    public AA(int i){
        System.out.println(i);
    }
    public void aa(){
        System.out.println("aa");
    }
}
class BB extends AA{

    public BB(int i){
        super(i);
    }
}