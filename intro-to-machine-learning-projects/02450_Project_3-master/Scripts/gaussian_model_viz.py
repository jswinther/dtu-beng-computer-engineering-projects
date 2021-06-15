from Scripts.gaussian_model import *

maxdist = 0
bestx = 0
besty = 0

for i in range(9):
    for j in range(9):
        if i != j:

            idx = [i,j] # feature index, choose two features to use as x and y axis in the plot
            #clusterplot(X_std[:,idx], clusterid=cls, centroids=cds[:,idx], y=y, covars=covs[:,idx,:][:,:,idx])
            #plt.xlabel(attributeNames[i])
            #plt.ylabel(attributeNames[j])
            centroids = cds[:,idx]
            dist = 0
            for ii in range(K):
                for jj in range(K):
                    if ii != jj:
                        dist += np.linalg.norm(centroids[ii] - centroids[jj])

            if dist > maxdist:
                maxdist = dist
                bestx = i
                besty = j

            print(attributeNames[i], "vs", attributeNames[j],
                "\tdistance between clusters points {0}\n".format(dist))

print(attributeNames)
np.set_printoptions(formatter={'float': lambda x: "{0:0.2f}".format(x)})
for t in range(K):
    print(cds[t,:])

print("Best plot",attributeNames[bestx],attributeNames[besty],"distance",maxdist)
idx = [bestx,besty] # feature index, choose two features to use as x and y axis in the plot
figure(figsize=(14, 9))
clusterplot(X_std[:,idx], clusterid=cls, centroids=cds[:,idx], y=y, covars=covs[:,idx,:][:,:,idx])
plt.xlabel(attributeNames[bestx])
plt.ylabel(attributeNames[besty])
show()