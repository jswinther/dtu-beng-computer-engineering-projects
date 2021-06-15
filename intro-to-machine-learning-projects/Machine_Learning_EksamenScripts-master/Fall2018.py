from import_this_file import *

"""
# Number of observations belonging to each class.
n = [263, 359, 358]

######################################
# Change this matrix.
InputMatrix = np.array([[143.0, 223.0],
                        [137.0, 251.0],
                        [54.0, 197.0]])

ConfusionMatrix = np.asarray([
    [14, 18],
    [10, 14]
])
CM_to_accuarcy_recall_precision(ConfusionMatrix)

w11 = np.asarray([-1.2, -1.3, 0.6])
w12 = np.asarray([-1.0, -0.0, 0.9])
w2 = np.asarray([-0.3, 0.5])
w02 = 2.2

x1, x2 = 3, 3

compute_activation(w02, w11, w12, w2, x1, x2)
"""

# Question 2
# DiagonalInSMatrix = np.asarray([14.4, 8.19, 7.83, 6.91, 6.01])
# PCA_SVD(DiagonalInSMatrix)

# Question 4
# Check if o4 could be an outlier, compute ARD.
# K = 2

# Distance to its closest neighbors.
# o4 = [1.04, 1.88]

# Take the closest two neighbors and find their two closest neighbors
# o1 = [0.63, 1.02]
# o6 = [1.04, 1.8]

# ard(o4, [o1, o6])

# Question 7
"""
Z = [{1, 2, 3}, {4, 5, 6, 7, 8}, {9, 10}]
Q = [{1, 3, 5, 9, 10, 8, 7}, {4, 6}, {2}]
Jaccard_SMC_NMI(Z, Q)
"""


# Question 10
"""
matrix = np.asarray([
    [108, 58],
    [112, 75],
    [56, 116]
])

Impurity(matrix, np.asarray([108 + 58, 112 + 75, 56 + 116]))
Accuracy(matrix, np.asarray([108 + 58, 112 + 75, 56 + 116]))
"""

# Question 14
"""
bin_mat = np.asarray([
    [1, 1, 0, 0, 0, 1, 0, 0, 0, 1],
    [1, 0, 0, 0, 0, 0, 0, 0, 0, 0],
    [1, 1, 0, 0, 0, 1, 0, 0, 0, 1],
    [0, 1, 1, 1, 0, 0, 0, 1, 1, 0],
    [1, 1, 0, 0, 0, 1, 0, 0, 0, 1],
    [0, 1, 1, 1, 0, 0, 1, 1, 1, 0],
    [1, 1, 1, 0, 0, 1, 1, 1, 1, 0],
    [0, 1, 1, 1, 0, 1, 1, 0, 0, 1],
    [0, 0, 0, 0, 1, 1, 1, 0, 1, 1],
    [1, 0, 0, 0, 0, 1, 1, 1, 1, 0]
])
df = pd.DataFrame(bin_mat, columns=['f1','f2','f3','f4','f5','f6','f7','f8','f9','f10'])
list = [[0, 1, 2], [3, 4, 5, 6, 7], [8, 9]]
# value of class from 0 to 2, if y = 1, 2, 3
y = 0
N = 10
compute_binary_matrix(bin_mat, list, N=10)
a1 = df.loc[np.min(list[y]):np.max(list[y])].query('f1 == 1').shape[0]/len(list[y])
a2 = df.loc[np.min(list[y]):np.max(list[y])].query('f2 == 1').shape[0]/len(list[y])
a3 = df.loc[np.min(list[y]):np.max(list[y])].query('f6 == 0').shape[0]/len(list[y])
a4 = len(list[y])/N

y = y + 1

b1 = df.loc[np.min(list[y]):np.max(list[y])].query('f1 == 1').shape[0]/len(list[y])
b2 = df.loc[np.min(list[y]):np.max(list[y])].query('f2 == 1').shape[0]/len(list[y])
b3 = df.loc[np.min(list[y]):np.max(list[y])].query('f6 == 0').shape[0]/len(list[y])
b4 = len(list[y])/N

y = y + 1

c1 = df.loc[np.min(list[y]):np.max(list[y])].query('f1 == 1').shape[0]/len(list[y])
c2 = df.loc[np.min(list[y]):np.max(list[y])].query('f2 == 1').shape[0]/len(list[y])
c3 = df.loc[np.min(list[y]):np.max(list[y])].query('f6 == 0').shape[0]/len(list[y])
c4 = len(list[y])/N

# Calculate pNB
pNB = (a1*a2*a3*a4)/((a1*a2*a3*a4) + (b1*b2*b3*b4) + (c1*c2*c3*c4))
print(pNB)
"""


# Question 17
"""

"""




