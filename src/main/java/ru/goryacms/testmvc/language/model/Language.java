package ru.goryacms.testmvc.language.model;

import javax.persistence.*;

@Entity
@Table(name = "countrylanguage")
public class Language {
    @EmbeddedId
    private LanguageId id;

    @Basic(optional = false)
    @Column(name = "IsOfficial")
    private String isOfficial;

    @Basic(optional = false)
    @Column(name = "Percentage")
    private Double percentage;

    public Language() {
    }

    public LanguageId getId() {
        return id;
    }

    public void setId(LanguageId id) {
        this.id = id;
    }

    public String getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(String isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}