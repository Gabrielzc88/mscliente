//package br.com.fiap.mscliente.repository;
//
//import br.com.fiap.mscliente.model.Cliente;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest // Anotação para carregar apenas a camada de JPA e o banco de dados em memória
//public class ClienteRepositoryTest {
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    private Cliente cliente;
//
//    @BeforeEach
//    public void setUp() {
//        // Configuração inicial antes de cada teste
//        cliente = new Cliente();
//        cliente.setNome("João");
//        cliente.setEmail("joao@email.com");
//        cliente.setTelefone("123456789");
//        cliente.setCpf("12345678901");
//        cliente.setEstado("SP");
//        cliente.setCidade("São Paulo");
//        cliente.setEndereco("Rua A, 123");
//        cliente.setCep("01000000");
//    }
//
//    @Test
//    public void testSalvarCliente() {
//        // Salva o cliente no banco de dados em memória
//        Cliente clienteSalvo = clienteRepository.save(cliente);
//
//        assertNotNull(clienteSalvo.getId(), "O ID do cliente salvo não deve ser nulo");
//        assertEquals("João", clienteSalvo.getNome(), "O nome do cliente salvo deve ser 'João'");
//        assertEquals("joao@email.com", clienteSalvo.getEmail(), "O email do cliente salvo deve ser 'joao@email.com'");
//    }
//
//    @Test
//    public void testBuscarClientePorId() {
//        // Salva o cliente para garantir que existe no banco
//        clienteRepository.save(cliente);
//
//        // Busca o cliente pelo ID
//        Optional<Cliente> clienteEncontrado = clienteRepository.findById(cliente.getId());
//
//        assertTrue(clienteEncontrado.isPresent(), "O cliente deve ser encontrado no banco de dados");
//        assertEquals("João", clienteEncontrado.get().getNome(), "O nome do cliente encontrado deve ser 'João'");
//    }
//
//    @Test
//    public void testBuscarClientePorIdInexistente() {
//        // Tenta buscar um cliente que não existe
//        Optional<Cliente> clienteInexistente = clienteRepository.findById(999);
//
//        assertFalse(clienteInexistente.isPresent(), "O cliente com ID 999 não deve ser encontrado no banco de dados");
//    }
//
//    @Test
//    public void testAtualizarCliente() {
//        // Salva o cliente
//        Cliente clienteSalvo = clienteRepository.save(cliente);
//
//        // Atualiza o nome e email do cliente
//        clienteSalvo.setNome("João Atualizado");
//        clienteSalvo.setEmail("joao_atualizado@email.com");
//
//        // Salva as alterações
//        Cliente clienteAtualizado = clienteRepository.save(clienteSalvo);
//
//        // Verifica se as atualizações foram aplicadas corretamente
//        assertEquals("João Atualizado", clienteAtualizado.getNome(), "O nome do cliente deve ser 'João Atualizado'");
//        assertEquals("joao_atualizado@email.com", clienteAtualizado.getEmail(), "O email do cliente deve ser 'joao_atualizado@email.com'");
//    }
//
//    @Test
//    public void testDeletarCliente() {
//        // Salva o cliente
//        Cliente clienteSalvo = clienteRepository.save(cliente);
//
//        // Deleta o cliente
//        clienteRepository.delete(clienteSalvo);
//
//        // Verifica se o cliente foi deletado
//        Optional<Cliente> clienteDeletado = clienteRepository.findById(clienteSalvo.getId());
//        assertFalse(clienteDeletado.isPresent(), "O cliente deve ser deletado e não encontrado no banco de dados");
//    }
//
//    @Test
//    public void testDeletarClienteInexistente() {
//        // Tenta deletar um cliente que não existe
//        Cliente clienteInexistente = new Cliente();
//        clienteInexistente.setId(999);
//
//        // Verifica que o delete não vai lançar erro, apenas não encontrará o cliente
//        clienteRepository.delete(clienteInexistente);
//        Optional<Cliente> clienteDeletado = clienteRepository.findById(clienteInexistente.getId());
//        assertFalse(clienteDeletado.isPresent(), "O cliente inexistente não deve ser encontrado no banco de dados após a exclusão");
//    }
//}
