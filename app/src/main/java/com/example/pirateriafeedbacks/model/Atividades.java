package com.example.pirateriafeedbacks.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Atividades implements Serializable {
    @SerializedName("idAtividade")
    private int idAtividade;
    @SerializedName("tituloAtividade")
    private String tituloAtividade;
    @SerializedName("descricaoAtividade")
    private String descricaoAtividade;
    @SerializedName("dataAtividade")
    private String dataAtividade;

    @SerializedName("statusAtividade")
    private String statusAtividade;

    @SerializedName("acaoRealizada")
    private String acaoRealizada;

    public Atividades() {
        this.idAtividade = idAtividade;
        this.tituloAtividade = tituloAtividade;
        this.descricaoAtividade = descricaoAtividade;
        this.dataAtividade = dataAtividade;
        this.statusAtividade = statusAtividade;
        this.acaoRealizada = acaoRealizada;
    }

    public int getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(int idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getTituloAtividade() {
        return tituloAtividade;
    }

    public void setTituloAtividade(String tituloAtividade) {
        this.tituloAtividade = tituloAtividade;
    }

    public String getDescricaoAtividade() {
        return descricaoAtividade;
    }

    public void setDescricaoAtividade(String descricaoAtividade) {
        this.descricaoAtividade = descricaoAtividade;
    }

    public String getDataAtividade() {
        return dataAtividade;
    }

    public void setDataAtividade(String dataAtividade) {
        this.dataAtividade = dataAtividade;
    }

    public String getStatusAtividade() {
        return statusAtividade;
    }

    public void setStatusAtividade(String statusAtividade) {
        this.statusAtividade = statusAtividade;
    }

    public String getAcaoRealizada() {
        return acaoRealizada;
    }

    public void setAcaoRealizada(String acaoRealizada) {
        this.acaoRealizada = acaoRealizada;
    }
}

