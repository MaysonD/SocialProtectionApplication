package helpers;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageConverter {

    public static String convertImageToString(Image image) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String string;
        try {
            BufferedImage originalImage = SwingFXUtils.fromFXImage(image, null);
            BufferedImage bImage = Thumbnails.of(originalImage)
                    .size(150, 200)
                    .outputQuality(0.8)
                    .asBufferedImage();

            ImageIO.write(bImage, "png", byteArrayOutputStream);
            byte[] res = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            string = Base64.encode(res);
            return string;
        } catch (IOException e) {

        } catch (NullPointerException ignored) {

        }
        return null;
    }

    public static Image convertStringToImage(String string) {
        try {
            if (string != null) {
                byte[] imageByte = new BASE64Decoder().decodeBuffer(string);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                Image image = new Image(bis);
                bis.close();
                return image;
            }
        } catch (IOException e) {

        }
        return null;
    }


}
