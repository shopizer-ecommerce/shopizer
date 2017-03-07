/**
 * 
 */
package com.salesmanager.shop.admin.model.content;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * A bean class responsible for getting form data from shop Admin for uploading
 * content files for a given merchant and validating the provided data.
 * 
 * This will work as a wrapper for underlying cache where these content images will be stored
 * and retrieved in future.
 * 
 * @author Umesh Awasthi
 * @since 1.2
 *
 */
public class ContentFiles implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    
    private List<MultipartFile> file;
    
    public void setFile(List<MultipartFile> file) {
		this.file = file;
	}

	private String fileName;

    //@NotEmpty(message="{merchant.files.invalid}")
    //@Valid
    public List<MultipartFile> getFile()
    {
        return file;
    }

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

    
    
    

}
