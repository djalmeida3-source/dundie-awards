package com.ninjaone.dundie_awards.resources;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.domain.DundieAward;
import com.ninjaone.dundie_awards.domain.Employee;
import com.ninjaone.dundie_awards.domain.Organization;
import com.ninjaone.dundie_awards.repository.DundieAwardRepository;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final AwardsCache awardsCache;
    private final DundieAwardRepository dundieAwardRepository;

    @Override
    public void run(String... args) {
        // uncomment to reseed data
        // employeeRepository.deleteAll();
        // organizationRepository.deleteAll();

        if (employeeRepository.count() == 0) {
            dundieAwardRepository.save(DundieAward.builder().id(1).name("Employee of the year").build());
            dundieAwardRepository.save(DundieAward.builder().id(2).name("Best seller").build());
            dundieAwardRepository.save(DundieAward.builder().id(3).name("Better dressed").build());
            dundieAwardRepository.save(DundieAward.builder().id(4).name("More spicy person").build());

            Organization organizationPikashu = new Organization("Pikashu");
            organizationRepository.save(organizationPikashu);

            employeeRepository.save(new Employee("John", "Doe", organizationPikashu));
            employeeRepository.save(new Employee("Jane", "Smith", organizationPikashu));
            employeeRepository.save(new Employee("Creed", "Braton", organizationPikashu));

            Organization organizationSquanchy = new Organization("Squanchy");
            organizationRepository.save(organizationSquanchy);

            employeeRepository.save(new Employee("Michael", "Scott", organizationSquanchy));
            employeeRepository.save(new Employee("Dwight", "Schrute", organizationSquanchy));
            employeeRepository.save(new Employee("Jim", "Halpert", organizationSquanchy));
            employeeRepository.save(new Employee("Pam", "Beesley", organizationSquanchy));
        }

        int totalAwards = employeeRepository.findAll().stream()
                .mapToInt(employee -> Objects.requireNonNullElse(employee.getDundieAwards(), 0))
                .sum();
        this.awardsCache.setTotalAwards(totalAwards);
    }
}
