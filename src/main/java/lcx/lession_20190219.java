package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/2/19 10:42.
 */
public class lession_20190219 {
}

class Note2 {
    private int value;

    private Note2(int val) {
        value = val;
    }

    public static final Note2 middleC = new Note2(0), cSharp = new Note2(1), cFlat = new Note2(2);
} // Etc.

class Instrument2 {
    public void play(Note2 n) {
        System.out.println("Instrument2.play()");
    }
}

class Wind2 extends Instrument2 {
    @Override
    public void play(Note2 n) {
        System.out.println("Wind2.play()");
    }
}

class Stringed2 extends Instrument2 {
    @Override
    public void play(Note2 n) {
        System.out.println("Stringed2.play()");
    }
}

class Brass2 extends Instrument2 {
    @Override
    public void play(Note2 n) {
        System.out.println("Brass2.play()");
    }
}

class Music2 {
    public static void tune(Wind2 i) {
        i.play(Note2.middleC);
    }

    public static void tune(Stringed2 i) {
        i.play(Note2.middleC);
    }

    public static void tune(Brass2 i) {
        i.play(Note2.middleC);
    }

    public static void main(String[] args) {
        Wind2 flute = new Wind2();
        Stringed2 violin = new Stringed2();
        Brass2 frenchHorn = new Brass2();
        tune(flute); // No upcasting
        tune(violin);
        tune(frenchHorn);
    }
}


//-----------------------------------

class Instrument3 {
    public void play() {
        System.out.println("Instrument3.play()");
    }

    public String what() {
        return "Instrument3";
    }

    public void adjust() {
    }
}

class Wind3 extends Instrument3 {
    @Override
    public void play() {
        System.out.println("Wind3.play()");
    }

    @Override
    public String what() {
        return "Wind3";
    }

    @Override
    public void adjust() {
    }
}

class Percussion3 extends Instrument3 {
    @Override
    public void play() {
        System.out.println("Percussion3.play()");
    }

    @Override
    public String what() {
        return "Percussion3";
    }

    @Override
    public void adjust() {
    }
}

class Stringed3 extends Instrument3 {
    @Override
    public void play() {
        System.out.println("Stringed3.play()");
    }

    @Override
    public String what() {
        return "Stringed3";
    }

    @Override
    public void adjust() {
    }
}

class Brass3 extends Wind3 {
    @Override
    public void play() {
        System.out.println("Brass3.play()");
    }

    @Override
    public void adjust() {
        System.out.println("Brass3.adjust()");
    }
}

class Woodwind3 extends Wind3 {
    @Override
    public void play() {
        System.out.println("Woodwind3.play()");
    }

    @Override
    public String what() {
        return "Woodwind3";
    }
}

class Music3 {
    // Doesn't care about type, so new types   // added to the system still work right:
    static void tune(Instrument3 i) {     // ...
        i.play();
    }

    static void tuneAll(Instrument3[] e) {
        for (int i = 0; i < e.length; i++) tune(e[i]);
    }

    public static void main(String[] args) {
        Instrument3[] orchestra = new Instrument3[5];
        int i = 0;
        // Upcasting during addition to the array:
        orchestra[i++] = new Wind3();
        orchestra[i++] = new Percussion3();
        orchestra[i++] = new Stringed3();
        orchestra[i++] = new Brass3();
        orchestra[i++] = new Woodwind3();
        tuneAll(orchestra);
    }
}

//----------------------------

class NoteX {
    public static final int
            MIDDLE_C = 0, C_SHARP = 1, C_FLAT = 2;
}

class InstrumentX {
    public void play(int NoteX) {
        System.out.println("InstrumentX.play()");
    }
}

class WindX extends InstrumentX {   // OOPS! Changes the method interface:
    public void play(NoteX n) {
        System.out.println("WindX.play(NoteX n)");
    }
}

class WindError {
    public static void tune(InstrumentX i) {
        // ...
        i.play(NoteX.MIDDLE_C);
    }

