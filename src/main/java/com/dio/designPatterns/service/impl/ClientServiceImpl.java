package com.dio.designPatterns.service.impl;

import com.dio.designPatterns.model.entity.Address;
import com.dio.designPatterns.model.entity.Client;
import com.dio.designPatterns.repository.AddressRepository;
import com.dio.designPatterns.repository.ClientRepository;
import com.dio.designPatterns.service.AddressService;
import com.dio.designPatterns.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Override
    public Iterable<Client> searchAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client searchById(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if(client.isPresent()){
            return client.get();
        }else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void insert(Client client) {
        persist(client);
    }

    @Override
    public void update(Long id, Client client) {
        Optional<Client> returnedClient = clientRepository.findById(id);

        if(returnedClient.isPresent()){
            persist(client);
        }else{
            throw new PersistenceException("Cliente Inexistente! Operação Cancelada.");
        }
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void persist(Client client){
        Address address = addressRepository.findById(client.getAddress().getCep()).orElseGet(
                () -> {
                    Address newAddress = addressService.searchCep(client.getAddress().getCep());
                    addressRepository.save(newAddress);
                    return newAddress;
                }
        );
        client.setAddress(address);
        clientRepository.save(client);
    }
}
