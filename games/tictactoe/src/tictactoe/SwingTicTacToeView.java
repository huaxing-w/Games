package tictactoe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** The type Swing tic tac toe view. */
public class SwingTicTacToeView extends JFrame implements TicTacToeView {
  private JPanel infoPanel;
  private JPanel boardPanel;

  private JButton startNewGame;
  private JLabel message;

  private JLabel placeHolder1;
  private JLabel placeHolder2;

  private JLabel topLeft;
  private JLabel topMid;
  private JLabel topRight;

  private JLabel midLeft;
  private JLabel midMid;
  private JLabel midRight;

  private JLabel bottomLeft;
  private JLabel bottomMid;
  private JLabel bottomRight;

  /**
   * Instantiates a new Swing tic tac toe view.
   *
   * @param title the title
   * @throws IOException the io exception
   * @throws FontFormatException the font format exception
   */
  public SwingTicTacToeView(String title) throws IOException, FontFormatException {

    super(title);
    this.setPreferredSize(new Dimension(500,600));
    this.setLocation(1000,500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);

    this.infoPanel=new JPanel();
    this.infoPanel.setLayout(new FlowLayout(1,50,0));
    this.infoPanel.setBounds(0,0,500,50);

//    this.infoPanel.setBackground(Color.WHITE);
    this.infoPanel.setOpaque(false);
    this.add(infoPanel);

    InputStream newFont=this.getClass().getClassLoader().getResourceAsStream("rudiment.medium.ttf");
    Font cuteFont=Font.createFont(Font.TRUETYPE_FONT,newFont);
    Font forMessageFont=cuteFont.deriveFont(40f);
    Font forBoardFont=cuteFont.deriveFont(115f);


    this.message=new JLabel("Start a new Game!");
    this.message.setFont(forMessageFont);
    this.infoPanel.add(message);




    this.startNewGame=new JButton("New Game!");
    this.infoPanel.add(startNewGame);

    this.boardPanel=new JPanel();
    this.boardPanel.setLayout(new GridLayout(3,3));
    this.boardPanel.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
//    this.boardPanel.setBackground(Color.WHITE);
    this.boardPanel.setOpaque(false);

    this.add(boardPanel);


    this.topLeft=new JLabel();
    this.topLeft.setBorder(BorderFactory.createMatteBorder(0,0,1,1,Color.GRAY));
    this.topLeft.setText("");
    this.topLeft.setFont(forBoardFont);
    this.topLeft.setHorizontalAlignment(0);
    this.boardPanel.add(this.topLeft);

    this.topMid=new JLabel();
    this.topMid.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.GRAY));
    this.topMid.setText("");
    this.topMid.setFont(forBoardFont);
    this.topMid.setHorizontalAlignment(0);
    this.boardPanel.add(this.topMid);

    this.topRight=new JLabel();
    this.topRight.setBorder(BorderFactory.createMatteBorder(0,1,1,0,Color.GRAY));
    this.topRight.setText("");
    this.topRight.setFont(forBoardFont);
    this.topRight.setHorizontalAlignment(0);
    this.boardPanel.add(this.topRight);

    this.midLeft=new JLabel();
    this.midLeft.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.GRAY));
    this.midLeft.setText("");
    this.midLeft.setFont(forBoardFont);
    this.midLeft.setHorizontalAlignment(0);
    this.boardPanel.add(this.midLeft);

    this.midMid=new JLabel();
    this.midMid.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GRAY));
    this.midMid.setText("");
    this.midMid.setFont(forBoardFont);
    this.midMid.setHorizontalAlignment(0);
    this.boardPanel.add(this.midMid);

    this.midRight=new JLabel();
    this.midRight.setBorder(BorderFactory.createMatteBorder(1,1,1,0,Color.GRAY));
    this.midRight.setText("");
    this.midRight.setFont(forBoardFont);
    this.midRight.setHorizontalAlignment(0);
    this.boardPanel.add(this.midRight);

    this.bottomLeft=new JLabel();
    this.bottomLeft.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.GRAY));
    this.bottomLeft.setText("");
    this.bottomLeft.setFont(forBoardFont);
    this.bottomLeft.setHorizontalAlignment(0);
    this.boardPanel.add(this.bottomLeft);

    this.bottomMid=new JLabel();
    this.bottomMid.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.GRAY));
    this.bottomMid.setText("");
    this.bottomMid.setFont(forBoardFont);
    this.bottomMid.setHorizontalAlignment(0);
    this.boardPanel.add(this.bottomMid);

    this.bottomRight=new JLabel();
    this.bottomRight.setBorder(BorderFactory.createMatteBorder(1,1,0,0,Color.GRAY));
    this.bottomRight.setText("");
    this.bottomRight.setFont(forBoardFont);
    this.bottomRight.setHorizontalAlignment(0);
    this.boardPanel.add(this.bottomRight);



    pack();
    setVisible(true);
    validate();

  }
  @Override
  public void setMessage(String s){
    this.message.setText(s);

  }

  @Override
  public void addController(feature feature){
    this.startNewGame.addActionListener(evt->feature.startNewGame());

    this.topLeft.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(0,0);
      }
    });

    this.topMid.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(0,1);
      }
    });

    this.topRight.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(0,2);
      }
    });

    this.midLeft.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(1,0);
      }
    });

    this.midMid.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(1,1);
      }
    });

    this.midRight.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(1,2);
      }
    });

    this.bottomLeft.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(2,0);
      }
    });

    this.bottomMid.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(2,1);
      }
    });

    this.bottomRight.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        feature.makeNextMove(2,2);
      }
    });







  }

  @Override
  public void setMoveOut(int row,int col,String s){
    if(row==0 && col==0){
      this.topLeft.setText(s);
    }
    else if(row==0 && col==1){
      this.topMid.setText(s);
    }
    else if(row==0 && col==2){
      this.topRight.setText(s);
    }
    else if(row==1 && col==0){
      this.midLeft.setText(s);
    }
    else if(row==1 && col==1){
      this.midMid.setText(s);
    }
    else if(row==1 && col==2){
      this.midRight.setText(s);
    }
    else if(row==2 && col==0){
      this.bottomLeft.setText(s);
    }
    else if(row==2 && col==1){
      this.bottomMid.setText(s);
    }
    else if(row==2 && col==2){
      this.bottomRight.setText(s);
    }


  }

  @Override
  public void clearBoard(){
    this.topLeft.setText("");
    this.topMid.setText("");
    this.topRight.setText("");

    this.midLeft.setText("");
    this.midMid.setText("");
    this.midRight.setText("");

    this.bottomLeft.setText("");
    this.bottomMid.setText("");
    this.bottomRight.setText("");

  }


}
