from Scripts.data import *




X = X_std

# Range of K's to try
KRange = range(1, 21)
T = len(KRange)

covar_type = 'full'  # you can try out 'diag' as well
reps = 3  # number of fits with different initalizations, best result will be kept
init_procedure = 'kmeans'  # 'kmeans' or 'random'





K1 = 5
K2 = 5


# Allocate variables
BIC_ = np.zeros((K1, T, ))
AIC_ = np.zeros((K1, T, ))
CVE_ = np.zeros((K1, T, ))

for a in range(K1):
    # K-fold crossvalidation
    CV = model_selection.KFold(n_splits=K2, shuffle=True)
    # Allocate variables
    # Allocate variables
    BIC = np.zeros((T,))
    AIC = np.zeros((T,))
    CVE = np.zeros((T,))

    for t, K in enumerate(KRange):
        print('Fitting model for K={0}'.format(K))

        # Fit Gaussian mixture model
        gmm = GaussianMixture(n_components=K, covariance_type=covar_type,
                              n_init=reps, init_params=init_procedure,
                              tol=1e-6, reg_covar=1e-6).fit(X)

        BIC[t,] = gmm.bic(X)
        AIC[t,] = gmm.aic(X)
        # For each crossvalidation fold
        for train_index, test_index in CV.split(X):
            # extract training and test set for current CV fold
            X_train = X[train_index]
            X_test = X[test_index]



            # Fit Gaussian mixture model to X_train
            gmm = GaussianMixture(n_components=K, covariance_type=covar_type, n_init=reps).fit(X_train)
            # Get BIC and AIC

            # compute negative log likelihood of X_test
            CVE[t,] += -gmm.score_samples(X_test).sum()

    BIC_[a] = BIC
    AIC_[a] = AIC
    CVE_[a] = CVE


plot(KRange, BIC_.mean(axis=0), '-*b')
plot(KRange, AIC_.mean(axis=0), '-xr')
plot(KRange, 2 * CVE_.mean(axis=0), '-ok')

legend(['BIC', 'AIC', 'Crossvalidation'])
xlabel('K')
plt.savefig("BIC AIC Crossvalidation Mean.png")
plt.show()