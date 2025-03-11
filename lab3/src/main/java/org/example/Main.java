package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Properties;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try {
            props.load(new FileReader("lab3/bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config " + e);
        }
        CarRepository carRepo = new CarsDBRepository(props);

        carRepo.add(new Car("Tesla", "Model S", 2019));
        System.out.println("Toate masinile din db");
        for (Car car : carRepo.findAll())
            System.out.println(car);

        int id = StreamSupport.stream(carRepo.findAll().spliterator(), false)
                .sorted((c1,c2)->c2.getId()-c1.getId())
                .findFirst().get().getId();
        carRepo.update(id, new Car("Tesla", "Model X", 2020));
        System.out.println("Toate masinile din db");
        for (Car car : carRepo.findAll())
            System.out.println(car);

        String manufacturer = "Dacia";
        System.out.println("Masinile produse de " + manufacturer);
        for (Car car : carRepo.findByManufacturer(manufacturer))
            System.out.println(car);

        int min = 2018;
        int max = 2020;
        System.out.println("Masinile produse intre anii " + min + " si " + max);
        for (Car car : carRepo.findBetweenYears(min, max))
            System.out.println(car);
    }
}