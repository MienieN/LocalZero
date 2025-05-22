package server.controller;

import server.view.DatabaseConnection;
import shared.Action;
import shared.Biking;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActionController {
    private DatabaseConnection databaseConnection;

    //TODO rename this
    public void doAction(Action object) throws SQLException {
        String neighbourhoodName = object.getUser().getLocation();
        int  updatedCo2;
        String sqlQuery = "";

        if (neighbourhoodExists(neighbourhoodName)){
            ResultSet resultSet = getCurrentNeighbourhoodStats(neighbourhoodName);
            resultSet.next();
            updatedCo2 = resultSet.getInt("co2_saved");
            if (object instanceof Biking){
                int updatedKilometers = resultSet.getInt("kilometers_biked") + ((Biking) object).getKilometers();
                sqlQuery = "update neighbourhoods set kilometers_biked = " + updatedKilometers + ", co2_saved = " + updatedCo2 + " where name = '" + neighbourhoodName + "'";
            }

            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(sqlQuery);
            databaseConnection.sendQuery(statement);
            }

    }

    private ResultSet getCurrentNeighbourhoodStats(String neighbourhoodName) {
        String stringQuery = "select kilometers_biked, foodwaste_composted, co2_saved from neighbourhoods where name = 'location'";
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(stringQuery);
            ResultSet resultset = databaseConnection.sendQuery(statement);
            return resultset;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean neighbourhoodExists(String neighbourhoodName){
        String stringQuery = "select name from neighbourhoods";
        try {
            PreparedStatement statement = databaseConnection.getConnection().prepareStatement(stringQuery);
            ResultSet resultset = databaseConnection.sendQuery(statement);
            int i = 1;
            while (resultset.next()){
                if (neighbourhoodName.equals(resultset.getString(i++))){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void setDatabaseConnection(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
}
