package launchpad.effects;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.Color;
/**
 *
 * @author Jocopa3
 */
public class TextureTest {

    //public static String location = "/res/images/terrain-atlas.tga";
    public static int scale = 3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //String file = System.getProperty("user.dir") + location;
        //BufferedImage img = new Texture(file).getImage();

        //Scale the image up
        //img = scaledImage(img, img.getWidth() * scale, img.getHeight() * scale);
        SimplexNoise noise = new SimplexNoise();
        //SimplexNoise noise1 = new SimplexNoise();
        BufferedImage img = new BufferedImage(512,512,BufferedImage.TYPE_INT_ARGB);
        
        long time = System.currentTimeMillis();
        int c, g;
        float n;
        for(int x = 0; x < img.getWidth(); x++) {
            for(int y = 0; y < img.getHeight(); y++){
                n = (noise.octaveNoise2D(2, x/500f, y/500f) + noise.octaveNoise2D(2, x/400f, y/400f));
                g = (int)(n * 127 + 127);
                c = ((255 & 0xFF) << 24) |
                ((g & 0xFF) << 16) |
                ((g & 0xFF) << 8)  |
                ((g & 0xFF) << 0);
                //int r = c >> 16 & 0xFF;
                //int g = c >> 8 & 0xFF;
                //int b = c & 0xFF;
                img.setRGB(x, y, c);
            }
        }
        
        System.out.println(System.currentTimeMillis() - time);
        
        JFrame window = new JFrame();
        window.setTitle("Simplex Noise");
        window.setBackground(java.awt.Color.green);
        window.setSize(img.getWidth() + 16, img.getHeight());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        window.add(new ImagePane(img));
    }

    //Used for scaling, not important
    public static BufferedImage scaledImage(BufferedImage image, int width, int height) {
        BufferedImage newImage = createCompatibleImage(width, height);
        Graphics graphics = newImage.createGraphics();

        graphics.drawImage(image, 0, 0, width, height, null);

        graphics.dispose();
        return newImage;
    }

    public static BufferedImage createCompatibleImage(int width, int height) {
        GraphicsConfiguration configuration = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration();
        return configuration.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

}

//This stuff used for rendering the image on the jPanel; not very important otherwise
class ImagePane extends javax.swing.JPanel {

    private BufferedImage background;

    public ImagePane(BufferedImage image) {
        background = image;
    }

    @Override
    public java.awt.Dimension getPreferredSize() {
        return background == null ? super.getPreferredSize() : new java.awt.Dimension(background.getWidth(), background.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, this);
        }
    }
}
