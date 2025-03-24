package dubbelf.fr.ludotechtp.api;

import dubbelf.fr.ludotechtp.bll.ClientService;
import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.dto.ClientDTO;
import dubbelf.fr.ludotechtp.dto.EditClientDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> ajouterClient(@RequestBody ClientDTO client) {
        Client nouveauClient = clientService.ajouterClient(client);
        return ResponseEntity.ok(nouveauClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerClient(@PathVariable int id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build(); // HTTP 204 (No Content)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> modifierClient(@PathVariable int id, @RequestBody EditClientDTO client) {
        Client clientModifie = clientService.modifierClient(id, client);
        return ResponseEntity.ok(clientModifie);
    }

    @GetMapping
    public ResponseEntity<List<Client>> trouvertoutlesClients() {
        List<Client> clientList = clientService.trouvertoutlesclients();
        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/nom/{name}") // Changement du chemin pour éviter un conflit avec l'ID
    public ResponseEntity<Optional<List<Client>>> trouverClientParNom(@PathVariable String name) {
        Optional<List<Client>> clients = clientService.trouverClientParName(name);

        if (clients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clients);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> trouverClientParId(@PathVariable int id) {
        Optional<Client> client = clientService.trouverClientParId(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());


    }
}
