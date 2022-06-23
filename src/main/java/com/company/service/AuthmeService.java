package com.company.service;


import com.company.model.Authme;
import com.company.repository.AuthmeRepository;

public class AuthmeService {

    private AuthmeRepository authmeRepository;

    public AuthmeService(AuthmeRepository authmeRepository) {
        this.authmeRepository = authmeRepository;
    }

    public Authme loaddAuthme(int authmeId){
        Authme authme = authmeRepository.getAuthmeById(authmeId).orElseThrow(()-> new RuntimeException("Authme nie istnieje"));
        return authme;
    }
}
