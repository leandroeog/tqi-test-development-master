package br.com.tqi.test.development.controller;

import br.com.tqi.test.development.dto.AddressDTO;
import br.com.tqi.test.development.exception.BusinessException;
import br.com.tqi.test.development.service.AddressService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Api(value = "address", tags = "Address")
@SwaggerDefinition(tags = {@Tag(name = "Address", description = "Address services")})
public class AddressController {

    private AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping(value = "/{cep}")
    @ApiOperation(value = "Find by CEP")
    public ResponseEntity<?> findByCep(
            @ApiParam(name = "cep", value = "CEP (only numbers)", required = true)
            @PathVariable("cep") String cep) {
        return service.findByCep(cep);
    }

    @PutMapping(value = "{id}/client/{idClient}")
    @ApiOperation(value = "Update by client")
    public ResponseEntity<String> updateByClient(@PathVariable("id") Long id,
                                                 @PathVariable("idClient") Long idClient,
                                                 @RequestBody @Valid AddressDTO dto) throws Exception {

        try {

            service.updateByClient(id, idClient, dto);

            return ResponseEntity.ok("Address has been updated successfully!");

        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
