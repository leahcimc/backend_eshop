package com.FSSE2309.backend_eshop.api;

import com.FSSE2309.backend_eshop.config.EnvConfig;
import com.FSSE2309.backend_eshop.data.transaction.dto.TransactionBillDto;
import com.FSSE2309.backend_eshop.data.transaction.dto.TransactionDetailResponseDto;
import com.FSSE2309.backend_eshop.data.transaction.dto.TransactionStatusDto;
import com.FSSE2309.backend_eshop.data.transaction.entity.TransactionStatus;
import com.FSSE2309.backend_eshop.service.TransactionService;
import com.FSSE2309.backend_eshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@CrossOrigin("http://fsse2309-project-leahcim.s3-website-ap-southeast-1.amazonaws.com")
@CrossOrigin({EnvConfig.devEnvBaseUrl, EnvConfig.prodEnvBaseUrl})
@RestController
@RequestMapping("/transaction")
public class TransactionAPI {
    //Attribute
    private TransactionService transactionService;

    //Constructor
    @Autowired
    public TransactionAPI(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping("/prepare")
    public TransactionDetailResponseDto createTransaction(JwtAuthenticationToken token){
        return new TransactionDetailResponseDto(
                transactionService.createNewTransaction(JwtUtil.getFirebaseUser(token))
        );
    }

    @GetMapping("/{tid}")
    public TransactionDetailResponseDto getTransaction(@PathVariable Object tid, JwtAuthenticationToken token){
        return new TransactionDetailResponseDto(
                transactionService.getTransaction(tid, JwtUtil.getFirebaseUser(token))
        );
    }
    @GetMapping()
    public List<TransactionBillDto> getAllTransaction(JwtAuthenticationToken token){
        return (
                transactionService.getAllTransaction(JwtUtil.getFirebaseUser(token)))
                .stream()
                .map(TransactionBillDto::new)
                .collect(Collectors.toList()
        );
    }

    @PatchMapping("/{tid}/pay")
    public TransactionStatusDto updateTransactionStatus(@PathVariable Object tid, JwtAuthenticationToken token){
        return new TransactionStatusDto(
                transactionService.updateStatus(tid, JwtUtil.getFirebaseUser(token))
        );
    }

    @PatchMapping("/{tid}/finish")
    public TransactionStatusDto finishTransaction(@PathVariable Object tid, JwtAuthenticationToken token){
        return new TransactionStatusDto(
                transactionService.finishTransaction(tid, JwtUtil.getFirebaseUser(token))
        );
    }

    @DeleteMapping("/{tid}")
    public TransactionStatusDto deleteTransaction(@PathVariable Object tid, JwtAuthenticationToken token){
        return new TransactionStatusDto(
                transactionService.deleteTransaction(tid, JwtUtil.getFirebaseUser(token))
        );
    }
}
