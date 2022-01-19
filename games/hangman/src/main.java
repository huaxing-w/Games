/** the main class */
public class main {
  /**
   * main calss to run the file
   *
   * @param args the args
   */
  public static void main(String[] args) {
    iModel model=new model();
    controller controller=new controller(model);
    iView view = new JFrameView("hangman");
    controller.setView(view);
  }

}
