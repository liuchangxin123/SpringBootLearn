package lcx;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;

/**
 * @Author : liuchangxin
 * @Descpription: TODO()
 * @Created in 2019/4/28 10:54.
 */
public class lession_20190428 {
}

class JabberClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private static int counter = 0;
    private int id = counter++;
    private static int threadcount = 0;

    public static int threadCount() {
        return threadcount;
    }

    public JabberClientThread(InetAddress addr) {
        System.out.println("Making client " + id);
        threadcount++;
        try {
            socket = new Socket(addr, MultiJabberServer.PORT);
        } catch (IOException e) {
            // If the creation of the socket fails,        // nothing needs to be cleaned up.
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));       // Enable auto-flush:
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            start();
        } catch (IOException e) {
            // The socket should be closed on any
            // failures other than the socket
            // constructor:
            try {
                socket.close();
            } catch (IOException e2) {
            }
        }
        // Otherwise the socket will be closed by
        // the run() method of the thread.

    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                out.println("Client " + id + ": " + i);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } catch (IOException e) {
        } finally {       // Always close it:
            try {
                socket.close();
            } catch (IOException e) {
            }
            threadcount--; // Ending this thread
        }
    }
}

class MultiJabberClient {
    static final int MAX_THREADS = 40;

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
        while (true) {
            if (JabberClientThread.threadCount() < MAX_THREADS) {
                new JabberClientThread(addr);
            }
            Thread.currentThread().sleep(100);
        }
    }
}

//---------------------------------------------------------------


class Dgram {
    public static DatagramPacket toDatagram(String s, InetAddress destIA, int destPort) {     // Deprecated in Java 1.1, but it works:
        byte[] buf = new byte[s.length() + 1];
        s.getBytes(0, s.length(), buf, 0);
        // The correct Java 1.1 approach, but it's     // Broken (it truncates the String):
        //byte[] buf = s.getBytes();
        return new DatagramPacket(buf, buf.length, destIA, destPort);
    }

    public static String toString(DatagramPacket p) {
        // The Java 1.0 approach:
        // return new String(p.getData(),
        //  0, 0, p.getLength());
        // The Java 1.1 approach:
        return new String(p.getData(), 0, p.getLength());
    }
}


class ChatterServer {
    static final int INPORT = 1711;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);   // Can listen & send on the same socket:
    private DatagramSocket socket;

    public ChatterServer() {
        try {
            socket = new DatagramSocket(INPORT);
            System.out.println("Server started");
            while (true) {
                // Block until a datagram appears:
                socket.receive(dp);
                String rcvd = Dgram.toString(dp) +
                        ", from address: " + dp.getAddress() +
                        ", port: " + dp.getPort();
                System.out.println(rcvd);
                String echoString =
                        "Echoed: " + rcvd;
                // Extract the address and port from the         // received datagram to find out where to
                // send it back:
                DatagramPacket echo = Dgram.toDatagram(echoString, dp.getAddress(), dp.getPort());
                socket.send(echo);
            }
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Communication error");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatterServer();
    }
}


//-----------------------------------------------------------------------------


class ChatterClient extends Thread {   // Can listen & send on the same socket:
    private DatagramSocket s;
    private InetAddress hostAddress;
    private byte[] buf = new byte[1000];
    private DatagramPacket dp = new DatagramPacket(buf, buf.length);
    private int id;

    public ChatterClient(int identifier) {
        id = identifier;
        try {
            // Auto-assign port number:
            s = new DatagramSocket();
            hostAddress =
                    InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            System.err.println("Cannot find host");
            System.exit(1);
        } catch (SocketException e) {
            System.err.println("Can't open socket");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("ChatterClient starting");
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 25; i++) {
                String outMessage = "Client #" + id + ", message #" + i;
                // Make and send a datagram:
                s.send(Dgram.toDatagram(outMessage, hostAddress,
                        ChatterServer.INPORT));
                // Block until it echoes back:
                s.receive(dp);
                // Print out the echoed contents:
                String rcvd = "Client #" + id +
                        ", rcvd from " +
                        dp.getAddress() + ", " + dp.getPort() + ": " +
                        Dgram.toString(dp);
                System.out.println(rcvd);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new ChatterClient(i).start();
        }
    }
}

