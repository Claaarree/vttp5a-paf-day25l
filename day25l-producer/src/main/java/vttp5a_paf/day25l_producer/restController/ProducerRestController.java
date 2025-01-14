package vttp5a_paf.day25l_producer.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp5a_paf.day25l_producer.model.Order;
import vttp5a_paf.day25l_producer.model.Student;
import vttp5a_paf.day25l_producer.model.Todo;
import vttp5a_paf.day25l_producer.service.ProducerService;

@RestController
@RequestMapping("/api/messages")
public class ProducerRestController {

    @Autowired
    ProducerService producerService;

    @PostMapping()
    public ResponseEntity<String> sendMessage(@RequestBody Todo todo) {
        producerService.sendMessage(todo);
        
        return new ResponseEntity<>("Message sent", HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<String> sendMessage(@RequestBody Student student) {
        producerService.sendMessage(student);
        
        return new ResponseEntity<>("Student sent", HttpStatus.OK);
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendOrder(@RequestBody Order order) {
        producerService.publish(order);

        return new ResponseEntity<>("Message sent", HttpStatus.OK);
    }
    
}
