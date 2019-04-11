package lcx;

import java.util.Arrays;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/5 15:40.
 */
public class lession_20190305 {
    public static boolean a(){
        int x=1;
        return x==1;
    }

    public static void main(String[] args) {
        int[] array={88,8,6,89,22,96,78,66};
        int [] i=maoPao(array);
        System.out.println(Arrays.toString(i));

    }
   private static int[]  maoPao(int[] array){

        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                    int z=array[j];
                    array[j]=array[j+1];
                    array[j+1]=z;
                }
            }
        }
        return  array;
    }
}
