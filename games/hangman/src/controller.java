import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * this is the controller class for hangman game
 */
public class controller implements feature{
  private iModel model;
  private iView view;

  /**
   * construct a controller by given model
   * @param m the model of the game
   */
  public controller(iModel m){
    this.model=m;
  }

  /**
   * set view and controller to it
   * @param v the view of the game
   */
  public void setView(iView v){
    view=v;
    view.addFeature(this);
  }

  @Override
  public void makeGuess(String s){
    String toPrint=model.makeGuess(s);
    int guessLeft=model.getGuessLeft();
    view.setGuessOutPut(toPrint);
    view.clearInput();

    String wrongGuess = model.getWrongGuess();
    view.setWrongGuessOut(wrongGuess);

    String curGuess=model.getUserCurGuess();
    view.setGuessedWord(curGuess);

    ImageIcon image;

    switch (guessLeft){
      case 0:
        URL deadPic=this.getClass().getClassLoader().getResource("dead.png");
        image=new ImageIcon(deadPic);
        view.setPicOutPut(image);
        break;
      case 1:
        URL Hangman6=this.getClass().getClassLoader().getResource("Hangman6.png");
        image=new ImageIcon(Hangman6);
        view.setPicOutPut(image);
        break;

      case 2:
        URL Hangman5=this.getClass().getClassLoader().getResource("Hangman5.png");
        image=new ImageIcon(Hangman5);
        view.setPicOutPut(image);
        break;

      case 3:
        URL Hangman4=this.getClass().getClassLoader().getResource("Hangman4.png");
        image=new ImageIcon(Hangman4);
        view.setPicOutPut(image);
        break;

      case 4:
        URL Hangman3=this.getClass().getClassLoader().getResource("Hangman3.png");
        image=new ImageIcon(Hangman3);
        view.setPicOutPut(image);
        break;

      case 5:
        URL Hangman2=this.getClass().getClassLoader().getResource("Hangman2.png");
        image=new ImageIcon(Hangman2);
        view.setPicOutPut(image);
        break;

      case 6:
        URL Hangman1=this.getClass().getClassLoader().getResource("Hangman1.png");
        image=new ImageIcon(Hangman1);
        view.setPicOutPut(image);
        break;

      case 7:
        URL Hangman0=this.getClass().getClassLoader().getResource("Hangman0.png");
        image=new ImageIcon(Hangman0);
        view.setPicOutPut(image);
        break;

    }
    view.resetFocus();




  }

  @Override
  public void startGame(){
    this.model=new model();
    URL Hangman0=this.getClass().getClassLoader().getResource("Hangman0.png");
    ImageIcon init=new ImageIcon(Hangman0);
    String curGuess=model.getUserCurGuess();

    view.setGuessedWord(curGuess);
    view.setPicOutPut(init);
    view.setWrongGuessOut(model.getWrongGuess());
    view.setGuessOutPut("Game started! make a guess!");

  }
  @Override
  public void exitGame(){
    System.exit(0);
  }

}
