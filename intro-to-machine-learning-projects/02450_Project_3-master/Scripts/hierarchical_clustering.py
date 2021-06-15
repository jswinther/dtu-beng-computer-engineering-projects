'''
    Project 3

    Benjamin Bruun                  174866
    Frederik David Damsgaard Popp   174878
    Jonathan Winther                174887
'''

import numpy as np
import pandas as pd
from scipy.cluster.hierarchy import linkage, fcluster, dendrogram
from Tools.toolbox_02450 import clusterplot, clusterval
import matplotlib.pyplot as plt
from scipy.linalg import svd
from Scripts.data import *
from matplotlib.pyplot import figure, title, plot, ylim, legend, show
import numpy as np
from scipy.io import loadmat
from Tools.toolbox_02450 import clusterval
from sklearn.cluster import k_means

# -----------------------------CLUSTERING
# Perform hierarchical/agglomerative clustering on data matrix
'''
    Linkage methods
    single      : min distance
    complete    : max distance
    average     : average distance
    weighted    : nearest neighbor
    ward        : Ward variance minimization algorithm
    centroid    : UPGMC algorithm
    median      : WPGMC algorithm
'''
Method = 'ward'
Metric = 'euclidean'

Z = linkage(X_std, method=Method, metric=Metric)

# Compute and display clusters by thresholding the dendrogram
Maxclust = 5
cls = fcluster(Z, criterion='maxclust', t=Maxclust)
plt.figure(1, figsize=(15, 15))

# PERFORM PCA ON THE DATA TO VISUALIZE
# Compute PCA by finding SVD of X_std
U, S, Vh = svd(X_std)
V = Vh.T

# Project data onto principal component space
PCA = X_std @ V

clusterplot(PCA, cls.reshape(cls.shape[0], 1), y=y)
plt.xlabel('PC1')
plt.ylabel('PC2')
plt.show()
# Display dendrogram
max_display_levels = 6
plt.figure(2, figsize=(10, 4))
dendrogram(Z, truncate_mode='level', p=max_display_levels)
plt.show()

# Maximum number of clusters:


methods = ['single', 'complete', 'average', 'weighted', 'ward', 'centroid', 'median']
clusters = range(2, 6)

LenMethods = len(methods)
LenClusters = len(clusters)

# Allocate variables:
Rand = np.zeros((LenMethods,))
Jaccard = np.zeros((LenMethods,))
NMI = np.zeros((LenMethods,))

RandMean = np.zeros((LenClusters, LenMethods, ))
JaccardMean = np.zeros((LenClusters, LenMethods, ))
NMIMean = np.zeros((LenClusters, LenMethods, ))

for k, r in enumerate(clusters):

    for m in range(LenMethods):
        Metric = 'euclidean'

        Z = linkage(X_std, method=methods[m], metric=Metric)
        cls = fcluster(Z, criterion='maxclust', t=r)
        # compute cluster validities:
        Rand[m], Jaccard[m], NMI[m] = clusterval(y, cls)
        Rand[m] = round(Rand[m], 4)
        Jaccard[m] = round(Jaccard[m], 4)
        NMI[m] = round(NMI[m], 4)

    RandMean[k, :] = Rand
    JaccardMean[k, :] = Jaccard
    NMIMean[k, :] = NMI


# Plot results:

title('Cluster validity')
legends = []
# plot(methods, Rand)
for k, r in enumerate(clusters):
    plot(methods, JaccardMean[k])
    legends.append("Number of Clusters {0}".format(r))
# plot(methods, NMI)
legend(legends, loc=4)
show()


print("\t", methods)
for k, r in enumerate(clusters):
    print(r, "\t", JaccardMean[k])