//------------------------------------

final class Singleton {
    private static Singleton s = new Singleton(47);
    private int i;

    private Singleton(int x) {
        i = x;
    }

    public static Singleton getHandle() {
        return s;
    }

    public int getValue() {
        return i;
    }

    public void setValue(int x) {
        i = x;
    }
}

class SingletonPattern {
    public static void main(String[] args) {
        Singleton s = Singleton.getHandle();
        System.out.println(s.getValue());
        Singleton s2 = Singleton.getHandle();
        s2.setValue(9);
        System.out.println(s.getValue());
        try {
            // Can't do this: compile-time error.
            // Singleton s3 = (Singleton)s2.clone();
        } catch (Exception e) {
        }
    }
}


//--------------------------------------


class Pr {
    static void error(String e) {
        System.err.println("ERROR: " + e);
        System.exit(1);
    }
}

class IO {
    static BufferedReader disOpen(File f) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(f));
        } catch (IOException e) {
            Pr.error("could not open " + f);
        }
        return in;
    }

    static BufferedReader disOpen(String fname) {
        return disOpen(new File(fname));
    }

    static DataOutputStream dosOpen(File f) {
        DataOutputStream in = null;
        try {
            in = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(f)));
        } catch (IOException e) {
            Pr.error("could not open " + f);
        }
        return in;
    }

    static DataOutputStream dosOpen(String fname) {
        return dosOpen(new File(fname));
    }

    static PrintWriter psOpen(File f) {
        PrintWriter in = null;
        try {
            in = new PrintWriter(new BufferedWriter(new FileWriter(f)));
        } catch (IOException e) {
            Pr.error("could not open " + f);
        }
        return in;
    }

    static PrintWriter psOpen(String fname) {
        return psOpen(new File(fname));
    }

    static void close(Writer os) {
        try {
            os.close();
        } catch (IOException e) {
            Pr.error("closing " + os);
        }
    }

    static void close(DataOutputStream os) {
        try {
            os.close();
        } catch (IOException e) {
            Pr.error("closing " + os);
        }
    }

    static void close(Reader os) {
        try {
            os.close();
        } catch (IOException e) {
            Pr.error("closing " + os);
        }
    }
}

class SourceCodeFile {
    public static final String
            startMarker = "//:", // Start of source file
            endMarker = "} ///:~", // End of source
            endMarker2 = "}; ///:~", // C++ file end
            beginContinue = "} ///:Continued",
            endContinue = "///:Continuing",
            packMarker = "###",
    // Packed file header tag
    eol = System.getProperty("line.separator"), // Line separator on current system
            filesep = System.getProperty("file.separator");// System's file path separator
    public static String copyright = "";

    static {
        try {
            BufferedReader cr = new BufferedReader(new FileReader("Copyright.txt"));
            String crin;
            while ((crin = cr.readLine()) != null) {
                copyright += crin + "\n";
            }
            cr.close();
        } catch (Exception e) {
            copyright = "";
        }
    }

    private String filename, dirname, contents = new String();
    private static String chapter = "c02";   // The file name separator from the old system:
    public static String oldsep;

    @Override
    public String toString() {
        return dirname + filesep + filename;
    }
    // Constructor for parsing from document file:


