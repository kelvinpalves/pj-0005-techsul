/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.endereco.enderecopais;

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
@Table(name = "endereco_pais")
@NamedQueries({
    @NamedQuery(name = "EnderecoPais.findAll", query = "SELECT e FROM EnderecoPais e")})
public class EnderecoPais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_endereco_pais")
    private Integer idEnderecoPais;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo")
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enderecoPais")
    private List<EnderecoCidade> enderecoCidadeList;

    public EnderecoPais() {
    }

    public EnderecoPais(Integer idEnderecoPais) {
        this.idEnderecoPais = idEnderecoPais;
    }

    public EnderecoPais(Integer idEnderecoPais, int codigo, String nome) {
        this.idEnderecoPais = idEnderecoPais;
        this.codigo = codigo;
        this.nome = nome;
    }

    public Integer getIdEnderecoPais() {
        return idEnderecoPais;
    }

    public void setIdEnderecoPais(Integer idEnderecoPais) {
        this.idEnderecoPais = idEnderecoPais;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        hash += (idEnderecoPais != null ? idEnderecoPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EnderecoPais)) {
            return false;
        }
        EnderecoPais other = (EnderecoPais) object;
        if ((this.idEnderecoPais == null && other.idEnderecoPais != null) || (this.idEnderecoPais != null && !this.idEnderecoPais.equals(other.idEnderecoPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.empresa.empresaregimetributario.EnderecoPais[ idEnderecoPais=" + idEnderecoPais + " ]";
    }
    
}
