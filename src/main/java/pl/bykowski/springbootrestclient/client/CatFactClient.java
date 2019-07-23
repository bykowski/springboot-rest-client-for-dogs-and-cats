package pl.bykowski.springbootrestclient.client;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.bykowski.springbootrestclient.model.AnimalFact;
import pl.bykowski.springbootrestclient.model.AnimalType;

@Service
public class CatFactClient {

    public AnimalFact[] getCatFactFormApi(AnimalType animalType) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AnimalFact[]> exchange = restTemplate.exchange(
                "https://cat-fact.herokuapp.com/facts/random?animal_type=" + animalType.name().toLowerCase() +"&amount=5",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                AnimalFact[].class);
        return exchange.getBody();
    }
}
