package com.ninjaone.dundie_awards.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dundie_awards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DundieAward {
  @Id
  private long id;

  private String name;

  @OneToMany(mappedBy = "dundieAward")
  private List<EmployeesDundieAwards> dundieAwardsList;
}
