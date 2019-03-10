package com.salesmanager.admin.components.references;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.controller.exception.AdminAuthenticationException;
import com.salesmanager.admin.controller.exception.RestTemplateException;
import com.salesmanager.admin.model.references.Country;
import com.salesmanager.admin.model.references.Currency;
import com.salesmanager.admin.model.references.Language;
import com.salesmanager.admin.model.references.MeasureEnum;
import com.salesmanager.admin.model.references.Grouo;
import com.salesmanager.admin.model.references.Reference;
import com.salesmanager.admin.model.references.Zone;
import com.salesmanager.admin.utils.Constants;


/**
 * Load references from REST api
 * 
 * @author c.samson
 *
 */
@Component
public class ReferencesLoader {

  @Value("${shopizer.api.url}")
  private String backend;

  private static final Logger logger = LoggerFactory.getLogger(ReferencesLoader.class);



  public List<Language> loadLanguages(Locale locale) throws Exception {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String resourceUrl = backend + "/languages?lang=" + locale.getLanguage();

    // Invoke web service
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<List<Language>> resp = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity,
        new ParameterizedTypeReference<List<Language>>() {});


    if (!HttpStatus.OK.equals(resp.getStatusCode())) {
      throw new AdminAuthenticationException(
          "Cannot list languages [ " + resp.getStatusCode().name() + "]");
    }



    return resp.getBody();

  }

  public List<Country> loadCountry(Locale locale) throws Exception {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String resourceUrl = backend + "/country?lang=" + locale.getLanguage();

    // Invoke web service
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<List<Country>> resp = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity,
        new ParameterizedTypeReference<List<Country>>() {});


    if (!HttpStatus.OK.equals(resp.getStatusCode())) {
      throw new AdminAuthenticationException(
          "Cannot list country [ " + resp.getStatusCode().name() + "]");
    }

    return resp.getBody();

  }

  public List<Zone> loadZones(String code, Locale locale) throws Exception {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String resourceUrl = backend + "/zones?code=" + code + "&lang=" + locale.getLanguage();

    // Invoke web service
    // RestTemplate restTemplate = new RestTemplate();
    RestTemplateBuilder rtb = new RestTemplateBuilder();
    RestTemplate restTemplate = rtb.errorHandler(new RestTemplateException()).build();

    try {

      ResponseEntity<List<Zone>> resp = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity,
          new ParameterizedTypeReference<List<Zone>>() {});


      if (!HttpStatus.OK.equals(resp.getStatusCode())) {
        throw new AdminAuthenticationException(
            "Cannot list zones [ " + resp.getStatusCode().name() + "]");
      }

      return resp.getBody();

    } catch (Exception e) {
      logger.error(e.getMessage());
      return null;
    }

  }

  public List<Currency> loadCurrency() throws Exception {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String resourceUrl = backend + "/currency";

    // Invoke web service
    RestTemplate restTemplate = new RestTemplate();



    ResponseEntity<String> resp = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity,
        new ParameterizedTypeReference<String>() {});


    if (!HttpStatus.OK.equals(resp.getStatusCode())) {
      throw new AdminAuthenticationException(
          "Cannot list currency [ " + resp.getStatusCode().name() + "]");
    }

    String body = resp.getBody();
    ObjectMapper mapper = new ObjectMapper();

    List<Map<String, Object>> map = new ArrayList<Map<String, Object>>();

    // convert JSON string to Map
    try {
      map = mapper.readValue(body, new TypeReference<List<Map<String, Object>>>() {});
    } catch (Exception e) {
      logger.error("Cannot parse currency response body " + body, e);
      return null;
    }

    List<Currency> returnList = new ArrayList<Currency>();

    for (Map curr : map) {

      Currency c = new Currency();
      c.setCode((String) curr.get("code"));
      c.setName((String) curr.get("code"));
      returnList.add(c);

    }

    return returnList;

  }

  @SuppressWarnings("unchecked")
  public Map<MeasureEnum, List<Reference>> loadMeasures() throws Exception {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String resourceUrl = backend + "/measures";

    // Invoke web service
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> resp = restTemplate.exchange(resourceUrl, HttpMethod.GET, entity,
        new ParameterizedTypeReference<String>() {});


    if (!HttpStatus.OK.equals(resp.getStatusCode())) {
      throw new AdminAuthenticationException(
          "Cannot list currency [ " + resp.getStatusCode().name() + "]");
    }

    String body = resp.getBody();
    ObjectMapper mapper = new ObjectMapper();

    Map<String, List<String>> map = new HashMap<String, List<String>>();

    // convert JSON string to Map
    try {
      map = mapper.readValue(body, new TypeReference<Map<String, List<String>>>() {});
    } catch (Exception e) {
      logger.error("Cannot parse measures response body " + body, e);
      return null;
    }


    Map<MeasureEnum, List<Reference>> refs = new HashMap<MeasureEnum, List<Reference>>();
    List<String> weights = (List<String>) map.get("weights");

    List<Reference> ws = new ArrayList<Reference>();
    for (String w : weights) {
      Reference ref = new Reference();
      ref.setCode(w);
      ws.add(ref);
    }

    refs.put(MeasureEnum.weights, ws);


    List<String> measures = (List<String>) map.get("measures");

    List<Reference> ms = new ArrayList<Reference>();
    for (String m : measures) {
      Reference ref = new Reference();
      ref.setCode(m);
      ms.add(ref);
    }

    refs.put(MeasureEnum.sizes, ms);

    return refs;

  }

  public List<Grouo> loadPermissions(Locale locale) throws Exception {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> entity = new HttpEntity<String>(headers);

    String resourceUrl = backend + "/permissions";

    // Invoke web service
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<List<Grouo>> resp = restTemplate.exchange(resourceUrl, HttpMethod.GET,
        entity, new ParameterizedTypeReference<List<Grouo>>() {});


    if (!HttpStatus.OK.equals(resp.getStatusCode())) {
      throw new AdminAuthenticationException(
          "Cannot list languages [ " + resp.getStatusCode().name() + "]");
    }



    return resp.getBody();

  }

}
