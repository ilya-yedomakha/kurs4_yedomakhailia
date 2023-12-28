package stu.cn.ua.kurs4.model.services.people;

import org.springframework.stereotype.Service;
import stu.cn.ua.kurs4.model.domain.documents.superclass.Document;
import stu.cn.ua.kurs4.model.domain.people.Client;
import stu.cn.ua.kurs4.repositories.QueueRowRepo;
import stu.cn.ua.kurs4.repositories.documents.*;
import stu.cn.ua.kurs4.repositories.people.ClientRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final RegistrationRepo registrationRepo;

    private final DriverLicenseRepo driverLicenseRepo;

    private final PassportRepo passportRepo;

    private final BirthSertRepo birthSertRepo;

    private final VisaRepo visaRepo;

    private final ClientRepo clientRepo;

    private final QueueRowRepo queueRowRepo;


    public ClientService(RegistrationRepo registrationRepo, DriverLicenseRepo driverLicenseRepo, PassportRepo passportRepo, BirthSertRepo birthSertRepo, VisaRepo visaRepo, ClientRepo clientRepo, QueueRowRepo queueRowRepo) {
        this.registrationRepo = registrationRepo;
        this.driverLicenseRepo = driverLicenseRepo;
        this.passportRepo = passportRepo;
        this.birthSertRepo = birthSertRepo;
        this.visaRepo = visaRepo;
        this.clientRepo = clientRepo;
        this.queueRowRepo = queueRowRepo;
    }

    public Client findById(Long id){
        Optional<Client> clientOptional = clientRepo.findById(id);
        return clientOptional.orElse(null);
    }

    public List<Client> findAll(){
        List<Client> clients = clientRepo.findAll();
        return clientRepo.findAll();
    }

    public Client save(Client client){

        return clientRepo.save(client);
    }

    public void deleteById(Long id){
        clientRepo.deleteById(id);
    }

    public Client update(Client client, Long id){
        Client client1 = clientRepo.getById(id);
        //client.set(client1.get)
        clientRepo.save(client1);
        return client;
    }

    public List<Document> getDocumentsByClientId(Long id){
        Client client = clientRepo.getById(id);

        List<Document> documents = new ArrayList<>();
        documents.add(client.getBirthSertificate());
        documents.add(client.getDriverLicense());
        documents.add(client.getPassport());
        documents.add(client.getRegistration());
        documents.add(client.getVisa());



        return documents;
    }

}
