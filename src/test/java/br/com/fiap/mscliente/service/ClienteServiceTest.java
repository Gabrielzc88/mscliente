package br.com.fiap.mscliente.service;

import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Cliente Teste");
        cliente.setEmail("cliente@teste.com");
        cliente.setTelefone("123456789");
        cliente.setCpf("123.456.789-00");
        cliente.setEstado("SP");
        cliente.setCidade("São Paulo");
        cliente.setEndereco("Rua Teste, 123");
        cliente.setCep("12345-678");
    }

    @Test
    void testListarCliente() {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.listarCliente();

        assertEquals(1, result.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testCadastrarCliente() {
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.cadastrarCliente(cliente);

        assertEquals(cliente.getNome(), result.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testListarUmCliente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        ResponseEntity<?> result = clienteService.listarUmCliente(1);

        assertEquals(200, result.getStatusCodeValue());
        verify(clienteRepository, times(1)).findById(1);
    }

    @Test
    void testAtualizarCliente() {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Novo Cliente");

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.atualizarCliente(1, novoCliente);

        assertEquals("Novo Cliente", result.getNome());
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void testExcluirCliente() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        clienteService.exluirCliente(1);

        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).delete(cliente);
    }
}