    public static void main(String[] args) {
        WindX flute = new WindX();
        tune(flute); // Not the desired behavior!
    }
}

//------------------------------------

interface Instrument5 {
    // Compile-time constant:
    int i = 5; // static & final   // Cannot have method definitions:

    void play(); // Automatically public

    String what();

    void adjust();
}

class Wind5 implements Instrument5 {
    @Override
    public void play() {
        System.out.println("Wind5.play()");
    }

    @Override
    public String what() {
        return "Wind5";
    }

    @Override
    public void adjust() {
    }
}

class Percussion5 implements Instrument5 {
    @Override
    public void play() {
        System.out.println("Percussion5.play()");
    }

    @Override
    public String what() {
        return "Percussion5";
    }

    @Override
    public void adjust() {
    }
}

class Stringed5 implements Instrument5 {
    @Override
    public void play() {
        System.out.println("Stringed5.play()");
    }

    @Override
    public String what() {
        return "Stringed5";
    }

    @Override
    public void adjust() {
    }
}

class Brass5 extends Wind5 {
    @Override
    public void play() {
        System.out.println("Brass5.play()");
    }

    @Override
    public void adjust() {
        System.out.println("Brass5.adjust()");
    }
}

class Woodwind5 extends Wind5 {
    @Override
    public void play() {
        System.out.println("Woodwind5.play()");
    }

    @Override
    public String what() {
        return "Woodwind5";
    }
}

class Music5 {   // Doesn't care about type, so new types   // added to the system still work right:
    static void tune(Instrument5 i) {
        // ...
        i.play();
    }

    static void tuneAll(Instrument5[] e) {
        for (int i = 0; i < e.length; i++) tune(e[i]);
    }

    public static void main(String[] args) {
        Instrument5[] orchestra = new Instrument5[5];
        int i = 0;
        // Upcasting during addition to the array:
        orchestra[i++] = new Wind5();
        orchestra[i++] = new Percussion5();
        orchestra[i++] = new Stringed5();
        orchestra[i++] = new Brass5();
        orchestra[i++] = new Woodwind5();
        tuneAll(orchestra);
    }
}

//-----------------------------------

interface CanFight {
    void fight();
}

interface CanSwim {
    void swim();
}

interface CanFly {
    void fly();
}

class ActionCharacter {
    public void fight() {
    }
}

class Hero extends ActionCharacter implements CanFight, CanSwim, CanFly {
    @Override
    public void swim() {
    }

    @Override
    public void fly() {
    }
}

class Adventure {
    static void t(CanFight x) {
        x.fight();
    }

    static void u(CanSwim x) {
        x.swim();
    }

    static void v(CanFly x) {
        x.fly();
    }

    static void w(ActionCharacter x) {
        x.fight();
    }

    public static void main(String[] args) {
        Hero i = new Hero();
        t(i); // Treat it as a CanFight
        u(i); // Treat it as a CanSwim
        v(i); // Treat it as a CanFly
        w(i); // Treat it as an ActionCharacter
    }
}

//-----------------------------------

interface Monster {
    void menace();
}

interface DangerousMonster extends Monster {
    void destroy();
}

interface Lethal {
    void kill();
}

class DragonZilla implements DangerousMonster {
    @Override
    public void menace() {
    }

    @Override
    public void destroy() {
    }
}

interface Vampire
        extends DangerousMonster, Lethal {
    void drinkBlood();
}

class HorrorShow {
    static void u(Monster b) {
        b.menace();
    }

    static void v(DangerousMonster d) {
        d.menace();
        d.destroy();
    }

    public static void main(String[] args) {
        DragonZilla if2 = new DragonZilla();
        u(if2);
        v(if2);
    }
}

//--------------------------------------

interface Months {
    int
            JANUARY = 1, FEBRUARY = 2, MARCH = 3,
            APRIL = 4, MAY = 5, JUNE = 6, JULY = 7,
            AUGUST = 8, SEPTEMBER = 9, OCTOBER = 10,
            NOVEMBER = 11, DECEMBER = 12;
}






