package org.awknet.commons.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
/*import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;*/
import javax.persistence.Table;

@Entity
@Table(name = "TUsuario", catalog = "DBSigerar")
public class User implements java.io.Serializable {

    private static final long serialVersionUID = 6450793412169953366L;
    private Long intIdUsuario;
/*    private Department department;*/
    private String strNomeUsuario;
    private String strCargoUsuario;
    private String strTelefoneUsuario;
    private String strEmailUsuario;
    private String strConhecimentoUsuario;
    private String strLoginUsuario;
    private String strSenhaUsuario;
    private Boolean blAdministradorUsuario;

    public User() {
    }

    public User(Long intIdUsuario, /*Department fkIntIdDepartamento,*/ Boolean blAdministradorUsuario) {
        this.intIdUsuario = intIdUsuario;
        /*this.department = fkIntIdDepartamento;*/
        this.blAdministradorUsuario = blAdministradorUsuario;
    }

    public User(Long intIdUsuario, /*Department fkIntIdDepartamento,*/ String strNomeUsuario, String strCargoUsuario, String strTelefoneUsuario, String strEmailUsuario, String strConhecimentoUsuario, String strLoginUsuario, String strSenhaUsuario, Boolean blAdministradorUsuario) {
        this.intIdUsuario = intIdUsuario;
        /*this.department = fkIntIdDepartamento;*/
        this.strNomeUsuario = strNomeUsuario;
        this.strCargoUsuario = strCargoUsuario;
        this.strTelefoneUsuario = strTelefoneUsuario;
        this.strEmailUsuario = strEmailUsuario;
        this.strConhecimentoUsuario = strConhecimentoUsuario;
        this.strLoginUsuario = strLoginUsuario;
        this.strSenhaUsuario = strSenhaUsuario;
        this.blAdministradorUsuario = blAdministradorUsuario;
    }

    @Id
    @Column(name = "INT_idUSUARIO", unique = true, nullable = false)
    public Long getIntIdUsuario() {
        return this.intIdUsuario;
    }

    public void setIntIdUsuario(Long intIdUsuario) {
        this.intIdUsuario = intIdUsuario;
    }

    /*@ManyToOne
    @JoinColumn(name = "fk_INT_idDEPARTAMENTO", nullable = false)
    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department fkIntIdDepartamento) {
        this.department = fkIntIdDepartamento;
    }*/

    @Column(name = "STR_Nome_USUARIO", length = 40)
    public String getStrNomeUsuario() {
        return this.strNomeUsuario;
    }

    public void setStrNomeUsuario(String strNomeUsuario) {
        this.strNomeUsuario = strNomeUsuario;
    }

    @Column(name = "STR_Cargo_USUARIO", length = 40)
    public String getStrCargoUsuario() {
        return this.strCargoUsuario;
    }

    public void setStrCargoUsuario(String strCargoUsuario) {
        this.strCargoUsuario = strCargoUsuario;
    }

    @Column(name = "STR_Telefone_USUARIO", length = 30)
    public String getStrTelefoneUsuario() {
        return this.strTelefoneUsuario;
    }

    public void setStrTelefoneUsuario(String strTelefoneUsuario) {
        this.strTelefoneUsuario = strTelefoneUsuario;
    }

    @Column(name = "STR_Email_USUARIO", length = 128)
    public String getStrEmailUsuario() {
        return this.strEmailUsuario;
    }

    public void setStrEmailUsuario(String strEmailUsuario) {
        this.strEmailUsuario = strEmailUsuario;
    }

    @Column(name = "STR_Conhecimento_USUARIO", length = 40)
    public String getStrConhecimentoUsuario() {
        return this.strConhecimentoUsuario;
    }

    public void setStrConhecimentoUsuario(String strConhecimentoUsuario) {
        this.strConhecimentoUsuario = strConhecimentoUsuario;
    }

    @Column(name = "STR_Login_USUARIO", length = 30)
    public String getStrLoginUsuario() {
        return this.strLoginUsuario;
    }

    public void setStrLoginUsuario(String strLoginUsuario) {
        this.strLoginUsuario = strLoginUsuario;
    }

    @Column(name = "STR_Senha_USUARIO", length = 40)
    public String getStrSenhaUsuario() {
        return this.strSenhaUsuario;
    }

    public void setStrSenhaUsuario(String strSenhaUsuario) {
        this.strSenhaUsuario = strSenhaUsuario;
    }

    @Column(name = "BL_Administrador_USUARIO", nullable = false)
    public Boolean getBlAdministradorUsuario() {
        return this.blAdministradorUsuario;
    }

    public void setBlAdministradorUsuario(Boolean blAdministradorUsuario) {
        this.blAdministradorUsuario = blAdministradorUsuario;
    }
}
