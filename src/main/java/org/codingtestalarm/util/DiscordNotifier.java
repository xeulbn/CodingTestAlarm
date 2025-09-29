package org.codingtestalarm.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DiscordNotifier {

    private final RestTemplate restTemplate;

    @Value("{discord.webhook-url}")
    private String webhookUrl;


    public void sendSimple(String content){
        if(webhookUrl == null){
            System.out.println("[WARN] discord.webhook-url 미설정");
            return;
        }

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            Map<String, Object> body = Map.of("content", content);
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);

            if(!response.getStatusCode().is2xxSuccessful()){
                System.out.println("[ERROR] Discord webhook 실패: " + response.getStatusCode());
            }
        }catch(Exception e){
            System.out.println("[ERROR] Discord webhook 예외: " + e.getMessage());
        }
    }
}
