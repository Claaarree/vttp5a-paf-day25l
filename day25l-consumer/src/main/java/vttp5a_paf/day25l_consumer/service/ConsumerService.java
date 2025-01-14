package vttp5a_paf.day25l_consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp5a_paf.day25l_consumer.model.Order;
import vttp5a_paf.day25l_consumer.model.Student;
import vttp5a_paf.day25l_consumer.model.Todo;

@Service
public class ConsumerService implements MessageListener{
    @Autowired
    @Qualifier("order")
    private RedisTemplate<String, Order> template;
    
    public void handleMessage(Todo todo) {
        System.out.println(todo);
    }

    public void handleMessage(Student student) {
        System.out.println(student);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String orderData = new String(message.getBody());
            System.out.println(orderData);

            // Use Json-P to map it back to object
            // call the API in day 24 using restTemplate to write to mySQL database

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
