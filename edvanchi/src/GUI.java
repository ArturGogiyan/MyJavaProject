
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.event.*;
import javafx.application.*;
import java.io.BufferedWriter;
import java.io.File;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import java.io.FileWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class GUI  extends Application implements Runnable {

    public void run(){
        launch();
}

    public void start(Stage mystage){
        Stage createStage = new Stage();
        ColorPicker cp = new ColorPicker();
        GridPane gp = new GridPane();
        gp.setHgap(10);
        gp.setVgap(10);
        gp.setAlignment(Pos.CENTER);
        Scene sc = new Scene(gp, 400, 400);
        GridPane pp = new GridPane();
        Scene deleteScene = new Scene( pp, 300, 150);
        pp.setHgap(10);
        pp.setVgap(10);
        pp.setAlignment(Pos.CENTER);
        TreeViewHelper helper = new TreeViewHelper();
        TreeView tw = new TreeView();
        TreeItem rt = new TreeItem(Main.myResources.getString("s1"));
        rt.getChildren().addAll(helper.getContent());
        tw.setRoot(rt);
        GridPane chose = new GridPane();
        chose.setVgap(10);
        chose.setHgap(10);
        chose.setAlignment(Pos.TOP_CENTER);
        Scene chooseScene = new Scene(chose, 300, 120);


        Button eng = new Button(Main.myResources.getString("s2"));
        eng.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.myResources = ResourceBundle.getBundle("MyResources", Main.gb);
                mystage.close();
                Stage mstg = new Stage();
                start(mstg);
                createStage.close();
            }
        });
        Button rus = new Button(Main.myResources.getString("s5"));
        rus.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.myResources = ResourceBundle.getBundle("MyResources", Main.ru);
                mystage.close();
                Stage mstg = new Stage();
                start(mstg);
                createStage.close();
            }
        });
        Button fin = new Button(Main.myResources.getString("s6"));
        fin.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.myResources = ResourceBundle.getBundle("MyResources_fin", Main.fin);
                mystage.close();
                Stage mstg = new Stage();
                start(mstg);
                createStage.close();
            }
        });
        Button lat = new Button(Main.myResources.getString("s7"));
        lat.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.myResources = ResourceBundle.getBundle("MyResources_lv", Main.lv);
                mystage.close();
                Stage mstg = new Stage();
                start(mstg);
                createStage.close();
            }
        });
        Button engind = new Button(Main.myResources.getString("s8"));
        engind.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.myResources = ResourceBundle.getBundle("MyResources", Main.gb);
                mystage.close();
                Stage mstg = new Stage();
                start(mstg);
                createStage.close();
            }
        });
        chose.add(eng,1,1);
        chose.add(rus,2,1);
        chose.add(lat,2,2);
        chose.add(fin,1,2);
        chose.add(engind,3,1);
        GridPane rp = new GridPane();
        Scene readScene = new Scene(rp, 400, 150);
        rp.setHgap(30);
        rp.setVgap(10);

        Label adr = new Label(Main.myResources.getString("s9"));
        rp.add(adr, 0, 5);
        TextField readtf = new TextField();
        rp.add(readtf, 1, 5);

        Label userName = new Label(Main.myResources.getString("s10"));
        gp.add(userName, 0, 1);

        TextField userTextField = new TextField();
        gp.add(cp, 1, 1);
        TextArea tf = new TextArea();
        tf.setPrefWidth(300);
        tf.setPrefHeight(200);
        tf.setMinWidth(150);
        tf.setMinHeight(100);
        Label pw = new Label(Main.myResources.getString("s11"));
        Label pm = new Label(Main.myResources.getString("s11"));
        gp.add(pm, 0, 2);
        pp.add(pw, 0 ,2);
        TextField lenField = new TextField();
        TextField lnField = new TextField();
        lnField.setMaxWidth(60);
        Slider sl = new Slider();
        sl.setMin(1);
        sl.setMax(100);

        Parcer pr = new Parcer();
        sl.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lnField.setText(String.valueOf((int)sl.getValue()));
            }
        });
        gp.add(sl, 1, 2);
        gp.add(lnField, 2,2);
        pp.add(lenField, 1, 2);
        Button b1 = new Button(Main.myResources.getString("s12"));
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.tails.add(new MTsTail(cp.getValue().getHue(),cp.getValue().getSaturation(),cp.getValue().getBrightness(),(int)sl.getValue()));
                tf.appendText(Main.myResources.getString("s38")+pr.parce(cp.getValue().getHue(),cp.getValue().getSaturation(),cp.getValue().getBrightness())+", "+Main.myResources.getString("s11")+(int)sl.getValue()+")"+"\n");
                createStage.close();
            }
        });
        gp.add(b1,1,3);

        Button b2 = new Button(Main.myResources.getString("s12"));
        b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int k=0;
                Iterator<MTsTail> iter = Main.tails.iterator();
                while (iter.hasNext()) {
                    if (iter.next().getLength() == Integer.parseInt(lenField.getText())) {
                        iter.remove();
                        k++;
                    }
                }
                tf.appendText(Main.myResources.getString("s39")+k+"\n");
                createStage.close();
            }
        });
        pp.add(b2,1,3);

        Button b3 = new Button(Main.myResources.getString("s12"));
        Button b56 = new Button(Main.myResources.getString("s28"));

        b56.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
                {
                    //PROVODNIK
                    @Override
                    public void handle(MouseEvent event) {
                        ExecutorService serv = Executors.newFixedThreadPool(1);
                             JFileChooser fileopen = new JFileChooser();
                        int ret = fileopen.showDialog(null, Main.myResources.getString("s31"));
                        if (ret == JFileChooser.APPROVE_OPTION) {
                            serv.execute(new ReadTails(fileopen.getSelectedFile().getName()));
                            if (ReadTails.readException()!="")
                                tf.appendText(ReadTails.readException());
                            else tf.appendText(Main.myResources.getString("s32")+ReadTails.readValue()+Main.myResources.getString("s33")+"\n");
                            createStage.close();
                        }
                    }
                }
        );
        b3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ExecutorService serv = Executors.newFixedThreadPool(1);
                ReadTails.flag=false;
                serv.execute(new ReadTails(readtf.getText()));
                while(!ReadTails.flag){System.out.print("");/*костыль*/}
                if (ReadTails.readException() != "")
                    tf.appendText(ReadTails.readException() + "\n");
                else tf.appendText(Main.myResources.getString("s32") + ReadTails.readValue() + Main.myResources.getString("s33")+"\n");
                createStage.close();
                serv.shutdown();

            }
        });
        rp.add(b3,1,6);
        rp.add(b56,2,5);

        mystage.setTitle(Main.myResources.getString("s29"));
        FlowPane root = new FlowPane(70,20);

        root.getChildren().add(tf);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 750, 600);
        mystage.setScene(scene);
        root.getChildren().add(tw);
        Button but = new Button(Main.myResources.getString("s30"));
        but.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lnField.setText("");
                userTextField.setText("");
                createStage.setScene(sc);
                createStage.setTitle(Main.myResources.getString("s34"));
                createStage.show();
            }
        });
        root.getChildren().add(but);
        mystage.show();

        Button update = new Button(Main.myResources.getString("s35"));
        //PORNHUB CAREFULL
        Button btn = new Button(":O");
        btn.setOnAction((ActionEvent event) -> {
            tf.appendText(Main.myResources.getString("s36")+"\n");
            getHostServices().showDocument("http://www.pornhub.com");

        });
        VBox opps = new VBox();
        opps.getChildren().addAll(btn);
        update.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rt.getChildren().clear();
                rt.getChildren().addAll(helper.getContent());
            }
        });
        root.getChildren().add(update);
        root.getChildren().add(btn);

        Button delete = new Button(Main.myResources.getString("s37"));
        delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lenField.setText("");
                createStage.setTitle(Main.myResources.getString("s25"));
                createStage.setScene(deleteScene);
                createStage.show();
            }
        });
        root.getChildren().add(delete);

        Button read = new Button(Main.myResources.getString("s31"));
        read.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                readtf.setText("");
                createStage.setTitle(Main.myResources.getString("s3"));
                createStage.setScene(readScene);
                createStage.show();
            }
        });
        root.getChildren().add(read);

        Button choose = new Button(Main.myResources.getString("s4"));
        choose.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                createStage.setTitle(Main.myResources.getString("s4"));
                createStage.setScene(chooseScene);
                createStage.show();
            }
        });
        root.getChildren().add(choose);
    }


    @Override
    public void stop() throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(1);
        es.execute(new WriteGromServer());
        /*es.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    FileWriter fstream1 = new FileWriter("TailCatalogue.xml");// конструктор с одним параметром - для перезаписи
                    BufferedWriter out1 = new BufferedWriter(fstream1); //  создаём буферезированный поток
                    out1.write("<TailCatalogue></TailCatalogue>"); // очищаем, перезаписав поверх пустую строку
                    out1.close(); // закрываем

                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document doc = db.parse(new File("TailCatalogue.xml"));

                for (MTsTail i : Main.tails) {
                    AddNewTail.addNewTail(doc, i.getLength(), i.getcolor());
                }
                }catch(Exception e){
                }
            }
        });*/
        es.shutdown();
    }
}