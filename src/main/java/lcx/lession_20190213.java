package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/2/13 11:01.
 */
public class lession_20190213 {
    private String valve1, valve2, valve3, valve4;
    WaterSource source;
    int i;
    float f;

    void print() {
        System.out.println("valve1 = " + valve1);
        System.out.println("valve2 = " + valve2);
        System.out.println("valve3 = " + valve3);
        System.out.println("valve4 = " + valve4);
        System.out.println("i = " + i);
        System.out.println("f = " + f);
        System.out.println("source = " + source);
    }

    public static void main(String[] args) {
        lession_20190213 x = new lession_20190213();
        x.print();
    }

    class WaterSource {
        private String s;

        WaterSource() {
            System.out.println("WaterSource()");
            s = new String("Constructed");
        }

        @Override
        public String toString() {
            return s;
        }
    }
}

class bath {
    private String      // Initializing at point of definition:
            s1 = new String("Happy"), s2 = "Happy", s3, s4;
    Soap castille;
    int i;
    float toy;

    bath() {
        System.out.println("Inside Bath()");
        s3 = new String("Joy");
        i = 47;
        toy = 3.14f;
        castille = new Soap();
    }

    void print() {
        // Delayed initialization:
        if (s4 == null) {
            s4 = new String("Joy");
        }
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("s3 = " + s3);
        System.out.println("s4 = " + s4);
        System.out.println("i = " + i);
        System.out.println("toy = " + toy);
        System.out.println("castille = " + castille);
    }

    public static void main(String[] args) {
        bath b = new bath();
        b.print();
    }
}

class Soap {
    private String s;

    Soap() {
        System.out.println("Soap()");
        s = new String("Constructed");
    }

    @Override
    public String toString() {
        return s;
    }
}

//--------------------------------------

class Cleanser {
    private String s = new String("Cleanser");

    public void append(String a) {
        s += a;
    }

    public void dilute() {
        append(" dilute()");
    }

    public void apply() {
        append(" apply()");
    }

    public void scrub() {
        append(" scrub()");
    }

    public void print() {
        System.out.println(s);
    }

    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute();
        x.apply();
        x.scrub();
        x.print();
    }
}

class Detergent extends Cleanser {   // Change a method:
    @Override
    public void scrub() {
        append(" Detergent.scrub()");
        super.

                scrub(); // Call base-class version
    }

    // Add methods to the interface:
    public void foam() {
        append(" foam()");
    }   // Test the new class:

    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        x.print();
        System.out.println("Testing base class:");
        Cleanser.main(args);
    }
}

//----------------------

class Art {
    Art() {
        System.out.println("Art constructor");
    }
}

class Drawing extends Art {
    Drawing(int i) {
        System.out.println("Drawing constructor");
        System.out.println(i);
    }
}

class Cartoon extends Drawing {
    Cartoon(int i) {
        super(11);
        System.out.println("Cartoon constructor");
        System.out.println(i);
    }

    public static void main(String[] args) {
        Cartoon x = new Cartoon(25);
    }
}

//-------------------------

class Game {
    Game() {
        System.out.println("Game constructor");
//        System.out.println(i);
    }
}

class BoardGame extends Game {
    BoardGame(int i) {
//        super(i);
        System.out.println("BoardGame constructor");
        System.out.println(i);

    }
}

class Chess extends BoardGame {
    Chess(int i) {
        super(11);
        System.out.println("Chess constructor");
        System.out.println(i);

    }

    public static void main(String[] args) {
        Chess x = new Chess(25);
    }
}

//------------------------------------

class Plate {
    Plate(int i) {
        System.out.println("Plate constructor");
    }
}

class DinnerPlate extends Plate {
    DinnerPlate(int i) {
        super(i);
        System.out.println(
                "DinnerPlate constructor");
    }
}

class Utensil {
    Utensil(int i) {
        System.out.println("Utensil constructor");
    }
}

class Spoon extends Utensil {
    Spoon(int i) {
        super(i);
        System.out.println("Spoon constructor");
    }
}

class Fork extends Utensil {
    Fork(int i) {
        super(i);
        System.out.println("Fork constructor");
    }
}

class Knife extends Utensil {
    Knife(int i) {
        super(i);
        System.out.println("Knife constructor");
    }
}

// A cultural way of doing something:
class Custom {
    Custom(int i) {
        System.out.println("Custom constructor");
    }
}

class PlaceSetting extends Custom {
    Spoon sp;
    Fork frk;
    Knife kn;
    DinnerPlate pl;

    PlaceSetting(int i) {
        super(i + 1);
        sp = new Spoon(i + 2);
        frk = new Fork(i + 3);
        kn = new Knife(i + 4);
        pl = new DinnerPlate(i + 5);
        System.out.println(
                "PlaceSetting constructor");
    }

    public static void main(String[] args) {
        PlaceSetting x = new PlaceSetting(9);
    }
}










