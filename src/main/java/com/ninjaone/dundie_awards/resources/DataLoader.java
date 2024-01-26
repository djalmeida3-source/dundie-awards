package com.ninjaone.dundie_awards.resources;

import com.ninjaone.dundie_awards.AwardsCache;
import com.ninjaone.dundie_awards.model.DundieAward;
import com.ninjaone.dundie_awards.model.Employee;
import com.ninjaone.dundie_awards.model.Organization;
import com.ninjaone.dundie_awards.repository.DundieAwardRepository;
import com.ninjaone.dundie_awards.repository.EmployeeRepository;
import com.ninjaone.dundie_awards.repository.OrganizationRepository;
import java.util.Objects;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final OrganizationRepository organizationRepository;
    private final AwardsCache awardsCache;
    private final DundieAwardRepository dundieAwardRepository;

    public DataLoader(EmployeeRepository employeeRepository, OrganizationRepository organizationRepository, AwardsCache awardsCache, DundieAwardRepository dundieAwardRepository) {
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
        this.awardsCache = awardsCache;
        this.dundieAwardRepository = dundieAwardRepository;
    }

    @Override
    public void run(String... args) {
        // uncomment to reseed data
        // employeeRepository.deleteAll();
        // organizationRepository.deleteAll();

        if (employeeRepository.count() == 0) {
            dundieAwardRepository.save(new DundieAward(1,"Employee of the year"));
            dundieAwardRepository.save(new DundieAward(2,"Best seller"));
            dundieAwardRepository.save(new DundieAward(3,"Better dressed"));
            dundieAwardRepository.save(new DundieAward(4,"More spicy person"));

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
