from matplotlib import pyplot as plt
import numpy as np
from scipy.optimize import curve_fit

# Define the Fitting function
def pol_var(x, A, k, B):
    return A*np.cos(k*x)**2 + B

# Generate some data to fit to
#xdata = np.linspace(-10, 10, num=50)
#y = lorentzian(xdata, 2, 0, 1) + np.random.normal(0, 0.1, 50)
# Working in angular frequency 
xdata=np.array([0,10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180])*np.pi/180
y= np.array([194,
185,
163,
138,
107.1,
73.9,
45,
21.8,
9.4,
8.8,
21,
40.5,
70.2,
102.2,
133.7,
159.7,
183.2,
195.8,
197.2])
# Perform the curve fit
guess = np.array([70, 1, 10])
popt, pcov = curve_fit(pol_var, xdata, y, guess)

# Print the results
print("A ", popt[0])
print("k ", popt[1])
print("B ", popt[2])

# Generate fit curve to graph
# first make finer x axis points for fit, which is defined everywhere, not just measured points.  
xfit = np.linspace(min(xdata), max(xdata), num=100)
y_fitted = popt[0] * (1 / ((xfit**2 - popt[1]**2)**2 + xfit**2 * (popt[2])**2))**0.5
#y_fitted = popt[0] * xdata
# Plot the results
# plt.plot(xdata, y)
ax = plt.axes()
ax.scatter(xdata, y, label='Raw data')
ax.plot(xdata, pol_var(xdata, popt[0], popt[1],popt[2] ), 'k', label='Fitted curve')
ax.set_title('Using curve_fit() to fit Amplitude vs Radians')
ax.set_ylabel('Amplitude')
#ax.set_ylim(10, 10)
ax.set_xlabel('Radians')
ax.legend()
plt.savefig("p1.png") 
plt.show()
