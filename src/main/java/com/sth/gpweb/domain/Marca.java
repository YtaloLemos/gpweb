package com.sth.gpweb.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Marca.
 */
@Entity
@Table(name = "marca")
@Document(indexName = "marca")
public class Marca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "nm_fabricante", length = 35, nullable = false, unique = true)
    private String nmFabricante;

    @Size(max = 18)
    @Column(name = "cd_cgc", length = 18)
    private String cdCgc;

    @Size(max = 18)
    @Column(name = "cd_cgf", length = 18)
    private String cdCgf;

    @Column(name = "nn_numero")
    private Integer nnNumero;

    @Size(max = 20)
    @Column(name = "ds_complemento", length = 20)
    private String dsComplemento;

    @Size(max = 13)
    @Column(name = "cd_tel", length = 13)
    private String cdTel;

    @Size(max = 13)
    @Column(name = "cd_fax", length = 13)
    private String cdFax;

    @Column(name = "fl_inativo")
    private Boolean flInativo;

    @Size(max = 35)
    @Column(name = "nm_fantasia", length = 35)
    private String nmFantasia;

    @Column(name = "vl_comissao", precision=10, scale=2)
    private BigDecimal vlComissao;

    @Column(name = "dt_operacao")
    private LocalDate dtOperacao;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNmFabricante() {
        return nmFabricante;
    }

    public void setNmFabricante(String nmFabricante) {
        this.nmFabricante = nmFabricante;
    }

    public String getCdCgc() {
        return cdCgc;
    }

    public void setCdCgc(String cdCgc) {
        this.cdCgc = cdCgc;
    }

    public String getCdCgf() {
        return cdCgf;
    }

    public void setCdCgf(String cdCgf) {
        this.cdCgf = cdCgf;
    }

    public Integer getNnNumero() {
        return nnNumero;
    }

    public void setNnNumero(Integer nnNumero) {
        this.nnNumero = nnNumero;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }

    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
    }

    public String getCdTel() {
        return cdTel;
    }

    public void setCdTel(String cdTel) {
        this.cdTel = cdTel;
    }

    public String getCdFax() {
        return cdFax;
    }

    public void setCdFax(String cdFax) {
        this.cdFax = cdFax;
    }

    public Boolean isFlInativo() {
        return flInativo;
    }

    public void setFlInativo(Boolean flInativo) {
        this.flInativo = flInativo;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public BigDecimal getVlComissao() {
        return vlComissao;
    }

    public void setVlComissao(BigDecimal vlComissao) {
        this.vlComissao = vlComissao;
    }

    public LocalDate getDtOperacao() {
        return dtOperacao;
    }

    public void setDtOperacao(LocalDate dtOperacao) {
        this.dtOperacao = dtOperacao;
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Marca marca = (Marca) o;
        if(marca.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, marca.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Marca{" +
            "id=" + id +
            ", nmFabricante='" + nmFabricante + "'" +
            ", cdCgc='" + cdCgc + "'" +
            ", cdCgf='" + cdCgf + "'" +
            ", nnNumero='" + nnNumero + "'" +
            ", dsComplemento='" + dsComplemento + "'" +
            ", cdTel='" + cdTel + "'" +
            ", cdFax='" + cdFax + "'" +
            ", flInativo='" + flInativo + "'" +
            ", nmFantasia='" + nmFantasia + "'" +
            ", vlComissao='" + vlComissao + "'" +
            ", dtOperacao='" + dtOperacao + "'" +           
            '}';
    }
}
