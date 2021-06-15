from Scripts.data import *
import matplotlib.pyplot as plt

i = 5
j = 3
plt.title('Car Evaluation')
for c in range(len(classNames)):
    idx = y == c
    plt.scatter(x=X[idx, i],
                y=X[idx, j],
                s=50, alpha=0.5,
                label=classNames[c])
plt.legend()
plt.xlabel(attributeNames[i])
plt.ylabel(attributeNames[j])
plt.show()


