/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import br.com.techsulsistemas.servico.config.ForgeController;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kelvin
 */

@Stateless
@Path("basico/empresa/empresa")
public class EmpresaController extends ForgeController {
    
    private final EmpresaServico servico;

    public EmpresaController() {
        this.servico = new EmpresaServico();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(EmpresaCommand command) {
        try {
            EmpresaDto dto = EmpresaDto.builder()
                    .razaoSocial(command.getRazaoSocial())
                    .nomeFantasia(command.getNomeFantasia())
                    .endereco(command.getEndereco())
                    .numero(command.getNumero())
                    .complemento(command.getComplemento())
                    .cidade(command.getCidade())
                    .cep(command.getCep())
                    .bairro(command.getBairro())
                    .inscricaoEstadual(command.getInscricaoEstadual())
                    .celular(command.getCelular())
                    .crt(command.getCrt())
                    .baseIcms(command.getBaseIcms())
                    .baseIcmsSt(command.getBaseIcmsSt())
                    .aliquotaCredito(command.getAliquotaCredito())
                    .cnpj(command.getCnpj())
                    .logo(command.getLogo())
                    .contador(command.getContador())
                    .build();
              
            servico.criar(dto);
            addMessage("Sucesso ao salvar o grupo de produto");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @PUT
    @Path("{id:\\d+}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, EmpresaCommand command) {
        try {
//            ProdutoGrupoDto dto = ProdutoGrupoDto.builder()
//                    .id(id)
//                    .descricao(command.getDescricao())
//                    .percentualLucro(command.getPercentualLucro())
//                    .build();
//            
//            servico.atualizar(dto);
//            addMessage("Sucesso ao atualizar o grupo de produto");
        } catch (Exception ex) {
            addError(ex);
        }
        
        return build();
    }

    @DELETE
    @Path("{id:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        try {
//            servico.remover(id);
//            addMessage("Sucesso ao remover o registro");
        } catch (Exception ex) {
            addError("Ocorreu um erro ao remover o registro");
        }

        return build();
    }

    @GET
    @Path("{id:\\d+}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Integer id) {
        try {
//            ProdutoGrupoDto dto = servico.carregar(id);
//            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("primeiro")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findFirst() {
        try {
//            ProdutoGrupoDto dto = servico.primeiro();
//            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("ultimo")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findLast() {
        try {
//            ProdutoGrupoDto dto = servico.ultimo();
//            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("proximo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response proximo(@PathParam("id") Integer id) {
        try {
//            ProdutoGrupoDto dto = servico.proximo(id);
//            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("anterior/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response anterior(@PathParam("id") Integer id) {
        try {
//            ProdutoGrupoDto dto = servico.anterior(id);
//            addData(dto);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar o objeto");
        }
        
        return build();
    }
    
    @GET
    @Path("autocomplete/{filtro}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response autocomplete(@PathParam("filtro") String filtro) {
        try {
//            List<AutoCompleteDto> lista = servico.autocomplete(filtro);
//            addData(lista);
        } catch (Exception ex) {
            addError("Ocorreu um erro ao carregar a lista");
        }
        
        return build();
    }
}