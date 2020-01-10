import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 * Created by JUST on 01/02/2019.
 * this application is related
 * compressing and sving files
 * with a safety way
 */
public class ZiplagichInterface extends JFrame{
    /**
     * first button for setting file path
     */
    private JButton path;
    /**
     * second button for setting file path
     */
    private JButton path2;
    /**
     * a button for next file path
     */
    private JButton plus;
    /**
     * a button for compressing file(s)
     */
    private JButton ziplash;
    /**
     * a button for a decompressing a compressed file
     */
    private JButton tiklash;
    /**
     * list for decompressed files
     */
    private JList royhat;
    /**
     * a text field for file path
     */
    private JTextField filePath;
    /**
     * a text field for file path
     */
    private JTextField password;
    /**
     * a text field for new file path
     */
    private JTextField newFilePath;
    /**
     * a panel which is located on the top
     */
    private JPanel panelTop;
    /**
     * a panel on the centre top
     */
    private JPanel panelCentreTop;
    /**
     * a panel on the centre
     */
    private JPanel panelCentre;
    /**
     * a scroll
     */
    private JScrollPane pane;

//    private JLabel label1 = new JLabel("Yangi fayl nomi");

    /**
     * a variable for counting files count
     */
    private int fileSoni = 0;
    /**
     * a string variable for getting file(s)
     */
    private String []faylString = new String[30];
    /**
     * a variable for getting files name
     */
    private String []filesName = new String[30];
    /**
     * a variable for files' short name
     */
    private String []filesQisqaName = new String[30];
    /**
     * a string variables for backing files
     */
    private String[] filesBack = new String[30];//{"fileBack1.jpg", "fileBack2.jpg", "fileBack3.jpg", "fileBack4.mp3"}; //hozircha
    /**
     * a variable for new file's name
     */
    private String newFileName;//= new String("JoinedFile.just");
    /**
     * a variables for files
     */
    private File[] files= new File[30];
    /**
     * a variable for file count
     */
    private long fileCount;
//    private Boolean natija;

    private boolean isTruePassword = false;


