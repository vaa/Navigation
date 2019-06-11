package com.zhou.dto;

import com.zhou.model.Category;
import com.zhou.model.Site;

import java.util.List;

public class SiteRanking {

    private Category category;
    private List<Site> sites;


    public SiteRanking() {
    }

    public SiteRanking(Category category, List<Site> sites) {
        this.category = category;
        this.sites = sites;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    public String toString() {
        return "SiteRanking{" +
                "category=" + category +
                ", sites=" + sites +
                '}';
    }
}
