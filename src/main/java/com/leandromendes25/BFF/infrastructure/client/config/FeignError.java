package com.leandromendes25.BFF.infrastructure.client.config;

import com.leandromendes25.BFF.infrastructure.exceptions.BusinessException;
import com.leandromendes25.BFF.infrastructure.exceptions.ConflictException;
import com.leandromendes25.BFF.infrastructure.exceptions.IllegalArgumentException;
import com.leandromendes25.BFF.infrastructure.exceptions.ResourceNotFoundException;
import com.leandromendes25.BFF.infrastructure.exceptions.UnathorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String mensagemErro = mensagemErro(response);
        switch (response.status()){
            case 409:
                return new ConflictException("Erro " + mensagemErro);
            case 403:
                return new ResourceNotFoundException("Erro " + mensagemErro);
            case 401:
                return new UnathorizedException("Erro " + mensagemErro);
            case 400:
                return new IllegalArgumentException("Erro " + mensagemErro);
            default:
            return new BusinessException("Erro " + mensagemErro);
        }
    }
    private String mensagemErro(Response response){
        try {
            if(Objects.isNull(response.body())){
                return "";
            }
          return new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8) ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
