from sklearn.model_selection import KFold
from sklearn.linear_model import LogisticRegression
from Scripts.data import *
import matplotlib.pyplot as plt
from sklearn import metrics
K_inner, K_outer = 10, 10
KFold_Inner = KFold(n_splits=K_inner, shuffle=True)
KFold_Outer = KFold(n_splits=K_outer, shuffle=True)
plt.figure(figsize=(8, 8))

# S different model complexities to evaluate.
lambda_interval = np.logspace(-8, 2, 11)

# Estimated generalization error
E_gen_s = np.zeros(len(lambda_interval), dtype=float)
Average_error_gen_s = np.zeros((K_outer, len(lambda_interval)))
E_gen = 0

best_depths = np.zeros(K_outer)
Test_error = np.empty(K_outer)

# Make K1 folds.
i = 0
for par, test in KFold_Outer.split(X):

    # Split into parameter training data and test data.
    X_par, y_par = X[par, :], y[par]
    X_test, y_test = X[test, :], y[test]
    E_val = np.empty((K_inner, (len(lambda_interval))))

    j = 0
    for train, val in KFold_Inner.split(X_par):

        X_train, y_train = X_par[train, :], y_par[train]
        X_val, y_val = X_par[val, :], y_par[val]
        coefficient_norm = np.zeros(len(lambda_interval))

        for k in range(0, len(lambda_interval)):
            mdl = LogisticRegression(penalty='l2', C=1 / lambda_interval[k], multi_class='auto', solver='lbfgs', max_iter=5000)
            mdl.fit(X_train, y_train)
            y_test_est = mdl.predict(X_val).T
            E_val[j, k] = 1 - metrics.accuracy_score(y_val, y_test_est)

        min_error = np.min(E_val[j])
        opt_lambda_idx = np.argmin(E_val[j])
        opt_lambda = lambda_interval[opt_lambda_idx]
        plt.semilogx(lambda_interval, E_val[j] * 100)
        plt.semilogx(opt_lambda, min_error * 100, 'o')
        j += 1

    for s in range(len(lambda_interval)):
        E_gen_s[s] = E_val[:, s].mean()
    Average_error_gen_s[i] = E_gen_s
    best_depth = np.argmin(E_gen_s)
    print("Regularized Logistic Regression with lambda: ", lambda_interval[best_depth], "\terror ", E_gen_s[best_depth] * 100, "%")
    mdl = LogisticRegression(penalty='l2', C=1 / lambda_interval[best_depth], multi_class='auto', solver='lbfgs', max_iter=5000).fit(X_train, y_train)
    y_best_est = mdl.predict(X_test)
    print(mdl.coef_, mdl.intercept_)
    best_depths[i] = lambda_interval[best_depth]
    Test_error[i] = np.sum(y_best_est != y_test) / float(len(y_test))
    i += 1

print("Average Regularized Logistic Regression: ", Test_error.mean() * 100, "%")
print("Average best lambda ", lambda_interval[np.argmin(Average_error_gen_s.mean(axis=0))])

plt.text(1e-8, 3, "Minimum test error: " + str(np.round(min_error*100,2)) + ' % at 1e' + str(np.round(np.log10(opt_lambda),2)))
plt.xlabel('Regularization strength, $\log_{10}(\lambda)$')
plt.ylabel('Error rate (%)')
plt.title('Classification error')
plt.legend(['Training error','Test error','Test minimum'],loc='upper right')
plt.ylim([0, 50])
plt.grid()
plt.show()