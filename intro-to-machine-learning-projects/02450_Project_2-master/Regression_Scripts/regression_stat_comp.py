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
# exercise 8.3.1 Fit neural network classifiers using softmax output weighting
from matplotlib.pyplot import figure, show, title
from scipy.io import loadmat
from Tools.toolbox_02450 import dbplotf, train_neural_net, visualize_decision_boundary
from sklearn.model_selection import KFold, train_test_split
import numpy as np
import torch
# Load Matlab data file and extract variables of interest
from Scripts.data import *

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

    lr = lm.LinearRegression(fit_intercept=True).fit(X_train, y_train)

    dtc = lambda: torch.nn.Sequential(
        torch.nn.Linear(M, 7),  # M features to H hidden units
        torch.nn.ReLU(),  # 1st transfer function
        # Output layer:
        # H hidden units to C classes
        # the nodes and their activation before the transfer
        # function is often referred to as logits/logit output
        torch.nn.Linear(7, C),  # C logits
        # To obtain normalised "probabilities" of each class
        # we use the softmax-funtion along the "class" dimension
        # (i.e. not the dimension describing observations)
        torch.nn.Softmax(dim=1)  # final tranfer function, normalisation of logit output
    )

    loss_fn = torch.nn.CrossEntropyLoss()
    # Train the network:
    net, _, _ = train_neural_net(dtc, loss_fn,
                                 X=torch.tensor(X_train, dtype=torch.float),
                                 y=torch.tensor(y_train, dtype=torch.long),
                                 n_replicates=3,
                                 max_iter=10000)
    # Determine probability of each class using trained network
    softmax_logits = net(torch.tensor(X_test, dtype=torch.float))
    # Get the estimated class as the class with highest probability (argmax on softmax_logits)
    yhat_dtc = (torch.max(softmax_logits, dim=1)[1]).data.numpy()

    yhat_lr = lr.predict(X_test)
    yhat_baseline = np.ones(len(y_test)) * y.mean()

    # perform statistical comparison of the models
    # compute z with squared error.
    z_lr = np.abs(y_test - yhat_lr) ** 2
    z_dtc = np.abs(y_test - yhat_dtc) ** 2
    z_baseline = np.abs(y_test - yhat_baseline) ** 2
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
print("CI Linear Regression: ", CI_lr.mean(axis=0))
print("CI ANN: ", CI_dtc.mean(axis=0))
print("CI Linear Regression vs Baseline: ", CI_lr_vs_baseline.mean(axis=0))
print("CI Baseline vs ANN: ", CI_baseline_vs_dtc.mean(axis=0))
print("CI Linear Regression vs ANN: ", CI_lr_vs_dtc.mean(axis=0))
print("P-value Linear Regression vs Baseline: ", p_lr_vs_baseline.mean())
print("P-value Baseline vs ANN: ", p_baseline_vs_dtc.mean())
print("P-value Linear Regression vs ANN: ", p_lr_vs_dtc.mean())

print("Linear Regression vs Baseline\ntheta = theta_A-theta_B point estimate", thetahat_lr_baseline.mean(), " CI: ", CI_mcnemar_lr_baseline.mean(axis=0), "p-value", p_mcnemar_lr_baseline.mean())
print("Baseline vs ANN\ntheta = theta_A-theta_B point estimate", thetahat_baseline_vs_dtc.mean(), " CI: ", CI_mcnemar_baseline_vs_dtc.mean(axis=0), "p-value", p_mcnemar_baseline_vs_dtc.mean())
print("Linear Regression vs ANN\ntheta = theta_A-theta_B point estimate", thetahat_lr_vs_dtc.mean(), " CI: ", CI_mcnemar_lr_vs_dtc.mean(axis=0), "p-value", p_mcnemar_lr_vs_dtc.mean())