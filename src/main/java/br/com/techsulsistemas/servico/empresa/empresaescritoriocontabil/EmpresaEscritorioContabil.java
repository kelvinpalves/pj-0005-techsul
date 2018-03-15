/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.empresa.empresaescritoriocontabil;

import br.com.techsulsistemas.servico.empresa.empresa.Empresa;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "empresa_escritorio_contabil")
@NamedQueries({
    @NamedQuery(name = "EmpresaEscritorioContabil.findAll", query = "SELECT e FROM EmpresaEscritorioContabil e")})
public class EmpresaEscritorioContabil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa_escritorio_contabil")
    private Integer idEmpresaEscritorioContabil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nome")
    private String nome;
    @Size(max = 2147483647)
    @Column(name = "contador")
    private String contador;
    @Size(max = 2147483647)
    @Column(name = "telefone")
    private String telefone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "empresaEscritorioContabil")
    private List<Empresa> empresaList;

    public EmpresaEscritorioContabil() {
    }

    public EmpresaEscritorioContabil(Integer idEmpresaEscritorioContabil) {
        this.idEmpresaEscritorioContabil = idEmpresaEscritorioContabil;
    }

    public EmpresaEscritorioContabil(Integer idEmpresaEscritorioContabil, String nome) {
        this.idEmpresaEscritorioContabil = idEmpresaEscritorioContabil;
        this.nome = nome;
    }

    public Integer getIdEmpresaEscritorioContabil() {
        return idEmpresaEscritorioContabil;
    }

    public void setIdEmpresaEscritorioContabil(Integer idEmpresaEscritorioContabil) {
        this.idEmpresaEscritorioContabil = idEmpresaEscritorioContabil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        hash += (idEmpresaEscritorioContabil != null ? idEmpresaEscritorioContabil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmpresaEscritorioContabil)) {
            return false;
        }
        EmpresaEscritorioContabil other = (EmpresaEscritorioContabil) object;
        if ((this.idEmpresaEscritorioContabil == null && other.idEmpresaEscritorioContabil != null) || (this.idEmpresaEscritorioContabil != null && !this.idEmpresaEscritorioContabil.equals(other.idEmpresaEscritorioContabil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.techsulsistemas.servico.empresa.empresaregimetributario.EmpresaEscritorioContabil[ idEmpresaEscritorioContabil=" + idEmpresaEscritorioContabil + " ]";
    }
    
}
