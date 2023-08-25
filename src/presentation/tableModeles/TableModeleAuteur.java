package presentation.tableModeles;

import metier.entity.Auteur;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleAuteur extends AbstractTableModel {
    private List<Auteur> auteurs = new ArrayList<>();
    private final String[] titres = {
            "ID",
            "Nom",
            "Prénom",
            "Expertise",
            "À Propos"
    };

    @Override
    public int getRowCount() {
        return auteurs.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> auteurs.get(rowIndex).getId();
            case 1 -> auteurs.get(rowIndex).getNom();
            case 2 -> auteurs.get(rowIndex).getPrenom();
            case 3 -> auteurs.get(rowIndex).getExpertise();
            case 4 -> auteurs.get(rowIndex).getInformations();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Auteur> list) {
        auteurs = list;

        fireTableDataChanged();
    }
}
