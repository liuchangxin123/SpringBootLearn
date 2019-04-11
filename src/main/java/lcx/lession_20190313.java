package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/13 15:39.
 */
public class lession_20190313 {
    public static int trimGBK(byte[] buf,int n){
        int num=0;
        boolean bChineseFirstHalf=false;
        for(int i=0;i<n;i++){
            if(buf[i]<0 && !bChineseFirstHalf){
                bChineseFirstHalf=true;
            }else {
                num++;
                bChineseFirstHalf=false;
            }
        }
        return num;
    }

    public static void main(String[] args) throws Exception {
        String str="我a爱中华abc我爱def";
//        String str="我爱abc汗";
        int num=trimGBK(str.getBytes("GBK"),7);
        System.out.println(str.substring(0,num));
    }
}

