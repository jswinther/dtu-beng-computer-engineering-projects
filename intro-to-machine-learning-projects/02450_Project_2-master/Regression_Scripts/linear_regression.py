import sklearn.linear_model as lm
from sklearn import model_selection

from Scripts.data import *

K = 10
CV = model_selection.KFold(n_splits=K, shuffle=True)

# Initialize variable
training_error = np.empty((K))
test_error = np.empty((K))
coefs = np.empty((K, 4, 6))
intercepts = np.empty((K, 4))


k = 0
for train_index, test_index in CV.split(X):
    X_train, y_train = X[train_index, :], y[train_index]
    X_test, y_test = X[test_index, :], y[test_index]
    lr = lm.LinearRegression(fit_intercept=True)
    lr.fit(X_train, y_train)
    coefs[k] = lr.coef_
    intercepts = lr.intercept_
    y_est_test = lr.predict(X_test)
    y_est_train = lr.predict(X_train)
    misclass_rate_test = np.sum(np.abs((y_est_test - y_test)) ** 2) / len(y_test)
    misclass_rate_train = np.sum(np.abs((y_est_train - y_train)) ** 2) / len(y_train)
    test_error[k] = misclass_rate_test
    training_error[k] = misclass_rate_train
    print("Linear Regression Test Squared Error: ", test_error[k])
    print("Linear Regression Train Squared Error: ", training_error[k])
    k += 1

# https://stackoverflow.com/questions/21008858/formatting-floats-in-a-numpy-array

print("Average Linear Regression Squared Error: ", test_error.mean())
np.set_printoptions(precision=2)
print(coefs.mean(axis=0))
print(intercepts.mean())