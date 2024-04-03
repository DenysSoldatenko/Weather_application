package utils;

import static javax.imageio.ImageIO.read;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import lombok.extern.slf4j.Slf4j;

/**
 * A utility class to load images from files into ImageIcons.
 */
@Slf4j
public class ImageLoader {

  /**
  * Loads an image from the specified file path and returns it as an ImageIcon.
  *
  * @param resourcePath the path to the image file to be loaded
  * @return an ImageIcon representing the loaded image, or null if loading fails
  */
  public static ImageIcon loadImage(String resourcePath) {
    try {
      BufferedImage image = read(new File(resourcePath));
      return new ImageIcon(image);
    } catch (IOException e) {
      log.error("Failed to load image: {}", resourcePath, e);
      return null;
    }
  }
}
