package com.customer.fawvw.issues.utils;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.customer.fawvw.issues.exception.FawvmLoaderException;


/**
 * �����ļ�·����ȡͼƬ����,ֻ֧��JPEG��ʽ
 * 
 * @author LeslieGu
 */
public class ImageUtil {

	/**
	 * width of 1px in columns with default width in units of 1/256 of a
	 * character width
	 */
	private static final float PX_DEFAULT = 32.00f;

	/**
	 * width of 1px in columns with overridden width in units of 1/256 of a
	 * character width
	 */
	private static final float PX_MODIFIED = 36.56f;

	/**
	 * Height of 1px of a row
	 */
	private static final int PX_ROW = 15;

	public static final String LOGO_FILE_PATH = System.getenv("TPR") + "\\plugins\\Template\\images\\logo.jpg"; 
	
	public static final String RED_LIGHT_PATH = System.getenv("TPR") + "\\plugins\\Template\\images\\redLight.jpg"; 
	
	public static final String GREEN_LIGHT_PATH = System.getenv("TPR") + "\\plugins\\Template\\images\\greenLight.jpg"; 
	
	public static final String YELLOW_LIGHT_PATH = System.getenv("TPR") + "\\plugins\\Template\\images\\yellowLight.jpg"; 
	
	public static final String SINGLE_REPORT_BOTTOM = System.getenv("TPR") + "\\plugins\\Template\\images\\singleReportBottom.jpg"; 
	
	public static final String RED_LIGHT_PATH2 = System.getenv("TPR") + "\\plugins\\Template\\images\\redLightR.jpg"; 
	
	public static final String GREEN_LIGHT_PATH2 = System.getenv("TPR") + "\\plugins\\Template\\images\\greenLightR.jpg"; 
	
	public static final String YELLOW_LIGHT_PATH2 = System.getenv("TPR") + "\\plugins\\Template\\images\\yellowLightR.jpg"; 
	
	public static final String ASW = System.getenv("TPR") + "\\plugins\\Template\\images\\ASW.jpg"; 
	public static final String BMG = System.getenv("TPR") + "\\plugins\\Template\\images\\BMG.jpg"; 
	public static final String EMTAnl = System.getenv("TPR") + "\\plugins\\Template\\images\\EMTAnl.jpg"; 
	public static final String SWZTeile = System.getenv("TPR") + "\\plugins\\Template\\images\\SWZ-Teile.jpg"; 
	public static final String Note1 = System.getenv("TPR") + "\\plugins\\Template\\images\\Note1.jpg"; 
	public static final String Note3 = System.getenv("TPR") + "\\plugins\\Template\\images\\Note3.jpg"; 
	public static final String WZGErst = System.getenv("TPR") + "\\plugins\\Template\\images\\WZG-Erst.jpg"; 
	public static final String WZGVerl = System.getenv("TPR") + "\\plugins\\Template\\images\\WZG-Verl.jpg"; 
	
	public static final String TBTVFF = System.getenv("TPR") + "\\plugins\\Template\\images\\TBT_VFF.jpg"; 
	public static final String TBTPVS = System.getenv("TPR") + "\\plugins\\Template\\images\\TBT_PVS.jpg"; 
	public static final String TBT0S = System.getenv("TPR") + "\\plugins\\Template\\images\\TBT_0S.jpg"; 
	public static final String TBTSOP = System.getenv("TPR") + "\\plugins\\Template\\images\\TBT_SOP.jpg"; 
	
	public static final String VFF = System.getenv("TPR") + "\\plugins\\Template\\images\\VFF.jpg"; 
	public static final String PVS = System.getenv("TPR") + "\\plugins\\Template\\images\\PVS.jpg"; 
	public static final String OS = System.getenv("TPR") + "\\plugins\\Template\\images\\0S.jpg"; 
	public static final String SOP = System.getenv("TPR") + "\\plugins\\Template\\images\\SOP.jpg"; 
	
	public static final String RED = System.getenv("TPR") + "\\plugins\\Template\\images\\red_01.png"; 
	public static final String YELLOW = System.getenv("TPR") + "\\plugins\\Template\\images\\yellow_01.png"; 
	public static final String GREEN = System.getenv("TPR") + "\\plugins\\Template\\images\\green_01.png"; 
	public static final String WHITE = System.getenv("TPR") + "\\plugins\\Template\\images\\white.png"; 
	
	
	public static final String CAR = System.getenv("TPR") + "\\plugins\\Template\\images\\car.jpg"; 
	public static final String TOP = System.getenv("TPR") + "\\plugins\\Template\\images\\top.jpg"; 
	public static final String BOTTOM = System.getenv("TPR") + "\\plugins\\Template\\images\\bottom.jpg"; 

