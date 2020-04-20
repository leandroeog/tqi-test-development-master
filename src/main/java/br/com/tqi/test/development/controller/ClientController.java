package br.com.tqi.test.development.controller;

import br.com.tqi.test.development.dto.ClientDTO;
import br.com.tqi.test.development.exception.BusinessException;
import br.com.tqi.test.development.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/client")
@Api(value = "client", tags = "Client")
@SwaggerDefinition(tags = {@Tag(name = "Client", description = "Client services")})
public class ClientController {

    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping
    @ApiOperation(value = "Find all clients")
    public ResponseEntity<List<ClientDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Find by id")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping
    @ApiOperation(value = "Create new client")
    public ResponseEntity<String> save(@RequestBody @Valid ClientDTO dto) throws BusinessException {

        service.save(dto);

        return ResponseEntity.ok("Client has been created successfully!");

    }

}
