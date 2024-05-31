from matplotlib import pyplot as plt
import numpy as np
from scipy.optimize import curve_fit

# Define the Fitting function
def amp_vs_frequency(x, amplitude, x0, gamma):
    return amplitude * (1 / ((x**2 - x0**2)**2 + x**2 * gamma**2))**0.5

# Generate some data to fit to
#xdata = np.linspace(-10, 10, num=50)
#y = lorentzian(xdata, 2, 0, 1) + np.random.normal(0, 0.1, 50)
# Working in angular frequency 
xdata=np.array([2.65,
2.7,
2.75,
2.77,
2.79,
2.8,
2.81,
2.82,
2.83,
2.84,
2.85,
2.86,
2.87,
2.88,
2.89,
2.9,
2.91,
2.93,
2.95,
3,
3.05
])*2*3.14
y= np.array([0.002,
0.0015,
0.0043,
0.005,
0.0066,
0.0082,
0.0084,
0.0098,
0.011,
0.04,
0.0499,
0.039,
0.0164,
0.0123,
0.0112,
0.01,
0.009,
0.0078,
0.0073,
0.0061,
0.0053
])
# Perform the curve fit
guess = np.array([0.45, 18, 0.2])
popt, pcov = curve_fit(amp_vs_frequency, xdata, y, guess)

# Print the results
print("Amplitude: ", popt[0])
print("X0: ", popt[1])
print("Gamma: ", popt[2])

# Generate fit curve to graph
# first make finer x axis points for fit, which is defined everywhere, not just measured points.  
xfit = np.linspace(16.5, 19.5, num=100)
y_fitted = popt[0] * (1 / ((xfit**2 - popt[1]**2)**2 + xfit**2 * (popt[2])**2))**0.5
#y_fitted = popt[0] * xdata
# Plot the results
# plt.plot(xdata, y)
ax = plt.axes()
ax.scatter(xdata, y, label='Raw data')
ax.plot(xfit, y_fitted, 'k', label='Fitted curve')
ax.set_title('Using curve_fit() to fit Amplitude vs Frequency')
ax.set_ylabel('Amplitude')
#ax.set_ylim(10, 10)
ax.set_xlabel('Frequency')
ax.legend() 
plt.show()