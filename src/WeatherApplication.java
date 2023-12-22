import static java.awt.Cursor.getPredefinedCursor;
import static java.awt.Font.BOLD;
import static java.awt.Font.PLAIN;
import static javax.swing.SwingConstants.CENTER;
import static utils.ImageLoader.loadImage;
import static utils.WeatherDataHandler.updateWeatherDisplay;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * The main GUI class for the Weather Application.
 */
public class WeatherApplication extends JFrame {

  /**
   * Constructs the Weather Application GUI.
   */
  public WeatherApplication() {
    super("Weather Application");
    setSize(450, 650);
    setLocationRelativeTo(null);
    setLayout(null);
    setResizable(false);
    addComponents();
    setIconImage(new ImageIcon("external/photos/app.png").getImage());
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  private void addComponents() {
    JTextField searchTextField = new JTextField();
    searchTextField.setBounds(15, 15, 351, 45);
    searchTextField.setFont(new Font("Dialog", PLAIN, 24));
    add(searchTextField);

    JLabel weatherConditionLabel = new JLabel(loadImage("external/photos/clouds.png"));
    weatherConditionLabel.setBounds(0, 125, 450, 217);
    add(weatherConditionLabel);

    JLabel temperatureLabel = new JLabel("10 C");
    temperatureLabel.setBounds(0, 350, 450, 54);
    temperatureLabel.setFont(new Font("Dialog", BOLD, 48));
    temperatureLabel.setHorizontalAlignment(CENTER);
    add(temperatureLabel);

    JLabel weatherConditionDesc = new JLabel("Cloudy");
    weatherConditionDesc.setBounds(0, 405, 450, 36);
    weatherConditionDesc.setFont(new Font("Dialog", PLAIN, 32));
    weatherConditionDesc.setHorizontalAlignment(CENTER);
    add(weatherConditionDesc);

    JLabel humidityLabel = new JLabel(loadImage("external/photos/humidity.png"));
    humidityLabel.setBounds(15, 500, 74, 66);
    add(humidityLabel);

    JLabel humidityText = new JLabel("<html><b>Humidity</b> 100%</html>");
    humidityText.setBounds(90, 500, 85, 55);
    humidityText.setFont(new Font("Dialog", PLAIN, 16));
    add(humidityText);

    JLabel windSpeedLabel = new JLabel(loadImage("external/photos/wind_speed.png"));
    windSpeedLabel.setBounds(220, 500, 74, 66);
    add(windSpeedLabel);

    JLabel windSpeedText = new JLabel("<html><b>Wind speed</b> 15km/h</html>");
    windSpeedText.setBounds(310, 500, 90, 55);
    windSpeedText.setFont(new Font("Dialog", PLAIN, 16));
    add(windSpeedText);

    JButton searchButton = new JButton(loadImage("external/photos/search.png"));
    searchButton.setCursor(getPredefinedCursor(Cursor.HAND_CURSOR));
    searchButton.setBounds(375, 13, 47, 45);
    searchButton.addActionListener(e -> updateWeatherDisplay(
        searchTextField.getText(), weatherConditionLabel,
        temperatureLabel, weatherConditionDesc,
        humidityText, windSpeedText)
    );
    add(searchButton);
  }
}
