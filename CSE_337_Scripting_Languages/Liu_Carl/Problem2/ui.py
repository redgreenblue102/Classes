#!/usr/bin/env python3

import tkinter as tk
from tkinter import ttk
import locale

from business import Investment

class FutureValueFrame(ttk.Frame):
    def __init__(self, parent,exit):
        ttk.Frame.__init__(self, parent, padding="10 10 10 10")
        self.parent = parent
        self.investment = Investment()
        self.exit =exit
        self.entrywidgets = []
        result = locale.setlocale(locale.LC_ALL, '')
        if result == 'C':
            locale.setlocale(locale.LC_ALL, 'en_US')        

        # Define string variables for text entry fields
        self.monthlyInvestment = tk.StringVar()
        self.yearlyInterestRate = tk.StringVar()
        self.years = tk.StringVar()
        self.futureValue = tk.StringVar()

        self.initComponents()

    def initComponents(self):
        self.pack(side=tk.LEFT)

        # Display the grid of labels and text entry fields
        ttk.Label(self, text="Monthly Investment:").grid(
            column=0, row=0, sticky=tk.E)
        ttk.Entry(self, width=25, textvariable=self.monthlyInvestment).grid(
            column=1, row=0)

        ttk.Label(self, text="Yearly Interest Rate:").grid(
            column=0, row=1, sticky=tk.E)
        ttk.Entry(self, width=25, textvariable=self.yearlyInterestRate).grid(
            column=1, row=1)

        ttk.Label(self, text="Years:").grid(
            column=0, row=2, sticky=tk.E)
        ttk.Entry(self, width=25, textvariable=self.years).grid(
            column=1, row=2)

        ttk.Label(self, text="Future Value:").grid(
            column=0, row=3, sticky=tk.E)
        ttk.Entry(self, width=25, textvariable=self.futureValue,
                  state="readonly").grid(
            column=1, row=3)

        self.makeButtons(self.exit)

    def makeButtons(self,exit):
        # Create a frame to store the two buttons
        buttonFrame = ttk.Frame(self)

        # Add the button frame to the bottom row of the main grid
        buttonFrame.grid(column=0, row=4, columnspan=2, sticky=tk.E)

        # Add two buttons to the button frame
        ttk.Button(buttonFrame, text="Clear", command=self.clear) \
            .grid(column=0, row=0)
        ttk.Button(buttonFrame, text="Calculate", command=self.calculate) \
            .grid(column=1, row=0, padx=5)
        if(exit):
             ttk.Button(buttonFrame, text="Exit", command=self.parent.destroy) \
            .grid(column=1, row=1, padx=5,pady=5)
        else:
            ttk.Label(buttonFrame) \
            .grid(column=1, row=1, padx=5,pady=5)

    def clear(self):
        self.monthlyInvestment.set("")
        self.yearlyInterestRate.set("")
        self.years.set("")
        self.futureValue.set("")
    def calculate(self):
        self.investment.monthlyInvestment = float(
            self.monthlyInvestment.get())
        self.investment.yearlyInterestRate = float(
            self.yearlyInterestRate.get())
        self.investment.years = int(
            self.years.get())
        if(self.investment.monthlyInvestment <0 or self.investment.yearlyInterestRate <0 or self.investment.years < 0):
            raise Exception() 
        self.futureValue.set(locale.currency(
                self.investment.calculateFutureValue(), grouping=True))

if __name__ == "__main__":
    root = tk.Tk()
    root.title("Future Value Calculator")
    
    right =FutureValueFrame(root,False)
    left =FutureValueFrame(root,True)

    root.mainloop()
