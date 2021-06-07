package com.aegon.task.controller;

import com.aegon.task.entity.dto.TopicDto;
import com.aegon.task.service.TopicService;
import com.aegon.task.util.MapValidationErrorUtil;
import com.aegon.task.validator.TopicDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("topic")
public class TopicController {

    final MapValidationErrorUtil mapValidationErrorUtil;
    final TopicService topicService;
    final TopicDtoValidator topicDtoValidator;

    @Autowired
    public TopicController(MapValidationErrorUtil mapValidationErrorUtil, TopicService topicService, TopicDtoValidator topicDtoValidator) {
        this.mapValidationErrorUtil = mapValidationErrorUtil;
        this.topicService = topicService;
        this.topicDtoValidator = topicDtoValidator;
    }

    @PostMapping( "save")
    public ResponseEntity<?> save(@RequestBody TopicDto dto, BindingResult bindingResult) {

        topicDtoValidator.validate(dto, bindingResult);
        ResponseEntity<?> errorMap = mapValidationErrorUtil.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(topicService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping( "all")
    public ResponseEntity<?> all() {
        List<TopicDto> topicDtoList =  topicService.findAll();
        return new ResponseEntity<>(topicDtoList, HttpStatus.OK);
    }

//    @GetMapping( "allWithFeedBacks")
//    public ResponseEntity<?> allWithFeedBacks() {
//        List<TopicDto> topicDtoList =  topicService.findAllWithFeedBacks();
//        return new ResponseEntity<>(topicDtoList, HttpStatus.OK);
//    }





}










