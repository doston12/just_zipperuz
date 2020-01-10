import javax.swing.*;

/**
 * Created by JUST on 01.02.2019.
 */
public class ZiplagichMain {
    public static void main(String[] args) {
        ZiplagichInterface oyna = new ZiplagichInterface();
        oyna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        oyna.setSize(650, 400);
        oyna.setLocation(400,200);
        oyna.setVisible(true);

    }
}
