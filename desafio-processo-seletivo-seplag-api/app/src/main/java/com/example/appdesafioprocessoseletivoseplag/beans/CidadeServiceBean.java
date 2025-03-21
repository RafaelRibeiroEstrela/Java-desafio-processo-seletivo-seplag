package com.example.appdesafioprocessoseletivoseplag.beans;

import com.example.ports.CidadePort;
import com.example.services.impl.CidadeServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CidadeServiceBean {

    private final CidadePort port;

    public CidadeServiceBean(CidadePort port) {
        this.port = port;
    }

    @Bean
    public CidadeServiceImpl injectCidadeService() {
        return new CidadeServiceImpl(port);
    }

}