	public static final String RYG = System.getenv("TPR") + "\\plugins\\Template\\images\\r_y_g.jpg";
	public static final String RYW = System.getenv("TPR") + "\\plugins\\Template\\images\\r_y_w.jpg";
	public static final String RWG = System.getenv("TPR") + "\\plugins\\Template\\images\\r_w_g.jpg";
	public static final String RWW = System.getenv("TPR") + "\\plugins\\Template\\images\\r_w_w.jpg";
	public static final String WYG = System.getenv("TPR") + "\\plugins\\Template\\images\\w_y_g.jpg";
	public static final String WYW = System.getenv("TPR") + "\\plugins\\Template\\images\\w_y_w.jpg";
	public static final String WWG = System.getenv("TPR") + "\\plugins\\Template\\images\\w_w_g.jpg";
	public static final String WWW = System.getenv("TPR") + "\\plugins\\Template\\images\\w_w_w.jpg";
	
	public static byte[] readImg(File imageFile) {
		try {
			return readImg(new FileInputStream(imageFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static BufferedImage readBufferedImg(File imageFile) {
		try {
			InputStream in = new FileInputStream(imageFile);
			BufferedImage image = readBufferedImg(in);
			return image;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

	public static byte[] readImg(InputStream in) {
		return getImgValue(readBufferedImg(in));
	}

	public static BufferedImage readBufferedImg(InputStream in) {
		if (in != null) {
			try {
				return ImageIO.read(in);
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}

	public static byte[] getImgValue(BufferedImage bufferImg) {
		byte[] imgByte = null;

		if (bufferImg != null) {
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

			try {
				ImageIO.write(bufferImg, "JPEG", byteArrayOut); //$NON-NLS-1$
				imgByte = byteArrayOut.toByteArray();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				if (byteArrayOut != null) {
					try {
						byteArrayOut.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return imgByte;
	}

	public static BufferedImage BufferedImageResizeByWitdh(
			BufferedImage bufferedImage, int newWidth) {
		int width = bufferedImage.getWidth();
		int hight = bufferedImage.getHeight();

		if (width > newWidth) {
			int newHight = (int) (((hight * 1.0) / width) * newWidth);

			BufferedImage tag = new BufferedImage(newWidth, newHight,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.createGraphics();

			g.setColor(Color.white);
			g.fillRect(0, 0, newWidth, newHight);
			g.drawImage(bufferedImage, 0, 0, newWidth, newHight, null);

			return tag;
		}

		return bufferedImage;
	}

	public static void GenerateImage(HSSFWorkbook wb, HSSFSheet sheet,
			HSSFPatriarch patri, ImageCellInfo cellInfo, BufferedImage value) {
		if (value != null) {
			BufferedImage bufferedImage = (BufferedImage) value;

			byte[] imageValue = ImageUtil.getImgValue(bufferedImage);
			HSSFClientAnchor anchor = new HSSFClientAnchor();

			HSSFPicture picture = patri.createPicture(anchor, wb.addPicture(
					imageValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
			setImageCoordinateRegion(sheet, anchor, picture, cellInfo);
		}
	}

	public static BufferedImage getRYG() throws Exception {
		try {
			File image = new File(RYG);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("r_y_g.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getRYW() throws Exception {
		try {
			File image = new File(LOGO_FILE_PATH);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	public static BufferedImage getRWG() throws Exception {
		try {
			File image = new File(RWG);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	public static BufferedImage getRWW() throws Exception {
		try {
			File image = new File(RWW);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	public static BufferedImage getWYG() throws Exception {
		try {
			File image = new File(WYG);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	public static BufferedImage getWYW() throws Exception {
		try {
			File image = new File(WYW);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	public static BufferedImage getWWG() throws Exception {
		try {
			File image = new File(WWG);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	public static BufferedImage getWWW() throws Exception {
		try {
			File image = new File(WWW);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getLogoImage() throws Exception {
		try {
			File image = new File(LOGO_FILE_PATH);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("logo.jpg������"); //$NON-NLS-1$
		}
	}

	public static BufferedImage getRedLight() throws Exception {
		try {
			File image = new File(RED_LIGHT_PATH);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("redLight.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getYellowLight() throws Exception {
		try {
			File image = new File(YELLOW_LIGHT_PATH);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("yellowLight.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getGreenLight() throws Exception {
		try {
			File image = new File(GREEN_LIGHT_PATH);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("greenLight.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getBottomImage() throws Exception {
		try {
			File image = new File(SINGLE_REPORT_BOTTOM);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("singleReportBottom.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getRedLightR() throws Exception{
		try {
			File image = new File(RED_LIGHT_PATH2);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("redLightR.jpg������"); //$NON-NLS-1$
		}
		
	}
	
	public static BufferedImage getYellowLightR() throws Exception {
		try {
			File image = new File(YELLOW_LIGHT_PATH2);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("yellowLightR.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getGreenLightR() throws Exception{
		try {
			File image = new File(GREEN_LIGHT_PATH2);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("greenLightR.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getASW() throws Exception{
		try {
			File image = new File(ASW);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("ASW.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getBMG() throws Exception {
		try {
			File image = new File(BMG);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("BMG.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getEMTAnl() throws Exception {
		try {
			File image = new File(EMTAnl);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("EMTAnl.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getNote1() throws Exception {
		try {
			File image = new File(Note1);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("Note1.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getNote3() throws Exception {
		try {
			File image = new File(Note3);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("Note3.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getSWZTeile() throws Exception {
		try {
			File image = new File(SWZTeile);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("SWZTeile.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getWZGErst() throws Exception {
		try {
			File image = new File(WZGErst);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("WZGErst.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getWZGVerl() throws Exception {
		try {
			File image = new File(WZGVerl);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("WZGVerl.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getTBTVFF() throws Exception {
		try {
			File image = new File(TBTVFF);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("TBTVFF.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getTBTPVS() throws Exception {
		try {
			File image = new File(TBTPVS);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("TBTPVS.jpg������");  //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getTBT0S() throws Exception {
		try {
			File image = new File(TBT0S);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("TBT0S.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getTBTSOP() throws Exception {
		try {
			File image = new File(TBTSOP);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("TBTSOP.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getRed() throws Exception {
		try {
			File image = new File(RED);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("red_01.png������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getYellow() throws Exception {
		try {
			File image = new File(YELLOW);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("yellow_01.png������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getGreen() throws Exception {
		try {
			File image = new File(GREEN);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("green_01.png������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getWhite() throws Exception {
		try {
			File image = new File(WHITE);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("white.png������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getCarTop() throws Exception {
		try {
			File image = new File(TOP);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("top.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getCarBottom() throws Exception {
		try {
			File image = new File(BOTTOM);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("bottom.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getCarImage() throws Exception {
		try {
			File image = new File(CAR);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("car.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getVFF() throws Exception {
		try {
			File image = new File(VFF);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("VFF.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getPVS() throws Exception {
		try {
			File image = new File(PVS);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("PVS.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage get0S() throws Exception {
		try {
			File image = new File(OS);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("0S.jpg������"); //$NON-NLS-1$
		}
	}
	
	public static BufferedImage getSOP() throws Exception {
		try {
			File image = new File(SOP);
			InputStream ins = new FileInputStream(image);
			return readBufferedImg(ins);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FawvmLoaderException("SOP.jpg������"); //$NON-NLS-1$
		}
	}
	
	// ��ͼƬ��ʾ��������
	public static void setImageCoordinateRegion(HSSFSheet sheet,
			HSSFClientAnchor anchor, HSSFPicture picture, ImageCellInfo cellInfo) {

		picture.resize();
		anchor.setCol1((short) cellInfo.getColumn());
		anchor.setCol2((short) cellInfo.getToColumn());
		anchor.setRow1(cellInfo.getRow());
		anchor.setRow2(cellInfo.getToRow());
		anchor.setDx1(20);
		anchor.setDy1(20);

		double imageWidth = picture.getImageDimension().getWidth();
		double imageHeight = picture.getImageDimension().getHeight();

		float columnWidthInPixels = 0;

		for (int i = 0; i <= cellInfo.getToColumn() - cellInfo.getColumn(); i++) {
			columnWidthInPixels += getColumnWidthInPixels(sheet,
					(short) (cellInfo.getColumn() + i));
		}

		float rowHeightInPixels = 0;

		for (int j = 0; j <= cellInfo.getToRow() - cellInfo.getRow(); j++) {
			rowHeightInPixels += getRowHeightInPixels(sheet, cellInfo.getRow()
					+ j);
		}
		HSSFRow row = sheet.getRow(cellInfo.getToRow());

		if (imageWidth > columnWidthInPixels) {

			double imgWidthWantPixels = columnWidthInPixels;
			double scale = imgWidthWantPixels / imageWidth;
			double imgheightWantPixels = imageHeight * scale;

			int dx2 = (int) (imgWidthWantPixels / columnWidthInPixels * 1024);
			int dy2 = (int) (imgheightWantPixels / rowHeightInPixels * 256);

			if (imgheightWantPixels > rowHeightInPixels) {
				row.setHeight((short) (imgheightWantPixels * PX_ROW));
			}

			anchor.setDx2(dx2);
			anchor.setDy2(dy2);
		}

		if (imageWidth < columnWidthInPixels) {
			picture.resize();
			anchor.setDx1(20);
			anchor.setDy1(20);
		}

	}

	// ��ȡ�иߵ�����ֵ
	private static float getRowHeightInPixels(HSSFSheet sheet, int i) {

		HSSFRow row = sheet.getRow(i);
		float height;
		if (row != null)
			height = row.getHeight();
		else
			height = sheet.getDefaultRowHeight();

		return height / PX_ROW;
	}

	// ��ȡ�п�������ֵ
	@SuppressWarnings("deprecation")
	private static float getColumnWidthInPixels(HSSFSheet sheet, short column) {

		short cw = sheet.getColumnWidth(column);
		float px = getPixelWidth(sheet, column);

		return cw / px;
	}

	// ��ȡ��������ȵı���
	@SuppressWarnings("deprecation")
	private static float getPixelWidth(HSSFSheet sheet, short column) {

		int def = sheet.getDefaultColumnWidth() * 256;
		short cw = sheet.getColumnWidth(column);

		return cw == def ? PX_DEFAULT : PX_MODIFIED;
	}
}