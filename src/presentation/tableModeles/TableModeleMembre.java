package presentation.tableModeles;

import metier.entity.Membre;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableModeleMembre extends AbstractTableModel {
    private static TableModeleMembre tableModeleMembre;

    private TableModeleMembre(){}

    private List<Membre> membres = new ArrayList<>();
    private final String[] titres = {
            "ID",
            "Nom",
            "Prenom",
            "CIN",
            "Téléphone",
            "Date de naissance",
            "Email",
            "Genre",
            "Date d'adhésion",
            "Adresse",
            "Photo"
    };

    @Override
    public int getRowCount() {
        return  membres.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> membres.get(rowIndex).getId();
            case 1 -> membres.get(rowIndex).getNom();
            case 2 -> membres.get(rowIndex).getPrenom();
            case 3 -> membres.get(rowIndex).getCin();
            case 4 -> membres.get(rowIndex).getNumTel();
            case 5 -> membres.get(rowIndex).getDateDeNaissance();
            case 6 -> membres.get(rowIndex).getEmail();
            case 7 -> membres.get(rowIndex).getGenre();
            case 8 -> membres.get(rowIndex).getDateAdhesion();
            case 9 -> membres.get(rowIndex).getAdresse();
            case 10 -> membres.get(rowIndex).getPhoto().getName();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

    public void chargerTable(List<Membre> list) {
        membres = list;
        fireTableDataChanged();
    }

    public static TableModeleMembre getInstance() {
        if (tableModeleMembre == null) {
            tableModeleMembre = new TableModeleMembre();
        }

        return tableModeleMembre;
    }
}
