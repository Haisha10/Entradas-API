package com.upc.EntradasAPI.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.upc.EntradasAPI.model.Ticket;
import com.upc.EntradasAPI.repository.TicketRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public void run(String... args) throws Exception {
        // Leer el archivo de recursos
        Resource resource = new ClassPathResource("tickets.json");
        InputStream inputStream = resource.getInputStream();

        // Parsear el archivo de recursos y obtener datos
        ObjectMapper mapper = new ObjectMapper();
        List<Ticket> tickets = mapper.readValue(inputStream, new TypeReference<List<Ticket>>() {
        });

        // Verificar si los datos ya existen en la base de datos
        for (Ticket ticket : tickets) {
            Optional<Ticket> ticketOptional = ticketRepository.findById(ticket.getId());
            if (!ticketOptional.isPresent()) {
                ticketRepository.save(ticket);
            }
        }
    }
}
