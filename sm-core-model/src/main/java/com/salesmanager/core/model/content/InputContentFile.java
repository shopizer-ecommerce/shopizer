package com.salesmanager.core.model.content;

import java.io.InputStream;
import java.io.Serializable;


public class InputContentFile extends StaticContentFile implements Serializable 
{

    private static final long serialVersionUID = 1L;
   
    private InputStream file;
   
    
    public InputStream getFile()
    {
        return file;
    }
    public void setFile( InputStream file )
    {
        this.file = file;
    }
   
    
    
}