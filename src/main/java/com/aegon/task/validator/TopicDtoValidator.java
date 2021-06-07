package com.aegon.task.validator;

import com.aegon.task.entity.dto.FeedbackDto;
import com.aegon.task.entity.dto.TopicDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TopicDtoValidator implements Validator {



    @Override
    public boolean supports(Class<?> aClass) {
        return TopicDto.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        TopicDto topicDto = (TopicDto) object;

        if (topicDto.getTopicName() == null || topicDto.getTopicName().equals("")) {
            errors.rejectValue("topicName", "Match", "name cant be empty");
        }
        if (topicDto.getQuestion() == null || topicDto.getQuestion().equals("")) {
            errors.rejectValue("question", "Match", "question cant be empty");
        }
    }

}
