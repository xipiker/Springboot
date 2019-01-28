package pers.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import pers.springboot.util.Book;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Redis常见的五大数据类型
     * String(字符串)、List(列表)、Set(集合)、Hash(散列)、ZSet(有序集合)
     * stringRedisTemplate.opsForValue():字符串
     * stringRedisTemplate.opsForList():列表
     * stringRedisTemplate.opsForSet():集合
     * stringRedisTemplate.opsForHash():散列
     * stringRedisTemplate.opsForZSet():有序集合
     */
    @Test
    public void contextLoads() {
        //stringRedisTemplate.opsForValue().append("msg", "hello");
        //String msg = stringRedisTemplate.opsForValue().get("msg");
        //System.out.println(msg);
    }

    @Test
    public void rabbitTest(){
        Map<String, Object> map = new HashMap<>();
        map.put("result","this is test");
        map.put("status", true);
        //单播，点对点
        //map对象默认被序列化发送出去
        rabbitTemplate.convertAndSend("exchange.direct", "atxipiker", map);
    }

    @Test
    public void receive(){
        //接收queue
        Object atxipiker = rabbitTemplate.receiveAndConvert("atxipiker");
        log.info("atxipiker-----{}",atxipiker);
    }

    //发送广播
    @Test
    public void sendMsg(){
        Map<String, Object> map = new HashMap<>();
        map.put("result","this is test three");
        map.put("status", true);
        rabbitTemplate.convertAndSend("exchange.fanout","", new Book("name","test",23));
    }

    //创建exchange
    @Test
    public void createExchange(){
        //创建exhange
        //amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
        //创建queue
        //amqpAdmin.declareQueue(new Queue("amqpAdmin.queue"));
        //创建绑定规则
        //String destination, Binding.DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments
        //amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE, "amqpAdmin.exchange", "amqp.haha", null));
        //删除del
    }

}

