package presentation.tableModeles;

import metier.entity.Livre;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleLivre extends AbstractTableModel {

    private static TableModeleLivre tableModeleLivre;
    private List<Livre> livres = new ArrayList<>();
    private final String[] titres = {
            "ID",
            "ISBN",
            "Titre",
            "Auteur",
            "Genre",
            "Quantité",
            "Éditeur",
            "Prix",
            "Date-RCP"
    };


    private TableModeleLivre(){}


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
        return switch (columnIndex) {
            case 0 -> livres.get(rowIndex).getID();
            case 1 -> livres.get(rowIndex).getISBN();
            case 2 -> livres.get(rowIndex).getTitre();
            case 3 -> livres.get(rowIndex).getAuteur();
            case 4 -> livres.get(rowIndex).getGenre();
            case 5 -> livres.get(rowIndex).getQuantite();
            case 6 -> livres.get(rowIndex).getEditeur();
            case 7 -> livres.get(rowIndex).getPrix();
            case 8 -> livres.get(rowIndex).getDate_reception();
            case 9 -> livres.get(rowIndex).getDescription();
            case 10 -> livres.get(rowIndex).getPhoto().getName();

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
    public static TableModeleLivre getInstance() {
        if (tableModeleLivre == null) {
            tableModeleLivre = new TableModeleLivre();
        }

        return tableModeleLivre;
    }
}
