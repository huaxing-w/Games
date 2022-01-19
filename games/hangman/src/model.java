import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/** this is the model of hangman game. */
public class model implements iModel {
  private char[] wordToGuess;
  private int guessLeft;
  private char[] userCurGuess;
  private LinkedList<Character> wrongGuess;
  private LinkedList<Character> correctGuess;

  /** construct a model of hangman game. */
  public model() {
    // the word set that will be guessed
    InputStream file = this.getClass().getClassLoader().getResourceAsStream("word list.txt");
    Scanner myScanner=new Scanner(file);
    // randomly pick a word from the word set
    int randomInt = (int) (Math.random() * 100);
    int plainWordIndex = randomInt % 54;
    int count=0;
    String word="";
    while (myScanner.hasNextLine()){
      if(count==plainWordIndex){
        word=myScanner.nextLine();
        break;
      }
      else{
        String dummy=myScanner.nextLine();
      }
      count+=1;
    }
    myScanner.close();



    // convert the string to char array
    this.wordToGuess = new char[word.length()];

    for (int i = 0; i < word.length(); i++) {
      wordToGuess[i] = word.charAt(i);
    }
    // starting chances are 7
    this.guessLeft = 7;

    // create a list to store the wrong guesses
    this.wrongGuess = new LinkedList<Character>();
    // create a list to store the correct guesses
    this.correctGuess = new LinkedList<Character>();

    // set the black word representation
    this.userCurGuess = new char[word.length()];
    for (int i = 0; i < word.length(); i++) {
      this.userCurGuess[i] = '_';
    }
  }

  @Override
  public String makeGuess(String s) {
    //if game win, ask user to start a new one
    if (isGameWin()) {
      return "You already won the game! Start a new one!";
    }
    //if game lost, ask user to start a new one
    if (isGameLost()) {
      return "You already lost the game! Start a new one!";
    }

    //test if user input is valid
    for(int i=0;i<s.length();i++){
      if(!Character.isLetter(s.charAt(i))){
        return "Please enter a valid letter or word!";
      }
    }
    String returnMessage=new String();

    //if the user input is a word
    if (s.length() > 1) {
      String wordToGuess = new String (this.wordToGuess);
      //if guess correctly, set user input to correct word
      if (s.toLowerCase().equals(wordToGuess)){
        this.userCurGuess=s.toCharArray();
      }
      else{
        //else guess left -1
        this.guessLeft-=1;
        returnMessage =  String.format("Wrong word! You have %d chances left!", this.guessLeft);

      }
    }
    else{
      char letter = s.toLowerCase().charAt(0);

      if (isInWrongGuess(letter, wrongGuess)) {
        returnMessage =
            String.format("Already guessed this letter! You have %d chances left!", this.guessLeft);
      } else if (isInCorrectGuess(letter, correctGuess)) {
        returnMessage =
            String.format("Already guessed this letter! You have %d chances left!", this.guessLeft);
      } else {
        // guessed correct
        if (isLetterInWord(letter, wordToGuess)) {
          updateUserCurGuess(letter, wordToGuess, userCurGuess);
          updateCorrectGuess(letter);
          returnMessage = String.format("Correct! You have %d chances left!", this.guessLeft);
        }
        // guessed wrong
        else {
          updateWrongGuess(letter);
          this.guessLeft -= 1;
          returnMessage = String.format("Wrong guess! You have %d chances left!", this.guessLeft);
        }
      }

    }

    if (isGameWin()) {
      return "Congratulation! You win! Start a new one!";
    }

    if (isGameLost()) {
      return "Sorry! You lose! The correct word is "
          + this.getCorrectWord()
          + " ! Start a new game!";
    }

    return returnMessage;
  }

  @Override
  public int getGuessLeft() {
    return this.guessLeft;
  }

  @Override
  public boolean isGameLost() {
    if (this.guessLeft == 0) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isGameWin() {
    for (char c : userCurGuess) {
      if (c == '_') {
        return false;
      }
    }
    return true;
  }

  @Override
  public String getUserCurGuess() {
    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < userCurGuess.length; i++) {
      ans.append(userCurGuess[i]);
      if (i != userCurGuess.length - 1) {
        ans.append(' ');
      }
    }
    return ans.toString();
  }

  @Override
  public String getWrongGuess() {
    StringBuilder ans = new StringBuilder();
    for (char c : wrongGuess) {
      ans.append(c);
      ans.append(' ');
    }
    String toReturn = "Your wrong guesses are: " + ans.toString().trim();
    return toReturn;
  }

  /**
   * update the wrong guesses list
   * @param letter the wrong letter user guessed
   */
  private void updateWrongGuess(char letter) {
    this.wrongGuess.add(letter);
  }

  /**
   * update the correct guesses list
   * @param letter the correct letter user guessed
   */
  private void updateCorrectGuess(char letter) {
    this.correctGuess.add(letter);
  }

  /**
   * update the current correct guesses
   * @param letter the correct letter user guessed
   * @param wordToGuess the word to guess
   * @param userCurGuess the user current guess
   */
  private void updateUserCurGuess(char letter, char[] wordToGuess, char[] userCurGuess) {
    for (int i = 0; i < wordToGuess.length; i++) {
      if (wordToGuess[i] == letter) {
        userCurGuess[i] = letter;
      }
    }
  }


  /**
   * test if the letter user guessed is in the word
   * @param letter the letter user guessed
   * @param wordToGuess the correct word
   * @return the true if it is in the word otherwise return false
   */
  private boolean isLetterInWord(char letter, char[] wordToGuess) {
    for (char c : wordToGuess) {
      if (c == letter) {
        return true;
      }
    }
    return false;
  }

  /**
   * test if the word user guessed is in wrong guess list
   * @param letter the letter user guessed
   * @param wrongGuess the wrong guess list
   * @return true if it is in the wrong guess list
   */
  private boolean isInWrongGuess(char letter, LinkedList wrongGuess) {
    return wrongGuess.contains(letter);
  }

  /**
   * test if the word user guessed is in correct list
   * @param letter the letter user
   * @param correctGuess the correct guess list
   * @return true if it is in the correct guess list
   */
  private boolean isInCorrectGuess(char letter, LinkedList correctGuess) {
    return correctGuess.contains(letter);
  }

  /**
   * get the correct word
   * @return string of the correct word
   */
  private String getCorrectWord() {
    StringBuilder ans = new StringBuilder();
    for (int i = 0; i < wordToGuess.length; i++) {
      ans.append(wordToGuess[i]);
    }
    return ans.toString();
  }
}