    /**
     * designing Compressing interface for the application
     */
    public ZiplagichInterface(){
        super("Ziplagich");

        /**
         * setting  default values for components
         * begin
         */
        filesName[0] = "";

        path = new JButton("...");
        plus = new JButton("+");
        ziplash = new JButton("Ziplash");
        tiklash = new JButton("Tiklash");

        filePath = new JTextField("file directory...", 30);
        filePath.setFont(new Font("Times",Font.PLAIN, 14));

        password = new JTextField("parol...", 10);
        password.setFont(new Font("Times",Font.PLAIN, 14));
        /**
         * setting  default values for components
         * end
         */

        /**
         * adding for the interface
         * begin
         */
        panelTop = new JPanel();
        panelTop.setLayout(new FlowLayout());
        panelTop.add(filePath);
        panelTop.add(path);
        panelTop.add(plus);
        panelTop.add(password);
        add(panelTop, BorderLayout.NORTH);

        royhat = new JList(faylString);
        royhat.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panelCentre = new JPanel();
        pane = new JScrollPane(royhat);
        panelCentre.setLayout(new BorderLayout());
        panelCentreTop = new JPanel(new FlowLayout());
        newFilePath = new JTextField("Yangi fayl nomi...", 30);
        newFilePath.setFont(new Font("Times",Font.PLAIN, 14));
        path2 = new JButton("...");
        panelCentreTop.add(newFilePath);
        panelCentreTop.add(path2);
        panelCentreTop.add(ziplash);
        panelCentreTop.add(tiklash);
        panelCentre.add(panelCentreTop, BorderLayout.NORTH);
        panelCentre.add(pane, BorderLayout.CENTER);
        add(panelCentre, BorderLayout.CENTER);
        /**
         * adding for the interface
         * end
         */

        /**
         * events(methods) for the interface
         * begin
         */
        path.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent hodisa) {
                        analyzePath(true);
                    }
                }
        );

        path2.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent hodisa) {
                        analyzePath(false);
                    }
                }
        );

        /**
         * event when clicked 'plus' button
         */
        plus.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((filePath.getText()).endsWith(".just"))
                {
                    String faylString[] = new String[31];
//                    String s[] = new String[30];
                    int soni = 0, nuqtaIndex = 0;
                    char []cc = new char[100];
                    String ss;

                    fileSoni = 0;
                    faylString[fileSoni++] = " ...";//filePath.getText();
                    try {
                        soni = Hisobla();
                        if(soni == -1){
                            JOptionPane.showMessageDialog(null, "Parol xato kiritildi !!!", "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
//                    JOptionPane.showMessageDialog(null, filesBack, "sarlavha", JOptionPane.INFORMATION_MESSAGE);
                    for (int i = 0; i < soni ; i++) {
                        nuqtaIndex = filesBack[i].lastIndexOf('.');
                        //cc = filesBack[i].substring(0, nuqtaIndex).toCharArray();
                        ss = filesBack[i].substring(0, nuqtaIndex);

//                        faylString[fileSoni - 1]
                        int uzunlik = ss.length();
                        uzunlik = 50 - uzunlik;
                        //ss += uzunlik;
                        //uzunlik = 20;
                        while (uzunlik != 0)
                        {
                            ss += " . ";
                            uzunlik--;
                        }
                        ss += filesBack[i].substring(nuqtaIndex+1);
                        uzunlik = 10;
                        while (uzunlik != 0)
                        {
                            ss += " . ";
                            uzunlik--;
                        }
                        ss += fileCount + "bayt";

                        faylString[fileSoni] = "  " + ss;

                        fileSoni++;
                    }
                    royhat.setListData(faylString);
                    newFileName = filePath.getText();
//                    JOptionPane.showMessageDialog(null, faylString, "sarlavha", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    faylString[fileSoni] = filePath.getText();
                    int indexChiziq;
                    indexChiziq = filePath.getText().lastIndexOf('\\');
                    filesQisqaName[fileSoni] = filePath.getText().substring(indexChiziq + 1);
//                    JOptionPane.showMessageDialog(null, filesQisqaName[fileSoni], "sarlavha", JOptionPane.INFORMATION_MESSAGE);
                    royhat.setListData(faylString);
                    fileSoni++;
//                JOptionPane.showMessageDialog(null, filesName, "sarlavha", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        }
        );

        /**
         * event when clicked 'ziplash'
         */
        ziplash.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Ziplagich();
                            JOptionPane.showMessageDialog(null, "Muvofaqqiyatli arxivlandi !!!", "Finish", JOptionPane.INFORMATION_MESSAGE);
                            String faylString[] = new String[31];
                            royhat.setListData(faylString);
                            newFilePath.setText("Yangi fayl nomi...");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        tiklash.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            Qaytargich();
                            if(isTruePassword) {
                                JOptionPane.showMessageDialog(null, "Muvofaqqiyatli arxivdan ochildi !!!", "Finish", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Parol xato kiritildi !!!", "WARNING", JOptionPane.WARNING_MESSAGE);
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );

        /**
         * events(methods) for the interface
         * end
         */
    }

    /**
     * method for getting file or directory
     */
    private File getFileOrDirectory()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(
                JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);

        if(result == JFileChooser.CANCEL_OPTION)
            System.exit(1);

        File fileName = fileChooser.getSelectedFile();

        if((fileName == null) || (fileName.getName().equals("")))
        {
            JOptionPane.showMessageDialog(this, "File nomida xatolik",
                    "File nomida xatolik", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        return fileName;

    }

    /**
     * checking this file path is satisfied
     */
    private void analyzePath(Boolean natija){
        File name = getFileOrDirectory();
        if(name.exists())
        {
            if(natija) {
                filePath.setText(name.getAbsolutePath());
                //newFileName = name.getName();
                //filesQisqaName[fileSoni] = name.getName();
//            JOptionPane.showMessageDialog(null, newFileName, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                newFilePath.setText(name.getAbsolutePath());
                if(name.isDirectory())
                {
                    newFilePath.setText(name.getAbsolutePath() + "\\");
                }

            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, name + " mavjud emas.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * mthod for compressing file(s)
     */
    private void Ziplagich() throws IOException {
        filesName = faylString;
        for (int i = 0; i <fileSoni ; i++) {
            files[i] = new File(filesName[i]);
        }
        for (int i = 0; i < fileSoni; i++) {
            if (!files[i].exists()) {
                JOptionPane.showMessageDialog(this, files[i].getName() + " mavjud emas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }



        long data, fileCountCopy;
        long []fileCount = new long[files.length];
        byte []arr;
        int uzunlik = 1, kByteSoni, mByteSoni, gByteSoni;
        long aa, bb;

        int ee= 5, n=323;

        try{
            if(!(newFilePath.getText().equals("Yangi fayl nomi...") || newFilePath.getText() == null || newFilePath.getText().endsWith("\\")))
                newFileName = newFilePath.getText() + ".just";

            FileOutputStream output = new FileOutputStream(newFileName);
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Yangi fayl yo'lida yoki nomida xatolik bor !!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        FileInputStream input[] = new FileInputStream[files.length];
        FileOutputStream output0 = new FileOutputStream(newFileName);
        DeflaterOutputStream output=new DeflaterOutputStream(output0);
        //output.write(fileSoni);//fayllar sonini yozish
        String s = password.getText();
        for(int i = s.length(); i < 10; i++){
            s += "0";
        }
        for (int i = 0; i < 10; i++){
            output.write((int)s.charAt(i));
        }

        output.write(PowMod(fileSoni, ee, n));//fayllar sonini yozish ;




        for (int i = 0; i < fileSoni; i++) {
//            JOptionPane.showMessageDialog(this, filesQisqaName[i], "ERROR", JOptionPane.ERROR_MESSAGE);
            output.write(filesQisqaName[i].length());
            for (int j = 0; j <filesQisqaName[i].length() ; j++) {
                output.write((int)(filesQisqaName[i].charAt(j)));
            }
            //JOptionPane.showMessageDialog(this, filesQisqaName[i].charAt(1), "ERROR", JOptionPane.ERROR_MESSAGE);

            kByteSoni = 0; mByteSoni = 0; gByteSoni = 0;
            input[i] = new FileInputStream(files[i]);
            fileCount[i] = files[i].length();
            aa = fileCount[i];
            bb = fileCount[i];
            while((aa = aa / 10) != 0){
                uzunlik++;
            }
            output.write(uzunlik);
            for (int j = 0; j<uzunlik; j++){
                output.write((int) (bb%10));
                bb /= 10;
            }

            for (int t = 0; t<fileCount[i]; t++){
                /**
                int tt = 0;
                int byt = input[i].read();
                int res;
                if(byt > n){
                    tt = byt % n;
                    res = byt - tt + (int) Math.pow(tt, ee) % n;
                    output.write(res);
                }else{
                    res = (int) Math.pow(byt, ee) % n;
                    output.write(res);
                }
                 **/
                int res;
                res = input[i].read();
                //if(t <= 50)
                //{
                 //   res = PowMod(res, ee, n);
                 //   output.write(res);
                //}else{

                    output.write(res);
                //}
                //System.out.println(res);
            }
            /**
            fileCountCopy = fileCount[i];
            gByteSoni = (int)(fileCountCopy /(1024*1024*1024));
            fileCountCopy -= gByteSoni*1024*1024*1024;
            mByteSoni = (int)(fileCountCopy /(1024*1024));
            fileCountCopy -= mByteSoni*1024*1024;
            kByteSoni = (int)(fileCountCopy /(1024));
            fileCountCopy -= kByteSoni*1024;

            if(gByteSoni != 0){
                arr = new byte[1024*1024*1024];
                while (gByteSoni > 0) {
                    data = input[i].read(arr);
                    output.write(arr);
                    gByteSoni--;
                }
            }

            if(mByteSoni != 0){
                arr = new byte[1024*1024];
                while (mByteSoni > 0) {
                    data = input[i].read(arr);
                    output.write(arr);
                    mByteSoni--;
                }
            }

            if(kByteSoni != 0){
                arr = new byte[1024];
                while (kByteSoni > 0) {
                    data = input[i].read(arr);
                    output.write(arr);
                    kByteSoni--;
                }
            }
            arr = new byte[(int) fileCountCopy];
            data = input[i].read(arr);
            output.write(arr);

             **/
            input[i].close();
        }

        output.close();

    }

    public static int PowMod(int a, int pow, int mod){
        int  p = 1;
        while(pow > 0){
            p *= a;
            p %= mod;
            pow--;
        }
        return p;
    }

    //filelar sonini o'lchab ol. Hozi static turibdi
    /**
     * a method for decompressing file(s)
     */
    private void Qaytargich() throws IOException {
        long fileCount, p, fileCountCopy;
        int uzunlik, data, kByteSoni, mByteSoni, gByteSoni, faylSoni, faylQisqaNomiSoni=0;
        byte arr[];
        String faylQisqaNomi;

        int d = 173, n = 323;
        //char c[];

        try{
            if(filePath.getText().endsWith(".just"))
                newFileName = filePath.getText();
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, "Bu fayl tipini arxivdan chiqarib bo'maydi !!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

        File file = new File(newFileName);
        if (!(file.exists())) {
            return;
        }


        FileInputStream input0 = new FileInputStream(file);
        InflaterInputStream input=new InflaterInputStream(input0);

        String parol = password.getText();

        for(int i = parol.length(); i < 10; i++){
            parol += "0";
        }

        char[] s2 = new char[10];
        for (int i = 0; i < 10; i++){
            s2[i] = (char)input.read();
        }

        if(parol.equals(new String (s2))) {

            faylSoni = input.read();
            faylSoni = PowMod(faylSoni, d, n);
//        JOptionPane.showMessageDialog(this, file.getParent(), "Information", JOptionPane.INFORMATION_MESSAGE);

            FileOutputStream[] outputBack = new FileOutputStream[faylSoni];

            for (int i = 0; i < faylSoni; i++) {
                faylQisqaNomiSoni = input.read();
                char c[] = new char[faylQisqaNomiSoni];

                for (int j = 0; j < faylQisqaNomiSoni; j++) {
                    c[j] = (char) (input.read());
                }
                faylQisqaNomi = new String(c);
                filesBack[i] = faylQisqaNomi;
//            JOptionPane.showMessageDialog(null, faylQisqaNomi, "Information", JOptionPane.INFORMATION_MESSAGE);
                String s;
                s = file.getParent().endsWith("\\") ? "" : "\\";
                outputBack[i] = new FileOutputStream(file.getParent() + s + filesBack[i]);

                uzunlik = input.read();
                p = 1;
                fileCount = 0;
                for (int j = 0; j < uzunlik; j++) {
                    fileCount += p * input.read();
                    p *= 10;
                }

                for (int t = 0; t < fileCount; t++) {
                    /**int tt = 0;
                     int byt = input.read();
                     int res;
                     if(byt > n){
                     tt = byt % n;
                     res = byt - tt + (int) Math.pow(tt, d) % n;
                     outputBack[i].write(res);
                     }else{
                     res = (int) Math.pow(byt, d) % n;
                     outputBack[i].write(res);
                     }
                     **/
                    int res;
                    res = input.read();
                    //if(t <= 50)
                    //{
                    //    res = PowMod(res, d, n);
                    //    outputBack[i].write(res);
                    //}else{
                    outputBack[i].write(res);

                    //}
                    //System.out.println(res);

                }

                /**
                 fileCountCopy = fileCount;
                 gByteSoni = (int) (fileCountCopy / (1024 * 1024 * 1024));
                 fileCountCopy -= gByteSoni * 1024 * 1024 * 1024;
                 mByteSoni = (int) (fileCountCopy / (1024 * 1024));
                 fileCountCopy -= mByteSoni * 1024 * 1024;
                 kByteSoni = (int) (fileCountCopy / (1024));
                 fileCountCopy -= kByteSoni * 1024;

                 if (gByteSoni != 0) {
                 arr = new byte[1024 * 1024 * 1024];
                 while (gByteSoni > 0) {
                 data = input.read(arr);
                 outputBack[i].write(arr);
                 gByteSoni--;
                 }
                 }

                 if (mByteSoni != 0) {
                 arr = new byte[1024 * 1024];
                 while (mByteSoni > 0) {
                 data = input.read(arr);
                 outputBack[i].write(arr);
                 mByteSoni--;
                 }
                 }

                 if (kByteSoni != 0) {
                 arr = new byte[1024];
                 while (kByteSoni > 0) {
                 data = input.read(arr);
                 outputBack[i].write(arr);
                 kByteSoni--;
                 }
                 }

                 arr = new byte[(int) fileCountCopy];
                 data = input.read(arr);
                 outputBack[i].write(arr);
                 **/

                outputBack[i].flush();
                outputBack[i].close();
            }
            isTruePassword = true;
        }
        else{

            isTruePassword = false;
        }
        input.close();
    }

    /**
     * Helper method
     */
    private int Hisobla() throws IOException {
        long p, fileCountCopy;
        int uzunlik, data, kByteSoni, mByteSoni, gByteSoni, faylSoni, faylQisqaNomiSoni = 0;
        byte arr[];
        String faylQisqaNomi;

        int d = 173, n = 323;

        //char c[];

        try {
            if (filePath.getText().endsWith(".just"))
                newFileName = filePath.getText();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bu fayl tipini arxivdan chiqarib bo'maydi !!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

                File file = new File(newFileName);
//                if (!(file.exists())) {
//                    return;
//                }


        FileInputStream input0 = new FileInputStream(file);
        InflaterInputStream input=new InflaterInputStream(input0);

        String parol = password.getText();

        for(int i = parol.length(); i < 10; i++){
            parol += "0";
        }

        char[] s2 = new char[10];
        for (int i = 0; i < 10; i++){
            s2[i] = (char)input.read();
        }

        if(parol.equals(new String (s2))) {
            faylSoni = input.read();
            faylSoni = PowMod(faylSoni, d, n);
//        JOptionPane.showMessageDialog(this, file.getParent(), "Information", JOptionPane.INFORMATION_MESSAGE);

            FileOutputStream[] outputBack = new FileOutputStream[faylSoni];

            for (int i = 0; i < faylSoni; i++) {
                faylQisqaNomiSoni = input.read();
                char c[] = new char[faylQisqaNomiSoni];

                for (int j = 0; j < faylQisqaNomiSoni; j++) {
                    c[j] = (char) (input.read());
                }
                faylQisqaNomi = new String(c);
                filesBack[i] = faylQisqaNomi;
//            JOptionPane.showMessageDialog(null, faylQisqaNomi, "Information", JOptionPane.INFORMATION_MESSAGE);
                String s;
                s = file.getParent().endsWith("\\") ? "" : "\\";
                //outputBack[i] = new FileOutputStream(file.getParent() + s + filesBack[i]);

                uzunlik = input.read();
                p = 1;
                fileCount = 0;
                for (int j = 0; j < uzunlik; j++) {
                    fileCount += p * input.read();
                    p *= 10;
                }

                for (int t = 0; t < fileCount; t++) {
                    /**int tt = 0;
                     int byt = input.read();
                     int res;
                     if(byt > n){
                     tt = byt % n;
                     res = byt - tt + (int) Math.pow(tt, d) % n;
                     outputBack[i].write(res);
                     }else{
                     res = (int) Math.pow(byt, d) % n;
                     outputBack[i].write(res);
                     }
                     **/
                    int res;
                    res = input.read();
                    //if(t <= 50)
                    //{
                    //    res = PowMod(res, d, n);
                    //    outputBack[i].write(res);
                    //}else{
                    //outputBack[i].write(res);
                    //}
                    //System.out.println(res);

                }
                /**
                fileCountCopy = fileCount;
                gByteSoni = (int) (fileCountCopy / (1024 * 1024 * 1024));
                fileCountCopy -= gByteSoni * 1024 * 1024 * 1024;
                mByteSoni = (int) (fileCountCopy / (1024 * 1024));
                fileCountCopy -= mByteSoni * 1024 * 1024;
                kByteSoni = (int) (fileCountCopy / (1024));
                fileCountCopy -= kByteSoni * 1024;

                if (gByteSoni != 0) {
                    arr = new byte[1024 * 1024 * 1024];
                    while (gByteSoni > 0) {
                        data = input.read(arr);
                        //outputBack[i].write(arr);
                        gByteSoni--;
                    }
                }

                if (mByteSoni != 0) {
                    arr = new byte[1024 * 1024];
                    while (mByteSoni > 0) {
                        data = input.read(arr);
                        //outputBack[i].write(arr);
                        mByteSoni--;
                    }
                }

                if (kByteSoni != 0) {
                    arr = new byte[1024];
                    while (kByteSoni > 0) {
                        data = input.read(arr);
                        //outputBack[i].write(arr);
                        kByteSoni--;
                    }
                }
//
                arr = new byte[(int) fileCountCopy];
                data = input.read(arr);
                //outputBack[i].write(arr);
                **/

            }
            isTruePassword = true;
            input.close();

            return faylSoni;
        }
        else{
            isTruePassword = false;
            input.close();

            return -1;
        }

    }

}
