#pragma once
#include <sstream>
#include <string>
#include <iostream>
#include <cppTPS_Transaction.h>

class AddStop_Transaction : public cppTPS_Transaction{
	private:
		vector<wstring>* stops;
		wstring airport;
	public:
		AddStop_Transaction(vector<wstring>* initStops, wstring currAirport){
			stops = initStops;
			airport = currAirport;
		}
		void doTransaction(){
			stops->emplace_back(airport);
		}
		void undoTransaction(){
			stops->pop_back();
		}
		wstring toString(){
			wstringstream wss;
        	wss << L"Added Stop, " << airport;
        	return wss.str();
		}

};