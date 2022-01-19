import javax.swing.ImageIcon;

/** The view interface of the game hangman */
public interface iView {
  /**
   * add controller to this view.
   *
   * @param feature the feature to be added.
   */
  void addFeature(feature feature);

  /**
   * Set a pic in the view
   *
   * @param toDisplay the image to be displayed.
   */
  void setPicOutPut(ImageIcon toDisplay);

  /**
   * Set the return message from model.
   *
   * @param s the return message from the model.
   */
  void setGuessOutPut(String s);

  /** Clear input. */
  void clearInput();

  /** Reset focus. */
  void resetFocus();

  /**
   * view to print the wrong guess
   *
   * @param s the string representation of the wrong guess
   */
  void setWrongGuessOut(String s);

  /**
   * view to print the guessed word
   *
   * @param s the string representation of the guessed word
   */
  void setGuessedWord(String s);
}
