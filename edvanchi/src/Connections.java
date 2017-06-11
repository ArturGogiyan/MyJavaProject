
import java.net.Socket;
import java.io.*;

/**
 * Created by edvanchi on 30.05.2017.
 */
public class Connections {
    private DataInputStream in;
    private DataOutputStream out;
    private  int cont;

    public Connections(DataInputStream in, DataOutputStream out, int socket){
        setIn(in);
        setOut(out);
        setCont(socket);
    }
    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int socket) {
        this.cont = socket;
    }
}
