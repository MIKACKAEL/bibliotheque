package com.example.bibliotheque.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotheque.model.TypeAdherent;
import com.example.bibliotheque.repository.TypeAdherentRepository;



@Service
public class TypeAdherentService {
    private final TypeAdherentRepository typeAdherentRepository;

    @Autowired
    public TypeAdherentService(TypeAdherentRepository typeAdherentRepository) {
        this.typeAdherentRepository = typeAdherentRepository;
    }

    public List<TypeAdherent> findAll() {
        return typeAdherentRepository.findAll();
    }

    public TypeAdherent findById(Integer id) {
        return typeAdherentRepository.findById(id).orElse(null);
    }

    public TypeAdherent save(TypeAdherent typeAdherent) {
        return typeAdherentRepository.save(typeAdherent);
    }

    public void deleteById(Integer id) {
        typeAdherentRepository.deleteById(id);
    }
}