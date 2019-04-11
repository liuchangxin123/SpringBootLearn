package lcx;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/2/14 11:38.
 */
public class lession_20190214 { }
class Shape {
    Shape(int i) {
        System.out.println("Shape constructor");
    }

    void cleanup() {
        System.out.println("Shape cleanup");
    }

}

class Circle extends Shape {
    Circle(int i) {
        super(i);
        System.out.println("Drawing a Circle");
    }

    @Override
    void cleanup() {
        System.out.println("Erasing a Circle");
        super.cleanup();
    }
}

class Triangle extends Shape {
    Triangle(int i) {
        super(i);
        System.out.println("Drawing a Triangle");
    }

    @Override
    void cleanup() {
        System.out.println("Erasing a Triangle");
        super.cleanup();
    }
}

class Line extends Shape {
    private int start, end;

    Line(int start, int end) {
        super(start);
        this.start = start;
        this.end = end;
        System.out.println("Drawing a Line: " + start + ", " + end);
    }

    @Override
    void cleanup() {
        System.out.println("Erasing a Line: " + start + ", " + end);
        super.cleanup();
    }
}

class CADSystem extends Shape {
    private Circle c;
    private Triangle t;
    private Line[] lines = new Line[10];

    CADSystem(int i) {
        super(i + 1);
        for (int j = 0; j < 10; j++) {
            lines[j] = new Line(j, j * j);
            c = new Circle(1);
            t = new Triangle(1);
        }
        System.out.println("Combined constructor");
    }

    @Override
    void cleanup() {
        System.out.println("CADSystem.cleanup()");
        t.cleanup();
        c.cleanup();
        for (int i = 0; i < lines.length; i++) lines[i].cleanup();
        super.cleanup();
    }

    public static void main(String[] args) {
        CADSystem x = new CADSystem(47);
        try {
            // Code and exception handling...
        } finally {
            x.cleanup();
        }
    }
}

//---------------------------------


class Homer {
    char doh(char c) {
        System.out.println("doh(char)");
        return 'd';
    }

    float doh(float f) {
        System.out.println("doh(float)");
        return 1.0f;
    }
}

class Milhouse {
}

class Bart extends Homer {
    void doh(Milhouse m) {
        System.out.println("Milhouse");
    }
}

class Hide {
    public static void main(String[] args) {
        Bart b = new Bart();
        b.doh('x'); // doh(float) used
        System.out.println(b.doh('x'));
        b.doh('x');
        b.doh(1.0f);
        b.doh(new Milhouse());
    }
}

//-------------------

class Engine {
    public void start() {
    }

    public void rev() {
    }

    public void stop() {
    }
}

class Wheel {
    public void inflate(int psi) {
    }
}

class Window {
    public void rollup() {
    }

    public void rolldown() {
    }
}

class Door {
    public Window window = new Window();

    public void open() {
    }

    public void close() {
    }
}

class Car {
    public Engine engine = new Engine();
    public Wheel[] wheel = new Wheel[4];
    public Door left = new Door(), right = new Door(); // 2-door

    Car() {
        for (int i = 0; i < 4; i++) {
            wheel[i] = new Wheel();
        }
    }

    public static void main(String[] args) {
        Car car = new Car();
        car.left.window.rollup();
        car.wheel[0].inflate(72);
    }
}

//------------------------

class Villain {
    private int i;

    protected int read() {
        return i;
    }

    protected void set(int ii) {
        i = ii;
    }

    public Villain(int ii) {
        i = ii;
    }

    public int value(int m) {
        return m * i;
    }
}

class Orc extends Villain {
    private int j;

    public Orc(int jj) {
        super(jj);
        j = jj;
    }

    public void change(int x) {
        set(x);
    }
}

//---------------------------
