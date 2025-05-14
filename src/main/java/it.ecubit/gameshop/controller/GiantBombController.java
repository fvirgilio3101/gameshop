package it.ecubit.gameshop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api/giantbomb")
@CrossOrigin(origins = "http://localhost:4200")
public class GiantBombController {

    @Value("${giantbomb.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/games")
    public ResponseEntity<String> getGames(@RequestParam Map<String, String> params) {
        StringBuilder url = new StringBuilder("https://www.giantbomb.com/api/games/");
        url.append("?api_key=").append(apiKey).append("&format=json");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            url.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Gameshop");
        HttpEntity<Void> request = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url.toString(), HttpMethod.GET, request, String.class);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella chiamata all'API GiantBomb: " + e.getMessage());
        }
    }
}
