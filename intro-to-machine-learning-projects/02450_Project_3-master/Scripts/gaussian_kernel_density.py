from Scripts.data import *
from matplotlib.pyplot import (figure, imshow, bar, title, xticks, yticks, cm,
                               subplot, show)
from scipy.io import loadmat
from Tools.toolbox_02450 import gausKernelDensity
from sklearn.neighbors import NearestNeighbors

### Gausian Kernel density estimator
# cross-validate kernel width by leave-one-out-cross-validation
# (efficient implementation in gausKernelDensity function)
# evaluate for range of kernel widths
widths = X_std.var(axis=0).max() * (2.0 ** np.arange(-10, 3))
logP = np.zeros(np.size(widths))
for i, w in enumerate(widths):
    print('Fold {:2d}, w={:f}'.format(i, w))
    density, log_density = gausKernelDensity(X_std, w)
    logP[i] = log_density.sum()

val = logP.max()
ind = logP.argmax()

width = widths[ind]
print('Optimal estimated width is: {0}'.format(width))

# evaluate density for estimated width
density, log_density = gausKernelDensity(X_std, width)

# Sort the densities
i = (density.argsort(axis=0)).ravel()
density = density[i].reshape(-1, )

# Plot density estimate of outlier score
figure(1)
bar(range(N), density[:N])
title('Density estimate')
show()

### K-neighbors density estimator
# Neighbor to use:
K = 5

# Find the k nearest neighbors
knn = NearestNeighbors(n_neighbors=K).fit(X_std)
D, i = knn.kneighbors(X_std)

density = 1. / (D.sum(axis=1) / K)

# Sort the scores
i = density.argsort()
density = density[i]

# Plot k-neighbor estimate of outlier score (distances)
figure(2)
bar(range(N), density[:N])
title('KNN density: Outlier score')
show()

### K-nearest neigbor average relative density
# Compute the average relative density

knn = NearestNeighbors(n_neighbors=K).fit(X_std)
D, i = knn.kneighbors(X_std)
density = 1. / (D.sum(axis=1) / K)
avg_rel_density = density / (density[i[:, 1:]].sum(axis=1) / K)

# Sort the avg.rel.densities
i_avg_rel = avg_rel_density.argsort()
avg_rel_density = avg_rel_density[i_avg_rel]

# Plot k-neighbor estimate of outlier score (distances)
figure(3)
bar(range(N), avg_rel_density[:N])
title('KNN average relative density: Outlier score')
show()

### Distance to 5'th nearest neighbor outlier score
K = 5

# Find the k nearest neighbors
knn = NearestNeighbors(n_neighbors=K).fit(X_std)
D, i = knn.kneighbors(X_std)

# Outlier score
score = D[:, K - 1]
# Sort the scores
i = score.argsort()
score = score[i[::-1]]

# Plot k-neighbor estimate of outlier score (distances)
figure(4)
bar(range(N), score[:N])
title('5th neighbor distance: Outlier score')
show()


