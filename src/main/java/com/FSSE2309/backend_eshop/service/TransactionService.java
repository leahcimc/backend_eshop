package com.FSSE2309.backend_eshop.service;

import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionDetailData;
import com.FSSE2309.backend_eshop.data.transaction.domainObj.TransactionActionStatus;
import com.FSSE2309.backend_eshop.data.user.domainObj.FirebaseUserData;

public interface TransactionService {
    //Method from api
    TransactionDetailData createNewTransaction(FirebaseUserData data);

    TransactionDetailData getTransaction(Object tid, FirebaseUserData data);

    TransactionActionStatus updateStatus(Object tid, FirebaseUserData data);

    TransactionActionStatus finishTransaction(Object tid, FirebaseUserData data);

    TransactionActionStatus deleteTransaction(Object tid, FirebaseUserData data);
}
