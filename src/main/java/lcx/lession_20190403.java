package lcx;


/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/4/3 14:54.
 */
public interface lession_20190403 {
}

/*内部类对我们非常有用，因为利用它可对那些逻辑上相互联系的类进行分组，
并可控制一个类在另一个类里的“可见性”。然而，我们必须认识到内部类与“合成”方法存在着根本的区别。*/

//创建内部类的过程是平淡无奇的：将类定义置入一个用于封装它的类内部

class Parcel1 {
    class Contents {
        private int i = 11;

        public void value() {
            System.out.println("我是Contents的value方法");
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public void ship(String dest) {    //若在ship()内部使用，内部类的使用看起来和其他任何类都没什么分别。在这里，唯一明显的区别就是它的名字嵌套在Parcel1里面
        Contents c = new Contents();
        c.value();
        Destination d = new Destination(dest);
        String a = d.readLabel();
        System.out.println(a);
    }

    public static void main(String[] args) {
        Parcel1 p = new Parcel1();
        p.ship("Tanzania");
    }
}

//迄今为止，内部类看起来仍然没什么特别的地方。毕竟，用它实现隐藏显得有些大题小做。
// Java已经有一个非常优秀的隐藏机制——只允许类成为“友好的”（只在一个包内可见），而不是把它创建成一个内部类
//但是“友好的”可以在本包内以及继承者都可以访问到，而内部类可以只在本类中访问
////内部类的好处：要解决一个复杂的问题，并希望创建一个类，用来辅助自己的程序方案。同时不愿意把它公开。

//------------------------------------------------------
//更典型的一种情况是，一个外部类拥有一个特殊的方法，它会返回指向一个内部类的句柄。
class Parcel2 {
    class Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }

        String readLabel() {
            return label;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public Contents cont() {  //指向内部类
        return new Contents();
    }

    public void ship(String dest) {
        Contents c = cont();
        int i = c.value();
        System.out.println(i);
        Destination d = to(dest);
        String s = d.readLabel();
        System.out.println(s);
    }

    public static void main(String[] args) {
        Parcel2 p = new Parcel2();
        p.ship("Tanzania");
        Parcel2 q = new Parcel2();

        Parcel2.Contents c = q.cont();
        System.out.println(c.value());//若想在除外部类非static方法内部之外的任何地方生成内部类的一个对象，必须将那个对象的类型设为“外部类名.内部类名”
        Parcel2.Destination d = q.to("Borneo");
        System.out.println(d.readLabel());

    }
}

//-------------------------------------------------
//下面这个例子展示了如何在任意作用域内嵌套一个内部类：

class Parcel5 {
    private void internalTracking(boolean b) {
        if (b) {  //在定义它的那个作用域之外，它是不可使用的
            class TrackingSlip {
                private String id;

                TrackingSlip(String s) {
                    id = s;
                }

                String getSlip() {
                    return id;
                }
            }
            TrackingSlip ts = new TrackingSlip("slip");
            String s = ts.getSlip();
            System.out.println(s);
        }
//        TrackingSlip ts1 = new TrackingSlip("slip");
    }

    public void track() {
        internalTracking(true);
    }

    public static void main(String[] args) {
        Parcel5 p = new Parcel5();
        p.track();
    }
}

//--------------------------------------------
//匿名内部类
interface Destination {
    String readLabel();
}

interface Contents {
    int value();
}

class Wrapping {
    private int i;

    public Wrapping(int x) {
        i = x;
    }

    public int value() {
        return i;
    }
}

class Parcel6 {
    public Contents cont() {                    //"创建从Contents衍生出来的匿名类的一个对象”。由new表达式返回的句柄会自动上溯造型成一个Contents句柄
        return new Contents() {   //匿名的             class MyContents extends Contents {
            private int i = 11;                     //             private int i = 11;

            @Override
            public int value() {                        //   public int value() {
                return i;                                   //          return i;
            }                                             //      }
        };                                                //    }
    }                                                     //    return new MyContents();*/

