package com.ninjaone.dundie_awards.controller;

import com.ninjaone.dundie_awards.services.ActivityService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/activities")
public class ActivityController {

  @Autowired
  private ActivityService activityService;

  @PostMapping
  @ResponseBody
  public ResponseEntity<Map<String, Boolean>> processActivities() {
    return ResponseEntity.ok(activityService.processActivities());
  }
}
