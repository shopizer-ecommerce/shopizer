package com.salesmanager.web.init.data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverter {

	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	// Converts Object to XML file
	public void doMarshaling(String fileName, Object graph) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(fileName);
			marshaller.marshal(graph, new StreamResult(fos));
		} finally {
			fos.close();
		}
	}

	// Converts XML to Java Object
	public Object doUnMarshaling(String fileName) throws IOException {
		InputStream is = null;
		try {
			is=getClass().getClassLoader().getResourceAsStream("/"+fileName);
			return unmarshaller.unmarshal(new StreamSource(is));
		} finally {
			is.close();
		}
	}

}
