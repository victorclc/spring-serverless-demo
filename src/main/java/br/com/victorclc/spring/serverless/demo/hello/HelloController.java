package br.com.victorclc.spring.serverless.demo.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.function.Function;

@Slf4j
@org.springframework.stereotype.Controller
public class HelloController {
    @Bean
    public Function<Message<Void>, Message<String>> getHello() {
        return request -> {
            log.info("Request: {}", request);
            return MessageBuilder
                    .withPayload("Hello!")
                    .setHeader("statusCode", HttpStatus.OK.value())
                    .build();
        };
    }

    @Bean
    public Function<Message<HelloRequestDto>, Message<String>> postHello() {
        return request -> {
            log.info("Request: {}", request);
            return MessageBuilder
                    .withPayload("Hello " + request.getPayload().getName())
                    .setHeader("statusCode", HttpStatus.OK.value())
                    .build();
        };
    }
}

