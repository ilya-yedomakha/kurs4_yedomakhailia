package stu.cn.ua.kurs4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import stu.cn.ua.kurs4.model.domain.documents.superclass.Document;
import stu.cn.ua.kurs4.model.domain.people.Client;
import stu.cn.ua.kurs4.model.services.people.ClientService;

import java.util.List;
import java.util.Set;

@RestController
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("client/{id}")
    public Client getClient(@PathVariable Long id){
        return clientService.findById(id);
    }

    @GetMapping("/client/getAll")
    public List<Client> getClients(){
        return clientService.findAll();
    }

    @PostMapping("client/")
    public Client saveClient(@RequestBody Client client){
        return clientService.save(client);
    }

    @GetMapping("/client/{id}/getDocuments/")
    public List<Document> getDocumentsByClientId(@PathVariable Long id){
        return clientService.getDocumentsByClientId(id);
    }
}
