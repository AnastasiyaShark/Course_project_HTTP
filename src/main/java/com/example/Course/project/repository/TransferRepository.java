package com.example.Course.project.repository;

import com.example.Course.project.model.Operation;
import com.example.Course.project.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;

@Component
public class TransferRepository {

    private final List<Transfer> transferRepository;

    public TransferRepository() {
        this.transferRepository = new Vector<>();
    }

    public Transfer saveTransfer (Transfer transfer){
    transferRepository.add(transfer);
    return transfer;
}

    public Operation confirmTransfer (Operation confirm){
       return confirm;
    }
}
