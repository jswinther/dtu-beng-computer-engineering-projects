from sklearn.model_selection import KFold
from sklearn import tree
import sklearn.linear_model as lm
from matplotlib.pyplot import figure, plot, xlabel, ylabel, show
import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from sklearn import model_selection
import sklearn.tree
import scipy.stats
import numpy as np, scipy.stats as st


from Scripts.data import *
from matplotlib.pyplot import figure, plot, xlabel, ylabel, legend, show

K_inner, K_outer = 10, 10
KFold_Inner = KFold(n_splits=K_inner, shuffle=True)
KFold_Outer = KFold(n_splits=K_outer, shuffle=True)

models = [lm.logistic.LogisticRegression(penalty='l2', C=1 / 1e-08, multi_class='auto', solver='lbfgs',
                                         max_iter=5000),
          tree.DecisionTreeClassifier(max_depth=13)]

Error_gen_s = np.zeros((len(models)+1, K_outer), dtype=float)
E_test = np.empty(K_outer)
i = 0
for par, test in KFold_Outer.split(X):

    X_par, y_par = X[par, :], y[par]
    X_test, y_test = X[test, :], y[test]
    E_val = np.empty((len(models)+1, K_inner))
    j = 0
    for train, val in KFold_Inner.split(X_par):

        X_train, y_train = X_par[train, :], y_par[train]
        X_val, y_val = X_par[val, :], y_par[val]

        k = 0
        # baseline
        y_est = np.zeros(len(y_val))
        E_val[k, j] = np.sum(y_est != y_val) / float(len(y_est))
        k += 1

        for s in models:
            s.fit(X_train, y_train)
            y_est = s.predict(X_val)
            E_val[k, j] = np.sum(y_est != y_val) / float(len(y_est))
            k += 1
        j += 1

    for s in range(len(models)+1):
        Error_gen_s[s, i] += E_val[s, :].mean()
    i += 1


for i in range(len(models)+1):
    for k in range(K_outer):
        print((Error_gen_s[i, k])*100)
    print("\n")

print((Error_gen_s[0, :]).mean()*100)
print((Error_gen_s[1, :]).mean()*100)
print((Error_gen_s[2, :]).mean()*100, "\n\n")



