package com.juanc.services;

import com.juanc.models.Transfer;
import com.juanc.repositories.ITransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl {
    @Autowired
    private final ITransferRepository iTransferRepository;
    public Transfer saveTransfer(Transfer transfer, Long carId){
        transfer.getCard().setId(carId);
        return this.iTransferRepository.save(transfer);
    }
}
