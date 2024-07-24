#pragma once

#include <iostream>
#include <vector>
#include <string>
using namespace std;
#include "cppTPS_Transaction.h"

class cppTPS {
private:
    bool performingDo;
    bool performingUndo;
    std::vector<cppTPS_Transaction*> transactions; 
    int mostRecentTransaction;

public:
    // INLINED FUNCTIONS

    bool isPerformingDo() {
        return this->performingDo;
    }

    bool isPerformingUndo() {
        return this->performingUndo;
    }

    bool hasTransactionToRedo() {
        return ((this->mostRecentTransaction) < ((int)this->getSize()-1));
    }

    bool hasTransactionToUndo() {
        return this->mostRecentTransaction > -1;
    }

    size_t getSize() {
        return this->transactions.size();
    }

    size_t getRedoSize() {
        return this->transactions.size()-(this->mostRecentTransaction+1);
    }

    int getUndoSize() {
        return this->mostRecentTransaction+1;
    }

    // THE REMAINING FUNCTIONS ARE DEFINED INSIDE cppTPS.cpp

    /*
    * Default constructor for the transaction processing system.
    */
    cppTPS();

    /*
    * Adds a new transaction to the stack
    */
    void addTransaction(cppTPS_Transaction* transaction);

    /*
    * Does the transaction currently at the top of the stack. Note, it might
    * be a redo.
    */
    void doTransaction();

    /**
     * Gets the most recently executed transaction on the
     * TPS stack and undoes it, moving the TPS counter accordingly.
     */
    void undoTransaction();

    /*
    * Clears out all of the transactions on the stack.
    */
    void clearAllTransactions();

    /*
    * Returns a textual representation of the transaction stack.
    */
    wstring toString();
};