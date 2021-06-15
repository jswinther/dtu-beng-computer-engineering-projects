import numpy as np
Matrice = np.asarray([
    [108, 58],
    [112, 75],
    [56, 116]
])
obs = [108 + 58, 112 + 75, 56 + 116]

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
Gain2 = np.sum(np.max(R2, axis=0)) / Nr1
print(Gain2)