//package lcx;
//
//import java.util.Enumeration;
//import java.util.Vector;
//
///**
// * Descpription: TODO()
// * Created by liuchangxin on 2019/4/9 14:14.
// */
//public class lession_20190409 {
//}
//
//interface Shape {
//    void draw();
//}
//
//class Circle implements Shape {
//    @Override
//    public void draw() {
//        System.out.println("Circle.draw()");
//    }
//}
//
//class Square implements Shape {
//    @Override
//    public void draw() {
//        System.out.println("Square.draw()");
//    }
//}
//
//class Triangle implements Shape {
//    @Override
//    public void draw() {
//        System.out.println("Triangle.draw()");
//    }
//}
//
// class Shapes {
//    public static void main(String[] args) {
//        Vector s = new Vector();
//        s.addElement(new Circle());
//        s.addElement(new Square());
//        s.addElement(new Triangle());
//        Enumeration e = s.elements();
//        while (e.hasMoreElements()) {
//            ((Shape) e.nextElement()).draw();
//        }
//
//    }
//}
