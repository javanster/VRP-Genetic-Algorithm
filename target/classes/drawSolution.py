import json
import matplotlib.pyplot as plt


def drawMap(instanceName):
    with open(f"src/main/resources/{instanceName}.json") as f:
        problemInstance = json.load(f)
    with open(f"src/main/resources/{instanceName}_result_map.json") as f:
        solution = json.load(f)

    depot = problemInstance["depot"]
    patients = problemInstance["patients"]
    result = solution["routes"]

    depot_coordinates = [depot["x_coord"], depot["y_coord"]]
    patient_coordinates = [(patient["x_coord"], patient["y_coord"])
                           for patient in patients.values()]

    plt.figure(figsize=(8, 6))

    route_colors = ['#1f77b4', '#ff7f0e', '#2ca02c', '#d62728', '#9467bd', '#8c564b',
                    '#e377c2', '#7f7f7f', '#bcbd22', '#17becf', '#aec7e8', '#ffbb78',
                    '#98df8a', '#ff9896', '#c5b0d5', '#c49c94', '#f7b6d2', '#c7c7c7',
                    '#dbdb8d', '#9edae5', '#ad494a', '#8c6d31', '#b5cf6b', '#843c39']

    for i, route in enumerate(result):
        if len(route) > 0:
            color = route_colors[i % len(route_colors)]
            plt.plot([depot_coordinates[0], patient_coordinates[route[0]-1][0]],
                     [depot_coordinates[1], patient_coordinates[route[0]-1][1]], color=color, linestyle='-', linewidth=1)
            for j in range(len(route) - 1):
                plt.plot([patient_coordinates[route[j]-1][0], patient_coordinates[route[j + 1]-1][0]],
                         [patient_coordinates[route[j]-1][1], patient_coordinates[route[j + 1]-1][1]], color=color, linestyle='-', linewidth=1)
            plt.plot([patient_coordinates[route[-1]-1][0], depot_coordinates[0]],
                     [patient_coordinates[route[-1]-1][1], depot_coordinates[1]], color=color, linestyle='-', linewidth=1)

    plt.plot(depot_coordinates[0], depot_coordinates[1], 'ro', label='Depot')

    for patient_coord in patient_coordinates:
        plt.plot(patient_coord[0], patient_coord[1], 'bo', label='Patient')

    plt.xlabel('X Coordinate')
    plt.ylabel('Y Coordinate')
    plt.title("Best found routes for " + instanceName)

    plt.show()


drawMap("train_0")
