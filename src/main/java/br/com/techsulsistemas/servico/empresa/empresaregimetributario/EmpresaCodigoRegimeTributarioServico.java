/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresaregimetributario;

import br.com.techsulsistemas.servico.config.comum.ComboDto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author kelvin
 */
public class EmpresaCodigoRegimeTributarioServico {
    private final EmpresaCodigoRegimeTributarioDao dao;
    
    public EmpresaCodigoRegimeTributarioServico() {
        dao = new EmpresaCodigoRegimeTributarioDao();
    }
    
    public List<ComboDto> combo() throws Exception {
        try {
            List<ComboDto> lista = new ArrayList<>();
            List<EmpresaCodigoRegimeTributario> dados = dao.findAll();
            
            for (EmpresaCodigoRegimeTributario model : dados) {
                lista.add(criarConversorComboDto(model));
            }
            
            return lista;
        } catch (Exception ex) {
            return Collections.EMPTY_LIST;
        }
    }
    
    private ComboDto criarConversorComboDto(EmpresaCodigoRegimeTributario model) throws Exception {
        return ComboDto.builder()
                .id(model.getIdEmpresaCodigoRegimeTributario())
                .descricao(model.getDescricao())
                .build();
    }
    
}
