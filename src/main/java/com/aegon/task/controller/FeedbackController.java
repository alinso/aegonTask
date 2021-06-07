package com.aegon.task.controller;

import com.aegon.task.entity.dto.FeedbackDto;
import com.aegon.task.service.FeedBackService;
import com.aegon.task.util.MapValidationErrorUtil;
import com.aegon.task.validator.FeedbackDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("feedback")
public class FeedbackController {

    final MapValidationErrorUtil mapValidationErrorUtil;
    final FeedBackService feedBackService;
    final FeedbackDtoValidator feedbackDtoValidator;

    @Autowired
    public FeedbackController(MapValidationErrorUtil mapValidationErrorUtil, FeedBackService feedBackService, FeedbackDtoValidator feedbackDtoValidator) {
        this.feedBackService = feedBackService;
        this.mapValidationErrorUtil = mapValidationErrorUtil;
        this.feedbackDtoValidator = feedbackDtoValidator;
    }


    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody FeedbackDto dto, BindingResult bindingResult) {

        feedbackDtoValidator.validate(dto, bindingResult);
        ResponseEntity<?> errorMap = mapValidationErrorUtil.MapValidationService(bindingResult);
        if (errorMap != null) return errorMap;

        return new ResponseEntity<>(feedBackService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping("findByTopicId/{topicId}")
    public ResponseEntity<?> save(@PathVariable("topicId") Long topicId) {
        return new ResponseEntity<>(feedBackService.findByTopicId(topicId), HttpStatus.CREATED);

    }
}
