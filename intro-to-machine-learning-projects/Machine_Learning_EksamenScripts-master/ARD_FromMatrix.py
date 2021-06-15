from massImport import *

# Find K closest neighbours looking at their distance in the column of the "outlier". Solving problem 4 on Spring2019
# K = 2

def density(list):
    K = len(list)
    return 1/(np.sum(list)/K)

def ard(xi, obs):
    print("Calculating density for xi")
    print(density(xi))
    print("Calculating density for the specified neighbors.")
    sum = 0
    K = len(obs)
    for x in obs:
        q = density(x)
        print(q)
        sum = sum + q
   
    ard = density(xi) / (sum/K)
    return ard


# Edit these values, add more or less. These were just the closest 2 neighbors.
# this is the distance from o7 to o4, o6.

o7 = np.asarray([2.2, 1.7])


# Calculate the density o6 and o4.
o4 = np.asarray([0.9, 2.1])


o6 = np.asarray([1.8,1.7])

obs = [o4, o6]

print("Average relative distance", ard(o7, obs))

