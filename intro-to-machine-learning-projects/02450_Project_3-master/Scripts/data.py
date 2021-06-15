'''
    Project 3

    Benjamin Bruun                  174866
    Frederik David Damsgaard Popp   174878
    Jonathan Winther                174887
'''

import numpy as np
import pandas as pd
from matplotlib.pyplot import figure, show
from scipy.io import loadmat
from Tools.toolbox_02450 import clusterplot
from sklearn.mixture import GaussianMixture
from scipy.linalg import svd
# exercise 11.1.1
from matplotlib.pyplot import figure, show
from scipy.io import loadmat
from Tools.toolbox_02450 import clusterplot
from sklearn.mixture import GaussianMixture
# exercise 11.1.5
from matplotlib.pyplot import figure, plot, legend, xlabel, show
from scipy.io import loadmat
from sklearn.mixture import GaussianMixture
from sklearn import model_selection
import matplotlib.pyplot as plt


filename = 'project_data.csv'
df = pd.read_csv(filename)

df.loc[df.famhist == 'Present', 'famhist'] = 1
df.loc[df.famhist == 'Absent', 'famhist'] = 0

raw_data = df.to_numpy()

cols = range(1, 10)
X_raw = raw_data[:, cols]

attributeNames = df.columns[cols].tolist()
classNames = ['Diagnosed', 'Not Diagnosed']

y = raw_data[:, -1]

N, M = X_raw.shape

C = len(np.unique(y))

# The X_std variable in this project will be the standardized
X_std = (X_raw - np.ones((N, 1)) * X_raw.mean(axis=0)) / X_raw.std(axis=0)