package lcx;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : liuchangxin
 * @Descpription: TODO()
 * @Created in 2019/5/22 15:54.
 */
public class lession_20190522 {
    public static Map<String, String> supportBank = new HashMap<String, String>() {
        {
            this.put("ICBC", "ICBC");
            this.put("ABC", "ABC");
            this.put("CCB", "CCB");
            this.put("BOC", "BOC");
            this.put("SPDB", "SPDB");
            this.put("CEB", "CEB");
            this.put("CIB", "CIB");
            this.put("CITIC", "CITIC");
            this.put("SPABANK", "SZPAB");
            this.put("HXBANK", "HXB");
            this.put("GDB", "GDB");
            this.put("COMM", "COMM");
            this.put("SHBANK", "BOS");
            this.put("CZBANK", "CZB");
            this.put("BJBANK", "BCCB");
            this.put("PSBC", "PSBC");
            this.put("CMB", "CMB");
            this.put("CMBC", "CMBC");

        }
    };



    public static void main(String[] args) {
//        String s="HXBANsK";
//        if(supportBank.get(s)!= null){
//            System.out.println(supportBank.get(s));
//        }else {
//            System.out.println("空的");
//        }
        bb b=new bb();
        b.b();
    }

}


class asd{
    public void a(){
        System.out.println("aaa");
    }
        }

class bb extends  asd{
    public void b(){
        System.out.println("vvv");
    }
}
