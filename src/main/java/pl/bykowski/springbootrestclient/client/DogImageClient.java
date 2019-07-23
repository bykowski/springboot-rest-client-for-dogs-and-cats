package pl.bykowski.springbootrestclient.client;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DogImageClient {

    public String getDogImage() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(
                "https://random.dog/woof.json",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class);

        String json = exchange.getBody();
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        String file = jsonObject.get("url").getAsString();
        return file;
    }
}
