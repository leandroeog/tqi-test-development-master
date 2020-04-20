package br.com.tqi.test.development.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "cep", fallback = CepClientFallback.class)
public interface CepClient {

    @GetMapping("{cep}/json")
    ResponseEntity<?> findByCep(@PathVariable("cep") String cep);

}
