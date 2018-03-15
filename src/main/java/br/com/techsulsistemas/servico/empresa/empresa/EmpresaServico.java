/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresa;

import br.com.techsulsistemas.servico.config.banco.DAO;
import br.com.techsulsistemas.servico.config.comum.AutoCompleteDto;
import br.com.techsulsistemas.servico.empresa.empresaescritoriocontabil.EmpresaEscritorioContabil;
import br.com.techsulsistemas.servico.empresa.empresaregimetributario.EmpresaCodigoRegimeTributario;
import br.com.techsulsistemas.servico.endereco.enderecocidade.EnderecoCidade;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kelvin
 */
public class EmpresaServico {
    
    private final EmpresaDao dao;
    
    public EmpresaServico() {
        dao = new EmpresaDao();
    }
    
    public void atualizar(EmpresaDto dto) throws Exception {
//        validarEntrada(dto);
//        
//        try {
//            ProdutoGrupo model = dao.encontrar(dto.getId());
//            
//            model.setDescricao(dto.getDescricao());
//            model.setPercentualLucro(dto.getPercentualLucro());
//            model.setDescricaoCompleta(model.getIdProdutoGrupo() + " - " + model.getDescricao());
//            
//            DAO.begin();
//            DAO.getEM().merge(model);
//            DAO.commit();
//        } catch (Exception ex) {
//            DAO.rollback();
//            ex.printStackTrace();
//            throw ex;
//        }
    }
    
//    public List<AutoCompleteDto> autocomplete(String valor) throws Exception {
//        try {
//            List<AutoCompleteDto> lista = new ArrayList<>();
//            List<ProdutoGrupo> dados = dao.buscarSemelhantes("descricaoCompleta", valor);
//            
//            for (ProdutoGrupo model : dados) {
//                lista.add(criarConversorAutoCompleteDto(model));
//            }
//            
//            return lista;
//        } catch (Exception ex) {
//            return Collections.EMPTY_LIST;
//        }
//    }
    
//    public EmpresaDto carregar(Integer id) throws Exception {
//        return criarConversorDto(dao.encontrar(id));
//    }
    
//    public EmpresaDto primeiro() throws Exception {
//        return criarConversorDto(dao.encontrarPrimeiro("idProdutoGrupo"));
//    }
//    
//    public EmpresaDto ultimo() throws Exception {
//        return criarConversorDto(dao.encontrarUltimo("idProdutoGrupo"));
//    }
//    
//    public EmpresaDto proximo(Integer ultimoId) throws Exception {
//        return criarConversorDto(dao.proximo("idProdutoGrupo", ultimoId));
//    }
//    
//    public EmpresaDto anterior(Integer ultimoId) throws Exception {
//        return criarConversorDto(dao.anterior("idProdutoGrupo", ultimoId));
//    }
    
    public void criar(EmpresaDto dto) throws Exception {
        validarEntrada(dto);
        
        try {
            Empresa model = new Empresa();
               
            model.setRazaoSocial(dto.getRazaoSocial());
            model.setNomeFantasia(dto.getNomeFantasia());
            model.setEndereco(dto.getEndereco());
            model.setNumero(dto.getNumero());
            model.setComplemento(dto.getComplemento());
            model.setEnderecoCidade(dto.getCidade() == null ? null : new EnderecoCidade(dto.getCidade()));
            model.setCep(dto.getCep());
            model.setBairro(dto.getBairro());
            model.setInscricaoEstadual(dto.getInscricaoEstadual());
            model.setCelular(dto.getCelular());
            model.setEmpresaCodigoRegimeTributario(new EmpresaCodigoRegimeTributario(dto.getCrt()));
            model.setBaseIcms(dto.getBaseIcms());
            model.setBaseIcmsSt(dto.getBaseIcmsSt());
            model.setAliquotaCredito(dto.getAliquotaCredito());
            model.setCnpj(dto.getCnpj());
//            model.setLogo(logo);
            model.setEmpresaEscritorioContabil(dto.getContador() == null ? null : new EmpresaEscritorioContabil(dto.getContador()));
            
            DAO.begin();
            DAO.getEM().persist(model);
            DAO.commit();
        } catch (Exception ex) {
            DAO.rollback();
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public void remover(Integer id) throws Exception {
        dao.remover(id);
    }
    
    private void validarEntrada(EmpresaDto dto) throws Exception {
        if (StringUtils.isBlank(dto.getRazaoSocial())) {
            throw new Exception("O atributo razão social é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getNomeFantasia())) {
            throw new Exception("O atributo nome fantasia é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getInscricaoEstadual())) {
            throw new Exception("O atributo inscrição estadual é obrigatório.");
        }
        
        if (StringUtils.isBlank(dto.getCnpj())) {
            throw new Exception("O atributo CNPJ é obrigatório.");
        }
        
        if (dto.getCrt() == null) {
            throw new Exception("O atributo CRT é obrigatório.");
        } 
        
        if (dto.getBaseIcms() == null) {
            throw new Exception("O atributo Base ICMS é obrigatório.");
        }
        
        if (dto.getBaseIcmsSt() == null) {
            throw new Exception("O atributo Base ICMS ST é obrigatório.");
        }
        
        if (dto.getAliquotaCredito() == null) {
            throw new Exception("O atributo Aliquota de Crédito é obrigatório.");
        }
        
        if (dao.isNotUnique("empresa", "cnpj", dto.getCnpj(), dto.getId())) {
            throw new Exception("O CNPJ informado já consta na base de dados.");
        }
    }
    
//    private EmpresaDto criarConversorDto(Empresa model) throws Exception {
//        return ProdutoGrupoDto.builder()
//                    .id(model.getIdProdutoGrupo())
//                    .descricao(model.getDescricao())
//                    .percentualLucro(model.getPercentualLucro())
//                    .build();
//    }
    
//    private AutoCompleteDto criarConversorAutoCompleteDto(ProdutoGrupo model) throws Exception {
//        return AutoCompleteDto.builder()
//                .id(model.getIdProdutoGrupo())
//                .descricao(model.getDescricao())
//                .build();
//    }
    
}
