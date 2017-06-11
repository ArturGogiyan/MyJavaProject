


import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.Date;

/**
 * Created by edvanchi on 27.05.2017.
 */
public class WriteGromServer implements Runnable {
    final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    final SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);

    public void run() {
        try {
            String line ="DELETE FROM tails;";
            Main.out.writeUTF(line);
            Main.out.writeUTF("2");
            Iterator<MTsTail> iter = Main.tails.iterator();
            int k=0;
            while (iter.hasNext()) {
                MTsTail mts = iter.next();
                OffsetDateTime odt = mts.getDate();
                System.out.println(odt);
               String ln = "INSERT INTO tails (ID, hue, sat, value, length, Creation_date) VALUES ("+ ++k +" ,"+mts.getH()+", "+mts.getS()+", "+mts.getV()+", "
                       +mts.getLength()+", '"+odt.getYear()+"-0"+odt.getMonthValue()+"-0"+odt.getDayOfMonth()+" "+odt.getHour()+":"+odt.getMinute()+":"
                       +odt.getSecond()+"+0');";
                System.out.println(ln);
                //System.out.println(ln);
               /*String ln = "drop table public.tails; CREATE TABLE public.tails\n" +
                        "(\n" +
                        "  id integer NOT NULL,\n" +
                        "  hue real NOT NULL,\n" +
                       "  sat real NOT NULL,\n" +
                       "  value real NOT NULL,\n" +
                        "  length integer NOT NULL,\n" +
                        "  Creation_date timestamp without time zone NOT NULL,\n" +
                        "  CONSTRAINT tails_pkey PRIMARY KEY (id)\n" +
                        ")";*/

                Main.out.writeUTF(ln);
            }
            Main.out.writeUTF("quit");
            //out.flush();
        } catch (Exception e) {
            System.out.print(-2);
            System.out.print(e);
            e.printStackTrace();
            ExecutorService exec = Executors.newFixedThreadPool(1);
            exec.execute(new Refresh(0));
            exec.shutdown();
        }
    }
}
