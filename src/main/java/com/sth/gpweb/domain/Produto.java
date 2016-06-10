package com.sth.gpweb.domain;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Produto.
 */
@Entity
@Table(name = "produto")
@Document(indexName = "produto")
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cd_produto")
    private Long cdProduto;

    @Size(max = 25)
    @Column(name = "cd_barras", length = 25)
    private String cdBarras;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nm_produto", length = 50, nullable = false)
    private String nmProduto;

    @Size(max = 8)
    @Column(name = "cd_ncm", length = 8)
    private String cdNcm;

    @Size(max = 20)
    @Column(name = "cd_ean", length = 20)
    private String cdEan;

    @Size(max = 9)
    @Column(name = "cd_anp", length = 9)
    private String cdAnp;

    @Size(max = 30)
    @Column(name = "ds_anp", length = 30)
    private String dsAnp;

    @Size(max = 25)
    @Column(name = "cd_conta_contabil", length = 25)
    private String cdContaContabil;

    @Size(max = 1)
    @Column(name = "materia_prima", length = 1)
    private String materiaPrima;

    @Column(name = "fl_balanca")
    private Boolean flBalanca;

    @Column(name = "fl_inativo")
    private Boolean flInativo;

    @Column(name = "fl_sngpc")
    private Boolean flSngpc;

    @Column(name = "fl_med_prolonga")
    private Boolean flMedProlonga;

    @Size(max = 30)
    @Column(name = "ds_class_terapeutica", length = 30)
    private String dsClassTerapeutica;

    @Column(name = "vl_real", precision=10, scale=2)
    private BigDecimal vlReal;

    @Column(name = "vl_estoque", precision=10, scale=2)
    private BigDecimal vlEstoque;

    @Size(max = 200)
    @Column(name = "ds_informacoes", length = 200)
    private String dsInformacoes;

    @OneToOne
    @JoinColumn(unique = true)
    private Grupo grupo;

    @OneToOne
    @JoinColumn(unique = true)
    private Marca marca;

    @OneToOne
    @JoinColumn(unique = true)
    private Unidade unidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Long cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getCdBarras() {
        return cdBarras;
    }

    public void setCdBarras(String cdBarras) {
        this.cdBarras = cdBarras;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public String getCdNcm() {
        return cdNcm;
    }

    public void setCdNcm(String cdNcm) {
        this.cdNcm = cdNcm;
    }

    public String getCdEan() {
        return cdEan;
    }

    public void setCdEan(String cdEan) {
        this.cdEan = cdEan;
    }

    public String getCdAnp() {
        return cdAnp;
    }

    public void setCdAnp(String cdAnp) {
        this.cdAnp = cdAnp;
    }

    public String getDsAnp() {
        return dsAnp;
    }

    public void setDsAnp(String dsAnp) {
        this.dsAnp = dsAnp;
    }

    public String getCdContaContabil() {
        return cdContaContabil;
    }

    public void setCdContaContabil(String cdContaContabil) {
        this.cdContaContabil = cdContaContabil;
    }

    public String getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(String materiaPrima) {
        this.materiaPrima = materiaPrima;
    }

    public Boolean isFlBalanca() {
        return flBalanca;
    }

    public void setFlBalanca(Boolean flBalanca) {
        this.flBalanca = flBalanca;
    }

    public Boolean isFlInativo() {
        return flInativo;
    }

    public void setFlInativo(Boolean flInativo) {
        this.flInativo = flInativo;
    }

    public Boolean isFlSngpc() {
        return flSngpc;
    }

    public void setFlSngpc(Boolean flSngpc) {
        this.flSngpc = flSngpc;
    }

    public Boolean isFlMedProlonga() {
        return flMedProlonga;
    }

    public void setFlMedProlonga(Boolean flMedProlonga) {
        this.flMedProlonga = flMedProlonga;
    }

    public String getDsClassTerapeutica() {
        return dsClassTerapeutica;
    }

    public void setDsClassTerapeutica(String dsClassTerapeutica) {
        this.dsClassTerapeutica = dsClassTerapeutica;
    }

    public BigDecimal getVlReal() {
        return vlReal;
    }

    public void setVlReal(BigDecimal vlReal) {
        this.vlReal = vlReal;
    }

    public BigDecimal getVlEstoque() {
        return vlEstoque;
    }

    public void setVlEstoque(BigDecimal vlEstoque) {
        this.vlEstoque = vlEstoque;
    }

    public String getDsInformacoes() {
        return dsInformacoes;
    }

    public void setDsInformacoes(String dsInformacoes) {
        this.dsInformacoes = dsInformacoes;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Produto produto = (Produto) o;
        if(produto.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
            "id=" + id +
            ", cdProduto='" + cdProduto + "'" +
            ", cdBarras='" + cdBarras + "'" +
            ", nmProduto='" + nmProduto + "'" +
            ", cdNcm='" + cdNcm + "'" +
            ", cdEan='" + cdEan + "'" +
            ", cdAnp='" + cdAnp + "'" +
            ", dsAnp='" + dsAnp + "'" +
            ", cdContaContabil='" + cdContaContabil + "'" +
            ", materiaPrima='" + materiaPrima + "'" +
            ", flBalanca='" + flBalanca + "'" +
            ", flInativo='" + flInativo + "'" +
            ", flSngpc='" + flSngpc + "'" +
            ", flMedProlonga='" + flMedProlonga + "'" +
            ", dsClassTerapeutica='" + dsClassTerapeutica + "'" +
            ", vlReal='" + vlReal + "'" +
            ", vlEstoque='" + vlEstoque + "'" +
            ", dsInformacoes='" + dsInformacoes + "'" +
            '}';
    }
}
