/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import br.com.techsulsistemas.servico.config.ForgeController;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */

@Path("basico/estoque/estoque-movimentacao")
public class EstoqueMovimentacaoController extends ForgeController {
    
    private final EstoqueMovimentacaoServico servico;
    
    public EstoqueMovimentacaoController() {
        this.servico = new EstoqueMovimentacaoServico();
    }
    
    @GET
    @Path("ultimos/produto/{id:\\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response combo(@PathParam("id") Integer produto) {
        try {
            addData(servico.carregarUltimasMovimentacoesPorProduto(produto));
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar as últimas movimentações.");
        }
        
        return build();
    }
    
}
