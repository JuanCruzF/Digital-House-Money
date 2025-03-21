package com.juanc.digital_money_service.orchestrator.accounts.transactions;

import com.itextpdf.text.DocumentException;
import com.juanc.digital_money_service.business.accounts.Account;
import com.juanc.digital_money_service.business.accounts.AccountService;
import com.juanc.digital_money_service.business.accounts.cards.CardService;
import com.juanc.digital_money_service.business.accounts.cards.exception.CardNotFoundException;
import com.juanc.digital_money_service.business.accounts.transactions.Transaction;
import com.juanc.digital_money_service.business.accounts.transactions.TransactionDirection;
import com.juanc.digital_money_service.business.accounts.transactions.TransactionService;
import com.juanc.digital_money_service.business.accounts.transactions.TransactionType;
import com.juanc.digital_money_service.business.accounts.transactions.dto.RequestCreateNewCardDepositTransaction;
import com.juanc.digital_money_service.business.accounts.transactions.dto.RequestCreateNewTransferTransaction;
import com.juanc.digital_money_service.business.accounts.transactions.dto.ResponseGetTransaction;
import com.juanc.digital_money_service.business.accounts.transactions.exception.InsufficientBalanceException;
import com.juanc.digital_money_service.business.accounts.transactions.util.PdfGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TransactionUseCaseHandler implements TransactionUseCaseOrchestrator {
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final CardService cardService;
    private final PdfGenerator pdfGenerator;


    @Override
    public void createTransferTransaction(Long accountId, RequestCreateNewTransferTransaction request) {
        Account senderAccount = accountService.findById(accountId);
        Account receiverAccount = accountService.findByAliasOrCvu(request.destinationAccountIdentifier());

        if (senderAccount.getBalance() < request.amount() || senderAccount.getBalance().equals(0.0)) {
            throw new InsufficientBalanceException("Insufficient account balance for transfer");
        }

        Transaction transaction = new Transaction();
        transaction.setFromAccount(senderAccount);
        transaction.setToAccount(receiverAccount);
        transaction.setAmount(request.amount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionType(TransactionType.TRANSFER);

        accountService.updateBalance(senderAccount.getId(), (senderAccount.getBalance() - request.amount()));
        accountService.updateBalance(receiverAccount.getId(), (receiverAccount.getBalance() + request.amount()));

        transactionService.saveTransaction(transaction);
    }

    @Override
    public void createCardDepositTransaction(Long accountId, RequestCreateNewCardDepositTransaction request) {
        Account account = accountService.findById(accountId);
        if (!cardService.isFoundByCardNumberAndAccountId(request.cardNumber(), accountId)) {
            throw new CardNotFoundException("Card with number: " + request.cardNumber() + " for user with id: " + accountId + " not found.");
        }

        Transaction transaction = new Transaction();
        transaction.setFromAccount(account);
        transaction.setToAccount(account);
        transaction.setAmount(request.amount());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setTransactionType(TransactionType.CARD_DEPOSIT);

        accountService.updateBalance(account.getId(), account.getBalance() + request.amount());
        transactionService.saveTransaction(transaction);
    }

    @Override
    public List<ResponseGetTransaction> getLastFiveTransactionsForAccount(Long accountId) {
        List<Transaction> transactions = transactionService.findLastFiveTransactionsForAccount(accountId);
        return transactions.stream()
                .map(transaction -> {
                    TransactionDirection direction =
                            (transaction.getToAccount().getId().equals(accountId)) ?
                                    TransactionDirection.INGRESS : TransactionDirection.EGRESS;
                    return new ResponseGetTransaction(
                            transaction.getAmount(),
                            transaction.getTransactionType(),
                            direction,
                            transaction.getFromAccount().getUser().getFullName(),
                            transaction.getFromAccount().getCvu(),
                            transaction.getToAccount().getUser().getFullName(),
                            transaction.getToAccount().getCvu(),
                            transaction.getTransactionDate()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseGetTransaction> getAllTransactionsForAccount(Long accountId) {
        List<Transaction> transactions = transactionService.findAllTransactionsForAccount(accountId);
        return transactions.stream()
                .map(transaction -> {
                    TransactionDirection direction =
                            (transaction.getToAccount().getId().equals(accountId)) ?
                                    TransactionDirection.INGRESS : TransactionDirection.EGRESS;
                    return new ResponseGetTransaction(
                            transaction.getAmount(),
                            transaction.getTransactionType(),
                            direction,
                            transaction.getFromAccount().getUser().getFullName(),
                            transaction.getFromAccount().getCvu(),
                            transaction.getToAccount().getUser().getFullName(),
                            transaction.getToAccount().getCvu(),
                            transaction.getTransactionDate()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public ResponseGetTransaction getTransactionById(Long accountId, Long transactionId) {
        Transaction transaction = transactionService.findTransactionById(transactionId);
        TransactionDirection direction =
                (transaction.getToAccount().getId().equals(accountId)) ?
                        TransactionDirection.INGRESS : TransactionDirection.EGRESS;
        return new ResponseGetTransaction(
                transaction.getAmount(),
                transaction.getTransactionType(),
                direction,
                transaction.getFromAccount().getUser().getFullName(),
                transaction.getFromAccount().getCvu(),
                transaction.getToAccount().getUser().getFullName(),
                transaction.getToAccount().getCvu(),
                transaction.getTransactionDate()
        );
    }

    @Override
    public List<ResponseGetTransaction> getTransactionsByAccountIdAndAmountRange(Long accountId, Double minAmount, Double maxAmount) {
        List<Transaction> transactions = transactionService.findTransactionsByAccountIdAndAmountRange(accountId, minAmount, maxAmount);
        return transactions.stream()
                .map(transaction -> {
                    TransactionDirection direction =
                            (transaction.getToAccount().getId().equals(accountId)) ?
                                    TransactionDirection.INGRESS : TransactionDirection.EGRESS;
                    return new ResponseGetTransaction(
                            transaction.getAmount(),
                            transaction.getTransactionType(),
                            direction,
                            transaction.getFromAccount().getUser().getFullName(),
                            transaction.getFromAccount().getCvu(),
                            transaction.getToAccount().getUser().getFullName(),
                            transaction.getToAccount().getCvu(),
                            transaction.getTransactionDate()
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public byte[] generateTransactionsPdf(Long accountId, Long transactionId) {
        Transaction transaction = transactionService.findTransactionById(transactionId);
        TransactionDirection direction =
                (transaction.getToAccount().getId().equals(accountId)) ?
                        TransactionDirection.INGRESS : TransactionDirection.EGRESS;
        try {
            return pdfGenerator.generateTransactionPdf(transaction, direction);
        } catch (IOException | DocumentException e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }
    }
}
