package com.altafjava.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

/**
 * @author Altaf
 *
 */
@Log4j2
public class CommonUtil {

	public static boolean writeStringDataToFile(String fileName, Object object) {
		File file = new File(fileName);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(file, object);
		} catch (IOException e) {
			log.error("Error occurred while writing List<Users> into file" + e);
			return false;
		}
		return true;
	}

	public static String loadPostRequestData(String fileLocation) {
		String post = null;
		log.debug("Application trying to load post request data file from '{}' ", fileLocation);
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(fileLocation));
			post = jsonObject.toString();
			log.debug("post.json file '{}' loaded successfully ", fileLocation);
		} catch (IOException | ParseException e) {
			log.error("Error while loading post.json file '{}'!", e);
		}
		return post;
	}
}
