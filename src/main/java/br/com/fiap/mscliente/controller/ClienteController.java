package br.com.fiap.mscliente.controller;

import br.com.fiap.mscliente.model.Cliente;
import br.com.fiap.mscliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarCliente(){
        return clienteService.listarCliente();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<?> listarUmCliente(@PathVariable Integer clienteId){
        return clienteService.listarUmCliente(clienteId);
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente){
        return clienteService.cadastrarCliente(cliente);
    }

    @PutMapping("/{clienteId}")
    public Cliente atualizarCliente(@PathVariable Integer clienteId,
                                    @RequestBody Cliente novoCliente){
        return clienteService.atualizarCliente(clienteId, novoCliente);
    }

    @DeleteMapping("/{clienteId}")
    public void excluirCliente(@PathVariable Integer clienteId){
        clienteService.exluirCliente(clienteId);
    }
}