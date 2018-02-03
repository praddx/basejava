package ru.javawebinar.basejava.model;

import java.util.List;

public class CompanySection extends Section {

    private List<Company> companiesList;

    public List<Company> getCompaniesList() {
        return companiesList;
    }

    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    public void addCompany(Company company) {
        this.companiesList.add(company);
    }

    public void deleteCompany(Company company) {
        this.companiesList.remove(company);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        companiesList.forEach(company -> {
            builder.append(company.toString() + "\n");
        });
        return "Experience: \n" + builder.toString();
    }
}
