import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Minesweeper implements ActionListener {

    JFrame frame = new JFrame();
    ImageIcon bannerIcon = new ImageIcon("src/resource/images/banner.jpg");
    JButton bannerBtn = new JButton(bannerIcon);

    ImageIcon guessIcon = new ImageIcon("src/resource/images/guess.png");
    ImageIcon bombIcon = new ImageIcon("src/resource/images/bomb.png");
    ImageIcon failIcon = new ImageIcon("src/resource/images/fail.png");
    ImageIcon winIcon = new ImageIcon("src/resource/images/win.png");
    ImageIcon winFlagIcon = new ImageIcon("src/resource/images/win_flag.png");

    // Data Structure
    int ROW = 20;
    int COL = 20;
    int[][] data = new int[ROW][COL];
    JButton[][] btns = new JButton[ROW][COL];
    int MINECOUNT = 30;
    int MINECODE = -1;
    int unOpened = ROW*COL;
    int opened = 0;
    int seconds = 0;

    JLabel UnopenedLabel = new JLabel("Unopened: " + unOpened);
    JLabel OpenedLabel = new JLabel("Opened: "+ opened);
    JLabel TimerLabel = new JLabel("Time: " + seconds + "S");

    Timer timer = new Timer(1000, this);

    public Minesweeper(){
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        setHeader();
        addMine();
        setButtons();

        timer.start();
        frame.setVisible(true);
    }

    private void setHeader() {
//        frame.add(bannerBtn,BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints(0, 0, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),0, 0);
        panel.add(bannerBtn,constraints);
        bannerBtn.addActionListener(this);

        UnopenedLabel.setOpaque(true);
        UnopenedLabel.setBackground(Color.WHITE);
        UnopenedLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        OpenedLabel.setOpaque(true);
        OpenedLabel.setBackground(Color.WHITE);
        OpenedLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        TimerLabel.setOpaque(true);
        TimerLabel.setBackground(Color.WHITE);
        TimerLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints constraints1 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),0, 0);
        panel.add(UnopenedLabel, constraints1);

        GridBagConstraints constraints2 = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),0, 0);
        panel.add(OpenedLabel, constraints2);

        GridBagConstraints constraints3 = new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0),0, 0);
        panel.add(TimerLabel, constraints3);

        frame.add(panel, BorderLayout.NORTH);
    }

    private void addMine() {
        Random random = new Random();
        for (int i = 0; i < MINECOUNT;) {
            int r = random.nextInt(ROW);
            int c = random.nextInt(COL);
            if (data[r][c] != MINECODE){
                data[r][c] = -1;
                i++;
            }
        }

        //计算周边雷数量
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (data[i][j] == MINECODE)continue;
                int count = 0;
                if (i > 0 && j > 0 && data[i - 1][j - 1] == MINECODE) count++;
                if (i > 0 && data[i - 1][j] == MINECODE) count++;
                if (i > 0 && j < (COL - 1) && data[i - 1][j + 1] == MINECODE) count++;
                if (j > 0 && data[i][j - 1] == MINECODE) count++;
                if (j < (COL - 1) && data[i][j + 1] == MINECODE) count++;
                if (i <(ROW - 1) && j > 0 && data[i + 1][j - 1] == MINECODE) count++;
                if (i <(ROW - 1) && data[i + 1][j] == MINECODE) count++;
                if (i <(ROW - 1) && j < (COL - 1) && data[i + 1][j + 1] == MINECODE) count++;
                data[i][j] = count;
            }
        }
    }

    private void setButtons() {
        Container container = new Container();
        container.setLayout(new GridLayout(ROW, COL));

        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++) {
                JButton btn = new JButton(guessIcon);
                btn.setOpaque(true);
                btn.setBackground(new Color(244, 183, 113));
                btn.addActionListener(this);
//                JButton btn = new JButton(data[i][j]+"");
                container.add(btn);
                btns[i][j] = btn;
            }
        }

        frame.add(container,BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        new Minesweeper();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer){
            seconds ++;
            TimerLabel.setText("Time: " + seconds + "s");
            timer.start();
            return;
        }else {
            JButton btn = (JButton) e.getSource();
            if (btn.equals(bannerBtn)){
                btn.setIcon(bannerIcon);
                restart();
            }else {
                for (int i = 0; i < ROW; i++) {
                    for (int j = 0; j < COL; j++) {
                        if (btn.equals(btns[i][j])) {
                            if (data[i][j] == MINECODE) {
                                lose();
                            } else {
                                openCell(i, j);
                                chectWin();
                            }
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * 数据清零 按钮恢复状态 重启时钟
     */
    private void restart() {

        for (int i = 0; i < ROW; i++){
            for (int j = 0; j < COL; j++) {
                data[i][j] = 0;
                btns[i][j].setEnabled(true);
                btns[i][j].setBackground(new Color(244, 183, 113));
                btns[i][j].setText("");
                btns[i][j].setIcon(guessIcon);
            }
        }

        unOpened = ROW*COL;
        opened = 0;
        seconds = -1;
        JLabel UnopenedLabel = new JLabel("Unopened: " + unOpened);
        JLabel OpenedLabel = new JLabel("Opened: "+ opened);
        JLabel TimerLabel = new JLabel("Time: " + seconds + "S");

        timer.start();
        addMine();

    }

    private void lose() {
        timer.stop();
        bannerBtn.setIcon(failIcon);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btns[i][j].isEnabled()){
                    JButton btn = btns[i][j];
                    if (data[i][j] == MINECODE){
                        btn.setEnabled(false);
                        btn.setIcon(bombIcon);
                        btn.setDisabledIcon(bombIcon);
                    } else {
                        btn.setIcon(null);
                        btn.setEnabled(false);
                        btn.setOpaque(true);
                        btn.setOpaque(true);
                        btn.setText(data[i][j]+"");

                    }
                }
            }
        }
        JOptionPane.showMessageDialog(frame, "可惜你暴雷了！\n你可以点击上面的Banner重新开始", "你暴雷了!", JOptionPane.PLAIN_MESSAGE);
    }

    private void chectWin() {
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btns[i][j].isEnabled()) count++;
            }
        }
        if (count == MINECOUNT) {
            timer.stop();
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if (btns[i][j].isEnabled()) {
                        btns[i][j].setIcon(winFlagIcon);
                        btns[i][j].setEnabled(false);
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, "恭喜你赢了！\n你可以点击上面的Banner重新开始", "你赢了!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void openCell(int i, int j){

        JButton btn = btns[i][j];
        if (!btn.isEnabled()) return;

        btn.setIcon(null);
        btn.setEnabled(false);
        btn.setOpaque(true);
        btn.setBackground(Color.GREEN);
        btn.setText(data[i][j]+"");

        addOpenCount();

        if (data[i][j] == 0){
            if (i > 0 && j > 0 && data[i - 1][j - 1] == 0) openCell(i - 1, j - 1);
            if (i > 0 && data[i - 1][j] == 0) openCell(i - 1, j);
            if (i > 0 && j < (COL - 1) && data[i - 1][j + 1] == 0) openCell(i - 1, j + 1);
            if (j > 0 && data[i][j - 1] == 0) openCell(i, j - 1);
            if (j < (COL - 1) && data[i][j + 1] == 0) openCell(i, j + 1);
            if (i <(ROW - 1) && j > 0 && data[i + 1][j - 1] == 0) openCell(i + 1, j - 1);
            if (i <(ROW - 1) && data[i + 1][j] == 0) openCell(i + 1, j);
            if (i <(ROW - 1) && j < (COL - 1) && data[i + 1][j + 1] == 0) openCell(i + 1, j + 1);
        }else if (data[i][j] != MINECODE){

        }

    }

    private void addOpenCount() {
        unOpened--;
        opened++;
        UnopenedLabel.setText("Unopened: " + unOpened);
        OpenedLabel.setText("Opened: " + opened);
//        TimerLabel.setText("Unopened: " + unOpened);
    }
}
