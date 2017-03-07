/**
 * 
 */
package com.salesmanager.core.business.modules.cms.common;

import java.io.Serializable;

import com.salesmanager.core.model.content.FileContentType;


/**
 * Abstract class for Static content data containing common attributes
 * for handling static content data.
 * 
 * @author Umesh Awasthi
 *@since 1.2
 */
public abstract class StaticContentData implements Serializable
{

   
    private static final long serialVersionUID = 1L;
    private String fileName;
    private FileContentType contentType = FileContentType.STATIC_FILE;
    private String fileContentType;
    
    public String getFileName()
    {
        return fileName;
    }
    public void setFileName( String fileName )
    {
        this.fileName = fileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }
    public void setFileContentType( String fileContentType )
    {
        this.fileContentType = fileContentType;
    }
	public void setContentType(FileContentType contentType) {
		this.contentType = contentType;
	}
	public FileContentType getContentType() {
		return contentType;
	}
    
    
}
