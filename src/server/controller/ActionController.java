package server.controller;

import server.view.DatabaseConnection;
import shared.Action;
import shared.ActionAbstract;
import shared.Biking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActionController {
    private DatabaseConnection databaseConnection;

    public void setDatabaseConnection() {
        this.databaseConnection = DatabaseConnection.getInstance();
    }

    /**
     * Update the neighbourhood total CO2 saved based on the Action object.
     * If the neighbourhood does not exist, create a new neighbourhood, initialize its CO2 savings to zero, and update.
     *
     * @param object the Action object containing the CO2 saved.
     * @throws SQLException if a database access error occurs
     */
    public void doAction(ActionAbstract object) throws SQLException {
        String neighbourhoodName = object.getLocation();
        int actionSavedCO2 = object.getSavedCo2();

        if (!neighbourhoodExists(neighbourhoodName)) {
            createNewNeighbourhood(neighbourhoodName);
        }
        updateNeighbourhoodSavedCo2(neighbourhoodName, actionSavedCO2);
    }

    /**
     * Checks if a neighbourhood with the given name exists in the database.
     *
     * @param neighbourhoodName the name of the neighbourhood to check for existence
     * @return true if the neighbourhood exists, false otherwise
     */
    public boolean neighbourhoodExists(String neighbourhoodName){
        String stringQuery = "SELECT name FROM neighbourhoods";
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(stringQuery);
            ResultSet resultSet = databaseConnection.sendQuery(statement);
            while (resultSet.next()){
                if (neighbourhoodName.equals(resultSet.getString("name"))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Inserts a new neighbourhood into the database with the given name and initializes its CO2 savings to zero.
     *
     * @param name the name of the new neighbourhood
     * @throws SQLException if a database access error occurs
     */
    private void createNewNeighbourhood(String name) throws SQLException {
        String sqlQuery = "INSERT INTO neighbourhoods VALUES(?, ?)";

        PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sqlQuery);
        statement.setString(1, name);
        statement.setInt(2, 0);
        databaseConnection.sendUpdate(statement);
    }

    /**
     * Updates the total CO2 saved for a specific neighbourhood in the database.
     *
     * @param neighbourhoodName the name of the neighbourhood to update CO2 savings for
     * @param savedCo2 the amount of CO2 saved to add to the neighbourhood's total
     * @throws SQLException if a database access error occurs
     */
    private void updateNeighbourhoodSavedCo2(String neighbourhoodName, int savedCo2) throws SQLException {
        try (ResultSet resultSet = getCurrentNeighbourhoodStats(neighbourhoodName)) {
            if (resultSet.next()) {
                int updatedCo2 = resultSet.getInt("co2_saved") + savedCo2;

                String sqlQuery = "UPDATE neighbourhoods SET co2_saved = ? WHERE name = ?";
                PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sqlQuery);
                statement.setInt(1, updatedCo2);
                statement.setString(2, neighbourhoodName);

                databaseConnection.sendUpdate(statement);
                System.out.println("does this happen?");
            } else {
                System.out.println("Neighbourhood not found for update.");
            }
        }
    }

    /**
     * Retrieves the current CO2 savings for a specific neighbourhood from the database.
     *
     * @param neighbourhoodName the name of the neighbourhood to retrieve CO2 savings for
     * @return the ResultSet containing the CO2 saved data for the specified neighbourhood
     */
    private ResultSet getCurrentNeighbourhoodStats(String neighbourhoodName) {
        String stringQuery = "SELECT co2_saved FROM neighbourhoods WHERE name = ?";
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(stringQuery);
            statement.setString(1, neighbourhoodName);
            return databaseConnection.sendQuery(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}