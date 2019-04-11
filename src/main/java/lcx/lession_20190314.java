package lcx;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/14 11:25.
 */
public class lession_20190314 {
    ArrayList al;

    public lession_20190314(int num, int mod) {
        al = new ArrayList(num);
        Random rand = new Random();
        System.out.println("The ArrayList Sort Before:");
        for (int i = 0; i < num; i++) {
            al.add(new Integer(Math.abs(rand.nextInt()) % mod + 1));
            System.out.println("al[" + i + "]=" + al.get(i));
        }
    }

    public void SortIt() {
        Integer tempInt;
        int MaxSize = 1;
        for (int i = 1; i < al.size(); i++) {
            tempInt = (Integer) al.remove(i);
            if (tempInt.intValue() >= ((Integer) al.get(MaxSize - 1)).intValue()) {
                al.add(MaxSize, tempInt);
                MaxSize++;
                System.out.println(al.toString());
            } else {
                for (int j = 0; j < MaxSize; j++) {
                    if (((Integer) al.get(j)).intValue() >= tempInt.intValue()) {
                        al.add(j, tempInt);
                        MaxSize++;
                        System.out.println(al.toString());
                        break;
                    }
                }
            }
        }
        System.out.println("The ArrayList After:");
        for (int i = 0; i < al.size(); i++) {
            System.out.println("al[" + i + "]=" + al.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        lession_20190314 is = new lession_20190314(10, 100);
        is.SortIt();

        lession_20190314.split("我a就aasds回答",5);

        Calendar cl=Calendar.getInstance();
        cl.add(Calendar.DATE,-1);
        System.out.println(cl.getTime());
    }

    public static void split(String source,int num)throws Exception{
        int k=0;
        String temp="";
        for(int i=0;i<source.length();i++){
            byte[] b=(source.charAt(i)+"").getBytes("GBK");
            k=k+b.length;
            if(k>num){
                break;
            }
            temp=temp+source.charAt(i);
        }
        System.out.println(temp);
    }

}

