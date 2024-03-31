import static javax.swing.SwingUtilities.invokeLater;

import gui.WeatherApplication;

/**
 * The entry point for the Weather Application.
 */
public class Main {
  public static void main(String[] args) {
    invokeLater(() -> new WeatherApplication().setVisible(true));
  }
}
