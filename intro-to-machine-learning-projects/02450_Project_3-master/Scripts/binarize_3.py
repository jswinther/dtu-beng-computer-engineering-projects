from Scripts.data import *
# Remove famhist from x, since it is already binary, and only needs to be divided into 2 attributes
X_mod = np.delete(X_std, 4, 1)
XlowQ = np.ones((M)) * np.percentile(X_std, 33, axis=0)
XhighQ = np.ones((M)) * np.percentile(X_std, 67, axis=0)


# First one-of-k encode with 3 groups
X_bin = np.zeros((N,(M-1)*3))
for column in range(M-1):
    for row in range(N):
        if X_mod[row, column] < XlowQ[column]:
            X_bin[row, column*3] = 1
        elif XlowQ[column] < X_mod[row, column] < XhighQ[column]:
            X_bin[row, column*3 + 1] = 1
        else:
            X_bin[row, column*3 + 2] = 1

# Famhist and chd can only be divided into two groups
famhist_bin = np.zeros((N,2))
chd_bin = np.zeros((N,2))
for i in range(N):
    # Binarize famhist
    if X_std[i,4] > 0:
        famhist_bin[i,0] = 1
    else:
        famhist_bin[i,1] = 1

    # Binarize chd/y
    if y[i] == 1:
        chd_bin[i, 0] = 1
    else:
        chd_bin[i, 1] = 1

X_bin = np.c_[X_bin, np.c_[famhist_bin,chd_bin]]

attributeNames.remove('famhist')
attributeNames.append('famhist')
attributeNames.append('chd')

attributeNames_bin = []
for name in attributeNames:
    if name == 'famhist' or name == 'chd':
        attributeNames_bin.append(name + ' True')
        attributeNames_bin.append(name + ' False')
    else:
        attributeNames_bin.append(name + ' 0th-33th percentile')
        attributeNames_bin.append(name + ' 33rd-67th percentile')
        attributeNames_bin.append(name + ' 67th-100th percentile')