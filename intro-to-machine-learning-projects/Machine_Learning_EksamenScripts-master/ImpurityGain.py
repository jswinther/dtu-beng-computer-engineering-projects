from massImport import *

# Number of observations belonging to each class.
n = [263, 359, 358]

######################################
# Change this matrix.
InputMatrix = np.array([[143.0, 223.0],
                        [137.0, 251.0],
                        [54.0, 197.0]])


######################################
def Impurity(Matrice, obs):
    # We look at the split x4 <= 0.43 and x4 <= 0.55
    R1 = np.zeros(shape=Matrice.shape)
    R1[:, 0] = Matrice[:, 0]
    R1[:, 1] = obs[:] - Matrice[:, 0]

    Nr1 = np.sum(R1)
    Nvk1 = np.sum(R1, axis=0)
    Ir1 = 1 - (np.max(obs)) / Nr1
    Ivk1 = 1 - (np.max(R1, axis=0)) / Nvk1
    Gain1 = Ir1 - np.sum((Nvk1[:] / Nr1) * Ivk1[:])
    print(Gain1)

    R2 = np.zeros(shape=Matrice.shape)
    R2[:, 0] = Matrice[:, 1]
    R2[:, 1] = obs[:] - Matrice[:, 1]

    Nr2 = np.sum(R2)
    Nvk2 = np.sum(R2, axis=0)
    Ir2 = 1 - (np.max(obs)) / Nr2
    Ivk2 = 1 - (np.max(R2, axis=0)) / Nvk2
    Gain2 = Ir2 - np.sum((Nvk2[:] / Nr2) * Ivk2[:])

    return np.asarray([Gain1, Gain2])


def Accuracy(Matrice, obs):
    # We look at the split x4 <= 0.43 and x4 <= 0.55
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

    return np.asarray([Gain1, Gain2])


print("Impurity Gain for the first and second split", Impurity(InputMatrix, n))
print("Accuracy for the first and second split", Accuracy(InputMatrix, n))

# Spring 2018 Question 11
X = np.asarray([[23, 9],
                [8, 16]])
nx = [32, 24]
print(X)
print("Impurity Gain for the first and second split", Impurity(X, nx))
