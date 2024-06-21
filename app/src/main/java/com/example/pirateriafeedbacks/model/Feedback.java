package com.example.pirateriafeedbacks.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Feedback implements Serializable {
    @SerializedName("id_feedback")
    private int id_feedback;
    @SerializedName("tituloFeedback")
    private String tituloFeedback;
    @SerializedName("descricaoFeedback")
    private String descricaoFeedback;
    @SerializedName("dataFeedback")
    private String dataFeedback;
    @SerializedName("statusFeedback")
    private String statusFeedback;

    public Feedback(int id_feedback, String tituloFeedback, String descricaoFeedback, String dataFeedback, String statusFeedback) {
        this.id_feedback = id_feedback;
        this.tituloFeedback = tituloFeedback;
        this.descricaoFeedback = descricaoFeedback;
        this.dataFeedback = dataFeedback;
        this.statusFeedback = statusFeedback;
    }

    public int getId_feedback() {
        return id_feedback;
    }

    public void setId_feedback(int id_feedback) {
        this.id_feedback = id_feedback;
    }

    public String getTituloFeedback() {
        return tituloFeedback;
    }

    public void setTituloFeedback(String tituloFeedback) {
        this.tituloFeedback = tituloFeedback;
    }

    public String getDescricaoFeedback() {
        return descricaoFeedback;
    }

    public void setDescricaoFeedback(String descricaoFeedback) {
        this.descricaoFeedback = descricaoFeedback;
    }

    public String getDataFeedback() {
        return dataFeedback;
    }

    public void setDataFeedback(String dataFeedback) {
        this.dataFeedback = dataFeedback;
    }

    public String getStatusFeedback() {
        return statusFeedback;
    }

    public void setStatusFeedback(String statusFeedback) {
        this.statusFeedback = statusFeedback;
    }
}
