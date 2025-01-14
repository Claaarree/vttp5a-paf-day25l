package vttp5a_paf.day25l_consumer.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import vttp5a_paf.day25l_consumer.model.Order;
import vttp5a_paf.day25l_consumer.model.Student;
import vttp5a_paf.day25l_consumer.model.Todo;
import vttp5a_paf.day25l_consumer.service.ConsumerService;

@Configuration
public class RedisConfig {
    
    @Value("${redis.topic1}")
    private String redisTopic1;

    @Value("${redis.topic2}")
    String redisTopic2;

    @Value("${redis.topic3}")
    String orderTopic;

    @Bean
    RedisTemplate<String, Todo> redisTemplate(RedisConnectionFactory connFac,
    Jackson2JsonRedisSerializer<Todo> serializer) {
        RedisTemplate<String, Todo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connFac);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Todo> jackson2JsonRedisSerializer() {
        return new Jackson2JsonRedisSerializer<>(Todo.class);
    }

    @Bean
    public RedisMessageListenerContainer listenerContainer(@Qualifier("todoadapter") MessageListenerAdapter messageListenerAdapter,
    RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, new PatternTopic(redisTopic1));

        return container;
    }

    @Bean("todoadapter")
    public MessageListenerAdapter listenerAdapter(ConsumerService redisConsumerService) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(redisConsumerService);
        adapter.setSerializer(new Jackson2JsonRedisSerializer<>(Todo.class));
        
        return adapter;
    }

    // BELOW IS FOR STUDENT
    @Bean("student")
    RedisTemplate<String, Student> redisTemplate1(RedisConnectionFactory connFac,
    Jackson2JsonRedisSerializer<Student> serializer) {
        RedisTemplate<String, Student> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connFac);
        redisTemplate.setDefaultSerializer(serializer);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    @Bean
    public Jackson2JsonRedisSerializer<Student> jackson2JsonRedisSerializer1() {
        return new Jackson2JsonRedisSerializer<>(Student.class);
    }

    @Bean
    public RedisMessageListenerContainer listenerContainer1(@Qualifier("studentadapter") MessageListenerAdapter messageListenerAdapter,
    RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, new PatternTopic(redisTopic2));

        return container;
    }

    @Bean("studentadapter")
    public MessageListenerAdapter listenerAdapter1(ConsumerService redisConsumerService) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(redisConsumerService);
        adapter.setSerializer(new Jackson2JsonRedisSerializer<>(Student.class));
        
        return adapter;
    }

    // BELOW IS FOR ORDER
    @Bean("order")
    public RedisTemplate<String, Order> redisTemplate2(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Order> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    // @Bean
    // public ChannelTopic topic() {
    //     return new ChannelTopic(orderTopic);
    // }

    // private MessageListener messageListener;
    // private RedisConnectionFactory redisConnFac;

    // @Bean
    // public MessageListenerAdapter messageListenerAdapter() {
    //     MessageListenerAdapter adapter = new MessageListenerAdapter(messageListener);
    //     adapter.setDefaultListenerMethod("onMessage"); // Specify the method to handle messages
    //     return adapter;
    // }

    // @Bean
    // public RedisMessageListenerContainer redisContainer(ChannelTopic topic) {
    //     RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    //     container.setConnectionFactory(redisConnFac);
    //     container.addMessageListener(messageListener, topic);
    //     return container;
    // }
}
