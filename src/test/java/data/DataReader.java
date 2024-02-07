package data;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getObjectFromJson() throws IOException {
        String jsonString=FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//java//data//products.json"), StandardCharsets.UTF_8);
        ObjectMapper mapper= new ObjectMapper();
        List<HashMap<String,String>> listMap =mapper.readValue(jsonString, new TypeReference<List<HashMap<String, String>>>() {});
        return listMap;

    }
}
