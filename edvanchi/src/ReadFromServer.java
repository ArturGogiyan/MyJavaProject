
import java.sql.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by edvanchi on 21.05.2017.
 */
public class ReadFromServer implements Runnable{
    public void run() {
        try {

            String line ="SELECT * FROM TAILS;";
            Main.out.writeUTF(line);
            Main.out.writeUTF("1");
            //out.flush();
            line = Main.in.readUTF();
            for(int i =0;i<Integer.parseInt(line);i++) {
                MTsTail s = (MTsTail) Main.ois.readObject();
                System.out.println("s:"+s);
                Main.tails.add(s);
            }

        } catch (Exception e) {
            ExecutorService exec = Executors.newFixedThreadPool(1);
            exec.execute(new Refresh(1));
        }
    }

}
