package vttp5a_paf.day25l_producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import vttp5a_paf.day25l_producer.model.Order;
import vttp5a_paf.day25l_producer.model.Student;
import vttp5a_paf.day25l_producer.model.Todo;

@Service
public class ProducerService {
    
    @Autowired
    @Qualifier("todo")
    RedisTemplate<String, Todo> redisTemplate;

    @Autowired
    @Qualifier("order")
    RedisTemplate<String, Order> orderTemplate;

    @Autowired
    @Qualifier("student")
    RedisTemplate<String, Student> studenTemplate;

    @Autowired
    ChannelTopic channelTopic;

    @Value("${redis.topic1}")
    private String topic1;

    @Value("${redis.topic2}")
    private String topic2;

    public void sendMessage(Todo todo) {
        redisTemplate.convertAndSend(topic1, todo);
    }

    public void sendMessage(Student student) {
        studenTemplate.convertAndSend(topic2, student);
    }

    public Long publish(Order order) {
        return orderTemplate.convertAndSend(channelTopic.getTopic(), order);
    }
}
