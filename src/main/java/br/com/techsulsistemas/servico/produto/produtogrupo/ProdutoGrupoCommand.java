/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.produto.produtogrupo;

import java.math.BigDecimal;
import lombok.Data;

/**
 *
 * @author kelvin
 */

@Data
public class ProdutoGrupoCommand {
    private Integer id;
    private String descricao;
    private BigDecimal percentualLucro;
}
