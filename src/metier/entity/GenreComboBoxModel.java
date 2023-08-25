package metier.entity;

import dao.SingletonConnection;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreComboBoxModel extends AbstractListModel<Genre> implements ComboBoxModel<Genre> {

    private List<Genre> genreList;
    private Genre selectedGenre;

    public GenreComboBoxModel() {
        genreList = retrieveGenresFromDatabase();

    }
    @Override
    public void setSelectedItem(Object anItem) {
        selectedGenre = (Genre) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedGenre;
    }

    @Override
    public int getSize() {
        return genreList.size();
    }

    @Override
    public Genre getElementAt(int index) {
        return genreList.get(index);
    }

    public int getIndexOf(Genre genre) {
        return genreList.indexOf(genre);
    }

    private List<Genre> retrieveGenresFromDatabase() {

        Connection connection = SingletonConnection.getInstance();
        List<Genre> genres = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement("select * from genre");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Genre genre = new Genre(
                        rs.getString(2)
                );
                genre.setId(rs.getInt(1));

                genres.add(genre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

}

