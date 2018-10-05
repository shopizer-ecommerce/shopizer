package com.salesmanager.admin.components.content;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.PatternSyntaxException;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.components.content.util.FileManagerUtils;
import com.salesmanager.admin.components.content.util.FileUtils;
import com.salesmanager.admin.components.content.util.ImageUtils;
import com.salesmanager.admin.components.content.util.StringUtils;
import com.salesmanager.admin.controller.exception.AdminAuthenticationException;
import com.salesmanager.admin.utils.Constants;


@Component
@Scope("singleton")
public class ContentManager {
	
	
    private static final Logger logger = LoggerFactory.getLogger(ContentManager.class);
    
    private final static String AUTHORIZATION = "Authorization";
    
    protected final static String CONFIG_DEFAULT_PROPERTIES = "filemanager/config.default.properties";
    protected final static String CONFIG_CUSTOM_PROPERTIES = "filemanager/config.properties";
    protected final static String LANG_FILE = "bundles/filemanager.lang.en.properties";

    protected Properties propertiesConfig = new Properties();
    protected Properties dictionnary = new Properties();
    
	@Value("${shopizer.api.url}")
	private String backend;
	
	@Value("${content.url}")
	private String contentUrl;



	protected DateFormat df;
    protected Locale locale;
    private File docRoot;
    private String basePath;
    
    @PostConstruct
    void init() {
    	try {
    		
    		initProperties();
    		initSettings();
    		
    	} catch(Exception e) {
    		logger.error("Content manager init error",e);
    	}
    }
    
/*    public LocalFileManager(Map<String,String> options, FileManagerDelegate delegate) throws FMInitializationException {
        this(null, options, delegate);
    }

    public LocalFileManager(Locale locale, FileManagerDelegate delegate) throws FMInitializationException {
        this(locale, null, delegate);
    }
    public LocalFileManager(Locale locale, Map<String,String> options, FileManagerDelegate delegate) throws FMInitializationException {

        super(locale, options);
        this.delegate = delegate;
        
        String basePath = this.getBasePath();
        if(org.apache.commons.lang3.StringUtils.isBlank((basePath))) {
        	basePath = "/";
        }
        
        docRoot = new File(basePath);

        
        //NEED DOC ROOT
        docRoot = new File(propertiesConfig.getProperty("fileRoot"));

        if (docRoot.exists() && docRoot.isFile()) {
            throw new FMInitializationException("File manager root must be a directory !");
        } else if (!docRoot.exists()) {
            //try {
                //Files.createDirectory(docRoot.toPath(), FileUtils.getPermissions755());
            //} catch(IOException e){
            //    throw new FMInitializationException("Unable the create the doc root directory: " + docRoot.getAbsolutePath(), e);
            //}
        }
    }*/

    
    void initSettings() throws Exception {}


