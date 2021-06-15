# exercise 2.1.3
# (requires data structures from ex. 2.2.1)


from massImport import *

# PCA by computing SVD of Y
D = np.asarray([13.5, 7.6, 6.5, 5.8, 3.5, 2.0])
I = np.identity(len(D))
S = np.asarray(np.dot(D,I))


# Compute variance explained by principal components
rho = (S * S) / (S * S).sum()

threshold = 0.95

# Plot variance explained

plt.figure()
plt.plot(range(1, len(rho) + 1), rho, 'x-')
plt.plot(range(1, len(rho) + 1), np.cumsum(rho), 'o-')
plt.plot([1, len(rho)], [threshold, threshold], 'k--')
plt.title('Variance explained by principal components')
plt.xlabel('Principal component')
plt.ylabel('Variance explained')
plt.legend(['Individual', 'Cumulative', 'Threshold'])
plt.grid()
plt.show()



print("The first line is cumulated sum of the variance explained by the principal components\n"
      "Second line is the variance explained by each component.")
print(np.cumsum(rho))
print(rho)
