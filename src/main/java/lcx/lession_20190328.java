package lcx;

import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/28 10:53.
 */
public class lession_20190328 {
}

//class Cat {
//    private int catNumber;
//
//    Cat(int i) {
//        catNumber = i;
//    }
//
//    void print() {
//        System.out.println("Cat #" + catNumber);
//    }
//}
//
//class Dog {
//    private int dogNumber;
//
//    Dog(int i) {
//        dogNumber = i;
//    }
//
//    void print() {
//        System.out.println("Dog #" + dogNumber);
//    }
//}
//
//class CatsAndDogs {
//    public static void main(String[] args) {
//        Vector cats = new Vector();
//        for (int i = 0; i < 7; i++) {
//            cats.addElement(new Cat(i));
//        }   // Not a problem to add a dog to cats:
//        cats.addElement(new Dog(7));
//        for (int i = 0; i < cats.size(); i++) {
//            ((Cat) cats.elementAt(i)).print();
//        }
//        // Dog is detected only at run-time
//    }
//}


class Mouse {
    private int mouseNumber;

    Mouse(int i) {
        mouseNumber = i;
    }

    // Magic method:
    @Override
    public String toString() {
        return "This is Mouse #" + mouseNumber;
    }

    void print(String msg) {
        if (msg != null) {
            System.out.println(msg);
        }
        System.out.println(
                "Mouse number " + mouseNumber);
    }
}

class MouseTrap {
    static void caughtYa(Object m) {
        Mouse mouse = (Mouse) m; // Cast from Object
        mouse.print("Caught one!");
    }
}

class WorksAnyway {
    public static void main(String[] args) {
        Vector mice = new Vector();
        for (int i = 0; i < 3; i++) {
            mice.addElement(new Mouse(i));
        }
        for (int i = 0; i < mice.size(); i++) {
            // No cast necessary, automatic call
            // to Object.toString():
            System.out.println(
                    "Free mouse: " + mice.elementAt(i));
            MouseTrap.caughtYa(mice.elementAt(i));
        }
    }
}

//--------------------------------------------

class Gopher {
    private int gopherNumber;

    Gopher(int i) {
        gopherNumber = i;
    }

    void print(String msg) {
        if (msg != null) {
            System.out.println(msg);
        }
        System.out.println(
                "Gopher number " + gopherNumber);
    }
}

class GopherTrap {
    static void caughtYa(Gopher g) {
        g.print("Caught one!");
    }
}

class GopherVector {
    private Vector v = new Vector();

    public void addElement(Gopher m) {
        v.addElement(m);
    }

    public Gopher elementAt(int index) {
        return (Gopher) v.elementAt(index);
    }

    public int size() {
        return v.size();
    }

    public static void main(String[] args) {
        GopherVector gophers = new GopherVector();
        for (int i = 0; i < 3; i++) {
            gophers.addElement(new Gopher(i));
        }
        for (int i = 0; i < gophers.size(); i++) {
            GopherTrap.caughtYa(gophers.elementAt(i));
        }
    }
}

//--------------------------------------------------------


class Cat2 {
    private int catNumber;

    Cat2(int i) {
        catNumber = i;
    }

    void print() {
        System.out.println("Cat number " + catNumber);
    }
}

class Dog2 {
    private int dogNumber;

    Dog2(int i) {
        dogNumber = i;
    }

    void print() {
        System.out.println("Dog number " + dogNumber);
    }
}

class CatsAndDogs2 {
    public static void main(String[] args) {
        Vector cats = new Vector();
        for (int i = 0; i < 7; i++) {
            cats.addElement(new Cat2(i));
        }     // Not a problem to add a dog to cats:
        cats.addElement(new Dog2(7));
        Enumeration e = cats.elements();
        while (e.hasMoreElements()) {
            ((Cat2) e.nextElement()).print();
        }
        // Dog is detected only at run-time
    }
}

//-------------------------------------------------


class Hamster {
    private int hamsterNumber;

    Hamster(int i) {
        hamsterNumber = i;
    }

    @Override
    public String toString() {
        return "This is Hamster #" + hamsterNumber;
    }
}

class Printer {
    static void printAll(Enumeration e) {
        while (e.hasMoreElements()) {
            System.out.println(
                    e.nextElement().toString());
        }
    }
}

class HamsterMaze {
    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 3; i++) {
            v.addElement(new Hamster(i));
        }
        Printer.printAll(v.elements());
    }
}


//----------------------------------------------------


class CrashJava {
    @Override
    public String toString() {
        return "CrashJava address: " + this + "\n";
    }

    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            v.addElement(new CrashJava());

        }
        System.out.println(v);
    }
}

//---------------------------------------------------------

class Stacks {
    static String[] months = {
            "January", "February", "March", "April",
            "May", "June", "July", "August", "September", "October", "November", "December"};

    public static void main(String[] args) {
        Stack stk = new Stack();
        for (int i = 0; i < months.length; i++) stk.push(months[i] + " ");
        System.out.println("stk = " + stk);
        // Treating a stack as a Vector:
        stk.addElement("The last line");
        System.out.println(
                "element 5 = " + stk.elementAt(5));
        System.out.println("popping elements:");
        while (!stk.empty()) {
            System.out.println(stk.pop());
        }
    }
}
