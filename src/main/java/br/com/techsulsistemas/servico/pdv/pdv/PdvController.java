/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.pdv.pdv;

import br.com.techsulsistemas.servico.config.ForgeController;
import br.com.techsulsistemas.servico.produto.produto.ProdutoDto;
import br.com.techsulsistemas.servico.produto.produto.ProdutoServico;
import javax.persistence.NoResultException;
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

@Path("basico/pdv/pdv")
public class PdvController extends ForgeController {
    
    private final PdvServico servico;
    private final ProdutoServico produtoServico;
    
    public PdvController() {
        this.servico = new PdvServico();
        this.produtoServico = new ProdutoServico();
    }
    
    @GET
    @Path("produto/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") String codigo) {
        try {
            ProdutoDto dto = produtoServico.carregar(codigo);
            addData(dto);
        } catch (NoResultException ex) {
            addError("Produto não encontrado.");
        } catch (Exception ex) {
            ex.printStackTrace();
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
}