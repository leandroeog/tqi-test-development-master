package br.com.tqi.test.development.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CepClientFallback implements CepClient {

    @Override
    public ResponseEntity<?> findByCep(String cep) {
        return ResponseEntity.badRequest().body("Problem ocurred while fetching data from VIACEP service!");
    }

}

