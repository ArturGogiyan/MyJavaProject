

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by edvanchi on 30.05.2017.
 */
public class Refresh extends Thread  implements Runnable {
    private  static final int    serverPort = 5000;
    private  static final String localhost  = "127.0.0.1";
    private int x=0;
    static DataInputStream in;
    static DataOutputStream out;
    public Refresh(int x){
        this.x=x;
    }

    public void run() {
        Connections h;
        while((h=Main.con()).getCont()==-1){
            try {
                wait(10);
            }catch (Exception e){

            }
        }
        Main.in=h.getIn();
        Main.out=h.getOut();
        ExecutorService es = Executors.newFixedThreadPool(1);
        if (x==0) es.execute(new WriteGromServer());
        else es.execute(new ReadFromServer());
        es.shutdown();
    }
}

