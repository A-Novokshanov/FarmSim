# Farming-Simulation

This is a game designed to simulate a farm and the functionalities one might expect from a farm.
The player can plant, water, and harvest crops. They can also sell their
view their crops in their inventory view and sell their harvested crops in the market.

## Setup
This game relies on the JFoenix library and SQLite JDBC. This will require you to download
the JFoenix jar, which can be downloaded at https://github.com/jfoenixadmin/JFoenix

Setup of the SQLite Database will require the jar file for:

1. In the Database tool window (View | Tool Windows | Database), click the Data Source Properties icon The Data Source Properties icon.

2. In the Data Sources and Drivers dialog, click the Add icon (The Add icon) and select SQLite.

4. At the bottom of the data source settings area, click the Download missing driver files link. Alternatively, you can specify user drivers for the data source. For more information about user drivers, see Add a user driver to an existing connection.

5. To connect to an existing SQLite database, specify a file path to the database file in the File field. -> USE THIS as the gamedatabase.db already exists.

6. To ensure that the connection to the data source is successful, click Test Connection.
 
If you are missing runtime components, make sure you have set your VM Options as such: --module-path %Path to JavaFX Lib folder% --add-modules javafx.controls,javafx.fxml --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
