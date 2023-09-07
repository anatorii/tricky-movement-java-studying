import com.sun.xml.internal.messaging.saaj.util.SAAJUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TrickyMovement extends JFrame {
    static int width = 800;
    static int height = 600;
    private int clientWidth;
    private int clientHeight;
    private BufferedImage image;
    public JLabel label;
    public JPanel panel;
    public TrickyMovement() throws IOException {
        super("tricky movement");
        initGui();
    }

    private void initGui() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(TrickyMovement.width, TrickyMovement.height));
        this.setLocation(d.width / 2 - TrickyMovement.width / 2, d.height / 2 - TrickyMovement.height / 2);
        this.getContentPane().setBackground(Color.orange);
        this.setResizable(false);

        panel = new JPanel();
        panel.setBackground(Color.lightGray);
        panel.setFocusable(true);
        panel.setLayout(null);
        this.getContentPane().add(panel);

        image = ImageIO.read(new File("object-50.jpg"));

        label = new JLabel();
        label.setIcon(new ImageIcon(image));
        label.setBounds(0, 0, image.getWidth(), image.getHeight());
        panel.add(label);

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int horShift = 0;
                int verShift = 0;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        horShift = -50;
                        break;
                    case KeyEvent.VK_RIGHT:
                        horShift = 50;
                        break;
                    case KeyEvent.VK_UP:
                        verShift = -50;
                        break;
                    case KeyEvent.VK_DOWN:
                        verShift = 50;
                        break;
                }
                int newX = label.getX() + horShift;
                int newY = label.getY() + verShift;
                if (newX < 0) {
                    newX = clientWidth - label.getWidth();
                } else if (newX >= (clientWidth - label.getWidth())) {
                    newX = 0;
                }
                if (newY < 0) {
                    newY = clientHeight - label.getHeight();
                } else if (newY >= (clientHeight - label.getHeight())) {
                    newY = 0;
                }
                label.setBounds(newX, newY, label.getWidth(), label.getHeight());
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    TrickyMovement frame = new TrickyMovement();
                    frame.pack();
                    frame.setVisible(true);

                    frame.clientWidth = TrickyMovement.width;
                    frame.clientHeight = TrickyMovement.height;
                    if (frame.isResizable()) {
                        frame.clientWidth = frame.getContentPane().getWidth();
                        frame.clientHeight = frame.getContentPane().getHeight();
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

            }
        });
    }
}
