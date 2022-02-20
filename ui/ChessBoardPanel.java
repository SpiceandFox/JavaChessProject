package ui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.*;

public class ChessBoardPanel extends JPanel {
        Color whiteColor;
        Color blackColor;

        ChessFigureButton[] squares = new ChessFigureButton[64];
        Hashtable<Character, Icon> images = new Hashtable<>();
        ActionListener listener;

        public ChessBoardPanel(int size, Color whiteColor, Color blackColor, ActionListener listener) {
                this.whiteColor = whiteColor;
                this.blackColor = blackColor;
                this.listener = listener;
                this.setLayout(new GridLayout(8, 8));
                this.setBackground(whiteColor);
                this.setPreferredSize(new Dimension(size, size));
                createSquares();
                createImageTable();
                this.setVisible(true);
        }

        public Point getChessSquareCenterPoint(int index) {
                Point p = squares[index].getLocation();
                int buttonSize = squares[index].getHeight();
                p.x += buttonSize / 2;
                p.x += 7;
                p.y += buttonSize / 2;
                p.y += 7;
                return p;
        }

        public void setChessPiece(char key, int position) {
                squares[position].setIcon(images.get(key));
        }

        private void createImageTable() {
                Icon icon = new ImageIcon((new ImageIcon("assets/bB.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('b', icon);

                icon = new ImageIcon((new ImageIcon("assets/bK.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('k', icon);

                icon = new ImageIcon((new ImageIcon("assets/bN.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('n', icon);

                icon = new ImageIcon((new ImageIcon("assets/bP.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('p', icon);

                icon = new ImageIcon((new ImageIcon("assets/bQ.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('q', icon);

                icon = new ImageIcon((new ImageIcon("assets/bR.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('r', icon);

                icon = new ImageIcon((new ImageIcon("assets/wB.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('B', icon);

                icon = new ImageIcon((new ImageIcon("assets/wK.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('K', icon);

                icon = new ImageIcon((new ImageIcon("assets/wN.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('N', icon);

                icon = new ImageIcon((new ImageIcon("assets/wP.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('P', icon);

                icon = new ImageIcon((new ImageIcon("assets/wQ.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('Q', icon);

                icon = new ImageIcon((new ImageIcon("assets/wR.png").getImage().getScaledInstance(64, 64,
                                java.awt.Image.SCALE_SMOOTH)));
                images.put('R', icon);
        }

        private void createSquares() {
                boolean checkeredBit = false;
                for (int i = 0; i < 64; i++) {

                        // needs to flip every row, otherwise there is no
                        // checker pattern on the board
                        if (i % 8 == 0) {
                                checkeredBit = !checkeredBit;
                        }

                        ChessFigureButton button = new ChessFigureButton(i);
                        button.addActionListener(listener);

                        // ^ is the XOR operator
                        if ((i % 2 == 0) ^ (checkeredBit)) {
                                button.setBackground(whiteColor);
                        } else {
                                button.setBackground(blackColor);
                        }
                        ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                        button.setIcon(icon);
                        button.setBorderPainted(false);
                        this.add(button);

                        squares[i] = button;
                }
        }
}
