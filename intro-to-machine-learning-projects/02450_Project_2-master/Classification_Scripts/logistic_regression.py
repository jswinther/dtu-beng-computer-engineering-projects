import sklearn.linear_model as lm
from sklearn import model_selection

from Scripts.data import *

K = 10
CV = model_selection.KFold(n_splits=K, shuffle=True)

# Initialize variable
training_error = np.empty((K))
test_error = np.empty((K))
coefs = np.empty((K, 4, 6))


k = 0
for train_index, test_index in CV.split(X):
    X_train, y_train = X[train_index, :], y[train_index]
    X_test, y_test = X[test_index, :], y[test_index]
    lr = lm.logistic.LogisticRegression(penalty='l2', C=1 / 1e-08, multi_class='auto', solver='lbfgs',
                                        max_iter=5000).fit(X_train, y_train)
    lr.fit(X_train, y_train)
    coefs[k] = lr.coef_
    y_est_test = lr.predict(X_test)
    y_est_train = lr.predict(X_train)
    misclass_rate_test = np.sum(y_est_test != y_test) / float(len(y_est_test))
    misclass_rate_train = np.sum(y_est_train != y_train) / float(len(y_est_train))
    test_error[k], training_error[k] = misclass_rate_test, misclass_rate_train
    print("Logistic Regression: ", test_error[k]*100,"%")
    k += 1

# https://stackoverflow.com/questions/21008858/formatting-floats-in-a-numpy-array

print("Average Logistic Regression: ", test_error.mean()*100, "%")
np.set_printoptions(precision=2)
print(coefs.mean(axis=0))