    public SourceCodeFile(String firstLine, BufferedReader in) {
        dirname = chapter;     // Skip past marker:
        filename = firstLine.substring(startMarker.length()).trim();     // Find space that terminates file name:
        if (filename.indexOf(' ') != -1) {
            filename = filename.substring(0, filename.indexOf(' '));
        }
        System.out.println("found: " + filename);
        contents = firstLine + eol;
        if (copyright.length() != 0) {
            contents += copyright + eol;
        }
        String s;
        boolean foundEndMarker = false;
        try {
            while ((s = in.readLine()) != null) {
                if (s.startsWith(startMarker)) {
                    Pr.error("No end of file marker for " + filename);
                }
                // For this program, no spaces before          // the "package" keyword are allowed
                // in the input source code:
                else if (s.startsWith("package")) {
                    // Extract package name:
                    String pdir = s.substring(s.indexOf(' ')).trim();
                    pdir = pdir.substring(0, pdir.indexOf(';')).trim();
                    // Capture the chapter from the package
                    // ignoring the 'com' subdirectories:
                    if (!pdir.startsWith("com")) {
                        int firstDot = pdir.indexOf('.');
                        if (firstDot != -1) {
                            chapter = pdir.substring(0, firstDot);
                        } else {
                            chapter = pdir;
                        }
                    }
                    // Convert package name to path name:
                    pdir = pdir.replace(
                            '.', filesep.charAt(0));
                    System.out.println("package " + pdir);
                    dirname = pdir;
                }
                contents += s + eol;
                // Move past continuations:
                if (s.startsWith(beginContinue)) {
                    while ((s = in.readLine()) != null) {
                        if (s.startsWith(endContinue)) {
                            contents += s + eol;
                            break;
                        }
                        // Watch for end of code listing:
                        if (s.startsWith(endMarker) ||
                                s.startsWith(endMarker2)) {
                            foundEndMarker = true;
                            break;
                        }
                    }
                }
            }
            if (!foundEndMarker) {
                Pr.error(
                        "End marker not found before EOF");
            }
            System.out.println("Chapter: " + chapter);
        } catch (IOException e) {
            Pr.error("Error reading line");
        }
    }

    // For recovering from a packed file:
    public SourceCodeFile(BufferedReader pFile) {
        try {
            String s = pFile.readLine();
            if (s == null) {
                return;
            }
            if (!s.startsWith(packMarker)) {
                Pr.error("Can't find " + packMarker
                        + " in " + s);
            }
            s = s.substring(packMarker.length()).trim();
            dirname = s.substring(0, s.indexOf('#'));
            filename = s.substring(s.indexOf('#') + 1);
            dirname = dirname.replace(oldsep.charAt(0), filesep.charAt(0));
            filename = filename.replace(oldsep.charAt(0), filesep.charAt(0));
            System.out.println("listing: " + dirname + filesep + filename);
            while ((s = pFile.readLine()) != null) {
                // Watch for end of code listing:
                if (s.startsWith(endMarker) ||
                        s.startsWith(endMarker2)) {
                    contents += s;
                    break;
                }
                contents += s + eol;
            }
        } catch (IOException e) {
            System.err.println("Error reading line");
        }
    }

    public boolean hasFile() {
        return filename != null;
    }

    public String directory() {
        return dirname;
    }

    public String filename() {
        return filename;
    }

    public String contents() {
        return contents;
    }

    // To write to a packed file:
    public void writePacked(DataOutputStream out) {
        try {
            out.writeBytes(packMarker + dirname + "#" + filename + eol);
            out.writeBytes(contents);
        } catch (IOException e) {
            Pr.error("writing " + dirname + filesep + filename);
        }
    }

    // To generate the actual file:
    public void writeFile(String rootpath) {
        File path = new File(rootpath, dirname);
        path.mkdirs();
        PrintWriter p =
                IO.psOpen(new File(path, filename));
        p.print(contents);
        IO.close(p);
    }
}

class DirMap {
    private Hashtable t = new Hashtable();
    private String rootpath;

    DirMap() {
        rootpath = System.getProperty("user.dir");
    }

    DirMap(String alternateDir) {
        rootpath = alternateDir;
    }

    public void add(SourceCodeFile f) {
        String path = f.directory();
        if (!t.containsKey(path)) {
            t.put(path, new Vector());
        }
        ((Vector) t.get(path)).addElement(f);
    }

    public void writePackedFile(String fname) {
        DataOutputStream packed = IO.dosOpen(fname);
        try {
            packed.writeBytes("###Old Separator:" +
                    SourceCodeFile.filesep + "###\n");
        } catch (IOException e) {
            Pr.error("Writing separator to " + fname);
        }
        Enumeration e = t.keys();
        while (e.hasMoreElements()) {
            String dir = (String) e.nextElement();
            System.out.println(
                    "Writing directory " + dir);
            Vector v = (Vector) t.get(dir);
            for (int i = 0; i < v.size(); i++) {
                SourceCodeFile f =
                        (SourceCodeFile) v.elementAt(i);
                f.writePacked(packed);
            }
        }
        IO.close(packed);
    }

