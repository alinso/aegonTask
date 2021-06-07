package com.aegon.task.service;

import com.aegon.task.entity.Topic;
import com.aegon.task.entity.dto.TopicDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public abstract class BaseService {

    @Autowired
    ModelMapper modelMapper;

    public List<TopicDto> toDtoList(List<Topic> topicList){

        List<TopicDto> topicDtoList = new ArrayList<>();
        topicList.forEach(topic -> {
            TopicDto topicDto = modelMapper.map(topic, TopicDto.class);
            topicDtoList.add(topicDto);
        });

        return topicDtoList;
    }


}
