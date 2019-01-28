package pers.springboot.server.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import pers.springboot.util.Book;

@Service
public class BookServiceImpl {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "atxipiker.users")
    public void receive(Book book){
        log.info("book-----{}",book);
    }
}
