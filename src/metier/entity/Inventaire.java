package metier.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class Inventaire {

    private int inventaireID;
    private int code_livre;
    private int quantite;
    private String book_description;
    private Date date;

    public Inventaire(int code_livre, int quantite,String book_description, Date date) {
        this.code_livre = code_livre;
        this.quantite = quantite;
        this.book_description=book_description;
        this.date = date;
    }

}
