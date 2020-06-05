package brickBracker;

import javax.swing.*;
public class Main {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Gameplay gameplay = new Gameplay();
        frame.setBounds(10,10, 700, 600);
        frame.setTitle("Break All Bricks");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gameplay);
        frame.setVisible(true);
    }
}
