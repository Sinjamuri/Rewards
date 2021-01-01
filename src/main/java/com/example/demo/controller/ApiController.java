package com.example.demo.controller;


import com.example.demo.model.Summary;
import com.example.demo.model.Transaction;
import com.example.demo.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping({"/",""})
public class ApiController {
    @Autowired
    ApiService apiService ;

    @RequestMapping(value = "/summary", method = RequestMethod.GET )
    public ResponseEntity<List<Summary>> getSummary(@RequestBody Transaction[] transactions){
        List<Summary> summaryList = apiService.getSummary(transactions);
        return new ResponseEntity<List<Summary>>(summaryList, HttpStatus.OK);
    }


}
