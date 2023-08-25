package metier.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Utilisateur {
    private int id;
    private String username;
    private String password;
    private int admin;

    public Utilisateur(String username, String password, int admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }


    protected boolean canEqual(final Object other) {
        return other instanceof Utilisateur;
    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Utilisateur)) return false;
        final Utilisateur other = (Utilisateur) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        if (this.getAdmin() != other.getAdmin()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        result = result * PRIME + this.getAdmin();
        return result;
    }

    public String toString() {
        return this.username.substring(0,1).toUpperCase() + this.username.substring(1);
    }
}
