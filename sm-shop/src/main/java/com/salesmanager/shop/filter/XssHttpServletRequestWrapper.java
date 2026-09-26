package com.salesmanager.shop.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.salesmanager.shop.utils.SanitizeUtils;

/**
 * Cross Site Scripting filter enforcing HTML encoding of request parameters, headers, and JSON body payloads.
 *
 * Remediates CVE-2026-36766 by intercepting getInputStream() and getReader() to sanitize JSON payloads.
 *
 * @author carlsamson
 *
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(XssHttpServletRequestWrapper.class);
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private byte[] cachedBody;

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	@Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	@Override
	public String getParameter(String parameter) {
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		return cleanXSS(value);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (isMultipartRequest()) {
			return super.getInputStream();
		}
		byte[] body = getSanitizedBody();
		return new CachedServletInputStream(body);
	}

	@Override
	public BufferedReader getReader() throws IOException {
		if (isMultipartRequest()) {
			return super.getReader();
		}
		return new BufferedReader(new InputStreamReader(getInputStream(), getCharset()));
	}

	private boolean isMultipartRequest() {
		String contentType = getContentType();
		return contentType != null && contentType.toLowerCase().startsWith("multipart/");
	}

	private boolean isJsonRequest() {
		String contentType = getContentType();
		if (contentType == null) {
			return false;
		}
		String lower = contentType.toLowerCase();
		return lower.contains("application/json") || lower.contains("application/problem+json") || lower.contains("text/json");
	}

	private byte[] getSanitizedBody() throws IOException {
		if (cachedBody == null) {
			ServletInputStream is = super.getInputStream();
			if (is == null) {
				cachedBody = new byte[0];
				return cachedBody;
			}
			byte[] rawBytes = StreamUtils.copyToByteArray(is);
			if (rawBytes.length == 0 || !isJsonRequest()) {
				cachedBody = rawBytes;
			} else {
				cachedBody = sanitizeJson(rawBytes);
			}
		}
		return cachedBody;
	}

	private byte[] sanitizeJson(byte[] rawBytes) {
		try {
			Charset charset = getCharset();
			String jsonString = new String(rawBytes, charset);
			if (StringUtils.isBlank(jsonString)) {
				return rawBytes;
			}
			JsonNode rootNode = OBJECT_MAPPER.readTree(jsonString);
			if (rootNode == null) {
				return rawBytes;
			}
			JsonNode sanitizedNode = sanitizeJsonNode(rootNode);
			return OBJECT_MAPPER.writeValueAsString(sanitizedNode).getBytes(charset);
		} catch (Exception e) {
			LOGGER.warn("Failed to sanitize JSON request body for XSS: {}", e.getMessage());
			return rawBytes;
		}
	}

	private JsonNode sanitizeJsonNode(JsonNode node) {
		if (node == null) {
			return null;
		}
		if (node.isObject()) {
			ObjectNode objectNode = (ObjectNode) node;
			Iterator<Map.Entry<String, JsonNode>> fields = objectNode.fields();
			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> entry = fields.next();
				entry.setValue(sanitizeJsonNode(entry.getValue()));
			}
			return objectNode;
		} else if (node.isArray()) {
			ArrayNode arrayNode = (ArrayNode) node;
			for (int i = 0; i < arrayNode.size(); i++) {
				arrayNode.set(i, sanitizeJsonNode(arrayNode.get(i)));
			}
			return arrayNode;
		} else if (node.isTextual()) {
			String text = node.asText();
			String safeText = cleanXSS(text);
			return new TextNode(safeText);
		}
		return node;
	}

	private Charset getCharset() {
		String encoding = getCharacterEncoding();
		if (StringUtils.isNotBlank(encoding)) {
			try {
				return Charset.forName(encoding);
			} catch (Exception ignore) {
			}
		}
		return StandardCharsets.UTF_8;
	}

	private String cleanXSS(String value) {
		if (value == null) {
			return null;
		}
		return SanitizeUtils.getSafeString(value);
	}

	private static class CachedServletInputStream extends ServletInputStream {
		private final ByteArrayInputStream inputStream;

		public CachedServletInputStream(byte[] buffer) {
			this.inputStream = new ByteArrayInputStream(buffer);
		}

		@Override
		public int read() throws IOException {
			return inputStream.read();
		}

		@Override
		public boolean isFinished() {
			return inputStream.available() == 0;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
			throw new UnsupportedOperationException("Asynchronous read listeners are not supported");
		}
	}

}
