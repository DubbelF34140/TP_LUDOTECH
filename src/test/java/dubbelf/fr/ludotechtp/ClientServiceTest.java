package dubbelf.fr.ludotechtp;


import dubbelf.fr.ludotechtp.bll.ClientService;
import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.dal.ClientRepositorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepositorie clientRepositorie;

    @InjectMocks
    private ClientService clientService;
}

