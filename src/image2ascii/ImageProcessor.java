package image2ascii;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author isurunix
 */
public class ImageProcessor {

    private BufferedImage bufferedImage;
    int width = 0;
    int height = 0;

    public ImageProcessor(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
    }

    //convert image to grayscale
    private void convrtToGray() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color color = new Color(bufferedImage.getRGB(j, i));
                int red = (int) (color.getRed() * 0.299);       
                int green = (int) (color.getGreen() * 0.587);  
                int blue = (int) (color.getBlue() * 0.114);
                
                Color grayScale = new Color(red + green + blue, red + green + blue, red + green + blue);
                bufferedImage.setRGB(j, i, grayScale.getRGB()); //set the pixel to grayscale

            }
        }
    }

    //convert grayscale image to characters
    //depending on color of each pixel
    public char[][] convertToChars() {
        char pixels[][] = new char[height][width];
        //"@%#*+=-:. " --> Black....White
        String colorDepth = "@%#*+=-:. ";
        int color = 0;

        //convert image to grayscale
        this.convrtToGray();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                //get grayscale value of the grayscale pixel
                color = bufferedImage.getRGB(j, i) & 0xFF;

                //setting the character according to the color intensity
                if (color >= 225 && color <= 255) {
                    pixels[i][j] = colorDepth.charAt(9);
                } else if (color >= 200) {
                    pixels[i][j] = colorDepth.charAt(8);
                } else if (color >= 175) {
                    pixels[i][j] = colorDepth.charAt(7);
                } else if (color >= 150) {
                    pixels[i][j] = colorDepth.charAt(6);
                } else if (color >= 125) {
                    pixels[i][j] = colorDepth.charAt(5);
                } else if (color >= 100) {
                    pixels[i][j] = colorDepth.charAt(4);
                } else if (color >= 75) {
                    pixels[i][j] = colorDepth.charAt(3);
                } else if (color >= 50) {
                    pixels[i][j] = colorDepth.charAt(2);
                } else if (color >= 25) {
                    pixels[i][j] = colorDepth.charAt(1);
                } else if (color >= 0) {
                    pixels[i][j] = colorDepth.charAt(0);
                }
            }
        }
        return pixels;
    }
}
