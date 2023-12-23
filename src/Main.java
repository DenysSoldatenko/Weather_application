import gui.WeatherApplication;
import javax.swing.SwingUtilities;

/**
 * The entry point for the Weather Application.
 */
public class Main {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new WeatherApplication().setVisible(true));
  }
}