    // Write all the files in their directories:
    public void write() {
        Enumeration e = t.keys();
        while (e.hasMoreElements()) {
            String dir = (String) e.nextElement();
            Vector v = (Vector) t.get(dir);
            for (int i = 0; i < v.size(); i++) {
                SourceCodeFile f = (SourceCodeFile) v.elementAt(i);
                f.writeFile(rootpath);
            }
            // Add file indicating file quantity       // written to this directory as a check:
            IO.close(IO.dosOpen(new File(new File(rootpath, dir),
                    Integer.toString(v.size()) + ".files")));
        }
    }
}

class CodePackager {
    private static final String usageString =
            "usage: java CodePackager packedFileName" +
                    "\nExtracts source code files from packed \n" +
                    "version of Tjava.doc sources into " +
                    "directories off current directory\n" +
                    "java CodePackager packedFileName newDir\n" +
                    "Extracts into directories off newDir\n" +
                    "java CodePackager -p source.txt packedFile" +
                    "\nCreates packed version of source files" + "\nfrom text version of Tjava.doc";

    private static void usage() {
        System.err.println(usageString);
        System.exit(1);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            usage();
        }
        if (args[0].equals("-p")) {
            if (args.length != 3) {
                usage();
            }
            createPackedFile(args);
        } else {
            if (args.length > 2) {
                usage();
            }
            extractPackedFile(args);
        }
    }

    private static String currentLine;
    private static BufferedReader in;
    private static DirMap dm;

    private static void createPackedFile(String[] args) {
        dm = new DirMap();
        in = IO.disOpen(args[1]);
        try {
            while ((currentLine = in.readLine())
                    != null) {
                if (currentLine.startsWith(SourceCodeFile.startMarker)) {
                    dm.add(new SourceCodeFile(currentLine, in));
                } else if (currentLine.startsWith(SourceCodeFile.endMarker)) {
                    Pr.error("file has no start marker");
                }
                // Else ignore the input line
            }
        } catch (IOException e) {
            Pr.error("Error reading " + args[1]);
        }
        IO.close(in);
        dm.writePackedFile(args[2]);
    }

    private static void extractPackedFile(String[] args) {
        if (args.length == 2) {// Alternate directory
            dm = new DirMap(args[1]);
        } else { // Current directory
            dm = new DirMap();
        }
        in = IO.disOpen(args[0]);
        String s = null;
        try {
            s = in.readLine();
        } catch (IOException e) {
            Pr.error("Cannot read from " + in);
        }
        // Capture the separator used in the system     // that packed the file:
        if (s.indexOf("###Old Separator:") != -1) {
            String oldsep = s.substring(
                    "###Old Separator:".length());
            oldsep = oldsep.substring(0, oldsep.indexOf('#'));
            SourceCodeFile.oldsep = oldsep;
        }
        SourceCodeFile sf = new SourceCodeFile(in);
        while (sf.hasFile()) {
            dm.add(sf);
            sf = new SourceCodeFile(in);
        }
        dm.write();
    }
}


//-----------------------------------

class MultiStringMap extends Hashtable {
    public void add(String key, String value) {
        if (!containsKey(key)) {
            put(key, new Vector());
        }
        ((Vector) get(key)).addElement(value);
    }

    public Vector getVector(String key) {
        if (!containsKey(key)) {
            System.err.println(
                    "ERROR: can't find key: " + key);
            System.exit(1);
        }
        return (Vector) get(key);
    }

    public void printValues(PrintStream p) {
        Enumeration k = keys();
        while (k.hasMoreElements()) {
            String oneKey = (String) k.nextElement();
            Vector val = getVector(oneKey);
            for (int i = 0; i < val.size(); i++) {
                p.println((String) val.elementAt(i));
            }
        }
    }
}

class ClassScanner {
    private File path;
    private String[] fileList;
    private Properties classes = new Properties();
    private MultiStringMap classMap = new MultiStringMap(), identMap = new MultiStringMap();
    private StreamTokenizer in;

    public ClassScanner() {
        path = new File(".");
        fileList = path.list(new JavaFilter());
        for (int i = 0; i < fileList.length; i++) {
            System.out.println(fileList[i]);
            scanListing(fileList[i]);
        }
    }

