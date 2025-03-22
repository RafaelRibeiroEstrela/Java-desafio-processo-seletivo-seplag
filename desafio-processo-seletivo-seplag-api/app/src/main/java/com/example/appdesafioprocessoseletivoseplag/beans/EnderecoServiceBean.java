package com.example.appdesafioprocessoseletivoseplag.beans;

import com.example.ports.EnderecoPort;
import com.example.services.impl.EnderecoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EnderecoServiceBean {

    private final EnderecoPort port;

    public EnderecoServiceBean(EnderecoPort port) {
        this.port = port;
    }

    @Bean
    public EnderecoServiceImpl injectEnderecoService() {
        return new EnderecoServiceImpl(port);
    }
}
