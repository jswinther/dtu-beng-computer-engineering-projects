import sklearn.linear_model as lm
from sklearn import model_selection

from Scripts.data import *

K = 10
CV = model_selection.KFold(n_splits=K, shuffle=True)

# Initialize variable
training_error = np.empty((K))
test_error = np.empty((K))

k = 0
for train_index, test_index in CV.split(X):
    X_train, y_train = X[train_index, :], y[train_index]
    X_test, y_test = X[test_index, :], y[test_index]
    y_est_test = np.zeros(len(y_test))
    y_est_train = np.zeros(len(y_train))
    misclass_rate_test = np.sum(y_est_test != y_test) / float(len(y_est_test))
    misclass_rate_train = np.sum(y_est_train != y_train) / float(len(y_est_train))
    test_error[k], training_error[k] = misclass_rate_test, misclass_rate_train
    print("Baseline: ", test_error[k] * 100, "%")
    k += 1

print("Average Baseline: ", test_error.mean()*100, "%")