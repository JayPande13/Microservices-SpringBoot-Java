package com.example.demo.services;

import com.example.demo.entities.Certification;

import java.util.List;

public interface CertificationService {
    public Certification createCert(Certification certification);
    public List<Certification> getAll();
    public Certification getCertById(Integer certId);
}
