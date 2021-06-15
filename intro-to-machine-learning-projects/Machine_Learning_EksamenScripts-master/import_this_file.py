import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from apyori import apriori

def PCA_SVD(DiagonalInSMatrix):
    # PCA by computing SVD of Y

    I = np.identity(len(DiagonalInSMatrix))
    S = np.asarray(np.dot(DiagonalInSMatrix, I))

    # Compute variance explained by principal components
    rho = (S * S) / (S * S).sum()

    threshold = 0.95

    # Plot variance explained

    plt.figure()
    plt.plot(range(1, len(rho) + 1), rho, 'x-')
    plt.plot(range(1, len(rho) + 1), np.cumsum(rho), 'o-')
    plt.plot([1, len(rho)], [threshold, threshold], 'k--')
    plt.title('Variance explained by principal components')
    plt.xlabel('Principal component')
    plt.ylabel('Variance explained')
    plt.legend(['Individual', 'Cumulative', 'Threshold'])
    plt.grid()
    plt.show()

    print("The first line is cumulated sum of the variance explained by the principal components\n"
          "Second line is the variance explained by each component.")
    print(np.cumsum(rho))
    print(rho)


def density(list):
    K = len(list)
    return 1 / (np.sum(list) / K)


def ard(xi, obs):
    print("Calculating density for xi")
    print(density(xi))
    print("Calculating density for the specified neighbors.")
    sum = 0
    K = len(obs)
    for x in obs:
        q = density(x)
        print(q)
        sum = sum + q

    ard = density(xi) / (sum / K)
    print("Ard", ard)

def J(S, N, D):
    return S / ((1 / 2) * (N * (N - 1)) - D)

def SMC(_S, _N, _D):
    return (_S + _D)/((1/2)*_N*(_N-1))

def Jaccard_SMC_NMI(Z, Q):
    nZ = []
    nQ = []

    for x in Z:
        nZ.append(len(x))

    for x in Q:
        nQ.append(len(x))

    N = np.sum(nZ)
    K = len(Z)
    M = len(Q)

    n = np.zeros(shape=(K, M))

    for k in range(K):
        for m in range(M):
            n[k][m] = len(Z[k].intersection(Q[m]))

    S = 0
    for k in range(K):
        for m in range(M):
            S = S + (n[k][m] * (n[k][m] - 1)) / 2

    D = 0

    D = D + (N * (N - 1)) / 2

    temp1 = 0
    for k in range(K):
        temp1 = temp1 + (nZ[k] * (nZ[k] - 1)) / 2

    temp2 = 0
    for m in range(M):
        temp2 = temp2 + (nQ[m] * (nQ[m] - 1)) / 2

    D = D - temp1 - temp2 + S

    S / ((1 / 2) * (N * (N - 1)) - D)
    HZ, HQ, HZQ = 0, 0, 0
    for i in nZ:
        HZ = HZ - (i / N) * np.log(i / N)


    for i in nQ:
        HQ = HQ - (i / N) * np.log(i / N)

    for i in range(K):
        for j in range(M):
            p = n[i,j]/N
            Q = n[i, j] / N
            print("Q",Q)
            if Q != 0:
                pn = np.log(Q)
            else:
                pn = 0
            HZQ = HZQ - p*pn

    NMI = (HZ+HQ-HZQ)/(np.sqrt(HZ)*np.sqrt(HQ))

    print("S",S,"\nD",D,"\nJaccard Similarity",J(S,N,D),"\nSMC", SMC(S,N,D),"\nNMI",NMI)

def Impurity(Matrice, obs):
    # this uses class error
    R1 = np.zeros(shape=Matrice.shape)
    R1[:, 0] = Matrice[:, 0]
    R1[:, 1] = obs[:] - Matrice[:, 0]

    Nr1 = np.sum(R1)
    Nvk1 = np.sum(R1, axis=0)
    Ir1 = 1 - (np.max(obs)) / Nr1
    Ivk1 = 1 - (np.max(R1, axis=0)) / Nvk1
    Gain1 = Ir1 - np.sum((Nvk1[:] / Nr1) * Ivk1[:])

    R2 = np.zeros(shape=Matrice.shape)
    R2[:, 0] = Matrice[:, 1]
    R2[:, 1] = obs[:] - Matrice[:, 1]

    Nr2 = np.sum(R2)
    Nvk2 = np.sum(R2, axis=0)
    Ir2 = 1 - (np.max(obs)) / Nr2
    Ivk2 = 1 - (np.max(R2, axis=0)) / Nvk2
    Gain2 = Ir2 - np.sum((Nvk2[:] / Nr2) * Ivk2[:])
    print("Impurity Gain for the first and second split", Gain1, Gain2)


def Accuracy(Matrice, obs):
    # this uses class error
    R1 = np.zeros(shape=Matrice.shape)
    R1[:, 0] = Matrice[:, 0]
    R1[:, 1] = obs[:] - Matrice[:, 0]

    Nr1 = np.sum(R1)
    Nvk1 = np.sum(R1, axis=0)
    Ir1 = 1 - (np.max(obs)) / Nr1
    Ivk1 = 1 - (np.max(R1, axis=0)) / Nvk1
    Gain1 = np.sum(np.max(R1, axis=0)) / Nr1

    R2 = np.zeros(shape=Matrice.shape)
    R2[:, 0] = Matrice[:, 1]
    R2[:, 1] = obs[:] - Matrice[:, 1]

    Nr2 = np.sum(R2)
    Nvk2 = np.sum(R2, axis=0)
    Ir2 = 1 - (np.max(obs)) / Nr2
    Ivk2 = 1 - (np.max(R2, axis=0)) / Nvk2
    Gain2 = np.sum(np.max(R2, axis=0)) / Nr1
    print("Accuracy for the first and second split", Gain1, Gain2)

def recall(CM):
    return TP(CM) / (TP(CM) + FN(CM))


def accuracy(CM):
    N = np.sum(CM)
    return (TP(CM) + TN(CM)) / N


def accuracy_scaled(CM):
    return TP(CM) / (2 * (TP(CM) + FN(CM))) + TN(CM) / (2 * (TN(CM) + FP(CM)))


def _precision(CM):
    return TP(CM) / (TP(CM) + FP(CM))


def FP(CM):
    return CM[1, 0]


def FN(CM):
    return CM[0, 1]


def TP(CM):
    return CM[0, 0]


def TN(CM):
    return CM[1, 1]


def CM_to_accuarcy_recall_precision(CM):
    print("", TP(CM), FN(CM), "\n", FP(CM), TN(CM))
    print("Accuracy", accuracy(CM))
    print("Precision", _precision(CM))
    print("Recall", recall(CM))
    print("Accuracy Scaled", accuracy_scaled(CM))


def compute_activation(w02, w11, w12, w2, x1, x2):
    n1 = 1 / (1 + np.exp(-np.dot([1, x1, x2], w11)))
    n2 = 1 / (1 + np.exp(-np.dot([1, x1, x2], w12)))
    n = np.asarray([n1,n2])
    s = 0

    for i in range(2):
        s = s + (w2[i] * n[i])
    print("Activation", s + w02)

    # Spring 2018
    # Given the weights and 4 images of ANN's which of the four images is the correct image.

def compute_binary_matrix(bm, class_sets_as_list, N):
    return 1



