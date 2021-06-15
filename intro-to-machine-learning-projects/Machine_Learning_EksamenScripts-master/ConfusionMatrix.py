from massImport import *

# Edit Confusion Matrix.

ConfusionMatrix = np.asarray([
    [14, 18],
    [10, 14]
])


def recall(CM):
    return TP(CM)/(TP(CM) + FN(CM))

def accuracy(CM):
    N = np.sum(CM)
    return (TP(CM) + TN(CM)) / N


def accuracy_scaled(CM):
    return TP(CM)/(2*(TP(CM)+FN(CM))) + TN(CM)/(2*(TN(CM)+FP(CM)))

def precision(CM):
    return TP(CM)/(TP(CM) + FP(CM))


def FP(CM):
    return CM[1, 0]


def FN(CM):
    return CM[0, 1]


def TP(CM):
    return CM[0, 0]


def TN(CM):
    return CM[1, 1]


def out(CM):
    print("",TP(CM), FN(CM), "\n", FP(CM), TN(CM))
    print("Accuracy", accuracy(CM))
    print("Precision", precision(CM))
    print("Recall", recall(CM))
    print("Accuracy Scaled", accuracy_scaled(CM))


out(ConfusionMatrix)