    public static void main(String[] args) {
        Parcel6 p = new Parcel6();
        Contents c = p.cont();
    }
}

//-----------------------------------------------------------
//匿名内部类
class Parcel9 {
    public Destination dest(final String dest, final float price) {
        return new Destination() {
            private int cost;

            {
                cost = Math.round(price);
                if (cost > 100) {
                    System.out.println("Over budget!");
                }
            }

            private String label = dest;


            @Override
            public String readLabel() {
                return label;
            }
        };
    }

    public static void main(String[] args) {
        Parcel9 p = new Parcel9();
        Destination d = p.dest("Tanzania", 101.395F);
    }
}


//----------------------------------------------------------
/*static内部类
static内部类意味着：
(1)     为创建一个static内部类的对象，我们不需要一个外部类对象。
(2)     不能从static内部类的一个对象中访问一个外部类对象。
但在存在一些限制：由于static成员只能位于一个类的外部级别，所以内部类不可拥有static数据或 static内部类。*/

abstract class Contents1 {
    abstract public int value();
}

interface Destination1 {
    String readLabel();
}

 class Parcel10 {
    private static class PContents extends Contents1 {
        private int i = 11;

        @Override
        public int value() {
            return i;
        }
    }

    protected static class PDestination implements Destination1 {
        private String label;

        private PDestination(String whereTo) {
            label = whereTo;
        }

        @Override
        public String readLabel() {
            return label;
        }
    }

    public static Destination1 dest(String s) {
        return new PDestination(s);
    }

    public static Contents1 cont() {
        return new PContents();
    }

    public static void main(String[] args) {
        Contents1 c = cont();
        Destination1 d = dest("Tanzania");
    }
}
/*因此，除非已拥有外部类的一个对象，否则不可能创建内部类的一个对象。
这是由于内部类的对象已同创建它的外部类的对象“默默”地连接到一起。
然而，如果生成一个static内部类，就不需要指向外部类对象的一个句柄。*/


//-----------------------------------------------------
//从内部类继承

//由于内部类构建器必须同封装类对象的一个句柄联系到一起，所以从一个内部类继承的时候，
// 情况会稍微变得有些复杂。这儿的问题是封装类的“秘密”句柄必须获得初始化，
// 而且在衍生类中不再有一个默认的对象可以连接。解决这个问题的办法是采用一种特殊的语法，明确建立这种关联：
class WithInner {
    class Inner {
    }
}

class InheritInner extends WithInner.Inner {
    InheritInner(WithInner wi) {
        wi.super();
    }

//    public static void main(String[] args) {
//        WithInner wi = new WithInner();
//        InheritInner ii = new InheritInner(wi);
//    }
}
//从中可以看到，InheritInner只对内部类进行了扩展，没有扩展外部类。但在需要创建一个构建器的时候，
//默认对象已经没有意义，我们不能只是传递封装对象的一个句柄。
// 此外，必须在构建器中采用下述语法： enclosingClassHandle.super(); 它提供了必要的句柄，以便程序正确编译。

//-------------------------------------------------------

/*内部类标识符
由于每个类都会生成一个.class文件，用于容纳与如何创建这个类型的对象有关的所有信息（这种信息产生了一个名为Class对象的元类），
所以大家或许会猜到内部类也必须生成相应的.class文件，用来容纳与它们的Class对象有关的信息。
这些文件或类的名字遵守一种严格的形式：先是封装类的名字，再跟随一个$，再跟随内部类的名字。例如，由InheritInner.java创建的.class文件包括：
InheritInner.class
WithInner$Inner.class
WithInner.class
如果内部类是匿名的，那么编译器会简单地生成数字，把它们作为内部类标识符使用。若内部类嵌套于其他内部类中，则它们的名字简单地追加在一个$以及外部类标识符的后面。
这种生成内部名称的方法除了非常简单和直观以外，也非常“健壮”，可适应大多数场合的要求（注释
③）。由于它是Java的标准命名机制，所以产生的文件会自动具备“与平台无关”的能力（注意Java编译器会根据情况改变内部类，使其在不同的平台中能正常工作）。

③：但在另一方面，由于“$”也是Unix外壳的一个元字符，所以有时会在列出.class文件时遇到麻烦。
对一家以Unix为基础的公司——Sun——来说，采取这种方案显得有些奇怪。我的猜测是他们根本没有仔细考虑这方面的问题，而是认为我们会将全部注意力自然地放在源码文件上
*/
