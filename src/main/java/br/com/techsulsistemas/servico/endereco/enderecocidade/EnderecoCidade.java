/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.endereco.enderecocidade;

import br.com.techsulsistemas.servico.empresa.empresa.Empresa;
import br.com.techsulsistemas.servico.endereco.enderecoestado.EnderecoEstado;
import br.com.techsulsistemas.servico.endereco.enderecopais.EnderecoPais;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kelvin
 */
@Entity
@Table(name = "endereco_cidade")
@NamedQueries({
    @NamedQuery(name = "EnderecoCidade.findAll", query = "SELECT e FROM EnderecoCidade e")})
public class EnderecoCidade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_endereco_cidade")
    private Integer idEnderecoCidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Column(name = "codigo_ibge")
    private Integer codigoIbge;
    @JoinColumn(name = "id_endereco_estado", referencedColumnName = "id_endereco_estado")
    @ManyToOne(optional = false)
    private EnderecoEstado enderecoEstado;
    @JoinColumn(name = "id_endereco_pais", referencedColumnName = "id_endereco_pais")
    @ManyToOne(optional = false)
    private EnderecoPais enderecoPais;
    @OneToMany(mappedBy = "enderecoCidade")
    private List<Empresa> empresaList;

    public EnderecoCidade() {
    }

    public EnderecoCidade(Integer idEnderecoCidade) {
        this.idEnderecoCidade = idEnderecoCidade;
    }

    public EnderecoCidade(Integer idEnderecoCidade, String nome) {
        this.idEnderecoCidade = idEnderecoCidade;
        this.nome = nome;
    }

    public Integer getIdEnderecoCidade() {
        return idEnderecoCidade;
    }

    public void setIdEnderecoCidade(Integer idEnderecoCidade) {
        this.idEnderecoCidade = idEnderecoCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(Integer codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public EnderecoEstado getEnderecoEstado() {
        return enderecoEstado;
    }

    public void setEnderecoEstado(EnderecoEstado enderecoEstado) {
        this.enderecoEstado = enderecoEstado;
    }

    public EnderecoPais getEnderecoPais() {
        return enderecoPais;
    }

    public void setEnderecoPais(EnderecoPais enderecoPais) {
        this.enderecoPais = enderecoPais;
    }

    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnderecoCidade != null ? idEnderecoCidade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnderecoCidade)) {
            return false;
        }
        EnderecoCidade other = (EnderecoCidade) object;
        if ((this.idEnderecoCidade == null && other.idEnderecoCidade != null) || (this.idEnderecoCidade != null && !this.idEnderecoCidade.equals(other.idEnderecoCidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.empresa.empresaregimetributario.EnderecoCidade[ idEnderecoCidade=" + idEnderecoCidade + " ]";
    }
    
}
