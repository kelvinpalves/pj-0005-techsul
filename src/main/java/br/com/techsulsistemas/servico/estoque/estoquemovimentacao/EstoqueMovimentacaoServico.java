/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.estoque.estoquemovimentacao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EstoqueMovimentacaoServico {
    
    private final EstoqueMovimentacaoDao dao;
    
    public EstoqueMovimentacaoServico() {
        this.dao = new EstoqueMovimentacaoDao();
    }
    
    public List<EstoqueMovimentacaoDtoUltimos> carregarUltimasMovimentacoesPorProduto(Integer produto) throws Exception {
        try {
            List<EstoqueMovimentacao> lista = dao.carregarUltimasMovimentacoesPorProduto(produto);
            List<EstoqueMovimentacaoDtoUltimos> retorno = new ArrayList<>();
            
            for (EstoqueMovimentacao mvt : lista) {
                
                Double quantidade = mvt.getQuantidade().doubleValue();
                
                if (!mvt.getEstoqueMovimentacaoTipo().getFgSoma()) {
                    quantidade *= -1;
                }
                
                EstoqueMovimentacaoDtoUltimos dto = EstoqueMovimentacaoDtoUltimos.builder()
                        .id(mvt.getIdEstoqueMovimentacao())
                        .produto(mvt.getProduto().getDescricao())
                        .quantidade(quantidade)
                        .valor(mvt.getPrecoVenda() == null ? new Double(0) : mvt.getPrecoVenda().doubleValue())
                        .build();
                        
                retorno.add(dto);
            }
            
            return retorno;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
