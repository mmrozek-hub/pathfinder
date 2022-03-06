# pathfinder
pathfinder project

Using rest api you can perform crud operations on graph. Additionally there is a service that allows you to find shortest path in graph between two verticies using following api:

/graphService/solve/1?begin=1&end=7

To find the path it is used jgrapht library (@see pom)



it is still missing a bit code for:
- shortest path should be returned in json!!
- exception handing for rest
- some unit tests

Have fun!
