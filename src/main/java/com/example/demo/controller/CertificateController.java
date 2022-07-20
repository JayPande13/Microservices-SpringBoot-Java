package com.example.demo.controller;

import com.example.demo.MicroservicesConstants;
import com.example.demo.entities.Certification;
import com.example.demo.services.CertificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MicroservicesConstants.BASE_URL + "/cert")
@Slf4j
public class CertificateController {

    @Autowired
    CertificationService certificationService;

    @PostMapping("/create")
    public ResponseEntity<Certification> createCertificate(@RequestBody Certification newCert){
        log.info("Started Creating Certification in Controller");
        Certification certification = null;
        certification = certificationService.createCert(newCert);
        if(certification == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(certification);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Certification>> getAllCert(){
        log.info("Started Searching Certification in Controller");
        List<Certification> allCert = null;
        allCert = certificationService.getAll();
        if(allCert == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body((allCert));
    }

    @GetMapping("/view/{certId}")
    public  ResponseEntity<Certification> getCertById(@PathVariable Integer certId){
        log.info("Finding Certification with Id : " + certId + " in Controller");
        Certification certification = null;
        certification = certificationService.getCertById(certId);
        if(certification == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(certification);
    }


}
