/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresaregimetributario;

import br.com.techsulsistemas.servico.empresa.empresa.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "empresa_codigo_regime_tributario")
@NamedQueries({
    @NamedQuery(name = "EmpresaCodigoRegimeTributario.findAll", query = "SELECT e FROM EmpresaCodigoRegimeTributario e")})
public class EmpresaCodigoRegimeTributario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa_codigo_regime_tributario")
    private Integer idEmpresaCodigoRegimeTributario;
    @Size(max = 2147483647)
    @Column(name = "descricao")
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaCodigoRegimeTributario")
    private List<Empresa> empresaList;

    public EmpresaCodigoRegimeTributario() {
    }

    public EmpresaCodigoRegimeTributario(Integer idEmpresaCodigoRegimeTributario) {
        this.idEmpresaCodigoRegimeTributario = idEmpresaCodigoRegimeTributario;
    }

    public Integer getIdEmpresaCodigoRegimeTributario() {
        return idEmpresaCodigoRegimeTributario;
    }

    public void setIdEmpresaCodigoRegimeTributario(Integer idEmpresaCodigoRegimeTributario) {
        this.idEmpresaCodigoRegimeTributario = idEmpresaCodigoRegimeTributario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        hash += (idEmpresaCodigoRegimeTributario != null ? idEmpresaCodigoRegimeTributario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaCodigoRegimeTributario)) {
            return false;
        }
        EmpresaCodigoRegimeTributario other = (EmpresaCodigoRegimeTributario) object;
        if ((this.idEmpresaCodigoRegimeTributario == null && other.idEmpresaCodigoRegimeTributario != null) || (this.idEmpresaCodigoRegimeTributario != null && !this.idEmpresaCodigoRegimeTributario.equals(other.idEmpresaCodigoRegimeTributario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.empresa.empresaregimetributario.EmpresaCodigoRegimeTributario[ idEmpresaCodigoRegimeTributario=" + idEmpresaCodigoRegimeTributario + " ]";
    }
    
}
