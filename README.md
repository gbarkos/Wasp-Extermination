# Wasp-Extermination
A java project tackling a specific issue of exterminating wasps using genetic algorithms

We are given a map containing the coordinates of the wasp nests as well as their population. We've got 3 insect bombs to spare in order to kill off the wasps. Our goal is to find the best combination of bomb positioning in order to exterminate the highest count of wasps.

Usefull equations

K = n*(dmax)/(20*d+0.00001)

K = count of wasps killed in each nest
n = count of wasps in each nest
d = bomb distance form the nest
dmax = max distance between 2 nests

d = sqrt((x1-x2)^2+(y1-y2)^2)

d = distance between 2 points on the map
