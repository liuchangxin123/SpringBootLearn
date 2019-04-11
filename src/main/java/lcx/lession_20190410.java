package lcx;

import java.net.InetAddress;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/4/10 10:25.
 */
public class lession_20190410 {
}

class Candy {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie {
    static {
        System.out.println("Loading Cookie");
    }
}

class SweetShop {
    public static void main(String[] args) {
        System.out.println("inside main");
        new Candy();
        System.out.println("After creating Candy");
        try {
            Class.forName("Gum");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(
                "After Class.forName(\"Gum\")");
        new Cookie();
        System.out.println("After creating Cookie");
    }
}

//----------------------------------------------

class PassHandles {
    private static void f(PassHandles h) {
        System.out.println("h inside f(): " + h);
    }

    public static void main(String[] args) {
        PassHandles p = new PassHandles();
        System.out.println("p inside main(): " + p);
        f(p);
    }
}

//---------------------------------------------------

class Alias1 {
    int i;

    Alias1(int ii) {
        i = ii;
    }

    public static void main(String[] args) {
        Alias1 x = new Alias1(7);
        Alias1 y = x; // Assign the handle
        System.out.println("x: " + x.i);
        System.out.println("y: " + y.i);
        System.out.println("Incrementing x");
        x.i++;
        System.out.println("x: " + x.i);
        System.out.println("y: " + y.i);
    }
}

//--------------------------------

class Alias2 {
    int i;

    Alias2(int ii) {
        i = ii;
    }

    static void f(Alias2 handle) {
        handle.i++;
    }

    public static void main(String[] args) {
        Alias2 x = new Alias2(7);
        System.out.println("x: " + x.i);
        System.out.println("Calling f(x)");
        f(x);
        System.out.println("x: " + x.i);
    }
}

//-------------------------------------------------

class WhoAmI {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println(
                    "Usage: WhoAmI MachineName");
            System.exit(1);
        }
        InetAddress a =
                InetAddress.getByName(args[0]);
        System.out.println(a);
    }
}

