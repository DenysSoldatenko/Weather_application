package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * A utility class to load images from files into ImageIcons.
 */
public class ImageLoader {
  private static final Logger logger = Logger.getLogger(ImageLoader.class.getName());

  /**
  * Loads an image from the specified file path and returns it as an ImageIcon.
  *
  * @param resourcePath the path to the image file to be loaded
  * @return an ImageIcon representing the loaded image, or null if loading fails
  */
  public static ImageIcon loadImage(String resourcePath) {
    try {
      BufferedImage image = ImageIO.read(new File(resourcePath));
      return new ImageIcon(image);
    } catch (IOException e) {
      logger.log(Level.WARNING, "Failed to load image: " + resourcePath, e);
      return null;
    }
  }
}
