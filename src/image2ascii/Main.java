package image2ascii;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author isurunix
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String imgPath = null;
        URL imgURL = null;
        File imgFile = null;
        BufferedImage bufferedImage = null;
        
        System.out.print("Please enter the image location(URL or file path) to convert to ASCII : ");
        imgPath = scanner.nextLine();
        
        //read in the image
        try{
            
            //reading image from URL
            if(imgPath.contains("http:")){
                imgURL = new URL(imgPath);
                bufferedImage = ImageIO.read(imgURL);
                ImageIO.write(bufferedImage, "jpg", new File("download.jpg"));
            }else{
            //reading local file
                imgFile = new File(imgPath);
                bufferedImage = ImageIO.read(imgFile);
            }            
        }catch (MalformedURLException ex){
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //processing: image --> ascii art 
        ImageProcessor image = new ImageProcessor(bufferedImage);
        char[][] imagePixels = image.convertToChars();
        
        //displying ascii art
        for (int i = 0; i < image.height; i++) {
            for (int j = 0; j < image.width; j++) {
                System.out.print(imagePixels[i][j]);
            }
            System.out.println();
        }
    }
}
