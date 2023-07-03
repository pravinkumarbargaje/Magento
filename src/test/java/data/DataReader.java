package data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataMap() throws IOException
	{
		//read json to string
		String jsonContent =	FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\ProductOrder.json"),
								StandardCharsets.UTF_8);
	
		//string to hashmap Jackson Datbind
		ObjectMapper mapper = new ObjectMapper();
		 List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
		 return data;
	
	}
}
