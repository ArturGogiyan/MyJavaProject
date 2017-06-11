

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private  static final int    serverPort = 5000;
    private  static final String localhost  = "127.0.0.1";
    static DataInputStream in;
    static DataOutputStream out;
    static ObjectOutputStream oos;
    static ObjectInputStream ois;
    static HashSet<MTsTail> tails = new HashSet<MTsTail>();
    static Locale ru=new Locale("ru","RU");
    static Locale gb=new Locale("en","GB");
    static Locale lv=new Locale("lv");
    static Locale fin=new Locale("fin");
    static ResourceBundle myResources = ResourceBundle.getBundle("MyResources",ru);
    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime());
            Connections cont = con();
            in=cont.getIn();
            out=cont.getOut();
        ExecutorService exec = Executors.newFixedThreadPool(2);
        exec.execute(new ReadFromServer());
        exec.execute(new GUI());
        exec.shutdown();
    }
    public static Connections con() {
        Connections c=new Connections(in,out,-1);
        try {
            Socket socket = null;
            InetAddress ipAddress = InetAddress.getByName(localhost);
            socket = new Socket(ipAddress, serverPort);
            System.out.println("The connection is established.");
            System.out.println(
                    "\tLocalPort = " +
                            socket.getLocalPort() +
                            "\n\tInetAddress.HostAddress = " +
                            socket.getInetAddress().getHostAddress() +
                            "\n\tReceiveBufferSize (SO_RCVBUF) = " +
                            socket.getReceiveBufferSize());
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            in = new DataInputStream(sin);
            out = new DataOutputStream(sout);
            oos = new ObjectOutputStream(sout);
            ois = new ObjectInputStream(sin);
            c = new Connections(in, out, 1);
        } catch (Exception e) {
           // System.err.print(e+"Edvanchi");
            //e.printStackTrace();
            System.out.print(-1);
            c.setCont(-1);
        }
        return c;
    }
}




