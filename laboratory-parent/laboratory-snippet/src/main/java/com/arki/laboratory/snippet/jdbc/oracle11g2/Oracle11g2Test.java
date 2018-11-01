package com.arki.laboratory.snippet.jdbc.oracle11g2;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;

public class Oracle11g2Test {
    public static void main(String[] args) throws SQLException {
        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL("jdbc:oracle:thin:username/password@192.168.1.229:1521/sid");
        Connection connection = oracleDataSource.getConnection();
        printDriverVersion(connection);

        String sql = "select dep_cd, table_nm,table_nm_cn from sw_tab_syn_rl where table_nm_cn is not null and rownum <=3";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String depCd = resultSet.getString("dep_cd");
            String tableNm = resultSet.getString("table_nm");
            String tableNmCn = resultSet.getString("table_nm_cn");
            System.out.println(depCd+"   "+tableNm+"   "+tableNmCn);
        }
        resultSet.close();
        connection.close();

    }

    /**
     * Determining the JDBC Driver Version.
     * @param connection
     */
    public static void printDriverVersion(Connection connection) {
        try {
            String driverVersion = connection.getMetaData().getDriverVersion();
            System.out.println("===============");
            System.out.println("Driver version: " + driverVersion);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from v$version");
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("DB version:");
            while (resultSet.next()) {
                String s = resultSet.getString(1);
                System.out.println(s);
            }
            resultSet.close();
            System.out.println("===============");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
