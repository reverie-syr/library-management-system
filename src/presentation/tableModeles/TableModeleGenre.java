package presentation.tableModeles;

import metier.entity.Genre;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleGenre extends AbstractTableModel {
    private List<Genre> genres = new ArrayList<>();
    private final String[] titres = {
            "ID",
            "Titre",
    };

    @Override
    public int getRowCount() {
        return genres.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> genres.get(rowIndex).getId();
            case 1 -> genres.get(rowIndex).getTitre();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Genre> list) {
        genres = list;

        fireTableDataChanged();
    }
}
