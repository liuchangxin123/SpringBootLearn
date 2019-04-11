package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/2/18 10:49.
 */
public class lession_20190218 {
}

class Instrument {
    public void play() {
    }

    static void tune(Instrument i) {
        // ...
        i.play();
    }
}

// Wind objects are instruments
// because they have the same interface:
class Wind extends Instrument {
    public static void main(String[] args) {
        Wind flute = new Wind();
        Instrument.tune(flute); // Upcasting
    }
}


//-------------------------------------------

class Value {
    int i = 1;
}

class FinalData {
    // Can be compile-time constants
    final int i1 = 9;
    static final int I2 = 99;
    // Typical public constant:
    public static final int I3 = 39;   // Cannot be compile-time constants:
    final int i4 = (int) (Math.random() * 20);
    static final int i5 = (int) (Math.random() * 20);

    Value v1 = new Value();
    final Value v2 = new Value();
    static final Value v3 = new Value();
    //! final Value v4; // Pre-Java 1.1 Error:                        // no initializer   // Arrays:
    final int[] a = {1, 2, 3, 4, 5, 6};

    public void print(String id) {
        System.out.println(id + ": " + "i4 = " + i4 +
                ", i5 = " + i5);
    }

    public static void main(String[] args) {
        FinalData fd1 = new FinalData();
        //! fd1.i1++; // Error: can't change value
        fd1.v2.i++; // Object isn't constant!
        fd1.v1 = new Value(); // OK -- not final
        for (int i = 0; i < fd1.a.length; i++) {
            fd1.a[i]++;
        }
        // Object isn't constant!
        // ! fd1.v2 = new Value(); // Error: Can't
        //! fd1.v3 = new Value(); // change handle
        //! fd1.a = new int[3];

        fd1.print("fd1");
        System.out.println("Creating new FinalData");
        FinalData fd2 = new FinalData();
        fd1.print("fd1");
        fd2.print("fd2");
    }
}

//--------------------------

class Poppet {
}

class BlankFinal {
    final int i = 0; // Initialized final
    final int j; // Blank final
    final Poppet p; // Blank final handle

    // Blank finals MUST be initialized   // in the constructor:
    BlankFinal() {
        j = 1; // Initialize blank final
        p = new Poppet();
    }

    BlankFinal(int x) {
        j = x; // Initialize blank final
        p = new Poppet();
    }

    public static void main(String[] args) {
        BlankFinal bf = new BlankFinal();
    }
}

//---------------------------

class Gizmo {
    public void spin() {
    }
}

class FinalArguments {
    void with(final Gizmo g) {
        //! g = new Gizmo(); // Illegal -- g is final
        g.spin();
    }

    void without(Gizmo g) {
        g = new Gizmo(); // OK -- g not final
        g.spin();
    }

    void f(final int i) {
        //i++;
    } // Can't change
    // You can only read from a final primitive:

    int g(final int i) {
        return i + 1;
    }

    public static void main(String[] args) {
        FinalArguments bf = new FinalArguments();
        bf.without(null);
        bf.with(null);
    }
}

//---------------

class Insect {
    int i = 9;
    int j;

    Insect() {
        prt("i = " + i + ", j = " + j);
        j = 39;
    }

    static int x1 = prt("static Insect.x1 initialized");

    static int prt(String s) {
        System.out.println(s);
        return 47;
    }
}

class Beetle extends Insect {
    int k = prt("Beetle.k initialized");

    Beetle() {
        prt("k = " + k);
        prt("j = " + j);
    }

    static int x2 = prt("static Beetle.x2 initialized");

    static int prt(String s) {
        System.out.println(s);
        return 63;
    }

    public static void main(String[] args) {
       prt("Beetle constructor");
        Beetle b = new Beetle();
    }
}

//---------------------

class A extends C{
    A(int i){

        }
        }
class B{
    B(int i){

    }
}
class  C{
    C(){

    }
    B b;
}
class acs{
    public static void main(String[] args) {
        C c=new C();
    }
}

//---------------------






