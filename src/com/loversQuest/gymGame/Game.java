package com.loversQuest.gymGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel {

   Ball ball = new Ball(this);
   Racquet racquet = new Racquet(this);

   public Game() {
       addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {

           }

           @Override
           public void keyPressed(KeyEvent e) {
                racquet.keyReleased(e);
           }

           @Override
           public void keyReleased(KeyEvent e) {
                racquet.keyPressed(e);
           }
       });
       setFocusable(true);
   }

   private void move() {
       ball.move();
       racquet.move();
   }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);
    }

    public void gameOver() {
       JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
       System.exit(ABORT);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Agility Training Game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(300,400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
