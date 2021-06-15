from Scripts.data import *
from apyori import apriori
from Tools.similarity import binarize2
#from Scripts.binarize_3 import *               #Remember to outcomment this, if binarize2 is used
X = np.c_[X_std,y]
attributeNames.append("chd")
Xbin, attributeNamesBin = binarize2(X, attributeNames)
labels = attributeNamesBin


# ex12_1_4
# This is a helper function that transforms a binary matrix into transactions.
# Note the format used for courses.txt was (nearly) in a transaction format,
# however we will need the function later which is why we first transformed
# courses.txt to our standard binary-matrix format.
def mat2transactions(X, labels=[]):
    T = []
    for i in range(X.shape[0]):
        l = np.nonzero(X[i, :])[0].tolist()
        if labels:
            l = [labels[i] for i in l]
        T.append(l)
    return T

# This function print the found rules and also returns a list of rules in the format:
# [(x,y), ...]
# where x -> y
def print_apriori_rules(rules):
    frules = []
    for r in rules:
        for o in r.ordered_statistics:
            conf = o.confidence
            supp = r.support
            x = ", ".join( list( o.items_base ) )
            y = ", ".join( list( o.items_add ) )
            print("{%s} -> {%s}  (supp: %.3f, conf: %.3f)"%(x,y, supp, conf))
            frules.append( (x,y) )
    return frules

def print_apriori_rules_chd(rules):
    frules = []
    for r in rules:
        for o in r.ordered_statistics:
            conf = o.confidence
            supp = r.support
            x = ", ".join( list( o.items_base ) )
            y = ", ".join( list( o.items_add ) )
            if y.__contains__("chd True") or y.__contains__("chd 50th-100th percentile"):
                print("{%s} -> {%s}  (supp: %.3f, conf: %.3f)"%(x,y, supp, conf))
            frules.append( (x,y) )
    return frules
# Given the processed data in the previous exercise this becomes easy:
T = mat2transactions(Xbin,labels=attributeNamesBin)
rules = apriori(T, min_support=0.1, min_confidence=.6)
print_apriori_rules_chd(rules)
