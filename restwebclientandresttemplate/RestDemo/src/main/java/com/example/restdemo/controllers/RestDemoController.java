package com.example.restdemo.controllers;

import com.example.restdemo.dto.IdeaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "rest_demo")
public class RestDemoController {

    private static final Map<Integer, IdeaDto> IDEA_DTO_MAP = new HashMap<>();

    public RestDemoController() {
        IdeaDto ideaDto1 = new IdeaDto(1, "BestIdea", "The best idea among all", "sandesh");
        IdeaDto ideaDto2 = new IdeaDto(2, "OKisIdea", "The Okish idea among all", "sanketh");
        IdeaDto ideaDto3 = new IdeaDto(3, "WorstIdea", "The Worst idea among all", "sudeep");
        IDEA_DTO_MAP.put(ideaDto1.getId(), ideaDto1);
        IDEA_DTO_MAP.put(ideaDto2.getId(), ideaDto2);
        IDEA_DTO_MAP.put(ideaDto3.getId(), ideaDto3);
    }

    @GetMapping(value = "/hello/{name}")
    public ResponseEntity<String> greetMe(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>("Hello " + name + " from Rest Demo App", HttpStatus.OK);
    }

    /**
     * R -> Retrieve
     * @return
     */
    @GetMapping(value = "ideas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IdeaDto>> getAllIdeas(@Nullable @RequestParam String id) {
        List<IdeaDto> ideaDtos;
        if (!StringUtils.isEmpty(id)) {
            ideaDtos = new ArrayList<>(IDEA_DTO_MAP.values());
            Map<Integer, List<IdeaDto>> map = ideaDtos.stream()
                    .collect(Collectors.groupingBy(IdeaDto::getId));
            ideaDtos = map.get(Integer.valueOf(id));
        } else {
            ideaDtos = new ArrayList<>(IDEA_DTO_MAP.values());
        }
        return new ResponseEntity<>(ideaDtos, HttpStatus.OK);
    }

    /**
     * C -> Create
     * @return
     */
    @PostMapping(value = "ideas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaDto> createIdea(@RequestBody IdeaDto ideaDto) {
        IDEA_DTO_MAP.put(ideaDto.getId(), ideaDto);
        return new ResponseEntity<>(ideaDto, HttpStatus.CREATED);
    }

    /**
     * C -> Create
     * @return
     */
    @PutMapping(value = "ideas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdeaDto> updateIdea(@RequestBody IdeaDto ideaDto) {
        IDEA_DTO_MAP.compute(ideaDto.getId(), (k,v) -> ideaDto);
        return new ResponseEntity<>(ideaDto, HttpStatus.ACCEPTED);
    }

    /**
     * C -> Create
     * @return
     */
    @DeleteMapping(value = "ideas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteIdea(@RequestBody IdeaDto ideaDto) {
        IDEA_DTO_MAP.remove(ideaDto.getId());
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
