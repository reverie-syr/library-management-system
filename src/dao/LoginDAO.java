package dao;

import metier.entity.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO implements ILoginDAO {

    @Override
    public Utilisateur validate(Utilisateur u) {
        Connection cx = SingletonConnection.getInstance();

        try {
            PreparedStatement ps = cx.prepareStatement("select * from user where username= ? and password= ? and isAdmin=?");

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getAdmin());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u.setId(rs.getInt(1));

                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
