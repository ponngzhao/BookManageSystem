package DataBase;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.CodabarEncoder;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;
/**
 * Created by hp on 2016/6/25.
 */
public class BarCode {
    public BarCode(int userID){
        try
        {
            JBarcode localJBarcode = new JBarcode(CodabarEncoder.getInstance(), WidthCodedPainter.getInstance(),BaseLineTextPainter.getInstance());
            //生成. 欧洲商品条码(=European Article Number)
            //这里我们用作图书条码
            String str = ""+userID;
            BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
            saveToJPEG(localBufferedImage, userID+".jpg");
            localJBarcode.setEncoder(Code39Encoder.getInstance());
            localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
            localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
            localJBarcode.setShowCheckDigit(false);

        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }
    static void saveToJPEG(BufferedImage paramBufferedImage, String paramString)
    {
        saveToFile(paramBufferedImage, paramString, "jpeg");
    }

    static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2)
    {
        try
        {
            FileOutputStream localFileOutputStream = new FileOutputStream("BarCode/" + paramString1);
            ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);
            localFileOutputStream.close();
        }
        catch (Exception localException)
        {
            localException.printStackTrace();
        }
    }
}
