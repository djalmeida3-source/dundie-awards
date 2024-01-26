package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.controller.dto.EmployeeResponseDto;
import com.ninjaone.dundie_awards.controller.dto.GrantAwardsRequestDto;
import com.ninjaone.dundie_awards.services.AwardsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/awards")
public class AwardController {

  @Autowired
  private AwardsService awardsService;
  @PostMapping("/grant/{organizationId}")
  @ResponseBody
  public List<EmployeeResponseDto> grantAwardByOrganization(
          @PathVariable Long organizationId,
          @RequestBody GrantAwardsRequestDto dto) {
    return awardsService.grantAwardByOrganization(
            organizationId,
            dto.getNumberOfAwards());
  }

}
