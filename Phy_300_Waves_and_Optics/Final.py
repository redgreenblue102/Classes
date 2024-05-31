import numpy as np
import math as math
from scipy.stats import norm, chi2
import matplotlib.pyplot as plt
#in between functions
def gA(x,f,sigma_f):
    A = 0
    for i, data in enumerate(x):
        A = A + (data / (sigma_f[i] * sigma_f[i]))
    return A
def gB(x,f,sigma_f):
    B = 0
    for i, data in enumerate(x):
        B = B + (1/(sigma_f[i] * sigma_f[i]))
    return B
def gC(x, f, sigma_f):
    C = 0
    for i, data in enumerate(x):
        C = C + (f[i]/(sigma_f[i] * sigma_f[i]))
    return C
def gD(x, f, sigma_f):
    D = 0
    for i, data in enumerate(x):
        D = D + ((data*data)/(sigma_f[i] * sigma_f[i]))
    return D
def gE(x, f, sigma_f):
    E = 0
    for i, data in enumerate(x):
        E = E + ((data*f[i])/(sigma_f[i] * sigma_f[i]))
    return E

# question 1
def getA(x,f, sigma_f):
    A = gA(x, f, sigma_f)
    B = gB(x, f, sigma_f)
    C = gC(x, f, sigma_f)
    D = gD(x, f, sigma_f)
    E = gE(x, f, sigma_f)
    a = ((E*B)-(C*A))/((D*B)-(A*A))
    return a

def getB(x,f, sigma_f):
    A = gA(x, f, sigma_f)
    B = gB(x, f, sigma_f)
    C = gC(x, f, sigma_f)
    D = gD(x, f, sigma_f)
    E = gE(x, f, sigma_f)
    b = ((D*C)-(E*A))/((D*B)-(A*A))
    return b


def getSigmaA(x,f, sigma_f):
    A = gA(x, f, sigma_f)
    B = gB(x, f, sigma_f)
    C = gC(x, f, sigma_f)
    D = gD(x, f, sigma_f)
    E = gE(x, f, sigma_f)
    sigma_a = math.sqrt(B/((D*B)-(A*A)))
    return sigma_a

def getSigmaB(x,f, sigma_f):
    A = gA(x, f, sigma_f)
    B = gB(x, f, sigma_f)
    C = gC(x, f, sigma_f)
    D = gD(x, f, sigma_f)
    E = gE(x, f, sigma_f)
    sigma_b = math.sqrt(D/((D*B)-(A*A)))
    return sigma_b

def get_smin(x,f, sigma_f,a,b):
    smin = 0
    for i, data in enumerate(x):
        smin = smin + (((f[i]-(a*data)-b)*(f[i]-(a*data)-b))/(sigma_f[i]*sigma_f[i]))
    return smin

# question 2
print('Question 2')
T1 = np.array([100.00, 150.00, 200.00, 250.00, 300.00])
P1 = np.array([-0.64, -0.54, -0.29, -0.17, 0.04])
SP1 = np.array([0.04, 0.04, 0.04, 0.04, 0.04])
A1 = getA(T1,P1,SP1)
B1 = getB(T1,P1,SP1)
sigmaA1 =getSigmaA(T1,P1,SP1)
sigmaB1 = getSigmaB(T1,P1,SP1)
smin1 = get_smin(T1,P1,SP1,A1,B1)
print("A is", round(A1,5))
print("Sigma A is", round(sigmaA1,5))
print("B is", round(B1,3))
print("Sigma B is", round(sigmaB1,3))
print("Smin is", smin1)
plt.figure(1)
plt.scatter(T1,P1)
plt.plot(T1, T1 * A1 + B1,'-r', label=f'y = {A1:f}x + {B1:f}')
plt.title('Question 2')
plt.xlabel('Temperature (K)')
plt.ylabel('Pressure (atm)')
plt.legend(loc= 'upper left')
integral1 = chi2.cdf(smin1, 3)
print('Smin/k =', smin1/3, 'which is somewhat close to 1 and therefore the line is a decent fit')
print('pvalue is', 1 - integral1)
R1 = A1*22.4
sigmaR1 = sigmaA1*22.4
print('R is', round(R1,4))
print('Sigma R is', round(sigmaR1, 4))


