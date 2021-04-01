/*
 * Author: William Watkins
 * Project: Magic 8-Ball
 * Date: Started 12/10/2020, Finished 12/12/2020
 * Purpose: Mimic the 80's "Magic 8-Ball" toy
 **/

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;



public class magic8ball extends JFrame{

    public JTextField question;
    public JButton ask8ball;
    public JLabel askLabel;
    public JLabel answer;
    public JLabel picLabel;
    public BufferedImage m8bPic;
    public Image rescaledm8bPic;

    // answer variables
    // edit numAnswers if necessary
    public int numAnswers = 8;
    public int min = 0;

    public JLabel topline;
    public JLabel middleline;
    public JLabel bottomline;

    public String[] a;
    public String[] b;
    public String[] c;
    public int r;

    public BufferedImage m8bPic2;
    public Image rescaledm8bPic2;
    public JLabel picLabel2;

    public magic8ball () throws IOException {

        super();				// call JFrame constructor
        this.buildComponents();		// helper method to set up components
        this.buildDisplay();		// Lay out the components on the display
    }

    private void buildComponents() throws IOException {

        m8bPic = ImageIO.read(new File("magic8_primary.jpg")); // pic size: 2220 x 1248
        rescaledm8bPic = m8bPic.getScaledInstance(1110, 624, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(rescaledm8bPic));
        askLabel = new JLabel("Ask the Magic 8-Ball :    ", SwingConstants.RIGHT);
        //askLabel.setForeground(Color.RED);
        question = new JTextField(100);
        ask8ball = new JButton("Ask now!");
        answer = new JLabel("");

        ask8ball.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    m8bPic2 = ImageIO.read(new File("Magic_eight_ball.png")); // pic size: 2220 x 1248
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                rescaledm8bPic2 = m8bPic2.getScaledInstance(700, 700, Image.SCALE_SMOOTH);
                picLabel2 = new JLabel(new ImageIcon(rescaledm8bPic2));
                picLabel2.setBounds(-10, -10, 700, 700);

                topline = new JLabel("", SwingConstants.CENTER);
                middleline = new JLabel("", SwingConstants.CENTER);
                bottomline = new JLabel("", SwingConstants.CENTER);

                a = new String[]{"As I", "Ask", "Better not", "Cannot", "Concentrate and", "Don't", "It is", "It is"};
                b = new String[]{"see it", "again", "tell you", "predict", "ask", "count on", "certain", "decidedly"};
                c = new String[]{"YES", "later", "now", "now", "again", "it", "", "so"};

                r = randgen();
                topline.setText(a[r]);
                middleline.setText(b[r]);
                bottomline.setText(c[r]);

                topline.setBounds(240, 265, 200, 100);
                topline.setForeground(Color.cyan);
                topline.setFont(new Font("Arial", Font.PLAIN, 22));
                middleline.setBounds(240, 300, 200, 100);
                middleline.setForeground(Color.cyan);
                middleline.setFont(new Font("Arial", Font.PLAIN, 22));
                bottomline.setBounds(240, 335, 200, 100);
                bottomline.setForeground(Color.cyan);
                bottomline.setFont(new Font("Arial", Font.PLAIN, 22));

                JPanel answerPanel = new JPanel();
                answerPanel.setLayout(null);

                answerPanel.add(topline);
                answerPanel.add(middleline);
                answerPanel.add(bottomline);
                answerPanel.add(picLabel2);

                JFrame frame2 = new JFrame();
                frame2.add(answerPanel);
                frame2.setSize(700, 700);
                frame2.setVisible(true);
                frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

    }

    private void buildDisplay () {

        JPanel imagePanel = new JPanel();
        GridLayout griiid = new GridLayout(1,1);
        imagePanel.setLayout(griiid);

        imagePanel.add(picLabel);
        JPanel controlPanel = new JPanel();
        GridLayout grid = new GridLayout (1, 3);
        controlPanel.setLayout(grid);
        controlPanel.add(askLabel);
        controlPanel.add(question);
        controlPanel.add(ask8ball);


        // Add panels and image data component to the JFrame
        Container c = this.getContentPane();
        c.add(imagePanel, BorderLayout.CENTER);
        c.add(controlPanel, BorderLayout.SOUTH);
    }

    public int randgen(){

        // return random int from min to (numAnswers - 1)
        // this will give random indexed answer
        return min  + (int)(Math.random() * (((numAnswers - 1) - min) + 1));

    }
    public static void main(String[] args) throws IOException {

        JFrame frame = new magic8ball();
        frame.setSize(800, 650);
        frame.setVisible(true);
        frame.addWindowListener (
                new WindowAdapter () {
                    public void windowClosing ( WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }
}