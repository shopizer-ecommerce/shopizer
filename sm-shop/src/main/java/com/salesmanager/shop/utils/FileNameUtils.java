package com.salesmanager.shop.utils;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Determines if a file name seems to be valid.
 * This utility opens the door to the validation of a file name and see if it meets the following
 * 
 * - has an extension
 * - has a name
 * - ... your own rules ...
 * @author carlsamson
 *
 */
@Component
public class FileNameUtils {
	
	public boolean validFileName(String fileName) {
		
		boolean validName = true;
		
		//has an extention
		if(StringUtils.isEmpty(FilenameUtils.getExtension(fileName))) {
			validName = false;
		}
		
		//has a filename
		if(StringUtils.isEmpty(FilenameUtils.getBaseName(fileName))) {
			validName = false;
		}
		
		return validName;
	}

}