# question 3
print('\nQuestion 3')
T2 = np.array([100.00, 115.00, 130.00, 145.00, 160.00, 175.00, 190.00, 205.00, 220.00, 235.00, 250.00, 265.00, 280.00,
               295.00, 310.00])
P2 = np.array([-0.64, -0.56, -0.44, -0.45, -0.44, -0.35, -0.28, -0.25, -0.15, -0.13, -0.07, -0.02, 0.03, 0.09, 0.16])
SP2 = np.array([0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03, 0.03])
A2 = getA(T2,P2,SP2)
B2 = getB(T2,P2,SP2)
sigmaA2 =getSigmaA(T2,P2,SP2)
sigmaB2 = getSigmaB(T2,P2,SP2)
smin2 = get_smin(T2,P2,SP2,A2,B2)
print("A is", round(A2,5))
print("Sigma A is", round(sigmaA2,5))
print("B is", round(B2,3))
print("Sigma B is", round(sigmaB2,3))
print("Smin is", smin2)
plt.figure(2)
plt.scatter(T2,P2)
plt.plot(T2, T2 * A2 + B2,'-r', label=f'y = {A2:f}x + {B2:f}')
plt.title('Question 3')
plt.xlabel('Temperature (K)')
plt.ylabel('Pressure (atm)')
plt.legend(loc= 'upper left')
integral2 = chi2.cdf(smin2, 13)
print('Smin/k =', smin2/13, 'which is  far from 1 and therefore the line is not a good fit')
print('pvalue is', 1 - integral2)
R2 = A2*22.4
sigmaR2 = sigmaA2*22.4
print('R is', round(R2,4))
print('Sigma R is', round(sigmaR2,4))



# question 4
print('\nQuestion 4')
Rth = 0.0821
def w(sigma):
    return 1/(sigma*sigma)
def z_score(t1, t2, sigmat1, sigmat2):
    return (t1 - t2) / math.sqrt(sigmat1*sigmat1+sigmat2*sigmat2)
def p_value(z, sigmat1,sigmat2):
    sigma = math.sqrt(sigmat1*sigmat1+sigmat2*sigmat2)
    z = abs(z)
    return 1 - (norm.cdf(z * sigma, 0, sigma) - norm.cdf(-z * sigma, 0, sigma))

# 4a
print('\n4a')
z1 = z_score(R1,Rth,sigmaR1,0)
p1 = p_value(z1, sigmaR1, 0)
print('the z score for R from question 2 is', z1)
print('the p value for R from question 2 is', p1, 'therefore the result is not consistent with a confidence interval '
                                                  'of 68.3% because the p value is nowhere near 0.307')

#4b
print('\n4b')
z2 = z_score(R2,Rth,sigmaR2,0)
p2 = p_value(z2, sigmaR2, 0)
print('the z score for R from question 3 is', z2)
print('the p value for R from question 3 is', p2, 'therefore the result is not consistent with a confidence interval '
                                                  'of 68.3% because the p value is nowhere near 0.307')
#4c
print('\n4c')
w1 = w(sigmaR1)
w2 = w(sigmaR2)
Rm = (R1 * w1 + R2 * w2)/(w1 + w2)
sigmaRm = math.sqrt(1/(w1 + w2))
print('Rm is', Rm)
print('sigma Rm is', sigmaRm)
zm = z_score(Rm,Rth,sigmaRm,0)
pm = p_value(zm, sigmaRm, 0)
print('the z score for Rm  is', zm)
print('the p value for Rm is', pm, 'therefore the result is not consistent with a confidence interval '
                                                  'of 68.3% because the p value is nowhere near 0.307')
plt.show()