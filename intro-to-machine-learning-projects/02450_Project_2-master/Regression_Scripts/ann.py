import sklearn.linear_model as lm
from Tools.toolbox_02450 import train_neural_net
from sklearn.model_selection import KFold
import torch
from Scripts.data import *

K_inner, K_outer = 3, 3
KFold_Inner = KFold(n_splits=K_inner, shuffle=True)
KFold_Outer = KFold(n_splits=K_outer, shuffle=True)

hidden_layers = np.array(range(1, 11))
Error_gen_s = np.zeros((len(hidden_layers), K_outer), dtype=float)
E_test = np.empty(K_outer)
i = 0
for par, test in KFold_Outer.split(X):

    X_par, y_par = X[par, :], y[par]
    X_test, y_test = X[test, :], y[test]
    E_val = np.empty((len(hidden_layers), K_inner))
    j = 0
    for train, val in KFold_Inner.split(X_par):
        X_train, y_train = X_par[train, :], y_par[train]
        X_val, y_val = X_par[val, :], y_par[val]

        for k in hidden_layers:
            print(i, j, k)
            dtc = lambda: torch.nn.Sequential(
                torch.nn.Linear(M, k),  # M features to H hidden units
                torch.nn.ReLU(),  # 1st transfer function
                # Output layer:
                # H hidden units to C classes
                # the nodes and their activation before the transfer
                # function is often referred to as logits/logit output
                torch.nn.Linear(k, C),  # C logits
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
            softmax_logits = net(torch.tensor(X_val, dtype=torch.float))
            # Get the estimated class as the class with highest probability (argmax on softmax_logits)
            yhat_dtc = (torch.max(softmax_logits, dim=1)[1]).data.numpy()
            E_val[k-1, j] = np.sum((y_val - yhat_dtc) ** 2) / len(y_val)

        np.set_printoptions(precision=2)
        best_h = np.argmin(E_val[:, j])
        print(best_h+1, " ", E_val[best_h-1, j]) # values in cross-validation table.
        j += 1

    for s in range(len(hidden_layers)):
        Error_gen_s[s, i] += E_val[s, :].mean()
    i += 1

for i in range(3):
    for k in range(K_outer):
        print((Error_gen_s[i, k]))
    print("\n")

for k in hidden_layers:
    print("h = ", k, " error ", (Error_gen_s[k, :]).mean())
