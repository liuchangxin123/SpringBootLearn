package lcx;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/3/29 14:10.
 */
public class lession_20190329 {
}

class Counter1 extends Applet {
    private int count = 0;
    private Button
            onOff = new Button("Toggle"), start = new Button("Start");
    private TextField t = new TextField(10);
    private boolean runFlag = true;

    @Override
    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    public void go() {
        while (true) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
            }
            if (runFlag) {
                t.setText(Integer.toString(count++));
            }
        }
    }

    class StartL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    class OnOffL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args) {
        Counter1 applet = new Counter1();
        Frame aFrame = new Frame("Counter1");
        aFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}

//--------------------------------------------------


class SimpleThread extends Thread {
    private int countDown = 5;
    private int threadNumber;
    private static int threadCount = 0;

    public SimpleThread() {
        threadNumber = ++threadCount;
        System.out.println("Making " + threadNumber);
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Thread " + threadNumber + "(" + countDown + ")");
            if (--countDown == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread().start();
        }
        System.out.println("All Threads Started");
    }
}

//-----------------------------------------------------


class SeparateSubTask extends Thread {
    private int count = 0;
    private Counter2 c2;
    private boolean runFlag = true;

    public SeparateSubTask(Counter2 c2) {
        this.c2 = c2;
        start();
    }

    public void invertFlag() {
        runFlag = !runFlag;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
            if (runFlag) {
                c2.t.setText(Integer.toString(count++));
            }
        }
    }
}

class Counter2 extends Applet {
    TextField t = new TextField(10);
    private SeparateSubTask sp = null;
    private Button onOff = new Button("Toggle"), start = new Button("Start");

    @Override
    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    class StartL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sp == null) {
                sp = new SeparateSubTask(Counter2.this);
            }
        }
    }

    class OnOffL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (sp != null) {
                sp.invertFlag();
            }
        }
    }

    public static void main(String[] args) {
        Counter2 applet = new Counter2();
        Frame aFrame = new Frame("Counter2");
        aFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}

//---------------------------------------------------------------------------------------------


class Counter3
        extends Applet implements Runnable {
    private int count = 0;
    private boolean runFlag = true;
    private Thread selfThread = null;
    private Button onOff = new Button("Toggle"), start = new Button("Start");
    private TextField t = new TextField(10);

    @Override
    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
    }

    @Override
    public void run() {
        while (true) {
            try {
                selfThread.sleep(100);
            } catch (InterruptedException e) {
            }
            if (runFlag) {
                t.setText(Integer.toString(count++));
            }
        }
    }

    class StartL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (selfThread == null) {
                selfThread = new Thread(Counter3.this);
                selfThread.start();
            }
        }
    }

    class OnOffL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }

    public static void main(String[] args) {
        Counter3 applet = new Counter3();
        Frame aFrame = new Frame("Counter3");
        aFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(300, 200);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}




