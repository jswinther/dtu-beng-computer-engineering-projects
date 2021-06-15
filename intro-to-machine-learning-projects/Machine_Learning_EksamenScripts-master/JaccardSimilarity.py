from massImport import *

# Actual Observation Clusters (See Coloring on Table)
Z = [{1,2},{3,4,5},{6,7,8,9,10}]

# Predicted Clusters (See Dendrogram or provided clusters.)
Q = [{10},{1,2,4,5,6,7},{3,8,9}]


# Change above arrays. {} are sets [] are arrays

###########################################################

def J(S, N, D):
    return S/((1/2)*(N*(N-1))-D)

nZ = []
nQ = []

for x in Z:
    nZ.append(len(x))

for x in Q:
    nQ.append(len(x))

N = np.sum(nZ)
K = len(Z)
M = len(Q)

n = np.zeros(shape=(K,M))


for k in range(K):
    for m in range(M):
        n[k][m] = len(Z[k].intersection(Q[m]))

S = 0
for k in range(K):
    for m in range(M):
        S = S + (n[k][m]*(n[k][m] - 1))/2


D = 0

D = D + (N*(N-1))/2

temp1 = 0
for k in range(K):
    temp1 = temp1 + (nZ[k]*(nZ[k]-1))/2

temp2 = 0
for m in range(M):
    temp2 = temp2 + (nQ[m]*(nQ[m]-1))/2

D = D - temp1 - temp2 + S

print("n =\n",n)
print("S =",S)
print("D =",D)
print("Jaccard =",J(S,N,D))




