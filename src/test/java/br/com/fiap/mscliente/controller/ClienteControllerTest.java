package br.com.fiap.mscliente.controller;

import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private MockMvc mockMvc;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();

        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("Cliente Teste");
    }

    @Test
    void testListarCliente() throws Exception {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteService.listarCliente()).thenReturn(clientes);

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk());

        verify(clienteService, times(1)).listarCliente();
    }

//    @Test void testListarUmCliente() {
//        ResponseEntity<Cliente> a = ResponseEntity.ok(cliente);
//        when(clienteService.listarUmCliente(1)).thenReturn(a);
//
//        ResponseEntity<?> responseEntity = clienteController.listarUmCliente(1);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        verify(clienteService, times(1)).listarUmCliente(1);
//    }

    @Test
    void testAtualizarCliente() {
        Cliente novoCliente = new Cliente();
        novoCliente.setNome("Novo Cliente");

        when(clienteService.atualizarCliente(1, novoCliente)).thenReturn(novoCliente);

        Cliente response = clienteController.atualizarCliente(1, novoCliente);

        assertEquals("Novo Cliente", response.getNome());
        verify(clienteService, times(1)).atualizarCliente(1, novoCliente);
    }

    @Test
    void testExcluirCliente() {
        doNothing().when(clienteService).exluirCliente(1);

        clienteController.excluirCliente(1);

        verify(clienteService, times(1)).exluirCliente(1);
    }
}