    void initProperties() throws Exception {
        // load server properties
    	Locale locale = LocaleContextHolder.getLocale();
        InputStream tempLoadIS= null;
        try {
            // load default config
            tempLoadIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_DEFAULT_PROPERTIES);
            propertiesConfig.load(tempLoadIS);
            try { tempLoadIS.close(); } catch(IOException e){}

            // load custom config if exist
            tempLoadIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_CUSTOM_PROPERTIES);
            if(tempLoadIS != null){
                Properties customConfig = new Properties();
                customConfig.load(tempLoadIS);
                propertiesConfig.putAll(customConfig);
                try { tempLoadIS.close(); } catch(IOException e){}
            }
        } catch (IOException e) {
            throw new Exception("Config file is not found: (" + CONFIG_DEFAULT_PROPERTIES + ")", e);
        }

        try {
        	String templateFile = LANG_FILE.replace("en", locale.getLanguage());
            tempLoadIS = Thread.currentThread().getContextClassLoader().getResourceAsStream(templateFile);
            if(tempLoadIS == null){
                logger.error(String.format("Lang file for language \"%s\" not founded, loading the default file", locale.getLanguage()));
                tempLoadIS = Thread.currentThread().getContextClassLoader().getResourceAsStream("bundles/" + LANG_FILE);
            }
            dictionnary.load(tempLoadIS);
            tempLoadIS.close();
        } catch (IOException e) {
            logger.error(String.format("Dictionnary for the locale %s not found, loanding the default dic", locale.getLanguage()));
            throw new Exception("Dictionnary file is not found : (" + "bundles/" + LANG_FILE + ")", e);
        }

        try {
            df = new SimpleDateFormat(propertiesConfig.getProperty("dateFormat"));
        }catch(IllegalArgumentException e){
            logger.error("The date format is not valid - setting the default one instead : yyyy-MM-dd HH:mm:ss");
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

/*        if(options != null && !options.isEmpty()) {
            propertiesConfig.putAll(options);
        }*/
    }


    @SuppressWarnings("unchecked")
	public JSONObject getErrorResponse(String msg) {

        JSONObject errorInfo = new JSONObject();

        try {
            errorInfo.put("id", "server");
            errorInfo.put("code", "500");
            errorInfo.put("title", msg);
            //errorInfo.put("arguments", "");

        } catch (Exception e) {
            logger.error("JSONObject error");
        }
        
        org.json.simple.JSONArray arr = new org.json.simple.JSONArray();
        arr.add(errorInfo);
        
        JSONObject o = new JSONObject();
        o.put("errors", arr);
        
        return o;
    }

   public static JSONObject getDirSummary(Path path) throws IOException
    {

        final Map<String, Long> result =  new HashMap<>();
        result.put("files", 0L);
        result.put("folders", 0L);
        result.put("size", 0L);

        Files.walkFileTree(path, new SimpleFileVisitor<Path>()
        {
            
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException
            {
                result.put("files", (result.get("files")) + 1);
                result.put("size", (result.get("size")) + Files.size(file));
                return FileVisitResult.CONTINUE;
            }

            
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
            {
                result.put("files", (result.get("files")) + 1);
                return FileVisitResult.CONTINUE;
            }

            
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
            {
                if (exc == null)
                {
                    result.put("folders", (result.get("folders")) + 1);
                    return FileVisitResult.CONTINUE;
                }
                else
                {
                    // directory iteration failed; propagate exception
                    throw exc;
                }
            }
        });

        return new JSONObject(result);
    }


    public void generateResponse(HttpServletResponse response, String json)  {
        response.setStatus(200);
        response.addHeader("Content-Type", "application/json; charset=utf-8");
        try {
            response.getWriter().write(json);
        }catch(Exception e){
            logger.error("Error during the response generation", e);
        }
    }

    protected final boolean hasPermission(String action){
        return Arrays.stream(propertiesConfig.getProperty("capabilities").split(",")).anyMatch(x -> x.equals(action));
    }



    @SuppressWarnings("unchecked")
	public JSONObject actionInitiate(HttpServletRequest request) throws Exception {
        JSONObject init = new JSONObject();
        JSONObject data = new JSONObject();
        JSONObject attributes = new JSONObject();
        data.put("id", "/");
        data.put("type", "initiate");
        
        Locale locale = LocaleContextHolder.getLocale();

        JSONObject options = new JSONObject();
        String[] localeStrings = (locale.getLanguage().split("[-_]+"));
        options.put("culture", localeStrings[0]);
        options.put("charsLatinOnly", Boolean.parseBoolean(propertiesConfig.getProperty("charsLatinOnly")));
        if( propertiesConfig.getProperty("capabilities") != null ){
            options.put("capabilities", propertiesConfig.getProperty("capabilities"));
        } else{
            options.put("capabilities", false);
        }
        options.put("fileServeUrl", this.contentUrl);
        options.put("allowFolderDownload", Boolean.parseBoolean(propertiesConfig.getProperty("allowFolderDownload")));

        JSONObject security = new JSONObject();
        security.put("allowNoExtension", Boolean.parseBoolean(propertiesConfig.getProperty("allowNoExtension")));
        
        String[] restr = propertiesConfig.getProperty("editRestrictions").split(",");
        List<String> restrList = Arrays.asList(restr); 
        JSONArray restrArray = new JSONArray();
        restrArray.addAll(restrList);
        security.put("editRestrictions", restrArray);

        JSONObject upload = new JSONObject();
        try {
            upload.put("fileSizeLimit", Long.parseLong(propertiesConfig.getProperty("upload_fileSizeLimit")));
        }catch (NumberFormatException e){
            logger.error("fileSizeLimit -> Format Exception", e);
        }
        upload.put("policy", propertiesConfig.getProperty("upload_policy"));
        String[] urestr = propertiesConfig.getProperty("upload_restrictions").split(",");
        List<String> uRestrList = Arrays.asList(urestr); 
        JSONArray uRestrArray = new JSONArray();
        uRestrArray.addAll(uRestrList);
        upload.put("restrictions", uRestrArray);

        JSONObject sharedConfig = new JSONObject();
        sharedConfig.put("options", options);
        sharedConfig.put("security", security);
        sharedConfig.put("upload", upload);
        attributes.put("config", sharedConfig);
        

        data.put("attributes", attributes);
        init.put("data", data);
        return init;
    }


    public String getPath(HttpServletRequest request, String parameterName){
        if(request == null) throw new IllegalArgumentException("Request is null");
        String path = request.getParameter(parameterName);
        if(path == null) throw new IllegalArgumentException("Path is null");
        if(path.contains("http:") || path.contains("https:")) {
        	return path;
        } else {
        	return path.replace("//", "/").replace("..", "");
        }
    };

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected Map getFileModel(){

        Map model = new HashMap();
        Map modelAttribute = new HashMap();

        model.put("id", "");
        model.put("type", "file");

        modelAttribute.put("name", "");
        modelAttribute.put("extension", "");
        modelAttribute.put("path", "");
        modelAttribute.put("readable", 1);
        modelAttribute.put("writable", 1);
        modelAttribute.put("created", "");
        modelAttribute.put("modified", "");
        modelAttribute.put("timestamp", "");
        modelAttribute.put("height", 0);
        modelAttribute.put("width", 0 );
        modelAttribute.put("size", 0);

        model.put("attributes", modelAttribute);

        return model;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	protected Map getFolderModel(){

        Map model = new HashMap();
        Map modelAttribute = new HashMap();

        model.put("id", "");
        model.put("type", "folder");

        modelAttribute.put("name", "");
        modelAttribute.put("path", "");
        modelAttribute.put("readable", 1);
        modelAttribute.put("writable", 1);
        modelAttribute.put("created", "");
        modelAttribute.put("modified", "");

        model.put("attributes", modelAttribute);

        return model;
    }


    protected final boolean isAllowedImageExt(String ext){
        return Arrays.stream(propertiesConfig.getProperty("outputFilter_images").split(",")).anyMatch(x -> x.equals(ext));
    }

    protected final boolean isAllowedFileType(String file){
        String extension = FileUtils.getExtension(file);

        // no extension
        if(StringUtils.isEmpty(extension)){
            return Boolean.parseBoolean(propertiesConfig.getProperty("allowNoExtension"));
        }

        String[] uploadRestrictions = propertiesConfig.getProperty("upload_restrictions").split(",");
        String uploadPolicy = propertiesConfig.getProperty("upload_policy").toLowerCase();

        if(uploadPolicy.equals("DISALLOW_ALL")){
            return Arrays.stream(uploadRestrictions).anyMatch(x -> x.equals(extension));
        }

        if(uploadPolicy.equals("ALLOW_ALL")){
            return !Arrays.stream(uploadRestrictions).anyMatch(x -> x.equals(extension));
        }

        return true;
    }

    protected final boolean isEditable(String name) {
        String ext = FileUtils.getExtension(name);
        return Arrays.stream(propertiesConfig.getProperty("editRestrictions").split(",")).anyMatch(x -> x.equals(ext));
    }

    protected final boolean isAllowedName(String name, boolean isDir) throws Exception{

        if(isDir){

            boolean excluded_dir = Arrays.stream(propertiesConfig.getProperty("excluded_dirs").split(",")).anyMatch(x -> x.equals(name));
            boolean excluded_regex_dir = false;
            try {
                excluded_regex_dir = name.matches(propertiesConfig.getProperty("excluded_dirs_REGEXP"));
            }catch (PatternSyntaxException e){
                throw new Exception("Regex Dir Syntax Exception : " + propertiesConfig.getProperty("excluded_dirs_REGEXP") , e);
            }
            return !(excluded_dir || excluded_regex_dir);
        }
        else {
            boolean excluded_file = Arrays.stream(propertiesConfig.getProperty("excluded_files").split(",")).anyMatch(x -> x.equals(name));
            boolean excluded_regex_file = false;
            try {
                excluded_regex_file = name.matches(propertiesConfig.getProperty("excluded_files_REGEXP"));
            }catch (PatternSyntaxException e){
                throw new Exception("Regex File Syntax Exception : " + propertiesConfig.getProperty("excluded_files_REGEXP") , e);
            }
            return !(excluded_file || excluded_regex_file);
        }
    }

    public final BufferedImage generateThumbnail(BufferedImage source) {
        return Scalr.resize(source, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_WIDTH, Integer.parseInt(propertiesConfig.getProperty("image_thumbnail_maxWidth")), Integer.parseInt(propertiesConfig.getProperty("image_thumbnail_maxHeight")), Scalr.OP_ANTIALIAS);
    }
    

    public Properties getProperties() {
		return dictionnary;
	}	
	
	//private IFileManager fileManager;

    public final void handleRequest(HttpServletRequest request, HttpServletResponse response) {

        //baseUrl = ServletUtils.getBaseUrl(request);

        final String method = request.getMethod();
        final String mode = request.getParameter("mode");

        JSONObject responseData = null;
        response.setStatus(200);

        try {
            if (StringUtils.isEmpty("mode")) {
            	generateResponse(response, getErrorResponse(getProperties().getProperty("MODE_ERROR")).toString());
                return;
            }

            if (method.equals("GET")) {
                switch (mode) {
                    default:
                        responseData = getErrorResponse(getProperties().getProperty("MODE_ERROR"));
                        break;
                    case "initiate":
                        responseData = actionInitiate(request);
                        break;
                    case "getfile":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionGetFile(request);
                        }
                        break;
                    case "getfolder":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionGetFolder(request);
                        }
                        break;
                    case "rename":
                        if (!StringUtils.isEmpty(request.getParameter("old")) && !StringUtils.isEmpty(request.getParameter("new"))) {
                            responseData = actionRename(request);
                        }
                        break;
                    case "copy":
                        if (!StringUtils.isEmpty(request.getParameter("source")) && !StringUtils.isEmpty(request.getParameter("target"))) {
                            responseData = actionCopy(request);
                        }
                        break;
                    case "move":
                        if (!StringUtils.isEmpty(request.getParameter("old")) && !StringUtils.isEmpty(request.getParameter("new"))) {
                            responseData = actionMove(request);
                        }
                        break;
                    case "editfile":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionEditFile(request);
                        }
                        break;
                    case "delete":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionDelete(request);
                        }
                        break;
                    case "addfolder":
                        if (!StringUtils.isEmpty(request.getParameter("path")) &&
                                !StringUtils.isEmpty(request.getParameter("name"))) {
                            responseData = actionAddFolder(request);
                        }
                        break;
                    case "download":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionDownload(request, response);
                        }
                        break;
                    case "getimage":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            Boolean thumbnail = Boolean.parseBoolean(request.getParameter("thumbnail"));
                            responseData = actionGetImage(request, response, thumbnail);
                        }
                        break;
                    case "readfile":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionReadFile(request, response);
                        }
                        break;
                    case "summarize":
                        responseData = actionSummarize();
                        break;
                }
            } else if (method.equals("POST")) {
                switch (mode) {

                    default:
                        responseData = getErrorResponse(getProperties().getProperty("MODE_ERROR"));
                        break;
                    case "upload":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionUpload(request);
                        }
                        break;
                    case "replace":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionReplace(request);
                        }
                        break;
                    case "savefile":
                        if (!StringUtils.isEmpty(request.getParameter("path"))) {
                            responseData = actionSaveFile(request);
                        }
                        break;
                }
            }
        } catch(Exception e){
        	logger.error(e.getMessage(), e);
        	generateResponse(response, getErrorResponse(getProperties().getProperty("ERROR_SERVER")).toString());
        }

        if (responseData != null) {
        	//generateResponse(response, responseData.toString());
        	String converted = responseData.toJSONString();
        	
        	
        	generateResponse(response, converted);
        }
    }
	

    @SuppressWarnings("unchecked")
	public JSONObject actionGetFolder(HttpServletRequest request) throws Exception {
    	

    	String path = getPath(request, "path");
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
		try {
			
			path = URLEncoder.encode(path, "UTF-8");
			
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        HttpEntity<String> entity = new HttpEntity<String>(headers);
	        
	        String contentResourceUrl
	        = backend + "/content/folder?path=" + path;
	        
	        RestTemplate restTemplate = new RestTemplate();

	        ResponseEntity<String> resp = null;
	        
	        resp = restTemplate.exchange(contentResourceUrl, HttpMethod.GET, entity, String.class);
	        
		    if(!HttpStatus.OK.equals(resp.getStatusCode())) {
		        throw new Exception("Cannot get folder for this path [ " + path + "]");
		    }
		    
		    String body = resp.getBody();
		    Map<String, Object> map = new HashMap<String, Object>();

			 // convert JSON string to Map
			 try {
					map = mapper.readValue(body, new TypeReference<Map<String, Object>>(){});
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("Cannot parse get folder response body " + body, e);
					throw new Exception("Cannot get folder, response parsing problem [ " + body + "]");
			  }
			 
			 List<Map<String,String>> files = (List<Map<String,String>>)map.get("content");


			JSONArray array = new JSONArray();
			
			//images from CMS
			if(CollectionUtils.isNotEmpty(files)) {

				for(Map m : files) {
					String p = (String) m.get("path");
					String name = (String) m.get("name");
					Map fileInfo = getFileInfo(p,name);
					array.add(fileInfo);
				}
			}

			JSONObject o = new JSONObject();
			o.put("data", array);
			return o;
		
        } catch(HttpClientErrorException e) {
        	if(HttpStatus.FORBIDDEN.name().equals(e.getStatusCode().name())) {
        		throw new AdminAuthenticationException("Cannot authenticate this client [Forbidden]",e.getStatusCode());
        	}
        	if(HttpStatus.NOT_FOUND.name().equals(e.getStatusCode().name())) {
        		throw new AdminAuthenticationException("Cannot authenticate this client [Not found]",e.getStatusCode());
        	}
        	logger.error("Error during authentication [" + e.getMessage() + "] [" + e.getStatusCode().name() + "]" );
        	return getErrorResponse(String.format(getProperties().getProperty("ERROR_SERVER"), e.getMessage()));
        	
		} catch (Exception e) {
			logger.error(e.getMessage());
			return getErrorResponse(String.format(getProperties().getProperty("ERROR_SERVER"), e.getMessage()));
		}
		
		/*

        String path = getPath(request, "path");
        String type = request.getParameter("type");
        
        
        
        File dir = getFile(path);

        if(!dir.exists()){
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        if (!dir.isDirectory()) {
            return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_NOT_EXIST"), path));
        }

        if (!dir.canRead()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        String[] files;
        try {
            files = dir.list();
        } catch (SecurityException e) {
            logger.error("Reading directory's files: " + dir.getName(), e);
            return getErrorResponse(String.format(dictionnary.getProperty("UNABLE_TO_OPEN_DIRECTORY"), path));
        }

        String filePath;
        File file = null;

        org.json.simple.JSONArray array = new org.json.simple.JSONArray();
        //JSONObject array = new JSONObject();
        if(files!= null) {
            for (int i = 0; i < files.length; i++) {

                file = new File(docRoot.getPath() + path + files[i]);
                filePath = path + files[i];
                String filename = file.getName();
                if (file.isDirectory()) {
                    if (isAllowedName(filename, file.isDirectory())) {
                        //array.put(getFileInfo(filePath + "/"));
                    	array.add(new JSONObject(getFileInfo(filePath + "/")));
                    }
                } else if (isAllowedName(filename, file.isDirectory())) {
                    if (type == null || type.equals("images") && isAllowedImageExt(FileUtils.getExtension(filename))) {
                        //array.put(getFileInfo(filePath));
                    	array.add(new JSONObject(getFileInfo(filePath)));
                    }
                }
            }
        }
*/        


        //return new JSONObject().put("data", array);
    }

    @SuppressWarnings("unchecked")
	public JSONObject actionGetFile(HttpServletRequest request) throws Exception {
        String path =  getPath(request, "path");

        File file = new File(docRoot.getPath() + path);

        if (file.isDirectory()) {
            return getErrorResponse(dictionnary.getProperty("FORBIDDEN_ACTION_DIR"));
        }

        // check if the name is not in "excluded" list
        String filename = file.getName();

        if (!isAllowedName(filename, file.isDirectory())) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        // check if file is readable
        if (!file.canRead()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }
        
        
        JSONObject ret = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(path));
        ret.put("data", o);
        return ret;


        //return (JSONObject) new JSONObject().put("data", new JSONObject(getFileInfo(path)));
    }

    public JSONObject actionGetImage(HttpServletRequest request, HttpServletResponse response, Boolean thumbnail) throws Exception {
        

        InputStream is = null;
        String path = getPath(request, "path");
        
        //NEED CONTENT META (http, local, aws...)

        String urlBuilder = new StringBuilder().append(this.contentUrl).append(path).toString();

        try {

        	URL url = new URL(urlBuilder.toString());
         
            String fileName = FilenameUtils.getName(url.getPath());
            String encodedName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String fullPath = FilenameUtils.getFullPath(url.getPath());
            String queryUrlString = new StringBuilder()
            .append(fullPath).append(encodedName).toString();
            URL queryUrl = null;
            if(!StringUtils.isEmpty(this.contentUrl)) {
            	queryUrl = new URL(this.contentUrl + queryUrlString);
            } else {
            	queryUrl = url;
            }
 
            File file = new File(FilenameUtils.getName(url.getPath()));

        	
			org.apache.commons.io.FileUtils.copyURLToFile(queryUrl, file);

            String filename = file.getName();
            String fileExt = filename.substring(filename.lastIndexOf(".") + 1);
            String mimeType = (!StringUtils.isEmpty(FileUtils.getExtension(fileExt))) ? FileManagerUtils.getMimeTypeByExt(fileExt) : "application/octet-stream";
            long fileSize = file.length();

            is = new FileInputStream(file);

            response.setContentType(mimeType);
            response.setHeader("Content-Length", Long.toString(fileSize));
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

            FileUtils.copy(new BufferedInputStream(is), response.getOutputStream());
        } catch (IOException e) {
            throw new Exception("Error serving image: ", e);
        }
       
        return null;
    	
    }


    @SuppressWarnings("unchecked")
	public JSONObject actionMove(HttpServletRequest request) throws Exception {
        String sourcePath = getPath(request, "old");
        String targetPath = getPath(request, "new");

        // security check
        if (!targetPath.startsWith("/")) targetPath = "/" + targetPath;
        if (!targetPath.endsWith("/")) targetPath += "/";

        File sourceFile = getFile(sourcePath);
        String filename = sourceFile.getName();
        File targetDir = getFile(targetPath);
        File targetFile = getFile(targetPath + "/" + filename);

        String finalPath = targetPath + filename + (sourceFile.isDirectory() ? "/" : "");

        if (!hasPermission("move")) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }
        if (!targetDir.exists() || !targetDir.isDirectory()) {
            return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_NOT_EXIST"), targetPath));
        }
        // check system permission
        if (!sourceFile.canRead() && !targetDir.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }
        // check if not requesting main FM userfiles folder
        if (sourceFile.equals(docRoot)) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }
        // check if name are not excluded
        if (!isAllowedName(targetFile.getName(), false)) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }
        // check if file already exists
        if (targetFile.exists()) {
            if (targetFile.isDirectory()) {
                return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_ALREADY_EXISTS"), targetFile.getName()));
            } else {
                return getErrorResponse(String.format(dictionnary.getProperty("FILE_ALREADY_EXISTS"), targetFile.getName()));
            }
        }

        try {
            Files.move(sourceFile.toPath(), targetFile.toPath());
            File thumbnailFile = new File(getThumbnailPath(sourcePath));
            if (thumbnailFile.exists()) {
                if (thumbnailFile.isFile()) {
                    thumbnailFile.delete();
                } else {
                    FileUtils.removeDirectory(thumbnailFile.toPath());
                }
            }
        } catch (IOException e) {
            if (sourceFile.isDirectory()) {
                return getErrorResponse(String.format(dictionnary.getProperty("ERROR_MOVING_DIRECTORY"), sourceFile.getName(), targetPath));
            } else {
                return getErrorResponse(String.format(dictionnary.getProperty("ERROR_MOVING_FILE"), sourceFile.getName(), targetPath));
            }

        }
        
        JSONObject ret = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(finalPath));
        ret.put("data", o);
        return ret;

        //return new JSONObject().put("data", new JSONObject(getFileInfo(finalPath)));
    }


    @SuppressWarnings("unchecked")
	public JSONObject actionRename(HttpServletRequest request) throws Exception {

        String sourcePath = getPath(request, "old");
        if (sourcePath.endsWith("/")) {
            sourcePath = sourcePath.substring(0, (sourcePath.length() - 1));
        }

        String targetName = StringUtils.normalize(request.getParameter("new"));

        // get the path
        int pathPos = sourcePath.lastIndexOf("/");
        String path = sourcePath.substring(0, pathPos + 1);
        String targetPath = path + targetName;

        File sourceFile = getFile(sourcePath);
        File fileTo = getFile(targetPath);

        String filename = sourceFile.getName();

        if (!hasPermission("rename")) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if (!sourceFile.exists()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        // check if file is writable
        if (!sourceFile.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        // check if not requesting main FM userfiles folder
        if (sourceFile.equals(docRoot)) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if (!sourceFile.isDirectory()) {
            if (!isAllowedFileType(targetName)) {
                return getErrorResponse(dictionnary.getProperty("INVALID_FILE_TYPE"));
            }
        }

        if (!isAllowedName(targetName, false)) {
            return getErrorResponse(String.format(dictionnary.getProperty("FORBIDDEN_NAME"), targetName));
        }

        if (fileTo.exists()) {
            if (fileTo.isDirectory()) {
                return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_ALREADY_EXISTS"), targetName));
            } else { // fileTo.isFile
                return getErrorResponse(String.format(dictionnary.getProperty("FILE_ALREADY_EXISTS"), targetName));
            }
        } else if (!sourceFile.renameTo(fileTo)) {

            if (sourceFile.isDirectory()) {
                return getErrorResponse(String.format(dictionnary.getProperty("ERROR_RENAMING_DIRECTORY"), filename, targetName));
            } else {
                return getErrorResponse(String.format(dictionnary.getProperty("ERROR_RENAMING_FILE"), filename, targetName));
            }
        }

        File oldThumbnailFile = new File(getThumbnailPath(sourcePath));
        if (oldThumbnailFile.exists()) {
            oldThumbnailFile.renameTo(new File(getThumbnailPath(targetPath)));
        }

        if (fileTo.isDirectory()) {
            if (!targetPath.endsWith("/"))
                targetPath = targetPath + "/";
        }
        
        JSONObject ret = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(targetPath));
        ret.put("data", o);
        return ret;

        //return new JSONObject().put("data", new JSONObject(getFileInfo(targetPath)));
    }


    @SuppressWarnings("unchecked")
	public JSONObject actionDelete(HttpServletRequest request) throws Exception {
    	

        String path = getPath(request, "path");
        
        int pathPos = path.lastIndexOf("/");
        String fileName = path.substring(pathPos + 1);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);  
        String token = (String)request.getAttribute(Constants.TOKEN);
        
        headers.set(AUTHORIZATION, "Bearer " + token);//set bearer token
        
        
        RestTemplate restTemplate = new RestTemplate();
        
        String resourceUrl
        = backend + "/private/content";
        
        Map<String,String> m = new HashMap<String,String>();
        m.put("name", fileName);
        
        String fileType = "STATIC_FILE";
        String type = null;
        String encoded= URLEncoder.encode(fileName, "UTF-8");
        String mimetype = URLConnection.guessContentTypeFromName(encoded);
        if(mimetype!=null) {
    	   type = mimetype.split("/")[0]; 
        }
        
        if(type!=null && type.equals("image"))
        	fileType = "IMAGE";
        
        m.put("contentType", fileType);

        
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(m);
        
        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        
        ResponseEntity<String> resp = null;
        try {
        	resp = restTemplate.exchange(resourceUrl, HttpMethod.DELETE, entity,String.class);
        } catch(HttpClientErrorException e) {
        	if(HttpStatus.FORBIDDEN.name().equals(e.getStatusCode().name())) {
        		throw new AdminAuthenticationException("Cannot delete content " + fileName,e.getStatusCode());
        	}
        	logger.error("Error while uploading file " + e.getMessage());
        	throw new Exception("Cannot delete file " + fileName + " " + e.getMessage());
        }
        
        String urlPath = path.replace(fileName, "");
        JSONObject ret = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(urlPath, fileName));
        ret.put("data", o);
        return ret;

    }

    
    @SuppressWarnings("unchecked")
	public JSONObject actionAddFolder(HttpServletRequest request) throws Exception {

        String path = getPath(request, "path");

        String filename = StringUtils.normalize(request.getParameter("name"));

        if (filename.length() == 0) // the name existed of only special characters
            return getErrorResponse(String.format(dictionnary.getProperty("FORBIDDEN_NAME"), filename));

        File file = new File(docRoot.getPath() + path + filename);
        if (file.isDirectory()) {
            return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_ALREADY_EXISTS"), filename));
        }

        try {
            Files.createDirectories(file.toPath(), FileUtils.getPermissions755());
        } catch (IOException e) {
            return getErrorResponse(String.format(dictionnary.getProperty("UNABLE_TO_CREATE_DIRECTORY"), filename));
        }

        JSONObject result = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(path + filename + "/"));
        result.put("data", o);
        
        return result;
        
        //return new JSONObject().put("data", new JSONObject(getFileInfo(path + filename + "/")));

    }


    @SuppressWarnings("unchecked")
	public JSONObject actionDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String path = getPath(request, "path");
        
        String filePath = getFilePath(path);
        //filePath = URLEncoder.encode(filePath, "UTF-8");
        int statusCode = 0;
        File file = getFile(filePath);
        filePath = filePath.replaceAll(" ","%20");
        URL u = new URL (filePath);
        //URL u = new URL ("http://localhost:8080/static/files/DEFAULT/IMAGE/FX-IMG_0288_burned.jpg");
        HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
        //huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
        //huc.connect () ; 
        statusCode = huc.getResponseCode() ;

        	InputStream inputStream = null;

            try {
                response.setHeader("Content-Description", "File Transfer");
                if (statusCode == HttpURLConnection.HTTP_OK) {

                    String contentType = huc.getContentType();
                    int contentLength = huc.getContentLength();
                	
                    inputStream = huc.getInputStream();
                	
                    String fileExt = filePath.substring(filePath.lastIndexOf(".") + 1);
                    response.setContentType(contentType);
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                    response.setContentLength(contentLength);

                    FileUtils.copy(new BufferedInputStream(inputStream), 
                    response.getOutputStream());
                } else {
                	throw new Exception("File could not be downloaded " + filePath);
                }

            } catch (IOException e) {
                throw new Exception("Download error: " + filePath, e);
            } finally {
            	if(inputStream != null) {
            		try {
            			inputStream.close();
            		} catch(Exception e) {
            			//ignore
            		}
            	}
            }

            return null;
 
    }


    @SuppressWarnings("unchecked")
	public JSONObject actionUpload(HttpServletRequest request) throws Exception {
        
		//MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//LocalFileManager fileManager = (LocalFileManager)super.getFileManager();
        String path = getPath(request, "path");

        
        JSONArray array = new JSONArray();
        try {

            for (Part uploadedFile : request.getParts()) {
            	
                if (uploadedFile.getContentType() == null) {
                    continue;
                }

                if (uploadedFile.getSize() == 0) {
                    throw new Exception(getProperties().getProperty("FILE_EMPTY"));
                }
                
                String submittedFileName = uploadedFile.getSubmittedFileName();
                //String filename = StringUtils.normalize(FileUtils.getBaseName(submittedFileName)) + '.' + FileUtils.getExtension(submittedFileName);

                Long uploadFileSizeLimit = 0L;
                String uploadFileSizeLimitString = getPropertiesConfig().getProperty("upload_fileSizeLimit");
                try {
                    uploadFileSizeLimit = Long.parseLong(uploadFileSizeLimitString);
                } catch (NumberFormatException e) {
                    throw new Exception(String.format(getProperties().getProperty("ERROR_CONFIG_FILE"), "upload_fileSizeLimit:" + uploadFileSizeLimitString));
                }

                if (uploadedFile.getSize() > uploadFileSizeLimit) {
                    throw new Exception(getProperties().getProperty("upload_file_too_big"));
                }
                
                String type = uploadedFile.getContentType() .split("/")[0];
                
                BufferedInputStream inputStream = new BufferedInputStream(uploadedFile.getInputStream());
                byte[] bytes = IOUtils.toByteArray(inputStream);
                
                
                Map<String, Object> m = new HashMap<String, Object>();
                m.put("name", submittedFileName);
                m.put("contentType", type);
                m.put("file", bytes);
                
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(m);
                
                String token = (String)request.getAttribute(Constants.TOKEN);
                
                headers.set(AUTHORIZATION, "Bearer " + token);//set bearer token
                
                HttpEntity<String> entity = new HttpEntity<String>(json, headers);
                RestTemplate restTemplate = new RestTemplate();
                
                String resourceUrl
                = backend + "/private/content";
                
                ResponseEntity<String> resp = null;
                try {
                	resp = restTemplate.postForEntity(resourceUrl, entity, String.class);
                } catch(HttpClientErrorException e) {
                	if(HttpStatus.FORBIDDEN.name().equals(e.getStatusCode().name())) {
                		
                		throw new AdminAuthenticationException("Cannot post content[Forbidden]",e.getStatusCode());

                	}
                	logger.error("Error while uploading file " + e.getMessage());
                	throw new Exception("Error while uploading file " + e.getMessage());
                }
                
                String urlPath = resp.getBody().replace(submittedFileName, "");

                JSONObject o = new JSONObject(getFileInfo(urlPath, submittedFileName));
                array.add(o);
                
                
            }
        
            
        } catch(Exception e) {
        	throw new Exception("Error uploading image: ", e);
        }

        JSONObject r = new JSONObject();
        r.put("data", array);
        return r;
		
    	
/*    	String path = getPath(request, "path");
        File targetDirectory = getFile(path);
        String targetDirectoryString = path.substring(0, path.lastIndexOf("/") + 1);
        if (!hasPermission("upload")) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if(!targetDirectory.exists()){
            return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_NOT_EXIST"), path));
        }
        if (!targetDirectory.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        org.json.simple.JSONArray array = uploadFiles(request, targetDirectoryString);
        
        JSONObject o = new JSONObject(getFileInfo(path));
        o.put("data", array);
        
        return o;

        //return new JSONObject().put("data", array);
*/
    }

    
    @SuppressWarnings("unchecked")
	public JSONObject actionCopy(HttpServletRequest request) throws Exception {
        String sourcePath = getPath(request, "source");
        String targetPath = getPath(request, "target");

        // security check
        if (!targetPath.startsWith("/")) targetPath = "/" + targetPath;
        if (!targetPath.endsWith("/")) targetPath += "/";

        File sourceFile = getFile(sourcePath);
        String filename = sourceFile.getName();
        File targetDir = getFile(targetPath);
        File targetFile = getFile(targetPath + filename);

        String finalPath = targetPath + filename + (sourceFile.isDirectory() ? "/" : "");

        if (!hasPermission("copy")) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }
        if (!targetDir.exists() || !targetDir.isDirectory()) {
            return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_NOT_EXIST"), targetPath));
        }
        // check system permission
        if (!sourceFile.canRead() && !targetDir.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }
        // check if not requesting main FM userfiles folder
        if (sourceFile.equals(docRoot)) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }
        // check if name are not excluded
        if (!isAllowedName(targetFile.getName(), false)) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }
        // check if file already exists
        if (targetFile.exists()) {
            if (targetFile.isDirectory()) {
                return getErrorResponse(String.format(dictionnary.getProperty("DIRECTORY_ALREADY_EXISTS"), targetFile.getName()));
            } else {
                return getErrorResponse(String.format(dictionnary.getProperty("FILE_ALREADY_EXISTS"), targetFile.getName()));
            }
        }

        try {
            if (sourceFile.isDirectory()) {
                FileUtils.copyDirectory(sourceFile.toPath(), targetFile.toPath());
            } else {
                Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

        } catch (IOException e) {
            if (sourceFile.isDirectory()) {
                return getErrorResponse(String.format(dictionnary.getProperty("ERROR_COPYING_DIRECTORY"), filename, targetPath));
            } else {
                return getErrorResponse(String.format(dictionnary.getProperty("ERROR_COPYING_FILE"), filename, targetPath));
            }

        }
        
        JSONObject result = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(finalPath));
        result.put("data", o);
        
        return result;

        //return new JSONObject().put("data", new JSONObject(getFileInfo(finalPath)));
    }

    
    public JSONObject actionReadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String path = getPath(request, "path");

        File file = new File(docRoot.getPath() + path);

        if (!file.exists()) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        if (file.isDirectory()) {
            return getErrorResponse(dictionnary.getProperty("FORBIDDEN_ACTION_DIR"));
        }

        if (!isAllowedName(file.getName(), false)) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        // check if file is readable
        if (!file.canRead()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        String filename = file.getName();
        String fileExt = filename.substring(filename.lastIndexOf(".") + 1);
        String mimeType = FileManagerUtils.getMimeTypeByExt(fileExt);
        long fileSize = file.length();

        //TO DO : IMPLEMENT HTTP RANGE FOR STREAM FILE (AUDIO/VIDEO)

        response.setContentType(mimeType);
        response.setHeader("Content-Length", Long.toString(fileSize));
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");

        try {
            FileUtils.copy(new BufferedInputStream(new FileInputStream(file)), response.getOutputStream());
        } catch (IOException e) {
            throw new Exception("Read file error: " + path, e);
        }
        return null;
    }

    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public JSONObject actionEditFile(HttpServletRequest request) throws Exception {
        String path = getPath(request, "path");
        File file = getFile(path);

        if (!file.exists()) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        if (file.isDirectory()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if (!hasPermission("edit") || !isEditable(file.getName())) {
            return getErrorResponse(dictionnary.getProperty("FORBIDDEN_ACTION_DIR"));
        }

        if (!file.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        if (!isAllowedName(file.getName(), false)) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(file));
            for (String line; (line = br.readLine()) != null; ) {
                sb.append(line);
                sb.append('\n');
            }
        } catch (IOException e) {
            throw new Exception(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {}
            }
        }

        String fileContent = sb.toString();
        Map fileInfo = getFileInfo(path);
        Map attributes = (Map) fileInfo.get("attributes");
        attributes.put("content", fileContent);
        fileInfo.put("attributes", attributes);
        
        JSONObject result = new JSONObject();
        JSONObject o = new JSONObject(fileInfo);
        result.put("data", o);
        
        return result;

        //return new JSONObject().put("data", new JSONObject(fileInfo));
    }

    
    @SuppressWarnings("unchecked")
	public JSONObject actionSaveFile(HttpServletRequest request) throws Exception {
        String path = getPath(request, "path");
        String content = request.getParameter("content");
        File file = getFile(path);

        if (!file.exists()) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        if (file.isDirectory()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if (!hasPermission("edit") || !isEditable(file.getName())) {
            return getErrorResponse(dictionnary.getProperty("FORBIDDEN_ACTION_DIR"));
        }

        if (!file.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        if (!isAllowedName(file.getName(), false)) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        try {
            FileOutputStream oldFile = new FileOutputStream(file, false);
            oldFile.write(content.getBytes());
            oldFile.close();
        } catch (IOException e) {
            throw new Exception("Error writing modified file", e);
        }
        
        JSONObject result = new JSONObject();
        JSONObject o = new JSONObject(getFileInfo(path));
        result.put("data", o);
        
        return result;

        //return new JSONObject().put("data", new JSONObject(getFileInfo(path)));
    }

    
    @SuppressWarnings("unchecked")
	public JSONObject actionReplace(HttpServletRequest request) throws Exception {
        String path = getPath(request, "path");
        File file = getFile(path);
        File targetDirectory = new File(file.getParent());

        String targetDirectoryString = path.substring(0, path.lastIndexOf("/") + 1);

        if (!hasPermission("replace") || !hasPermission("upload")) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if (file.isDirectory()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED"));
        }

        if (!isAllowedName(file.getName(), false)) {
            return getErrorResponse(dictionnary.getProperty("INVALID_DIRECTORY_OR_FILE"));
        }

        if (!targetDirectory.canWrite()) {
            return getErrorResponse(dictionnary.getProperty("NOT_ALLOWED_SYSTEM"));
        }

        org.json.simple.JSONArray array = null;

        array = uploadFiles(request, targetDirectoryString);
        file.delete();
        File thumbnail = getThumbnail(path, false);
        if (thumbnail != null && thumbnail.exists()) {
            thumbnail.delete();
        }
        
        JSONObject o = new JSONObject();
        o.put("data", array);
        return o;

        //return new JSONObject().put("data", array);
    }

    
    @SuppressWarnings({ "static-access", "unchecked" })
	public JSONObject actionSummarize() throws Exception {
        JSONObject attributes = null;
        try {
            attributes = this.getDirSummary(getFile("/").toPath());
        } catch (IOException e) {
            throw new Exception("Error during the building of the summary", e);
        }
        JSONObject result = new JSONObject();
        result.put("id", "/");
        result.put("type", "summary");
        result.put("attributes", attributes);
        
        JSONObject ret = new JSONObject();
        ret.put("data", result);
        
        return ret;
    }
    
	private Map getFileInfo(String path, String fileName) throws Exception {
		
        Map model = this.getFileModel();

        String decodePath = java.net.URLDecoder.decode(path, "UTF-8");
        
        model.put("id", decodePath + fileName);
        model.put("type", "file");
        
        Map attributes = (Map) model.get("attributes");

        String fileExt = FileUtils.getExtension(fileName);

        attributes.put("extension", fileExt);
        attributes.put("name", fileName);
        attributes.put("path", decodePath + fileName);


        return model;
	}

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Map getFileInfo(String path) throws Exception {

        // get file
        File file = getFile(path);

        if(file.isDirectory() && !path.endsWith("/")){
            throw new Exception("Error reading the file: " + file.getAbsolutePath());
        }

        BasicFileAttributes attr;
        try {
            attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
            throw new Exception("Error reading the file: " + file.getAbsolutePath(), e);
        }

        // check if file is readable
        boolean isReadable = file.canRead();
        // check if file is writable
        boolean isWritable = file.canWrite();

        Map model;
        Map attributes;
        String filename = file.getName();
        if (file.isDirectory()) {
            model = this.getFolderModel();
            attributes = (Map) model.get("attributes");
        } else {
            model = this.getFileModel();
            attributes = (Map) model.get("attributes");

            String fileExt = FileUtils.getExtension(filename);

            attributes.put("extension", fileExt);

            if (isReadable) {
                attributes.put("size", file.length());
                if (isAllowedImageExt(fileExt)) {
                    Dimension dim;
                    if (file.length() > 0) {
                        dim = ImageUtils.getImageSize(docRoot.getPath() + path);
                    } else {
                        dim = new Dimension(0, 0);
                    }
                    attributes.put("width", dim.getWidth());
                    attributes.put("height", dim.getHeight());
                }
            }
        }

        model.put("id", path);
        attributes.put("name", filename);
        attributes.put("path", getDynamicPath(path));
        attributes.put("readable", isReadable ? 1 : 0);
        attributes.put("writable", isWritable ? 1 : 0);
        attributes.put("timestamp", attr.lastModifiedTime().toMillis());
        attributes.put("modified", df.format(new Date(attr.lastModifiedTime().toMillis())));
        attributes.put("created", df.format(new Date(attr.creationTime().toMillis())));
        model.put("attributes", attributes);
        return model;
    }

    private File getFile(String path) {
        return new File(contentUrl + path);
    }
    
    private String getFilePath(String path) {
    	return new StringBuilder().append(contentUrl).append(path).toString();
    }

    private String getDynamicPath(String path) {
        String serverRoot = propertiesConfig.getProperty("serverRoot");
        return (StringUtils.isEmpty(serverRoot)) ? path : serverRoot + path;
    }

    private File getThumbnailDir() throws Exception {

        File thumbnailDir = new File(propertiesConfig.getProperty("image_thumbnail_dir"));

        if (!thumbnailDir.exists()) {
            try {
                //Files.createDirectory(thumbnailDir.toPath(), FileUtils.getPermissions755());
                Files.createDirectory(thumbnailDir.toPath());
            } catch (IOException e) {
                throw new Exception("Cannot create the directory: " + thumbnailDir.getAbsolutePath(), e);
            }
        }
        return thumbnailDir;
    }

    private String getThumbnailPath(String path) throws Exception {
        return getThumbnailDir().getPath() + path;
    }

    public File getThumbnail(String path, boolean create) throws Exception, Exception {

        File thumbnailFile = new File(getThumbnailPath(path));

        if (thumbnailFile.exists()) {
            return thumbnailFile;
        } else if (!create) {
            return null;
        }

        File originalFile = new File(docRoot.getPath() + path);
        String ext = FileUtils.getExtension(originalFile.getName());

        if (!originalFile.exists())
            throw new Exception(path);

        try {
            if (!thumbnailFile.mkdirs()) {
                Files.createDirectories(thumbnailFile.getParentFile().toPath());
            }

            BufferedImage source = ImageIO.read(originalFile);
            BufferedImage resizedImage = generateThumbnail(source);
            ImageIO.write(resizedImage, ext, thumbnailFile);
        } catch (IOException e) {
            logger.error("Error during thumbnail generation - ext: " + ext + " name: " + originalFile.getName(), e);
            return null;
        }

        return thumbnailFile;
    }

    @SuppressWarnings("unchecked")
	private org.json.simple.JSONArray uploadFiles(HttpServletRequest request, String targetDirectory) throws Exception {
    	org.json.simple.JSONArray array = new org.json.simple.JSONArray();
        try {
            for (Part uploadedFile : request.getParts()) {

                if (uploadedFile.getContentType() == null) {
                    continue;
                }

                if (uploadedFile.getSize() == 0) {
                    throw new Exception(dictionnary.getProperty("FILE_EMPTY"));
                }

                String submittedFileName = uploadedFile.getSubmittedFileName();
                String filename = StringUtils.normalize(FileUtils.getBaseName(submittedFileName)) + '.' + FileUtils.getExtension(submittedFileName);

                if (!isAllowedName(filename, false)) {
                    throw new Exception(filename);
                }
                Long uploadFileSizeLimit = 0L;
                String uploadFileSizeLimitString = propertiesConfig.getProperty("upload_fileSizeLimit");
                try {
                    uploadFileSizeLimit = Long.parseLong(uploadFileSizeLimitString);
                } catch (NumberFormatException e) {
                    throw new Exception(String.format(dictionnary.getProperty("ERROR_CONFIG_FILE"), "upload_fileSizeLimit:" + uploadFileSizeLimitString));
                }

                if (uploadedFile.getSize() > uploadFileSizeLimit) {
                    throw new Exception(dictionnary.getProperty("upload_file_too_big"));
                }

                String uploadedPath = getFile(targetDirectory).getAbsolutePath() + "/" + filename;

                Files.copy(new BufferedInputStream(uploadedFile.getInputStream()), Paths.get(uploadedPath), StandardCopyOption.REPLACE_EXISTING);

                JSONObject o = new JSONObject(getFileInfo(targetDirectory + filename));
                array.add(o);

                //array.put(new JSONObject(getFileInfo(targetDirectory + filename)));
            }
        } catch (Exception e){
            throw new Exception(dictionnary.getProperty("ERROR_UPLOADING_FILE"), e);
        }
        return array;
    }


	public String getBasePath() {
		return this.basePath;
	}
	
    public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	
	public Properties getPropertiesConfig() {
		return propertiesConfig;
	}


}
