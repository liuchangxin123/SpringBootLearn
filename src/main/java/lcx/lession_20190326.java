package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/26 11:23.
 */
public class lession_20190326 {
}
//
//final class Month2 {
//    private String name;
//
//    private Month2(String nm) {
//        name = nm;
//    }

//    @Override
//    public String toString() {
//        return name;
//    }
//
//    public final static Month2 JAN = new Month2("January"),
//            FEB = new Month2("February"),
//            MAR = new Month2("March"),
//            APR = new Month2("April"),
//            MAY = new Month2("May"),
//            JUN = new Month2("June"),
//            JUL = new Month2("July"),
//            AUG = new Month2("August"),
//            SEP = new Month2("September"),
//            OCT = new Month2("October"),
//            NOV = new Month2("November"),
//            DEC = new Month2("December");
//    public final static Month2[] month = {
//            JAN, JAN, FEB, MAR, APR, MAY, JUN,
//            JUL, AUG, SEP, OCT, NOV, DEC
//    };

//    public static void main(String[] args) {
//        Month2 m = Month2.JAN;
//        System.out.println(m);
//        m = Month2.month[12];
//        System.out.println(m);
//        System.out.println(m == Month2.DEC);
//        System.out.println(m.equals(Month2.DEC));
//    }

//class Parcel1 {
//    class Contents {
//        private int i = 11;
//
//        public int value() {
//            return i;
//        }
//    }
//
//    class Destination {
//        private String label;
//
//        Destination(String whereTo) {
//            label = whereTo;
//        }
//
//        String readLabel() {
//            return label;
//        }
//    }
//
//    // Using inner classes looks just like   // using any other class, within Parcel1:
//    public void ship(String dest) {
//        Contents c = new Contents();
//        Destination d = new Destination(dest);
//    }
//
//    public static void main(String[] args) {
//        Parcel1 p = new Parcel1();
//        p.ship("Tanzania");
//    }
//}

//class Parcel2 {
//    class Contents {
//        private int i = 11;
//
//        public int value() {
//            return i;
//        }
//    }
//
//    class Destination {
//        private String label;
//
//        Destination(String whereTo) {
//            label = whereTo;
//        }
//
//        String readLabel() {
//            return label;
//        }
//    }
//
//    public Destination to(String s) {
//        return new Destination(s);
//    }
//
//    public Contents cont() {
//        return new Contents();
//    }
//
//    public void ship(String dest) {
//        Contents c = cont();
//        Destination d = to(dest);
//    }
//
//    public static void main(String[] args) {
//        Parcel2 p = new Parcel2();
//        p.ship("Tanzania");
//        Parcel2 q = new Parcel2();
//        // Defining handles to inner classes:
//        Parcel2.Contents c = q.cont();
//        Parcel2.Destination d = q.to("Borneo");
//    }
//}

//abstract class Contents {
//    abstract public int value();
//}
//
//interface Destination {
//    String readLabel();
//}
//
//class Parcel3 {
//    private class PContents extends Contents {
//        private int i = 11;
//
//        @Override
//        public int value() {
//            return i;
//        }
//    }
//
//    protected class PDestination implements Destination {
//        private String label;
//
//        private PDestination(String whereTo) {
//            label = whereTo;
//        }
//
//        @Override
//        public String readLabel() {
//            return label;
//        }
//    }
//
//    public Destination dest(String s) {
//        return new PDestination(s);
//    }
//
//    public Contents cont() {
//        return new PContents();
//    }
//}
//
//class Test {
//    public static void main(String[] args) {
//        Parcel3 p = new Parcel3();
//        Contents c = p.cont();
//        Destination d = p.dest("Tanzania");     // Illegal -- can't access private class:
////         Parcel3.PContents c = p.new PContents();
//    }
//}


//class Parcel4 {
//    public Destination dest(String s) {
//        class PDestination implements Destination {
//            private String label;
//
//            private PDestination(String whereTo) {
//                label = whereTo;
//            }
//
//            public String readLabel() {
//                return label;
//            }
//        }
//        return new PDestination(s);
//    }
//
//    public static void main(String[] args) {
//        Parcel4 p = new Parcel4();
//        Destination d = p.dest("Tanzania");
//    }
//}


interface Selector {
    boolean end();

    Object current();

    void next();
}

class Sequence {
    private Object[] o;
    private int next = 0;

    public Sequence(int size) {
        o = new Object[size];
    }

    public void add(Object x) {
        if (next < o.length) {
            o[next] = x;
            next++;
        }
    }

    private class SSelector implements Selector {
        int i = 0;

        @Override
        public boolean end() {
            return i == o.length;
        }

        @Override
        public Object current() {
            return o[i];
        }

        @Override
        public void next() {
            if (i < o.length) {
                i++;
            }
        }
    }

    public Selector getSelector() {
        return new SSelector();
    }

    public static void main(String[] args) {
        Sequence s = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            s.add(Integer.toString(i));
        }
        Selector sl = s.getSelector();
        System.out.println(sl.end());
        while (!sl.end()) {
            System.out.println((String) sl.current());
            sl.next();
        }
    }

    public void aa(){
        Parcel2 q=new Parcel2();
        System.out.println("aaa");
        Parcel2.Contents c = new Parcel2().cont();
    }

}



