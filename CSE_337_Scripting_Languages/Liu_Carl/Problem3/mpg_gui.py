#!/usr/bin/env python3

import tkinter as tk
from tkinter import ttk

class MyFrame(ttk.Frame):
    def __init__(self, parent):
        ttk.Frame.__init__(self, parent, padding="10 10 10 10")
        self.pack()

        # Define string variables for text entry fields
        self.milesdriven = tk.StringVar()
        self.gallons = tk.StringVar()
        self.mpg = tk.StringVar()

        # Display the grid of components
        ttk.Label(self, text="Miles Driven:").grid(
            column=0, row=0, sticky=tk.E)
        ttk.Entry(self, width=30, textvariable=self.milesdriven).grid(
            column=1, row=0)
        ttk.Label(self, text="Gallons of Gas Used:").grid(
            column=0, row=1, sticky=tk.E)
        ttk.Entry(self, width=30, textvariable=self.gallons).grid(
            column=1, row=1)
        ttk.Label(self, text="Miles Per Gallon:").grid(
            column=0, row=2, sticky=tk.E)
        ttk.Entry(self, width=30, textvariable=self.mpg,state="readonly").grid(
            column=1, row=2)
        ttk.Button(self, text="Calculate", command=self.calculate) \
            .grid(column=1, row=3,sticky=tk.E)

        # Add padding to all components
        for child in self.winfo_children():
            child.grid_configure(padx=5, pady=3)
    def calculate(self):
        self.mpg.set(float(self.milesdriven.get())/float(self.gallons.get()))


if __name__ == "__main__":
    root = tk.Tk()
    root.title("Program Title")
    MyFrame(root)
    root.mainloop()
