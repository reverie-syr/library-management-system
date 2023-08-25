package metier.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Genre {

    private int id;
    private String titre;

    public Genre(String titre){
        this.titre=titre;
    }

    public String toString() {
        return this.titre.substring(0,1).toUpperCase()+this.titre.substring(1);
    }
}
