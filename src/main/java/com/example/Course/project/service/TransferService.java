package com.example.Course.project.service;

import com.example.Course.project.model.Operation;
import com.example.Course.project.model.Transfer;
import com.example.Course.project.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferService {
    @Autowired
   TransferRepository repository;

    public Transfer saveTransfer(Transfer transfer) {
        return repository.saveTransfer(transfer);
    }

    public Operation confirmTransfer (Operation confirm){
        return repository.confirmTransfer(confirm);
    }

}
