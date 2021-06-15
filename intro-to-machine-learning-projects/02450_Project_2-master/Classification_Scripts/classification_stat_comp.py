from sklearn.model_selection import KFold
from sklearn import tree
import sklearn.linear_model as lm
from matplotlib.pyplot import figure, plot, xlabel, ylabel, show
import numpy as np
from sklearn.neighbors import KNeighborsClassifier
from sklearn import model_selection
import sklearn.tree
import scipy.stats
import scipy.stats as st
from Scripts.data import *
from Tools.toolbox_02450.statistics import mcnemar

K = 10
CV = model_selection.KFold(n_splits=K, shuffle=True)

CI_baseline = np.empty((K, 2))
CI_dtc = np.empty((K, 2))
CI_lr = np.empty((K, 2))

CI_lr_vs_dtc = np.empty((K, 2))
CI_lr_vs_baseline = np.empty((K, 2))
CI_baseline_vs_dtc = np.empty((K, 2))

p_lr_vs_baseline = np.empty(K)
p_baseline_vs_dtc = np.empty(K)
p_lr_vs_dtc = np.empty(K)

thetahat_lr_baseline = np.empty(K)
CI_mcnemar_lr_baseline = np.empty((K, 2))
p_mcnemar_lr_baseline = np.empty(K)

thetahat_lr_vs_dtc = np.empty(K)
CI_mcnemar_lr_vs_dtc = np.empty((K, 2))
p_mcnemar_lr_vs_dtc = np.empty(K)

thetahat_baseline_vs_dtc = np.empty(K)
CI_mcnemar_baseline_vs_dtc = np.empty((K, 2))
p_mcnemar_baseline_vs_dtc = np.empty(K)


k = 0
for train_index, test_index in CV.split(X):
    X_train, y_train = X[train_index, :], y[train_index]
    X_test, y_test = X[test_index, :], y[test_index]

    lr = lm.logistic.LogisticRegression(penalty='l2', C=1 / 1e-08, multi_class='auto', solver='lbfgs',
                                        max_iter=5000).fit(X_train, y_train)
    dtc = tree.DecisionTreeClassifier(max_depth=11).fit(X_train, y_train)

    yhat_lr = lr.predict(X_test)
    yhat_dtc = dtc.predict(X_test)
    yhat_baseline = np.zeros(len(y_test))

    # perform statistical comparison of the models
    # compute z with squared error.
    z_lr = np.abs(y_test - yhat_lr) ** 2
    z_dtc = np.abs(y_test - yhat_dtc) ** 2
    z_baseline = y_test ** 2
    alpha = 0.05

    CI_lr[k] = st.t.interval(1 - alpha, df=len(z_lr) - 1, loc=np.mean(z_lr), scale=st.sem(z_lr))  # Confidence interval
    CI_dtc[k] = st.t.interval(1 - alpha, df=len(z_dtc) - 1, loc=np.mean(z_dtc), scale=st.sem(z_dtc))
    CI_baseline[k] = st.t.interval(1 - alpha, df=len(z_baseline) - 1, loc=np.mean(z_baseline), scale=st.sem(z_baseline))

    # Compute confidence interval of z = zA-zB and p-value of Null hypothesis
    z_lr_vs_dtc = z_lr - z_dtc
    z_lr_vs_baseline = z_lr - z_baseline
    z_baseline_vs_dtc = z_baseline - z_dtc

    CI_lr_vs_dtc[k] = st.t.interval(1 - alpha, len(z_lr_vs_dtc) - 1, loc=np.mean(z_lr_vs_dtc),
                         scale=st.sem(z_lr_vs_dtc))  # Confidence interval
    CI_lr_vs_baseline[k] = st.t.interval(1 - alpha, len(z_lr_vs_baseline) - 1, loc=np.mean(z_lr_vs_baseline),
                         scale=st.sem(z_lr_vs_baseline))  # Confidence interval
    CI_baseline_vs_dtc[k] = st.t.interval(1 - alpha, len(z_baseline_vs_dtc) - 1, loc=np.mean(z_baseline_vs_dtc),
                         scale=st.sem(z_baseline_vs_dtc))  # Confidence interval

    p_lr_vs_dtc[k] = st.t.cdf(-np.abs(np.mean(z_lr_vs_dtc)) / st.sem(z_lr_vs_dtc), df=len(z_lr_vs_dtc) - 1)  # p-value
    p_lr_vs_baseline[k] = st.t.cdf(-np.abs(np.mean(z_lr_vs_baseline)) / st.sem(z_lr_vs_baseline), df=len(z_lr_vs_baseline) - 1)  # p-value
    p_baseline_vs_dtc[k] = st.t.cdf(-np.abs(np.mean(z_baseline_vs_dtc)) / st.sem(z_baseline_vs_dtc), df=len(z_baseline_vs_dtc) - 1)  # p-value

    # Compute the Jeffreys interval
    alpha = 0.05

    thetahat_lr_baseline[k], CI_mcnemar_lr_baseline[k], p_mcnemar_lr_baseline[k] = mcnemar(y_test, yhat_lr, yhat_baseline, alpha=alpha)
    thetahat_baseline_vs_dtc[k], CI_mcnemar_baseline_vs_dtc[k], p_mcnemar_baseline_vs_dtc[k] = mcnemar(y_test, yhat_baseline, yhat_dtc, alpha=alpha)
    thetahat_lr_vs_dtc[k], CI_mcnemar_lr_vs_dtc[k], p_mcnemar_lr_vs_dtc[k] = mcnemar(y_test, yhat_lr, yhat_dtc, alpha=alpha)

    k += 1

for i in range(50):
    print("\n")

print("CI Baseline: ", CI_baseline.mean(axis=0))
print("CI Logistic Regression: ", CI_lr.mean(axis=0))
print("CI Classification Tree: ", CI_dtc.mean(axis=0))
print("CI Logistic Regression vs Baseline: ", CI_lr_vs_baseline.mean(axis=0))
print("CI Baseline vs Decision Tree Classifier: ", CI_baseline_vs_dtc.mean(axis=0))
print("CI Logistic Regression vs Decision Tree Classifier: ", CI_lr_vs_dtc.mean(axis=0))
print("P-value Logistic Regression vs Baseline: ", p_lr_vs_baseline.mean())
print("P-value Baseline vs Decision Tree Classifier: ", p_baseline_vs_dtc.mean())
print("P-value Logistic Regression vs Decision Tree Classifier: ", p_lr_vs_dtc.mean())

print("Logistic Regression vs Baseline\ntheta = theta_A-theta_B point estimate", thetahat_lr_baseline.mean(), " CI: ", CI_mcnemar_lr_baseline.mean(axis=0), "p-value", p_mcnemar_lr_baseline.mean())
print("Baseline vs Decision Tree Classifier\ntheta = theta_A-theta_B point estimate", thetahat_baseline_vs_dtc.mean(), " CI: ", CI_mcnemar_baseline_vs_dtc.mean(axis=0), "p-value", p_mcnemar_baseline_vs_dtc.mean())
print("Logistic Regression vs Decision Tree Classifier\ntheta = theta_A-theta_B point estimate", thetahat_lr_vs_dtc.mean(), " CI: ", CI_mcnemar_lr_vs_dtc.mean(axis=0), "p-value", p_mcnemar_lr_vs_dtc.mean())