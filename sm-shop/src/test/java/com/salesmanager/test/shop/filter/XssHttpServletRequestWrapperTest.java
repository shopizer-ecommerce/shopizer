package com.salesmanager.test.shop.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.shop.filter.XssHttpServletRequestWrapper;

/**
 * Unit tests for XssHttpServletRequestWrapper (CVE-2026-36766 remediation).
 */
public class XssHttpServletRequestWrapperTest {

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void testSanitizeJsonWithScriptTag() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContentType("application/json;charset=UTF-8");
		String rawJson = "{\"name\":\"<script>alert('xss')</script>John Doe\",\"age\":30}";
		request.setContent(rawJson.getBytes(StandardCharsets.UTF_8));

		XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);

		ServletInputStream inputStream = wrapper.getInputStream();
		String sanitizedJson = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		assertFalse("Sanitized JSON should not contain script tag", sanitizedJson.contains("<script>"));
		assertFalse("Sanitized JSON should not contain alert", sanitizedJson.contains("alert"));
		assertTrue("Sanitized JSON should contain legitimate text", sanitizedJson.contains("John Doe"));

		JsonNode node = objectMapper.readTree(sanitizedJson);
		assertEquals(30, node.get("age").asInt());
	}

	@Test
	public void testSanitizeNestedJsonObjectAndArray() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContentType("application/json");
		String rawJson = "{\"user\":{\"name\":\"<img src=x onerror=alert(1)>Alice\"},\"tags\":[\"<script>evil()</script>tag1\",\"clean_tag\"]}";
		request.setContent(rawJson.getBytes(StandardCharsets.UTF_8));

		XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);

		BufferedReader reader = wrapper.getReader();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		String sanitizedJson = sb.toString();

		assertFalse("Sanitized JSON should not contain onerror", sanitizedJson.contains("onerror"));
		assertFalse("Sanitized JSON should not contain script tag", sanitizedJson.contains("<script>"));
		assertTrue("Sanitized JSON should contain clean tag", sanitizedJson.contains("clean_tag"));

		JsonNode root = objectMapper.readTree(sanitizedJson);
		assertNotNull(root.get("user"));
		assertEquals(2, root.get("tags").size());
	}

	@Test
	public void testRepeatableRead() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setContentType("application/json");
		String rawJson = "{\"title\":\"Product A\"}";
		request.setContent(rawJson.getBytes(StandardCharsets.UTF_8));

		XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);

		// First read
		String firstRead = StreamUtils.copyToString(wrapper.getInputStream(), StandardCharsets.UTF_8);
		// Second read
		String secondRead = StreamUtils.copyToString(wrapper.getInputStream(), StandardCharsets.UTF_8);

		assertEquals(firstRead, secondRead);
		assertTrue(firstRead.contains("Product A"));
	}

	@Test
	public void testParameterSanitization() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("search", "<script>alert('param')</script>laptop");

		XssHttpServletRequestWrapper wrapper = new XssHttpServletRequestWrapper(request);

		String sanitizedParam = wrapper.getParameter("search");
		assertNotNull(sanitizedParam);
		assertFalse(sanitizedParam.contains("<script>"));
		assertTrue(sanitizedParam.contains("laptop"));
	}
}
