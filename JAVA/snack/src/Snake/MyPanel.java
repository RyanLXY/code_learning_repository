package Snake;


import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.InputStream;
import java.util.Random;

public class MyPanel extends JPanel implements KeyListener, ActionListener {

//    ImageIcon title = new ImageIcon("title.jpeg");
//    ImageIcon body = new ImageIcon("body.png");
//    ImageIcon up = new ImageIcon("up.png");
//    ImageIcon down = new ImageIcon("down.png");
//    ImageIcon left = new ImageIcon("left.png");
//    ImageIcon right = new ImageIcon("right.png");
//    ImageIcon food = new ImageIcon("food.png");
    ImageIcon title;
    ImageIcon body;
    ImageIcon up;
    ImageIcon down;
    ImageIcon left;
    ImageIcon right;
    ImageIcon food;

    int len, score;
    int[] snakex = new int[750];
    int[] snakey = new int[750];
    String fx = "R"; // R L U D
    boolean isStart = false;
    boolean isFailed = false;
    Timer timer = new Timer(100, this);
    int foodx;
    int foody;
    Random random = new Random();
    Clip bgm;



    public MyPanel(){
        loadImages();
        initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
        loadBGM();
    }

    private void loadImages() {
        InputStream is;
        try {
            is = getClass().getClassLoader().getResourceAsStream("images/title.jpeg");
            title = new ImageIcon(ImageIO.read(is));

            is = getClass().getClassLoader().getResourceAsStream("images/body.png");
            body = new ImageIcon(ImageIO.read(is));

            is = getClass().getClassLoader().getResourceAsStream("images/up.png");
            up = new ImageIcon(ImageIO.read(is));

            is = getClass().getClassLoader().getResourceAsStream("images/down.png");
            down = new ImageIcon(ImageIO.read(is));

            is = getClass().getClassLoader().getResourceAsStream("images/left.png");
            left = new ImageIcon(ImageIO.read(is));

            is = getClass().getClassLoader().getResourceAsStream("images/right.png");
            right = new ImageIcon(ImageIO.read(is));

            is = getClass().getClassLoader().getResourceAsStream("images/food.png");
            food = new ImageIcon(ImageIO.read(is));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // 解决生成jar包后wav格式音频无法播放的问题
    // https://blog.csdn.net/l903445981/article/details/104132107
    private void loadBGM() {
        try{
            bgm = AudioSystem.getClip();
//            InputStream is = getClass().getClassLoader().getResourceAsStream("bgm2.wav");
//            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
//            bgm.open(ais);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/sound/bgm2.wav"));
            bgm.open(audioInputStream);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playBGM() {
        bgm.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void stopBGM(){
        bgm.stop();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.white);
        title.paintIcon(this, g, 25, 11);

        g.fillRect(25, 75, 850, 600);
        g.setColor(Color.white);
        g.drawString("Length:" + len, 750, 35);
        g.drawString("Score:" + score, 750, 55);

//        right.paintIcon(this, g, 100, 100);
//        body.paintIcon(this, g, 75, 100);
//        body.paintIcon(this, g, 50, 100);
        switch (fx){
            case "R":right.paintIcon(this, g, snakex[0], snakey[0]); break;
            case "L":left.paintIcon(this, g, snakex[0], snakey[0]); break;
            case "U":up.paintIcon(this, g, snakex[0], snakey[0]); break;
            case "D":down.paintIcon(this, g, snakex[0], snakey[0]); break;
        }

        for (int i = 1; i < len; i++){
            body.paintIcon(this, g, snakex[i], snakey[i]);
        }

        food.paintIcon(this, g, foodx, foody);
        if (isStart == false) {
            g.setColor(Color.white);
            g.setFont(new Font("arial", Font.BOLD, 40));
            g.drawString("Press space to start", 300, 300);
        }
        if (isFailed == true) {
            g.setColor(Color.red);
            g.setFont(new Font("arial", Font.BOLD, 40));
            g.drawString("Failed: Press space to start", 300, 300);
        }
    }

    public void initSnake(){
        len = 3;
        fx = "R";
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
        foodx = 25 + 25 * random.nextInt(34);
        foody = 75 + 25 * random.nextInt(24);
        score = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE){
            if (isFailed == true){
                isFailed = false;
                initSnake();
            }else {
                isStart = !isStart;
            }
            repaint();
            if (isStart){
                playBGM();
            } else {
                stopBGM();
            }

        }else if (keyCode == KeyEvent.VK_LEFT && !fx.equals("R")){
            fx="L";
        }else if (keyCode == KeyEvent.VK_RIGHT && !fx.equals("L")){
            fx="R";
        }else if (keyCode == KeyEvent.VK_UP && !fx.equals("D")){
            fx="U";
        }else if (keyCode == KeyEvent.VK_DOWN && !fx.equals("U")){
            fx="D";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && !isFailed) {
            for (int i = len - 1; i > 0; i--) {
                snakex[i] = snakex[i - 1];
                snakey[i] = snakey[i - 1];
            }

            switch (fx){
                case "R":
                    snakex[0] = snakex[0] + 25;
                    if (snakex[0] > 850) {
//                        snakex[0] = 25;
                        isFailed = true;
                    }
                    break;
                case "L":
                    snakex[0] = snakex[0] - 25;
                    if (snakex[0] < 25) {
//                        snakex[0] = 850;
                        isFailed = true;
                    }
                    break;
                case "U":
                    snakey[0] = snakey[0] - 25;
                    if (snakey[0] < 75) {
//                        snakey[0] = 650;
                        isFailed = true;
                    }
                    break;
                case "D":
                    snakey[0] = snakey[0] + 25;
                    if (snakey[0] > 650) {
//                        snakey[0] = 75;
                        isFailed = true;
                    }
                    break;
            }

            if (snakex[0] == foodx && snakey[0] == foody){
                len++;
                score = score + 10;
                foodx = 25 + 25 * random.nextInt(34);
                foody = 75 + 25 * random.nextInt(24);
            }

            for (int i = 1; i < len; i++){
                if(snakex[i] == snakex[0] && snakey[i] == snakey[0]){
                    isFailed = true;
                }
            }
            repaint();
        }
        timer.restart();
    }
}
