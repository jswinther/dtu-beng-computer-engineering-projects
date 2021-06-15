from massImport import *
# Define weights and max x, y Solution to 10 in Spring 2019

w11 = np.asarray([-1.2, -1.3, 0.6])
w12 = np.asarray([-1.0, -0.0, 0.9])
w2 = np.asarray([-0.3, 0.5])
w02 = 2.2

x1, x2 = 3, 3

n1 = 1/(1 + np.exp(-np.dot([1, x1, x2], w11)))
n2 = 1/(1 + np.exp(-np.dot([1, x1, x2], w12)))
n = np.asarray([n1,n2])

s = 0

for i in range(2):
    s = s + (w2[i] * n[i])
print(s + w02)

# Spring 2018
# Given the weights and 4 images of ANN's which of the four images is the correct image.

w11 = np.asarray([0.0189, 0.9159, -0.4256])
w21 = np.asarray([3.7336, -0.8003, 5.0741])
w2 = np.asarray([-0.3440*10**(-6), 0.0429*10**(-6)])
w02 = 0.3799*10**(-6)
x5, x6 = 0, 3
n1 = 1/(1 + np.exp(-np.dot([1, x5, x6], w11)))
n2 = 1/(1 + np.exp(-np.dot([1, x5, x6], w21)))
n = np.asarray([n1,n2])

s = 0

for i in range(2):
    s = s + (w2[i] * n[i])
print(s + w02)