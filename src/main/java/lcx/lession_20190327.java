package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/27 14:40.
 */
public enum lession_20190327 {
}

//class Meal {
//    Meal() {
//        System.out.println("Meal()");
//    }
//}
//
//class Bread {
//    Bread() {
//        System.out.println("Bread()");
//    }
//}
//
//class Cheese {
//    Cheese() {
//        System.out.println("Cheese()");
//    }
//}
//
//class Lettuce {
//    Lettuce() {
//        System.out.println("Lettuce()");
//    }
//}
//
//class Lunch extends Meal {
//    Lunch() {
//        System.out.println("Lunch()");
//    }
//}
//
//class PortableLunch extends Lunch {
//    PortableLunch() {
//        System.out.println("PortableLunch()");
//    }
//}
//
//class Sandwich extends PortableLunch {
//    Bread b = new Bread();
//    Cheese c = new Cheese();
//    Lettuce l = new Lettuce();
//
//    Sandwich() {
//        System.out.println("Sandwich()");
//    }
//
//    public static void main(String[] args) {
//        new Sandwich();
//    }
//}

//
//class DoBaseFinalization {
//    public static boolean flag = false;
//}
//
//class Characteristic {
//    String s;
//
//    Characteristic(String c) {
//        s = c;
//        System.out.println(
//                "Creating Characteristic " + s);
//    }
//
//    @Override
//    protected void finalize() {
//        System.out.println(
//                "finalizing Characteristic " + s);
//    }
//}
//
//class LivingCreature {
//    Characteristic p = new Characteristic("is alive");
//
//    LivingCreature() {
//        System.out.println("LivingCreature()");
//    }
//
//    @Override
//    protected void finalize() {
//        System.out.println("LivingCreature finalize");     // Call base-class version LAST!
//        if (DoBaseFinalization.flag) {
//            try {
//                super.finalize();
//            } catch (
//                    Throwable t)
//
//            {
//            }
//        }
//    }
//}
//
//class Animal extends LivingCreature {
//    Characteristic p = new Characteristic("has heart");
//
//    Animal() {
//        System.out.println("Animal()");
//    }
//
//    @Override
//    protected void finalize() {
//        System.out.println("Animal finalize");
//        if (DoBaseFinalization.flag) try {
//            super.finalize();
//        } catch (Throwable t) {
//        }
//    }
//}
//
//class Amphibian extends Animal {
//    Characteristic p =
//            new Characteristic("can live in water");
//
//    Amphibian() {
//        System.out.println("Amphibian()");
//    }
//
//    @Override
//    protected void finalize() {
//        System.out.println("Amphibian finalize");
//        if (DoBaseFinalization.flag) {
//            try {
//                super.finalize();
//            } catch (Throwable t) {
//            }
//        }
//    }
//}
//
//class Frog extends Amphibian {
//    Frog() {
//        System.out.println("Frog()");
//    }
//
//    @Override
//    protected void finalize() {
//        System.out.println("Frog finalize");
//        if (DoBaseFinalization.flag) {
//            try {
//                super.finalize();
//            } catch (Throwable t) {
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        if (args.length != 0 && args[0].equals("finalize")) {
//            DoBaseFinalization.flag = true;
//        } else {
//            System.out.println("not finalizing bases");
//
//            new Frog(); // Instantly becomes garbage
//
//        }
//        // Must do this to guarantee that all
//        // finalizers will be called:
//        System.runFinalizersOnExit(true);
//    }
//}

//abstract class Glyph {
//    abstract void draw();
//
//    Glyph() {
//        System.out.println("Glyph() before draw()");
//        draw();
//        System.out.println("Glyph() after draw()");
//    }
//}
//
//class RoundGlyph extends Glyph {
//    int radius = 1;
//
//    RoundGlyph(int r) {
//        radius = r;
//        System.out.println(
//                "RoundGlyph.RoundGlyph(), radius = "
//                        + radius);
//    }
//
//    @Override
//    void draw() {
//        System.out.println(
//                "RoundGlyph.draw(), radius = " + radius);
//    }
//}
//
//class PolyConstructors {
//    public static void main(String[] args) {
//        new RoundGlyph(5);
//    }
//}
//
//interface Actor {
//    void act();
//}
//
//class HappyActor implements Actor {
//    @Override
//    public void act() {
//        System.out.println("HappyActor");
//    }
//}
//
//class SadActor implements Actor {
//    @Override
//    public void act() {
//        System.out.println("SadActor");
//    }
//}
//
//class Stage {
//    Actor a = new HappyActor();
//
//    void change() {
//        a = new SadActor();
//    }
//
//    void go() {
//        a.act();
//    }
//}
//
//class Transmogrify {
//    public static void main(String[] args) {
//        Stage s = new Stage();
//        s.go(); // Prints "HappyActor"
//        s.change();
//        s.go(); // Prints "SadActor"
//    }
//}

//class Weeble {
//} // A small mythical creature
//
//class ArraySize {
//    public static void main(String[] args) {
//        // Arrays of objects:
//        Weeble[] a; // Null handle
//        Weeble[] b = new Weeble[5]; // Null handles
//        Weeble[] c = new Weeble[4];
//        for (int i = 0; i < c.length; i++) {
//            c[i] = new Weeble();
//        }
//        Weeble[] d = {
//                new Weeble(), new Weeble(), new Weeble()
//        };
//        // Compile error: variable a not initialized:
//        //!System.out.println("a.length=" + a.length);
//        System.out.println("b.length = " + b.length);
//        // The handles inside the array are
//        // automatically initialized to null:
//        for (int i = 0; i < b.length; i++) {
//            System.out.println("b[" + i + "]=" + b[i]);
//            System.out.println("c.length = " + c.length);
//            System.out.println("d.length = " + d.length);
//            a = d;
//            System.out.println("a.length = " + a.length);
//        }
//        // Java 1.1 initialization syntax:
//        a = new Weeble[]{new Weeble(), new Weeble()
//        };
//        System.out.println("a.length = " + a.length);
//
//        // Arrays of primitives:
//        int[] e; // Null handle
//        int[] f = new int[5];
//        int[] g = new int[4];
//        for (int i = 0; i < g.length; i++) {
//            g[i] = i * i;
//        }
//        int[] h = {11, 47, 93};
//        // Compile error: variable e not initialized:
//        //!System.out.println("e.length=" + e.length);
//        System.out.println("f.length = " + f.length);     // The primitives inside the array are
//        // automatically initialized to zero:
//        for (int i = 0; i < f.length; i++) {
//            System.out.println("f[" + i + "]=" + f[i]);
//            System.out.println("g.length = " + g.length);
//            System.out.println("h.length = " + h.length);
//            e = h;
//            System.out.println("e.length = " + e.length);
//        }
//        // Java 1.1 initialization syntax:
//        e = new int[]{1, 2};
//        System.out.println("e.length = " + e.length);
//    }
//}


class IceCream {
    static String[] flav = {
            "Chocolate", "Strawberry",
            "Vanilla Fudge Swirl", "Mint Chip",
            "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };

    static String[] flavorSet(int n) {
        // Force it to be positive & within bounds:
        n = Math.abs(n) % (flav.length + 1);
        String[] results = new String[n];
        int[] picks = new int[n];
        for (int i = 0; i < picks.length; i++) {
            picks[i] = -1;
        }
        for (int i = 0; i < picks.length; i++) {
            retry:
            while (true) {
                int t =
                        (int) (Math.random() * flav.length);
                for (int j = 0; j < i; j++) {
                    if (picks[j] == t) {
                        continue retry;
                    }
                }
                picks[i] = t;
                results[i] = flav[t];
                break;
            }
        }
        return results;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(
                    "flavorSet(" + i + ") = ");
            String[] fl = flavorSet(flav.length);
            for (int j = 0; j < fl.length; j++)
                System.out.println("\t" + fl[j]);
        }
    }
}
