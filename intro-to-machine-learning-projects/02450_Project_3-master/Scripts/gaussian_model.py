from Scripts.data import *
from matplotlib.pyplot import figure, show, ylabel
import numpy as np
from scipy.io import loadmat
from Tools.toolbox_02450 import clusterplot
from sklearn.mixture import GaussianMixture
from scipy.linalg import svd

K = 5
cov_type = 'full'  # e.g. 'full' or 'diag'

# define the initialization procedure (initial value of means)
initialization_method = 'random'  # 'random' or 'kmeans'
# random signifies random initiation, kmeans means we run a K-means and use the
# result as the starting point. K-means might converge faster/better than
# random, but might also cause the algorithm to be stuck in a poor local minimum

# type of covariance, you can try out 'diag' as well
reps = 1

# number of fits with different initalizations, best result will be kept
# Fit Gaussian mixture model
gmm = GaussianMixture(n_components=K, covariance_type=cov_type, n_init=reps,
                      tol=1e-6, reg_covar=1e-6, init_params=initialization_method).fit(X_std)
cls = gmm.predict(X_std)

# extract cluster labels
cds = gmm.means_
# extract cluster centroids (means of gaussians)
covs = gmm.covariances_
# extract cluster shapes (covariances of gaussians)
if cov_type.lower() == 'diag':
    new_covs = np.zeros([K, M, M])

    count = 0
    for elem in covs:
        temp_m = np.zeros([M, M])
        new_covs[count] = np.diag(elem)
        count += 1

    covs = new_covs


# PERFORM PCA ON THE DATA TO VISUALIZE
# Compute PCA by finding SVD of X_std
U, S, Vh = svd(X_std)
V = Vh.T

# Project data onto principal component space
PCA = X_std @ V