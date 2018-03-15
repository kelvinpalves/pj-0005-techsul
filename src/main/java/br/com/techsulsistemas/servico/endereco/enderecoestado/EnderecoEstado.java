/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.endereco.enderecoestado;

import br.com.techsulsistemas.servico.endereco.enderecocidade.EnderecoCidade;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "endereco_estado")
@NamedQueries({
    @NamedQuery(name = "EnderecoEstado.findAll", query = "SELECT e FROM EnderecoEstado e")})
public class EnderecoEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_endereco_estado")
    private Integer idEnderecoEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "sigla")
    private String sigla;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enderecoEstado")
    private List<EnderecoCidade> enderecoCidadeList;

    public EnderecoEstado() {
    }

    public EnderecoEstado(Integer idEnderecoEstado) {
        this.idEnderecoEstado = idEnderecoEstado;
    }

    public EnderecoEstado(Integer idEnderecoEstado, String nome, String sigla) {
        this.idEnderecoEstado = idEnderecoEstado;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Integer getIdEnderecoEstado() {
        return idEnderecoEstado;
    }

    public void setIdEnderecoEstado(Integer idEnderecoEstado) {
        this.idEnderecoEstado = idEnderecoEstado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public List<EnderecoCidade> getEnderecoCidadeList() {
        return enderecoCidadeList;
    }

    public void setEnderecoCidadeList(List<EnderecoCidade> enderecoCidadeList) {
        this.enderecoCidadeList = enderecoCidadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEnderecoEstado != null ? idEnderecoEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnderecoEstado)) {
            return false;
        }
        EnderecoEstado other = (EnderecoEstado) object;
        if ((this.idEnderecoEstado == null && other.idEnderecoEstado != null) || (this.idEnderecoEstado != null && !this.idEnderecoEstado.equals(other.idEnderecoEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.empresa.empresaregimetributario.EnderecoEstado[ idEnderecoEstado=" + idEnderecoEstado + " ]";
    }
    
}