    void scanListing(String fname) {
        try {
            try {
                in = new StreamTokenizer(new BufferedReader(new FileReader(fname)));
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
            // Doesn't seem to work:
            in.slashStarComments(true);
            in.slashSlashComments(true);
            in.ordinaryChar('/');
            in.ordinaryChar('.');
            in.wordChars('_', '_');
            in.eolIsSignificant(true);
            while (in.nextToken() != StreamTokenizer.TT_EOF) {
                if (in.ttype == '/') {
                    eatComments();
                } else if (in.ttype == StreamTokenizer.TT_WORD) {
                    if (in.sval.equals("class") || in.sval.equals("interface")) {
                        // Get class name:
                        while (in.nextToken() != StreamTokenizer.TT_EOF
                                && in.ttype !=
                                StreamTokenizer.TT_WORD)
                            ;
                        classes.put(in.sval, in.sval);
                        classMap.add(fname, in.sval);
                    }
                    if (in.sval.equals("import") || in.sval.equals("package")) {
                        discardLine();
                    } else // It's an identifier or keyword
                    {
                        identMap.add(fname, in.sval);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void discardLine() {
        try {
            while (in.nextToken() !=
                    StreamTokenizer.TT_EOF
                    && in.ttype !=
                    StreamTokenizer.TT_EOL)
                ; // Throw away tokens to end of line
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // StreamTokenizer's comment removal seemed   // to be broken. This extracts them:
    void eatComments() {
        try {
            if (in.nextToken() !=
                    StreamTokenizer.TT_EOF) {
                if (in.ttype == '/') {
                    discardLine();
                } else if (in.ttype != '*') {
                    in.pushBack();
                } else {
                    while (true) {
                        try {
                            if (in.nextToken() ==
                                    StreamTokenizer.TT_EOF) {
                                break;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (in.ttype == '*') {
                            try {
                                if (in.nextToken() != StreamTokenizer.TT_EOF && in.ttype == '/') {
                                    break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] classNames() {
        String[] result = new String[classes.size()];
        Enumeration e = classes.keys();
        int i = 0;
        while (e.hasMoreElements()) {
            result[i++] = (String) e.nextElement();

        }
        return result;
    }

    public void checkClassNames() {
        Enumeration files = classMap.keys();
        while (files.hasMoreElements()) {
            String file = (String) files.nextElement();
            Vector cls = classMap.getVector(file);
            for (int i = 0; i < cls.size(); i++) {
                String className =
                        (String) cls.elementAt(i);
                if (Character.isLowerCase(className.charAt(0))) {
                    System.out.println(
                            "class capitalization error, file: "
                                    + file + ", class: "
                                    + className);
                }
            }
        }
    }

    public void checkIdentNames() {
        Enumeration files = identMap.keys();
        Vector reportSet = new Vector();
        while (files.hasMoreElements()) {
            String file = (String) files.nextElement();
            Vector ids = identMap.getVector(file);
            for (int i = 0; i < ids.size(); i++) {
                String id = (String) ids.elementAt(i);
                if (!classes.contains(id)) {
                    // Ignore identifiers of length 3 or           // longer that are all uppercase           // (probably static final values):
                    if (id.length() >= 3 && id.equals(id.toUpperCase())) {
                        continue;
                    }
                    // Check to see if first char is upper:
                    if (Character.isUpperCase(id.charAt(0))) {
                        if (reportSet.indexOf(file + id) == -1) { // Not reported yet
                            reportSet.addElement(file + id);
                            System.out.println(
                                    "Ident capitalization error in:"
                                            + file + ", ident: " + id);
                        }
                    }
                }
            }
        }
    }

    static final String usage =
            "C:\\Users\\拿着糖葫芦狂奔\\Desktop\\qq.txt";

    private static void usage() {
        System.err.println(usage);
        System.exit(1);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1 || args.length > 2) {
            usage();
        }
        ClassScanner c = new ClassScanner();
        File old = new File(args[0]);
        if (old.exists()) {
            try {
                // Try to open an existing          // properties file:
                InputStream oldlist = new BufferedInputStream(new FileInputStream(old));
                c.classes.load(oldlist);
                oldlist.close();
            } catch (IOException e) {
                System.err.println("Could not open "
                        + old + " for reading");
                System.exit(1);
            }
        }
        if (args.length == 1) {
            c.checkClassNames();
            c.checkIdentNames();
        }
        // Write the class names to a repository:
        if (args.length == 2) {
            if (!args[1].equals("-a")) {
                usage();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(args[0]));
                c.classes.save(out,
                        "Classes found by ClassScanner.java");
                out.close();
            } catch (IOException e) {
                System.err.println(
                        "Could not write " + args[0]);
                System.exit(1);
            }
        }
    }
}

class JavaFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        // Strip path information:
        String f = new File(name).getName();
        return f.trim().endsWith(".java");
    }
}


//----------------------------------

class DisplayMethods extends Applet {
    Class cl;
    Method[] m;
    Constructor[] ctor;
    String[] n = new String[0];
    TextField name = new TextField(40), searchFor = new TextField(30);
    Checkbox strip = new Checkbox("Strip Qualifiers");
    TextArea results = new TextArea(40, 65);

    public void init() {
        strip.setState(true);
        name.addTextListener(new NameL());
        searchFor.addTextListener(new SearchForL());
        strip.addItemListener(new StripL());
        Panel top = new Panel(), lower = new Panel(), p = new Panel();
        top.add(new Label("Qualified class name:"));
        top.add(name);
        lower.add(
                new Label("String to search for:"));
        lower.add(searchFor);
        lower.add(strip);
        p.setLayout(new BorderLayout());
        p.add(top, BorderLayout.NORTH);
        p.add(lower, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(p, BorderLayout.NORTH);
        add(results, BorderLayout.CENTER);
    }

    class NameL implements TextListener {
        @Override
        public void textValueChanged(TextEvent e) {
            String nm = name.getText().trim();
            if (nm.length() == 0) {
                results.setText("No match");
                n = new String[0];
                return;
            }
            try {
                cl = Class.forName(nm);
            } catch (ClassNotFoundException ex) {
                results.setText("No match");
                return;
            }
            m = cl.getMethods();
            ctor = cl.getConstructors();       // Convert to an array of Strings:
            n = new String[m.length + ctor.length];
            for (int i = 0; i < m.length; i++) n[i] = m[i].toString();
            for (int i = 0; i < ctor.length; i++) n[i + m.length] = ctor[i].toString();
            reDisplay();
        }
    }

    void reDisplay() {
        // Create the result set:
        String[] rs = new String[n.length];
        String find = searchFor.getText();
        int j = 0;
        // Select from the list if find exists:
        for (int i = 0; i < n.length; i++) {
            if (find == null) rs[j++] = n[i];
            else if (n[i].indexOf(find) != -1) rs[j++] = n[i];
        }
        results.setText("");
        if (strip.getState() == true) for (int i = 0; i < j; i++)
            results.append(
                    StripQualifiers.strip(rs[i]) + "\n");
        else // Leave qualifiers on
            for (int i = 0; i < j; i++) results.append(rs[i] + "\n");
    }

    class StripL implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            reDisplay();
        }
    }

    class SearchForL implements TextListener {
        @Override
        public void textValueChanged(TextEvent e) {
            reDisplay();
        }
    }

    public static void main(String[] args) {
        DisplayMethods applet = new DisplayMethods();
        Frame aFrame = new Frame("Display Methods");
        aFrame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        aFrame.add(applet, BorderLayout.CENTER);
        aFrame.setSize(500, 750);
        applet.init();
        applet.start();
        aFrame.setVisible(true);
    }
}

class StripQualifiers {
    private StreamTokenizer st;

    public StripQualifiers(String qualified) {
        st = new StreamTokenizer(new StringReader(qualified));
        st.ordinaryChar(' ');
    }

    public String getNext() {
        String s = null;
        try {
            if (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_EOL:
                        s = null;
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        s = Double.toString(st.nval);
                        break;
                    case StreamTokenizer.TT_WORD:
                        s = new String(st.sval);
                        break;
                    default: // single character in ttype
                        s = String.valueOf((char) st.ttype);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return s;
    }

    public static String strip(String qualified) {
        StripQualifiers sq = new StripQualifiers(qualified);
        String s = "", si;
        while ((si = sq.getNext()) != null) {
            int lastDot = si.lastIndexOf('.');
            if (lastDot != -1) si = si.substring(lastDot + 1);
            s += si;
        }
        return s;
    }
}

