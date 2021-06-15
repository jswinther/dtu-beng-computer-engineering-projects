from import_this_file import *

# Question 6
"""
gini_impurity_root = 1 - (3 * (18 / 54) ** 2)
print(gini_impurity_root)
gini_impurity_v1 = 1 - ((6 / 18) ** 2 + (9 / 18) ** 2 + (3 / 18) ** 2)
print(gini_impurity_v1)
gini_impurity_v2 = 1 - ((4 / 20) ** 2 + (6 / 20) ** 2 + (10 / 20) ** 2)
print(gini_impurity_v2)
gini_impurity_v3 = 1 - ((8 / 16) ** 2 + (3 / 16) ** 2 + (5 / 16) ** 2)
print(gini_impurity_v3)

impurity_gain = gini_impurity_root \
                - ((18 * gini_impurity_v1) / 54
                   + (20 * gini_impurity_v2) / 54
                   + (16 * gini_impurity_v3) / 54)

print(impurity_gain)
"""

# Question 9
"""
o10 = [0.4, 0.5, 0.7]
o9 = [0.1, 0.3, 0.4]
o8 = [0.3, 0.1, 0.2]
o7 = [0.1, 0.2, 0.3]
obs = [o9, o8, o7]
ard(o10, obs)
"""

# Question 11
"""
Z = [{1, 2, 3, 4, 8, 9}, {5, 6, 7, 10}]
Q = [{8, 9, 6, 7, 10}, {1, 2, 3, 4, 5}]
Jaccard_SMC_NMI(Z, Q)
"""

# Question 12
"""
w = [0.37, 0.29, 0,34]
u = [6.12, 6.55, 6.93]
s = [0.09, 0.13, 0.12]

p = 0
x = 6.55
for i in range(3):
    m = 1/(np.sqrt(2*np.pi*s[i]))
    print("m", m)
    e = np.exp((-1/(2*s[i]))*(x - u[i]))
    print("e", e)
    p = p + w[i] * m * e
    print("p", w[i] * m * e)
print(p)
"""

# Question 13
"""
confusion_matrix = np.asarray([
    [18, 12],
    [9, 15]
])

CM_to_accuarcy_recall_precision(confusion_matrix)
"""

# Question 14
"""
p_y1_x1 = 1
p_y1_x0 = 0.2/100
p_x1 = 2/10**6
p_x0 = 1 - p_x1

p = (p_y1_x1*p_x1)/(p_y1_x1*p_x1 + p_y1_x0*p_x0)
print("p", p*100)
"""

# Question 15
"""
w1 = [[21.78, -1.65, 0, -13.26, -8.46], [-9.60, -0.44, 0.01, 14.54, 9.50]]
w2 = [3.25, 3.46]
w02 = 2.84
x_with_1_first = [1, 6.8, 225, 0.44, 0.68]
temp_sum = 0

def h(x):
    print(x)
    if(np.max(x) < 0):
        return 0
    else:
        return np.max(x)

for j in range(2):
    temp_sum = temp_sum + (w2[j] * h(np.dot(x_with_1_first, w1[j])))

print(w02+temp_sum)
"""

# Question 20
"""
p_y3_wl1_hh1 = 0

p_y3 = 4/10
p_y2 = 2/10
p_y1 = 4/10

p_y3_hh = 3/4
p_y2_hh = 0
p_y1_hh = 1/4

p_y3_wl = 3/4
p_y2_wl = 2/2
p_y1_wl = 1/2

p = (p_y3 * p_y3_hh * p_y3_wl)/\
    ((p_y3 * p_y3_hh * p_y3_wl) +
     (p_y2 * p_y2_hh * p_y2_wl) +
     (p_y1 * p_y1_hh * p_y1_wl))

print(p)
"""



