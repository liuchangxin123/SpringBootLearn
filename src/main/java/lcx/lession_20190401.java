package lcx;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Vector;

/**
 * Descpription: TODO()
 * Created by liuchangxin on 2019/4/1 14:25.
 */
public class lession_20190401 {
}


class Ticker extends Thread {
    private Button b = new Button("Toggle");
    private TextField t = new TextField(10);
    private int count = 0;
    private boolean runFlag = true;

    public Ticker(Container c) {
        b.addActionListener(new ToggleL());
        Panel p = new Panel();
        p.add(t);
        p.add(b);
        c.add(p);
    }

    class ToggleL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }


    @Override
    public void run() {
        while (true) {
            if (runFlag) {
                t.setText(Integer.toString(count++));
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Counter4 extends Applet {
    private Button start = new Button("Start");
    private boolean started = false;
    private Ticker[] s;
    private boolean isApplet = true;
    private int size;

    @Override
    public void init() {
        // Get parameter "size" from Web page:
        if (isApplet) {
            size =
                    Integer.parseInt(getParameter("size"));
        }
        s = new Ticker[size];
        for (int i = 0; i < s.length; i++) {
            s[i] = new Ticker(this);
        }
        start.addActionListener(new StartL());
        add(start);
    }

    class StartL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!started) {
                started = true;
                for (int i = 0; i < s.length; i++) s[i].start();
            }
        }
    }

    public static void main(String[] args) {
        Counter4 applet = new Counter4();
        // This isn't an applet, so set the flag and     // produce the parameter values from args:
        applet.isApplet = false;
        applet.size =
                (args.length == 0 ? 5 :
                        Integer.parseInt(args[0]));
        Frame aFrame = new Frame("Counter4");
        aFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(200, applet.size * 50);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}


//-------------------------------------------------------------------


class Daemon extends Thread {
    private static final int SIZE = 10;
    private Thread[] t = new Thread[SIZE];

    public Daemon() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        for (int i = 0; i < SIZE; i++) {
            t[i] = new DaemonSpawn(i);
        }
        for (int i = 0; i < SIZE; i++) {
            System.out.println(
                    "t[" + i + "].isDaemon() = " + t[i].isDaemon());
        }
        while (true) {
            yield();
        }
    }
}

class DaemonSpawn extends Thread {
    public DaemonSpawn(int i) {
        System.out.println(
                "DaemonSpawn " + i + " started");
        start();
    }

    @Override
    public void run() {
        while (true) yield();
    }
}

class Daemons {
    public static void main(String[] args) {
        Thread d = new Daemon();
        System.out.println(
                "d.isDaemon() = " + d.isDaemon());
        // Allow the daemon threads to finish
        // their startup processes:
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Waiting for CR");
        try {
            stdin.readLine();
        } catch (IOException e) {
        }
    }
}


//----------------------------------------------------------


class BangBean2 extends Canvas implements Serializable {
    private int xm, ym;
    private int cSize = 20; // Circle size
    private String text = "Bang!";
    private int fontSize = 48;
    private Color tColor = Color.red;
    private Vector actionListeners = new Vector();

    public BangBean2() {
        addMouseListener(new ML());
        addMouseMotionListener(new MM());
    }

    public synchronized int getCircleSize() {
        return cSize;
    }

    public synchronized void setCircleSize(int newSize) {
        cSize = newSize;
    }

    public synchronized String getBangText() {
        return text;
    }

    public synchronized void setBangText(String newText) {
        text = newText;
    }

    public synchronized int getFontSize() {
        return fontSize;
    }

    public synchronized void setFontSize(int newSize) {
        fontSize = newSize;
    }

    public synchronized Color getTextColor() {
        return tColor;
    }

    public synchronized void setTextColor(Color newColor) {
        tColor = newColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawOval(xm - cSize / 2, ym - cSize / 2, cSize, cSize);
    }

    // This is a multicast listener, which is   // more typically used than the unicast
    // approach taken in BangBean.java:
    public synchronized void addActionListener(ActionListener l) {
        actionListeners.addElement(l);
    }

    public synchronized void removeActionListener(ActionListener l) {
        actionListeners.removeElement(l);
    }

    // Notice this isn't synchronized:
    public void notifyListeners() {
        ActionEvent a = new ActionEvent(BangBean2.this,
                ActionEvent.ACTION_PERFORMED, null);
        Vector lv = null;
        // Make a copy of the vector in case someone     // adds a listener while we're
        // calling listeners:     synchronized(this) {
        lv = (Vector) actionListeners.clone();
        // Call all the listener methods:
        for (int i = 0; i < lv.size(); i++) {
            ActionListener al =
                    (ActionListener) lv.elementAt(i);
            al.actionPerformed(a);
        }
    }

    class ML extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(tColor);
            g.setFont(new Font(
                    "TimesRoman", Font.BOLD, fontSize));
            int width =
                    g.getFontMetrics().stringWidth(text);
            g.drawString(text,
                    (getSize().width - width) / 2, getSize().height / 2);
            g.dispose();
            notifyListeners();
        }
    }

    class MM extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent e) {
            xm = e.getX();
            ym = e.getY();
            repaint();
        }
    }

    // Testing the BangBean2:
    public static void main(String[] args) {
        BangBean2 bb = new BangBean2();
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ActionEvent" + e);
            }
        });
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("BangBean2 action");
            }
        });
        bb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("More action");
            }
        });
        Frame aFrame = new Frame("BangBean2 Test");
        aFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(bb, BorderLayout.CENTER);
        aFrame.setSize(300, 300);
        aFrame.setVisible(true);
    }
}

