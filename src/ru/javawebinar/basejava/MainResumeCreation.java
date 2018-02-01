package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainResumeCreation {

    public static void main(String[] args) {
        //Resume resume = new Resume("Superman");
        String companyName = "Ligue of Justice";
        String position = "Superhero";
        String description = "blablabla";
        LocalDate startDate = LocalDate.of(2015, 02, 15);
        LocalDate endDate = LocalDate.now();
        Company company = new Company(companyName, position, startDate, endDate, description);

        System.out.println(company.toString());


    }
}
