package com.example.demo.services.servicesImpl;

import com.example.demo.entities.Certification;
import com.example.demo.repositories.CertificationRepository;
import com.example.demo.services.CertificationService;
import jdk.jfr.Experimental;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    CertificationRepository certificationRepository;
    @Override
    public Certification createCert(Certification addCert){
        log.info("Creating Certificate in service IMPL");
        Certification savedCert=null;
        try{
            savedCert = certificationRepository.save(addCert);
            return savedCert;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public List<Certification> getAll(){
        log.info("Getting List of Certification n Service IMPL");
        List<Certification> certificationList = null;
        try{
            certificationList =certificationRepository.findAll();
            return  certificationList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Certification getCertById(Integer certId){
        log.info("Finding Certification with Id : " + certId + " in Service IMPL");
        Certification certification = null;
        try{
            certification = certificationRepository.findAll().stream().filter(user -> user.getId() == certId).findFirst().get();
            return certification;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
