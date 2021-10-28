package com.example.resttemplate.resttemplates;

import com.example.resttemplate.dtos.IdeaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateDemo {

    @Autowired
    private RestTemplate restTemplate;

    public void fetchData() {
        String uri = "http://localhost:8080/rest_demo/ideas";
        /*ResponseEntity<String> ideaResponse = restTemplate.getForEntity(uri, String.class);
        System.out.println("<!-----------response body----------->");
        System.out.println(ideaResponse.getBody());

        System.out.println("<!-----------status code----------->");
        System.out.println(ideaResponse.getStatusCode());

        System.out.println("<!-----------headers----------->");
        System.out.println(ideaResponse.getHeaders());*/

        List<IdeaDto> ideaDtos = restTemplate.getForObject(uri, List.class);
        System.out.println("<!-----retrieve----->");
        System.out.println(ideaDtos);

        IdeaDto ideaDto = new IdeaDto(5, "Crazy Idea", "This is a crazy idea", "somu");
        IdeaDto ideaDtoStored = restTemplate.postForObject(uri, ideaDto, IdeaDto.class);
        System.out.println("<!-----saved----->");
        System.out.println(ideaDtoStored);

        IdeaDto updatedIdea = new IdeaDto(5, "Crazy Idea always", "This is a crazy idea", "somu");
        HttpEntity<IdeaDto> ideaDtoHttpEntity = new HttpEntity<>(updatedIdea);
        ResponseEntity<IdeaDto> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, ideaDtoHttpEntity, IdeaDto.class, Void.class);
        System.out.println("<!-----saved----->");
        System.out.println(responseEntity.getBody());

        /*IdeaDto deletingIdea = new IdeaDto(5, "Crazy Idea always", "This is a crazy idea", "somu");
        restTemplate.delete(uri, deletingIdea, IdeaDto.class, Void.class);
        System.out.println("<!-----deleted----->");*/
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://localhost:8080/rest_demo/ideas";
        /*ResponseEntity<String> ideaResponse = restTemplate.getForEntity(uri, String.class);
        System.out.println("<!-----------response body----------->");
        System.out.println(ideaResponse.getBody());

        System.out.println("<!-----------status code----------->");
        System.out.println(ideaResponse.getStatusCode());

        System.out.println("<!-----------headers----------->");
        System.out.println(ideaResponse.getHeaders());*/

        List<IdeaDto> ideaDtos = restTemplate.getForObject(uri, List.class);
        System.out.println("<!-----retrieve----->");
        System.out.println(ideaDtos);

        IdeaDto ideaDto = new IdeaDto(5, "Crazy Idea", "This is a crazy idea", "somu");
        IdeaDto ideaDtoStored = restTemplate.postForObject(uri, ideaDto, IdeaDto.class);
        System.out.println("<!-----saved----->");
        System.out.println(ideaDtoStored);

        IdeaDto updatedIdea = new IdeaDto(5, "Crazy Idea always", "This is a crazy idea", "somu");
        HttpEntity<IdeaDto> ideaDtoHttpEntity = new HttpEntity<>(updatedIdea);
        ResponseEntity<IdeaDto> responseEntity = restTemplate.exchange(uri, HttpMethod.PUT, ideaDtoHttpEntity, IdeaDto.class, Void.class);
        System.out.println("<!-----saved----->");
        System.out.println(responseEntity.getBody());

        IdeaDto deletingIdea = new IdeaDto(5, "Crazy Idea always", "This is a crazy idea", "somu");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(deletingIdea.toString(), headers);
        restTemplate.delete(uri, entity, IdeaDto.class);
        System.out.println("<!-----deleted----->");



    }
}
