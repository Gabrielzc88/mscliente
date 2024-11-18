package br.com.fiap.mscliente.service;

import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    public Cliente cadastrarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public ResponseEntity<?> listarUmCliente(Integer clienteId){
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        if (cliente != null){
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
    }

    public Cliente atualizarCliente(Integer clienteId, Cliente novoCliente){
        Cliente clienteExistente = clienteRepository.findById(clienteId).orElse(null);

        if (clienteExistente != null){
            clienteExistente.setNome(novoCliente.getNome());
            clienteExistente.setCpf(novoCliente.getCpf());
            clienteExistente.setEstado(novoCliente.getEstado());
            clienteExistente.setCidade(novoCliente.getCidade());
            clienteExistente.setEndereco(novoCliente.getEndereco());
            clienteExistente.setCep(novoCliente.getCep());

            return clienteRepository.save(clienteExistente);
        } else {
            throw new NoSuchElementException("Cliente não encontrado!");
        }
    }

    public void exluirCliente(Integer clienteId){
        Cliente clienteExistente = clienteRepository.findById(clienteId).orElse(null);

        if (clienteExistente != null){
            clienteRepository.delete(clienteExistente);
        } else {
            throw new NoSuchElementException("Produto não encontrado.");
        }
    }
}
