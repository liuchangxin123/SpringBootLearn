package lcx;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author : liuchangxin
 * @Descpription: TODO()
 * @Created in 2019/4/25 15:07.
 */
public class lseeion_20190425 {
    public static void main(String[] args) {
        String s = Color.getValue(0).getDesc();
        System.out.println(s);

        Color color = Color.valueOf("GREEN");
        System.out.println(color.getDesc());
    }
}

enum Color {
    RED(0, "红色"),
    BLUE(1, "蓝色"),
    GREEN(2, "绿色"),;
    private int code;
    private String desc;

    Color(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    static Color getValue(int code) {
        for (Color color : values()) {
            if (color.getCode() == code) {
                return color;
            }
        }
        return null;
    }
}

class JabberServer {
    // Choose a port outside of the range 1-1024:
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Started: " + s);
        try {
            // Blocks until a connection occurs:
            Socket socket = s.accept();
            try {
                System.out.println("Connection accepted: " + socket);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // Output is automatically flushed
                // by PrintWriter:
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                while (true) {
                    String str = in.readLine();
                    if (str.equals("END")) {
                        break;
                    }
                    System.out.println("Echoing: " + str);
                    out.println(str);
                }
                // Always close the two sockets...
            } finally {
                System.out.println("closing...");
                socket.close();
            }
        } finally {
            s.close();
        }
    }
}


class JabberClient {
    public static void main(String[] args) throws IOException {
        // Passing null to getByName() produces the
        // special "Local Loopback" IP address, for
        // testing on one machine w/o a network:
        InetAddress addr = InetAddress.getByName(null);
        // Alternatively, you can use      // the address or name:
        // InetAddress addr =
        //    InetAddress.getByName("127.0.0.1");     // InetAddress addr =
        //    InetAddress.getByName("localhost");
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, JabberServer.PORT);
        // Guard everything in a try-finally to make     // sure that the socket is closed:
        try {
            System.out.println("socket = " + socket);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Output is automatically flushed       // by PrintWriter:
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            for (int i = 0; i < 10; i++) {
                out.println("howdy " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}


//-------------------------------------------------------------


class ServeOneJabber extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

     ServeOneJabber(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // Enable auto-flush:
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        // If any of the above calls throw an
        // exception, the caller is responsible for
        // closing the socket. Otherwise the thread     // will close it.
        start(); // Calls run()
    }

    @Override
    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("END")){ break;}
                System.out.println("Echoing: " + str);
                out.println(str);
            }
            System.out.println("closing...");
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}

class MultiJabberServer {
    static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                // Blocks until a connection occurs:
                Socket socket = s.accept();
                try {
                    new ServeOneJabber(socket);
                } catch (IOException e) {           // If it fails, close the socket,           // otherwise the thread will close it:
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }
}

class a{
    public static void main(String[] args) {
//        Object o="21.1";
//        BigDecimal b=null;
//        b=(BigDecimal)o;
//        System.out.println(b);
        Object o=21;
        a.getBigDecimal(o);

    }
    public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }
}
