package com.aegon.task.validator;

import com.aegon.task.entity.dto.FeedbackDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FeedbackDtoValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return FeedbackDto.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        FeedbackDto feedbackDto = (FeedbackDto) object;

        if (feedbackDto.getVote() > 10 || feedbackDto.getVote() < 0) {
            errors.rejectValue("vote", "Match", "Vote value should be in [0-10]");
        }
        if (feedbackDto.getId() < 1) {
            errors.rejectValue("id", "Match", "You have to select a topic!");
        }
    }


}
