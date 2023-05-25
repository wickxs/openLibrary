package utils;

import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class OpenLibraryApi {
    private static final String GET_AUTHOR = "https://openlibrary.org/api/books?bibkeys=ISBN:";
    private static final String PATH_CONFIG = "&jscmd=data&format=json";
    private final RestTemplate restTemplate;

    public OpenLibraryApi() {
        this.restTemplate = new RestTemplate();
    }

    public String getAuthor(String isbn) {
        var url = GET_AUTHOR + isbn + PATH_CONFIG;
        var result = restTemplate.getForEntity(url, HashMap.class);
        return ((HashMap<?, ?>)((ArrayList<?>)((HashMap<?, ?>) Objects.requireNonNull(result.getBody()).get("ISBN:"+isbn)).get("authors")).get(0)).get("name").toString();
    }
}
