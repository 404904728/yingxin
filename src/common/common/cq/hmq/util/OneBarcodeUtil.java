/*package common.cq.hmq.util;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

*//**
 * 2014-06-23
 * 
 * @author 何建
 *//*
public class OneBarcodeUtil {

	*//**
	 * 条码生成
	 * 
	 * @param str
	 *            需要生成的条码内容
	 *//*
	public static void png(String str) {
		JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),
				WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
		localJBarcode.setEncoder(Code39Encoder.getInstance());
		localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
		localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
		localJBarcode.setShowCheckDigit(false);
		BufferedImage localBufferedImage = null;
		try {
			localBufferedImage = localJBarcode.createBarcode(str);
			saveToPNG(localBufferedImage, str + ".png");
		} catch (InvalidAtributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] paramArrayOfString) {
		try {
			JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),
					WidthCodedPainter.getInstance(),
					EAN13TextPainter.getInstance());
			// 生成. 欧洲商品条码(=European Article Number)
			// 这里我们用作图书条码
			// String str = "788515004012";
			// BufferedImage localBufferedImage =
			// localJBarcode.createBarcode(str);
			// saveToGIF(localBufferedImage, "EAN13.gif");

			localJBarcode.setEncoder(Code39Encoder.getInstance());
			localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
			localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
			localJBarcode.setShowCheckDigit(false);
			// xx
			String str = "JBARCODE-39";
			BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
			saveToPNG(localBufferedImage, "Code39.png");

		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void saveToJPEG(BufferedImage paramBufferedImage,
			String paramString) {
		saveToFile(paramBufferedImage, paramString, "jpeg");
	}

	private static void saveToPNG(BufferedImage paramBufferedImage,
			String paramString) {
		saveToFile(paramBufferedImage, paramString, "png");
	}

	@SuppressWarnings("unused")
	private static void saveToGIF(BufferedImage paramBufferedImage,
			String paramString) {
		saveToFile(paramBufferedImage, paramString, "gif");
	}

	private static void saveToFile(BufferedImage paramBufferedImage,
			String name, String suff) {
		try {
			FileOutputStream localFileOutputStream = new FileOutputStream(
					"d:/upload/barcode/" + name);
			ImageUtil.encodeAndWrite(paramBufferedImage, suff,
					localFileOutputStream, 96, 96);
			localFileOutputStream.close();
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

}
*/