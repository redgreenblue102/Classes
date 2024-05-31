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
xdata=np.array([1.44,
1.49,
1.54,
1.56,
1.58,
1.59,
1.6,
1.61,
1.62,
1.63,
1.64,
1.65,
1.66,
1.67,
1.68,
1.69,
1.7,
1.72,
1.74,
1.79,
1.84
])*2*3.14
y= np.array([0.0059,
0.0019,
0.0053,
0.0073,
0.0062,
0.0091,
0.0091,
0.0081,
0.0078,
0.0293,
0.0325,
0.0517,
0.052,
0.0456,
0.0349,
0.0176,
0.012,
0.0108,
0.0081,
0.0064,
0.0051
])
# Perform the curve fit
guess = np.array([0.45, 11, 0.04])
popt, pcov = curve_fit(amp_vs_frequency, xdata, y, guess)

# Print the results
print("Amplitude: ", popt[0])
print("X0: ", popt[1])
print("Gamma: ", popt[2])

# Generate fit curve to graph
# first make finer x axis points for fit, which is defined everywhere, not just measured points.  
xfit = np.linspace(9.0, 11.6, num=100)
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