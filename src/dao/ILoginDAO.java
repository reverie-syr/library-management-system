package dao;

import metier.entity.Utilisateur;

public interface ILoginDAO {
    Utilisateur validate(Utilisateur u);
}
