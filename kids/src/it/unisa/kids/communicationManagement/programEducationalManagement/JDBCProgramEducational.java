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

    public String approveProject(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void notifyExpirationPlan(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<String> showNotifyExpirationPlan(AnnualProject pProject) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //soppresso
    public void approvedChangesProposed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //soppresso
    public void rejectChangedProposed() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void insertComment(CommentBean toAdd) throws SQLException {
        Connection con = DBConnectionPool.getConnection();
        PreparedStatement pstmt = null;

        try {

            String query = "INSERT INTO " + DBNames.TABLE_COMMENT + " ("
                    + DBNames.ATT_COMMENT_DATE + ", "
                    + DBNames.ATT_COMMENT_DESCRIPTION + ", "
                    // + DBNames.ATT_COMMENT_ID + ", "
                    + DBNames.ATT_COMMENT_IDAUTHOR + ", "
                    + DBNames.ATT_COMMENT_IDSECTION + ", "
                    + DBNames.ATT_COMMENT_IDYEAR + ", "
                    + DBNames.ATT_COMMENT_TYPEMODIFY
                    + ") VALUES(?, ?, ?, ?, ?, ?)";

            pstmt = con.prepareStatement(query);
            pstmt.setDate(1, new Date(toAdd.getDate().getTimeInMillis()));
            pstmt.setString(2, toAdd.getContenuto());
            //pstmt.setInt(3, toAdd.getId());
            pstmt.setInt(3, toAdd.getIdAutore());
            pstmt.setInt(4, toAdd.getIdSezione());
            pstmt.setInt(5, toAdd.getIdAnnuale());
            pstmt.setString(6, toAdd.getTipoModifica());
            pstmt.executeUpdate();
            con.commit();
        } finally {
            pstmt.close();
            DBConnectionPool.releaseConnection(con);
        }
    }

    public ArrayList<CommentBean> getComments(AnnualProjectSection toView) throws SQLException {
        ResultSet rs = null;
        ArrayList<CommentBean> toReturn = new ArrayList<CommentBean>();
        String query = "SELECT * FROM " + DBNames.TABLE_COMMENT
                + " WHERE " + DBNames.ATT_COMMENT_ID + " = " + toView.getId();
        PreparedStatement pstm;
        Connection con;
        con = DBConnectionPool.getConnection();
        pstm = con.prepareStatement(query);
        rs = pstm.executeQuery();
        while (rs.next()) {
            CommentBean toAdd = new CommentBean();
            GregorianCalendar todata = new GregorianCalendar();
            toAdd.setContenuto(rs.getString(DBNames.ATT_COMMENT_DESCRIPTION));
            todata.setTimeInMillis(rs.getDate(DBNames.ATT_COMMENT_DATE).getTime());
            toAdd.setDate(todata);
            toAdd.setId(rs.getInt(DBNames.ATT_COMMENT_ID));
            toAdd.setIdAnnuale(rs.getInt(DBNames.ATT_COMMENT_IDYEAR));
            toAdd.setIdAutore(rs.getInt(DBNames.ATT_COMMENT_IDAUTHOR));
            toAdd.setIdSezione(rs.getInt(DBNames.ATT_COMMENT_IDSECTION));
            toAdd.setTipoModifica(rs.getString(DBNames.ATT_COMMENT_TYPEMODIFY));
            toReturn.add(toAdd);

        }
        con.commit();
        return toReturn;
    }

    @Override
    public AnnualProjectSection getProgramEducational(int SectionId) throws SQLException {
        AnnualProjectSection toReturn = new AnnualProjectSection();
        toReturn.setCommenti(this.getComments(toReturn));
        ResultSet rs;
        GregorianCalendar data = new GregorianCalendar();

        String query = "SELECT * FROM " + DBNames.TABLE_SECTION_PROJ
                + " WHERE " + DBNames.ATT_PROJECTANNUALSECTION_SECTION + " = " + SectionId + " AND "
                + DBNames.ATT_PROJECTANNUALSECTION_IDYEAR + " LIKE ____%" + data.get(GregorianCalendar.YEAR);
        PreparedStatement pstm;
        Connection con;
        con = DBConnectionPool.getConnection();
        pstm = con.prepareStatement(query);
        rs = pstm.executeQuery();

        while (rs.next()) {
            toReturn.setId(rs.getInt(DBNames.ATT_PROJECTANNUALSECTION_ID));
            toReturn.setDescription(rs.getString(DBNames.ATT_PROJECTANNUALSECTION_DESCRIPTION));
            toReturn.setSection(rs.getInt(SectionId));
            toReturn.setAttached(rs.getString(DBNames.ATT_PROJECTANNUALSECTION_ATTACHED));
            toReturn.setName(rs.getString(DBNames.ATT_PROJECTANNUALSECTION_NAME));
        }
        DBConnectionPool.releaseConnection(con);
        return toReturn;

    }

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
    public void updateProgramEducational(AnnualProjectSection toUpdate) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DBConnectionPool.getConnection();
        String query;

        // constructing query string
        query = "UPDATE " + DBNames.TABLE_SECTION_PROJ + " SET "
                + DBNames.ATT_PROJECTANNUALSECTION_ATTACHED + " = ?, "
                + DBNames.ATT_PROJECTANNUALSECTION_DESCRIPTION + " = ?, "
                + DBNames.ATT_PROJECTANNUALSECTION_IDYEAR + " = ? "
                + DBNames.ATT_PROJECTANNUALSECTION_NAME + " = ? "
                + DBNames.ATT_PROJECTANNUALSECTION_SECTION + " = ? "
                + "WHERE " + DBNames.ATT_PROJECTANNUALSECTION_ID + " = ?";

        pstmt = con.prepareStatement(query);
        // setting pstmt's parameters

        pstmt.setString(1, toUpdate.getAttached());
        pstmt.setString(2, toUpdate.getDescription());
        pstmt.setInt(3, toUpdate.getIdYear());
        pstmt.setString(4, toUpdate.getName());
        pstmt.setInt(5, toUpdate.getSection());
        pstmt.setInt(6, toUpdate.getId());
        pstmt.executeUpdate();
        con.commit();
        DBConnectionPool.releaseConnection(con);


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

    @Override
    public void insertProgramEducational(AnnualProjectSection pAnnual) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
