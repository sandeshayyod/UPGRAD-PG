package com.example.resttemplate.webclients;

import com.example.resttemplate.dtos.IdeaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class WebClientDemo {
    @Autowired
    private WebClient webClient;

    public void fetchAllData() {
        IdeaDto ideaDto = new IdeaDto(6, "Psych Idea", "This is a Psych idea", "TD");
        Mono<IdeaDto> savedIdea = webClient.post().uri("/rest_demo/ideas").bodyValue(ideaDto).retrieve().bodyToMono(IdeaDto.class);
        System.out.println(savedIdea.block());

        Mono<List> allIdeas = webClient.get().uri("/rest_demo/ideas").retrieve().bodyToMono(List.class);
        System.out.println(allIdeas.block());

        IdeaDto updateIdeaDto = new IdeaDto(6, "Psych Idea by TD", "This is a Psych idea", "TD");
        savedIdea = webClient.put().uri("/rest_demo/ideas").bodyValue(updateIdeaDto).retrieve().bodyToMono(IdeaDto.class);
        System.out.println(savedIdea.block());

        webClient.method(HttpMethod.DELETE)
                .uri("/rest_demo/ideas")
                .bodyValue(updateIdeaDto)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(IdeaDto.class);
        System.out.println(savedIdea.block());

    }
}
