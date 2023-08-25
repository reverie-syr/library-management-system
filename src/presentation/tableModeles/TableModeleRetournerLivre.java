package presentation.tableModeles;

import metier.entity.Livre;
import metier.entity.Membre;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleRetournerLivre extends AbstractTableModel {

    private static TableModeleRetournerLivre tableModeleRetournerLivre;
    private List<Livre> livres = new ArrayList<>();
    private final String[] titres = {
            "Livre",
            "Membre",
            "Etat",
            "Date-RCP",
            "Date-Retour",
            "Note"
    };


    private TableModeleRetournerLivre(){}

    @Override
    public int getRowCount() {
        return livres.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Membre membre = livres.get(rowIndex).getMembre();

        return switch (columnIndex) {
            case 0 -> livres.get(rowIndex).getID();
            case 1 -> (membre != null)? membre.getId() : null;
            case 2 -> livres.get(rowIndex).getEtat().toString();
            case 3 -> livres.get(rowIndex).getDate_reception();
            case 4 -> livres.get(rowIndex).getDate_de_retour();
            case 5 -> livres.get(rowIndex).getNote();

            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Livre> list) {
        livres = list;

        fireTableDataChanged();
    }
    public static TableModeleRetournerLivre getInstance() {
        if (tableModeleRetournerLivre == null) {
            tableModeleRetournerLivre = new TableModeleRetournerLivre();
        }

        return tableModeleRetournerLivre;
    }
}

