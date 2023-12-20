import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WeatherAppGui().setVisible(true);
            // System.out.println(WeatherApp.getLocationData("Tokyo"));
            // System.out.println(WeatherApp.getCurrentTime());
        });
    }
}
