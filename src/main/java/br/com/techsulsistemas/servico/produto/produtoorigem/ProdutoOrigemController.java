/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtoorigem;

import br.com.techsulsistemas.servico.config.ForgeController;
import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */

@Stateless
@Path("basico/produto/produto-origem")
public class ProdutoOrigemController extends ForgeController {
    private final ProdutoOrigemServico servico;

    public ProdutoOrigemController() {
        this.servico = new ProdutoOrigemServico();
    }
    
    @GET
    @Path("combo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response combo() {
        try {
            List<ComboDto> lista = servico.combo();
            addData(lista);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar os dados");
        }
        
        return build();
    }
}
