package com.salesmanager.core.business.utils;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductImageCropUtils {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductImageCropUtils.class);
	
	private boolean cropeable = true;

	private int cropeBaseline = 0;// o is width, 1 is height

	private int getCropeBaseline() {
		return cropeBaseline;
	}



	private double cropAreaWidth = 0;
	private double cropAreaHeight = 0;
	
	//private InputStream originalFile = null;
	private BufferedImage originalFile = null;



	public ProductImageCropUtils(BufferedImage file, int largeImageWidth, int largeImageHeight) {
		
		
	
			try {
				
			
				this.originalFile = file;
				
				/** Original Image **/
				// get original image size

				int width = originalFile.getWidth();
				int height = originalFile.getHeight();
		
				/*** determine if image can be cropped ***/
				determineCropeable(width, largeImageWidth, height, largeImageHeight);
		
				/*** determine crop area calculation baseline ***/
				//this.determineBaseline(width, height);
		
				determineCropArea(width, largeImageWidth, height, largeImageHeight);
			
			} catch (Exception e) {
				LOGGER.error("Image Utils error in constructor", e);
			}
		


		
		
		
	}
	
	
	private void determineCropeable(int width, int specificationsWidth,
			int height, int specificationsHeight) {
		/*** determine if image can be cropped ***/
		// height
		int y = height - specificationsHeight;
		// width
		int x = width - specificationsWidth;

		if (x < 0 || y < 0) {
			setCropeable(false);
		}

		if (x == 0 && y == 0) {
			setCropeable(false);
		}
		
		
		if((height % specificationsHeight) == 0 && (width % specificationsWidth) == 0 ) {
			setCropeable(false);
		}

		
		
	}


	private void determineCropArea(int width, int specificationsWidth,
			int height, int specificationsHeight) {

		cropAreaWidth = specificationsWidth;
		cropAreaHeight = specificationsHeight;
		
		
		double factorWidth = Integer.valueOf(width).doubleValue() / Integer.valueOf(specificationsWidth).doubleValue();
		double factorHeight = Integer.valueOf(height).doubleValue() / Integer.valueOf(specificationsHeight).doubleValue();

		double factor = factorWidth;
		
		if(factorWidth>factorHeight) {
			factor = factorHeight;
		}
		
		
		// crop factor
/*		double factor = 1;
		if (this.getCropeBaseline() == 0) {// width
			factor = new Integer(width).doubleValue()
					/ new Integer(specificationsWidth).doubleValue();
		} else {// height
			factor = new Integer(height).doubleValue()
					/ new Integer(specificationsHeight).doubleValue();
		}*/

		double w = factor * specificationsWidth;
		double h = factor * specificationsHeight;
		
		if(w==h) {
			setCropeable(false);
		}
		

		cropAreaWidth = w;
		
		if(cropAreaWidth > width)
			cropAreaWidth = width;
		
		cropAreaHeight = h;
		
		if(cropAreaHeight > height)
			cropAreaHeight = height;

		/*
		 * if(factor>1) { //determine croping section for(double
		 * i=factor;i>1;i--) { //multiply specifications by factor int newWidth
		 * = (int)(i * specificationsWidth); int newHeight = (int)(i *
		 * specificationsHeight); //check if new size >= original image
		 * if(width>=newWidth && height>=newHeight) { cropAreaWidth = newWidth;
		 * cropAreaHeight = newHeight; break; } } }
		 */

	}
	
	
	public File getCroppedImage(File originalFile, int x1, int y1, int width,
			int height) throws Exception {
		
		if(!this.cropeable) {
			return originalFile;
		}

		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor(originalFile.getName());
		
		String extension = contentType.substring(contentType.indexOf("/"),contentType.length());
		
		BufferedImage image = ImageIO.read(originalFile);
		BufferedImage out = image.getSubimage(x1, y1, width, height);
		File tempFile = File.createTempFile("temp", "." + extension );
		tempFile.deleteOnExit();
		ImageIO.write(out, extension, tempFile);
		return tempFile;
	}
	
	public BufferedImage getCroppedImage() throws IOException {
		

			//out if croppedArea == 0 or file is null
		


		
			Rectangle goal = new Rectangle( (int)this.getCropAreaWidth(), (int) this.getCropAreaHeight()); 
			
			//Then intersect it with the dimensions of your image:

			Rectangle clip = goal.intersection(new Rectangle(originalFile.getWidth(), originalFile.getHeight())); 
			
			//Now, clip corresponds to the portion of bi that will fit within your goal. In this case 100 x50.

			//Now get the subImage using the value of clip.


		return originalFile.getSubimage(clip.x, clip.y, clip.width, clip.height);

		
		
		
	}
	


	
	public double getCropAreaWidth() {
		return cropAreaWidth;
	}

	public void setCropAreaWidth(int cropAreaWidth) {
		this.cropAreaWidth = cropAreaWidth;
	}

	public double getCropAreaHeight() {
		return cropAreaHeight;
	}

	public void setCropAreaHeight(int cropAreaHeight) {
		this.cropAreaHeight = cropAreaHeight;
	}

	public void setCropeable(boolean cropeable) {
		this.cropeable = cropeable;
	}

	public boolean isCropeable() {
		return cropeable;
	}



}
