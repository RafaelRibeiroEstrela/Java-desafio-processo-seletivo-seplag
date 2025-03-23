package com.example.appdesafioprocessoseletivoseplag.beans;

import com.example.ports.EnderecoPort;
import com.example.services.CidadeService;
import com.example.services.impl.EnderecoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EnderecoServiceBean {

    private final EnderecoPort port;
    private final CidadeService cidadeService;

    public EnderecoServiceBean(EnderecoPort port, CidadeService cidadeService) {
        this.port = port;
        this.cidadeService = cidadeService;
    }

    @Bean
    public EnderecoServiceImpl injectEnderecoService() {
        return new EnderecoServiceImpl(port, cidadeService);
    }
}
