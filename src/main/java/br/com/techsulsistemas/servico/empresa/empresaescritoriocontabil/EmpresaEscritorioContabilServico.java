/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresaescritoriocontabil;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EmpresaEscritorioContabilServico {
    
    private final EmpresaEscritorioContabilDao dao;
    
    public EmpresaEscritorioContabilServico() {
        dao = new EmpresaEscritorioContabilDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<EmpresaEscritorioContabil> dados = dao.findAll();
            
            for (EmpresaEscritorioContabil model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(EmpresaEscritorioContabil model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdEmpresaEscritorioContabil())
                .descricao(model.getContador())
                .build();
    }
    
}
