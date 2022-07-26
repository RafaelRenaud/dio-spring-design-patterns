package com.dio.designPatterns.service;

import com.dio.designPatterns.model.entity.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface AddressService {

    @RequestMapping(method = RequestMethod.GET, value = "/{cep}/json/")
    Address searchCep(@PathVariable("cep") String cep);
}
