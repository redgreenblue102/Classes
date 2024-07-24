// cppTPS.cpp : Defines the functions for the static library.

// C++ LIBRARIES FOR I/O AND TEXT
#include <sstream>
#include <string>
#include "cppTPS.h"

cppTPS::cppTPS() {
    std::vector<cppTPS_Transaction*> transactions; 
    mostRecentTransaction = -1;
    performingUndo = false;
    performingDo = false;
}

void cppTPS::addTransaction(cppTPS_Transaction* transaction) {
    if(this->hasTransactionToRedo()){
        for(int i = transactions.size()-1; i > mostRecentTransaction; i--){
            transactions.pop_back();
        }
    }
    transactions.emplace_back(transaction);
    this->doTransaction();
    
    
}
void cppTPS::doTransaction() {
    if(hasTransactionToRedo()){
        performingDo = true;
        transactions[mostRecentTransaction+1]->doTransaction();
        mostRecentTransaction += 1;
        performingDo = false;

    }
    
}

void cppTPS::undoTransaction() {
    if(this->hasTransactionToUndo()){
        performingUndo = true;
        transactions[mostRecentTransaction]->undoTransaction();
        mostRecentTransaction--;
        performingUndo = false;
    }
}

void cppTPS::clearAllTransactions() {
    transactions.clear();
    mostRecentTransaction = -1;
}


wstring cppTPS::toString() {
    wstring text = L"--Number of Transactions: " + to_wstring(getSize()) + L"\n"
    + L"--Current Index on Stack: " + to_wstring(mostRecentTransaction) + L"\n"
    + L"--Current Transaction Stack:\n";
        for (int i = 0; i <= mostRecentTransaction; i++) {
            text += L"----" + transactions[i]->toString() + L"\n";
        }
    return text;
}