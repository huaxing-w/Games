import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** this is the view class of the game hangman. */
public class JFrameView extends JFrame implements iView {
  private JLabel displayPic;
  private JLabel displayGuessReturnMessage;
  private JLabel displayWrongGuess;
  private JLabel displayGuessedWord;

  private JButton startButton;
  private JButton exitButton;

  private JButton guessButton;
  private JTextField input;

  private JPanel panel;

  /**
   * Construct a new view.
   *
   * @param caption the name of the view
   */
  public JFrameView(String caption) {
    //super the name of the view
    super(caption);
    //set the frame size
    this.setPreferredSize(new Dimension(800, 1000));
    //set the location of the frame
    setLocation(1000, 10);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    //create a new panel
    this.panel = new JPanel();
    //set layout to boxlayout
    this.panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.setBackground(Color.WHITE);
    //set the intro pic

    URL introPic=this.getClass().getClassLoader().getResource("test1.gif");
    this.displayPic = new JLabel(new ImageIcon(introPic));
    //add the pic to panel
    panel.add(displayPic);
    //set the message and add to panel
    this.displayGuessedWord = new JLabel("");
    this.displayGuessedWord.setFont(new Font("sansserif", Font.PLAIN, 40));
    panel.add(displayGuessedWord);

    this.displayWrongGuess = new JLabel("");
    this.displayWrongGuess.setFont(new Font("sansserif", Font.PLAIN, 20));
    panel.add(displayWrongGuess);

    this.displayGuessReturnMessage = new JLabel("");
    this.displayGuessReturnMessage.setFont(new Font("sansserif", Font.PLAIN, 25));
    panel.add(displayGuessReturnMessage);

    this.input = new JTextField(5);
    this.input.setMaximumSize(new Dimension(100, 20));

    this.guessButton = new JButton("Guess!");

    this.startButton = new JButton("Start the new game!");
    //set up the start button
    this.startButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startButton) {
              panel.add(input);
              panel.add(guessButton);
              int inputIndex = panel.getComponentZOrder(input);
              int guessButtonIndex = panel.getComponentZOrder(guessButton);
              int startButtonIndex = panel.getComponentZOrder(startButton);
              int exitButtonIndex = panel.getComponentZOrder(exitButton);
              panel.setComponentZOrder(input, startButtonIndex);
              panel.setComponentZOrder(guessButton, exitButtonIndex);
              panel.setComponentZOrder(startButton, inputIndex);
              panel.setComponentZOrder(exitButton, guessButtonIndex);
            }
          }
        });
    //add start button to panel
    panel.add(startButton);
    //set and add exit button to the panel
    this.exitButton = new JButton("Exit");
    panel.add(exitButton);
    //add panel to frame
    this.add(panel);
    pack();
    setVisible(true);
    validate();
  }

  @Override
  public void addFeature(feature feature) {
    exitButton.addActionListener(evt -> feature.exitGame());
    startButton.addActionListener(evt -> feature.startGame());
    guessButton.addActionListener(evt -> feature.makeGuess(input.getText()));
  }

  @Override
  public void setPicOutPut(ImageIcon toDisplay) {

    displayPic.setPreferredSize(new Dimension(50, 50));
    displayPic.setIcon(toDisplay);
  }

  @Override
  public void setGuessOutPut(String s) {
    displayGuessReturnMessage.setText(s);
  }

  @Override
  public void setWrongGuessOut(String s) {
    displayWrongGuess.setText(s);
  }

  @Override
  public void setGuessedWord(String s) {
    displayGuessedWord.setText(s);
  }

  @Override
  public void clearInput() {
    input.setText("");
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
