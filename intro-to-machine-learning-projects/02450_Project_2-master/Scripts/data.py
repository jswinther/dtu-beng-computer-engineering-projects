import pandas as pd
import numpy as np

df = pd.read_csv('../Data/car.csv', header=None)
df.columns = ['price', 'maint', 'doors', 'persons', 'lug_boot', 'safety', 'class']
price_label = {"vhigh": 0, "high": 1, "med": 2, "low": 3}
lug_label = {"small": 0, "med": 1, "big": 2}
safety_label = {"low": 0, "med": 1, "high": 2}
doors_label = {"2": 0, "3": 1, "4": 2, "5more": 3}
persons_label = {"2": 0, "4": 1, "more": 2}
df.price = [price_label[item] for item in df.price]
df.maint = [price_label[item] for item in df.maint]
df.lug_boot = [lug_label[item] for item in df.lug_boot]
df.safety = [safety_label[item] for item in df.safety]
df.doors = [doors_label[item] for item in df.doors]
df.persons = [persons_label[item] for item in df.persons]

subframe = df.iloc[:, 0:6]
subframe = subframe - subframe.mean()
subframe = subframe * 1/subframe.std()

raw_data = df.to_numpy()
cols = range(0, 6)
X = subframe.to_numpy()
attributeNames = np.asarray(['price', 'maint', 'doors', 'persons', 'lug_boot', 'safety'])
classLabels = raw_data[:, -1]  # -1 takes the last column
classNames = np.asarray(['unacc', 'acc', 'good', 'vgood'])
classDict = dict(zip(classNames, range(len(classNames))))
y = np.array([classDict[cl] for cl in classLabels], dtype=float)
N, M = X.shape
C = len(classNames)





