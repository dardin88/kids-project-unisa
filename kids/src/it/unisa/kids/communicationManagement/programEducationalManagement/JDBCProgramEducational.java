package it.unisa.kids.communicationManagement.programEducationalManagement;

import it.unisa.kids.common.DBNames;
import it.unisa.storage.connectionPool.DBConnectionPool;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCProgramEducational implements IProgramEducational {

    private static IProgramEducational manager;

    public static IProgramEducational getInstance() {
        if (manager == null) {
            return (manager = new JDBCProgramEducational());
        } else {
            return manager;
        }
    }

   
    @Override
    public int insertPathProject(AnnualProject project) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        con = DBConnectionPool.getConnection();
        String query = "Insert into " + DBNames.TABLE_ANNUAL_PROJ + " (" + DBNames.ATT_PROJECTANNUAL_PATH + "," + DBNames.ATT_PROJECTANNUAL_STATE + ") values (?,?)";
        stmt = con.prepareStatement(query);
        stmt.setString(1, project.getPath());
        stmt.setString(2, project.getState());
        stmt.executeUpdate();
        con.commit();
        return getProject(project);
    }
    
    public int getProject(AnnualProject project){
        try {
            Connection con= DBConnectionPool.getConnection();
            Statement stmt=null;
            ResultSet rs=null;
            String query="select * from "+DBNames.TABLE_ANNUAL_PROJ + " where "+ DBNames.ATT_PROJECTANNUAL_PATH+"='"+project.getPath()+"' and "+DBNames.ATT_PROJECTANNUAL_STATE+"='"+project.getState()+"'";
            stmt= con.createStatement();
            rs=stmt.executeQuery(query);
            while(rs.next())
                return rs.getInt(DBNames.ATT_PROJECTANNUALSECTION_ID);
            
                
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProgramEducational.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public void updateProgramEducational(AnnualProject pProject){
        try {
            Connection con=DBConnectionPool.getConnection();
            Statement pstmt=null;
            String query="Update "+DBNames.TABLE_ANNUAL_PROJ + " SET "+
                    DBNames.ATT_PROJECTANNUAL_STATE+"='"+pProject.getState()+"' where "+DBNames.ATT_PROJECTANNUAL_ID+"="+pProject.getId();
            pstmt=con.createStatement();
            pstmt.executeUpdate(query);
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProgramEducational.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public ArrayList<AnnualProject> show() throws SQLException {
       Connection  con = DBConnectionPool.getConnection();
       ArrayList<AnnualProject> list=new ArrayList<>();
       Statement stmt=null;
       ResultSet rs=null;
       String query="select * from "+DBNames.TABLE_ANNUAL_PROJ;
       stmt=con.createStatement();
       rs=stmt.executeQuery(query);
       con.commit();
       while(rs.next()){
           AnnualProject a=new AnnualProject();
           a.setId(rs.getInt(DBNames.ATT_PROJECTANNUAL_ID));
           a.setPath(rs.getString(DBNames.ATT_PROJECTANNUAL_PATH));
           a.setState(rs.getString(DBNames.ATT_PROJECTANNUAL_STATE));
           list.add(a);
       }
       return list;
    }

    
}
