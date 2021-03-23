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
           Operation op = sendTransfer.getOperationId();
           System.out.println("Я отправил OperationId" + " " + op);
         return op;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/confirmOperation")
        public Operation confirm (@RequestBody ConfirmationOfTheOperation confirmOperation){

        System.out.println("operationId" + " "+ confirmOperation.getOperationId());
        System.out.println("code" + " "+ confirmOperation.getCode());
//           if (code == null || code.isEmpty()){
////               System.out.println("Я отправил" + service.confirmTransfer(operationId));
//               throw new ErrorTransfer("hbfgv");
//           }

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

//    @PostMapping("/transfer")
//    public Operation save(@RequestBody Transfer transfer) {
//        Transfer savedTransfer = service.saveTransfer(transfer);
//        Operation op = savedTransfer.getOperationId();
//        return op;
//    }
