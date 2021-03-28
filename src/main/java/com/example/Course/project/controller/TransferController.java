package com.example.Course.project.controller;


import com.example.Course.project.exeption.ErrorInputData;

import com.example.Course.project.exeption.ErrorTransfer;
import com.example.Course.project.model.ConfirmationOfTheOperation;
import com.example.Course.project.model.Operation;
import com.example.Course.project.model.Transfer;
import com.example.Course.project.service.TransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TransferController {
    private static final Logger logger = LoggerFactory.getLogger("transfersLogger");

    @Autowired
    TransferService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/transfer")
    public Operation save(@RequestBody Transfer transfer) {
        Transfer sendTransfer = service.saveTransfer(transfer);
        String msg = String.format("CardFrom = %s, CardTo = %s, Amount = %s", transfer.getCardFrom(),
                transfer.getCardTo(), transfer.getAmount());
        logger.info(msg);
        return sendTransfer.getOperationId();
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/confirmOperation")
    public Operation confirm(@RequestBody ConfirmationOfTheOperation confirmOperation) {
        String code = confirmOperation.getCode();
        if (code == null || code.isEmpty()) {
            throw new ErrorTransfer("Verification code is empty.");
        }
        return service.confirmTransfer(confirmOperation.getOperationId());
    }


    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<String> errorInputDataHandler(ErrorInputData e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ErrorTransfer.class)
    public ResponseEntity<String> errorTransferHandler(ErrorTransfer e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

