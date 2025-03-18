package com.juanc.digital_money_service.orchestrator.accounts.transactions;

import com.juanc.digital_money_service.business.accounts.transactions.dto.RequestCreateNewCardDepositTransaction;
import com.juanc.digital_money_service.business.accounts.transactions.dto.RequestCreateNewTransferTransaction;
import com.juanc.digital_money_service.business.accounts.transactions.dto.ResponseGetTransaction;

import java.util.List;

public interface TransactionUseCaseOrchestrator {
    void createTransferTransaction(Long accountId, RequestCreateNewTransferTransaction request);

    void createCardDepositTransaction(Long accountId, RequestCreateNewCardDepositTransaction request);

    List<ResponseGetTransaction> getLastFiveTransactionsForAccount(Long accountId);

    List<ResponseGetTransaction> getAllTransactionsForAccount(Long accountId);

    ResponseGetTransaction getTransactionById(Long accountId, Long transactionId);

    List<ResponseGetTransaction> getTransactionsByAccountIdAndAmountRange(Long accountId, Double minAmount, Double maxAmount);

    byte[] generateTransactionsPdf(Long accountId, Long transactionId);
}
