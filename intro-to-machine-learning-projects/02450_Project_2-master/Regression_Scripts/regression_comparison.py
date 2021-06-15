import sklearn.linear_model as lm
from Tools.toolbox_02450 import train_neural_net
from sklearn.model_selection import KFold
import torch
from Scripts.data import *
from sklearn.metrics import r2_score

K_inner, K_outer = 5, 5
KFold_Inner = KFold(n_splits=K_inner, shuffle=True)
KFold_Outer = KFold(n_splits=K_outer, shuffle=True)

Error_gen_s = np.zeros((3, K_outer), dtype=float)
Error_train_s = np.zeros((3, K_outer), dtype=float)
Error_gen_r2 = np.zeros((3, K_outer), dtype=float)
Error_train_r2 = np.zeros((3, K_outer), dtype=float)

E_test = np.empty(K_outer)
i = 0
for par, test in KFold_Outer.split(X):

    X_par, y_par = X[par, :], y[par]
    X_test, y_test = X[test, :], y[test]
    E_val = np.empty((3, K_inner))
    E_train = np.empty((3, K_inner))
    E_valr2 = np.empty((3, K_inner))
    E_trainr2 = np.empty((3, K_inner))
    j = 0
    for train, val in KFold_Inner.split(X_par):
        print("Loop i ", i, " j ", j)
        X_train, y_train = X_par[train, :], y_par[train]
        X_val, y_val = X_par[val, :], y_par[val]

        k = 0

        y_val_est = np.ones(len(y_val))*y.mean()
        y_train_est = np.ones(len(y_train))*y.mean()

        E_val[k, j] = np.sum(np.abs((y_val - y_val_est) ** 2)) / len(y_val)
        E_train[k, j] = np.sum(np.abs((y_train - y_train_est) ** 2)) / len(y_train_est)

        k += 1

        lr = lm.LinearRegression().fit(X_train, y_train)
        y_val_est = lr.predict(X_val)
        y_train_est = lr.predict(X_train)

        E_val[k, j] = np.sum(np.abs((y_val - y_val_est) ** 2)) / len(y_val)
        E_train[k, j] = np.sum(np.abs((y_train - y_train_est) ** 2)) / len(y_train_est)

        E_valr2[k, j] = r2_score(y_val, y_val_est)
        E_trainr2[k, j] = r2_score(y_train, y_train_est)

        k += 1

        ann = lambda: torch.nn.Sequential(
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
        net, _, _ = train_neural_net(ann, loss_fn,
                                     X=torch.tensor(X_train, dtype=torch.float),
                                     y=torch.tensor(y_train, dtype=torch.long),
                                     n_replicates=1,
                                     max_iter=10000)
        # Determine probability of each class using trained network
        softmax_logits = net(torch.tensor(X_val, dtype=torch.float))
        # Get the estimated class as the class with highest probability (argmax on softmax_logits)
        y_val_est = (torch.max(softmax_logits, dim=1)[1]).data.numpy()

        # Determine probability of each class using trained network
        softmax_logits = net(torch.tensor(X_train, dtype=torch.float))
        # Get the estimated class as the class with highest probability (argmax on softmax_logits)
        y_train_est = (torch.max(softmax_logits, dim=1)[1]).data.numpy()

        E_val[k, j] = np.sum((y_val - y_val_est) ** 2) / len(y_val)
        E_train[k, j] = np.sum(np.abs((y_train - y_train_est) ** 2)) / len(y_train_est)
        E_valr2[k, j] = r2_score(y_val, y_val_est)
        E_trainr2[k, j] = r2_score(y_train, y_train_est)

        k += 1
        j += 1

    # Compute gen s.
    for s in range(3):
        Error_gen_s[s, i] += E_val[s, :].mean()
        Error_train_s[s, i] += E_train[s, :].mean()
        Error_gen_r2[s, i] += E_valr2[s, :].mean()
        Error_train_r2[s, i] += E_trainr2[s, :].mean()

    i += 1

for u in range(3):
    print("Model", u)
    print('- Training error: {0}'.format(Error_train_s[u, :].mean()))
    print('- Test error:     {0}'.format(Error_gen_s[u, :].mean()))
    print('- R^2 train:     {0}'.format((Error_train_r2[u, :].mean())))
    print('- R^2 test:     {0}\n'.format((Error_gen_r2[u, :].mean())))




