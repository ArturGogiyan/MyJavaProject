

import java.io.IOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.time.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by edvanchi on 14.05.2017.
 */
public class Server extends Thread {
    static private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/MTsTails";
    static private final String USER = "postgres";
    static private final String PASS = "postgres";
    HashSet<MTsTail> set = new HashSet<>();
    private Socket socket;
    private int num;

    private int port;

    private Server() {
    }

    private synchronized void updateTails(DataInputStream s, String ds) {
        try {
            Statement statement = dbConnection().createStatement();
            statement.executeUpdate(ds);
            String line;
            while(true) {
                line = s.readUTF();
                System.out.println(line);
                if (line.equals("quit")) break;
                statement.executeUpdate(line);
            }

        } catch (Exception e) {
            System.err.print(e);
          //  e.printStackTrace();
        }
    }

    private synchronized void getTails(String insert) {
        try {
            Statement statement = dbConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(insert);

            while (resultSet.next()) {
                Timestamp ts = resultSet.getTimestamp("creation_date");
                ZoneOffset zof = ZoneOffset.of("+00:00");
                OffsetDateTime odt = ts.toLocalDateTime().atOffset(zof);
                System.out.println(odt);
                set.add(new MTsTail(resultSet.getDouble("hue"),resultSet.getDouble("sat"),resultSet.getDouble("value"),
                        resultSet.getInt("length"), odt));
            }
        } catch (Exception e) {
            System.err.print(e);
           e.printStackTrace();
        }
    }


    private void setSocket(int num, Socket socket)
    {
        this.num    = num;
        this.socket = socket;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    private Connection dbConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            //e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
           // e.printStackTrace();
        }
        return connection;
    }
    public void run(){

        try {
            // Определяем входной и выходной потоки сокета
            // для обмена данными с клиентом
            InputStream  sin  = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            ObjectInputStream ois = new ObjectInputStream(sin);
            ObjectOutputStream oos = new ObjectOutputStream(sout);
            DataInputStream  dis = new DataInputStream (sin );
            DataOutputStream dos = new DataOutputStream(sout);

            String line = null;
            while(true) {
                // Ожидание сообщения от клиента
                line = dis.readUTF();
                System.out.println(line);
                String order = dis.readUTF();
                if (order.equals("1")) {getTails(line);
                    int str = set.size();
                    dos.writeUTF(String.valueOf(str));
                Iterator<MTsTail> iterator = set.iterator();
                while (iterator.hasNext()){
                    MTsTail s=iterator.next();
                    System.out.println("s:"+s);
                    oos.writeObject(s);
                }
                }
                 else updateTails(dis, line);
                System.out.println("QUIT");
                dos.flush();
                // Завершаем передачу данных
                if (line.equalsIgnoreCase("quit")) {
                    // завершаем соединение
                    socket.close();
                    break;
                }
            }
        } catch(Exception e) {
            System.out.println("Exception : " + e);
        }
    }
    public static void main(String[] args) {
        ServerSocket srvSocket = null;
        try {
            try {
                int i = 0; // Счётчик подключений
                // Подключение сокета к localhost
                srvSocket = new ServerSocket(5000);
                System.out.println("Server started\n\n");
                while (true) {
                    // ожидание подключения
                    Socket socket = srvSocket.accept();
                    System.out.println("Client accepted");
                    // Стартуем обработку клиента в отдельном потоке
                    new Server().setSocket(i++, socket);
                }
            } catch (Exception e) {
                System.out.println("Exception : " + e);
            }
        } finally {
            try {
                if (srvSocket != null)
                    srvSocket.close();
            } catch (IOException e) {}
        }
        System.exit(0);
    }
}