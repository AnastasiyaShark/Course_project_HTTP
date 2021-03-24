package com.example.Course.project.controller;

import com.example.Course.project.exeption.ErrorInputData;

import com.example.Course.project.exeption.ErrorTransfer;
import com.example.Course.project.model.ConfirmationOfTheOperation;
import com.example.Course.project.model.Operation;
import com.example.Course.project.model.Transfer;
import com.example.Course.project.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {
    @Autowired
   TransferService service;
       @CrossOrigin(origins = "http://localhost:3000")
       @PostMapping("/transfer")
        public Operation save(@RequestBody Transfer transfer) {
           Transfer sendTransfer = service.saveTransfer(transfer);
           return sendTransfer.getOperationId();
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/confirmOperation")
        public Operation confirm (@RequestBody ConfirmationOfTheOperation confirmOperation){
           String code = confirmOperation.getCode();
           if (code == null || code.isEmpty()){
               throw new ErrorTransfer("Verification code is empty.");
           }
        return confirmOperation.getOperationId();
    }

    
    @ExceptionHandler(ErrorInputData.class)
    public ResponseEntity<String> errorInputDataHandler (ErrorInputData e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ErrorTransfer.class)
    public ResponseEntity<String> errorTransferHandler (ErrorTransfer e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

