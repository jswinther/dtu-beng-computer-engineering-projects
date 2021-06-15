from ctypes import c_bool

from sklearn.model_selection import KFold
from sklearn import tree
from Scripts.data import *
from matplotlib.pyplot import figure, plot, xlabel, ylabel, legend, show

K_inner, K_outer = 10, 10
KFold_Inner = KFold(n_splits=K_inner, shuffle=True)
KFold_Outer = KFold(n_splits=K_outer, shuffle=True)
f = figure()

# S different model complexities to evaluate.
models = np.arange(2, 21, 1)

# Estimated generalization error
E_gen_s = np.zeros(len(models), dtype=float)
Average_error_gen_s = np.zeros((K_outer, len(models)))
E_gen = 0


Test_error = np.empty(K_outer)

# Make K1 folds.
i = 0
for par, test in KFold_Outer.split(X):

    # Split into parameter training data and test data.
    X_par, y_par = X[par, :], y[par]
    X_test, y_test = X[test, :], y[test]
    E_val = np.empty((K_inner, (len(models))))

    j = 0
    for train, val in KFold_Inner.split(X_par):

        X_train, y_train = X_par[train, :], y_par[train]
        X_val, y_val = X_par[val, :], y_par[val]

        for k, s in enumerate(models):
            dtc = tree.DecisionTreeClassifier(max_depth=s).fit(X_train, y_train)
            y_est = dtc.predict(X_val)
            E_val[j, k] = np.sum(y_est != y_val) / float(len(y_est))

        j += 1

    for s in range(len(models)):
        E_gen_s[s] = E_val[:, s].mean()
    Average_error_gen_s[i] = E_gen_s
    plot(models, E_gen_s)
    best_depth = np.argmin(E_gen_s)
    print("Classification Tree with depth: ", best_depth, " \terror ", E_gen_s[best_depth] * 100, "%")

    dtc = tree.DecisionTreeClassifier(max_depth=best_depth).fit(X_par, y_par)
    y_best_est = dtc.predict(X_test)
    Test_error[i] = np.sum(y_best_est != y_test) / float(len(y_test))
    i += 1


print("Average Classification Tree Error: ", Test_error.mean() * 100, "%")
print("Average best depth ", np.argmin(Average_error_gen_s.mean(axis=0)))
plot(models, Average_error_gen_s.mean(axis=0), 'x', color='k')
xlabel('Model complexity (max tree depth)')
ylabel('Error rate')
show()
