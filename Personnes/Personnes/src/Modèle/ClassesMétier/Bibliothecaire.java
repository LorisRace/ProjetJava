package Modèle.ClassesMétier;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Properties;

public class Bibliothecaire extends Personne
{
    private String AdminName;
    private String AdminPassword;
    private static HashMap<String, String> admins = new HashMap<>();

    public Bibliothecaire()
    {
        super();
        AdminName = "Admin";
        AdminPassword = "1234Admin";
    }

    public Bibliothecaire(String AdminName, String AdminPassword)
    {
        this.AdminName = AdminName;
        this.AdminPassword = AdminPassword;
    }

    public Bibliothecaire(String Nom, String Prenom, String AdminName, String AdminPassword)
    {
        super(Nom, Prenom);
        this.AdminName = AdminName;
        this.AdminPassword = AdminPassword;
    }

    public static Bibliothecaire seConnecter(String username, String password)
    {
        loadAdminData(); // Charger les données des administrateurs depuis le fichier
        // Vérifier si l'administrateur existe déjà
        if (admins.containsKey(username)) {
            String storedPassword = admins.get(username);
            // Vérifier le mot de passe
            if (storedPassword.equals(password)) {
                System.out.println("Connexion réussie pour " + username);
                return new Bibliothecaire(username, password);
            } else {
                System.out.println("Mot de passe incorrect pour " + username);
                return null;
            }
        }
        else
        {
            // Créer un nouveau administrateur
            admins.put(username, password);
            saveAdminData(); // Sauvegarder les données des administrateurs dans le fichier
            System.out.println("Nouvel administrateur créé : " + username);
            return new Bibliothecaire(username, password);
        }
    }

    private static void loadAdminData()
    {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("admins.properties"))
        {
            properties.load(fis);
            for (String username : properties.stringPropertyNames())
            {
                admins.put(username, properties.getProperty(username));
            }
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void saveAdminData()
    {
        Properties properties = new Properties();
        for (String username : admins.keySet()) {
            properties.setProperty(username, admins.get(username));
        }
        try (FileOutputStream fos = new FileOutputStream("admins.properties"))
        {
            properties.store(fos, "Administrateurs enregistrés");
        }
        catch (IOException e)
        {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getters and setters
    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String AdminName) {
        this.AdminName = AdminName;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String AdminPassword) {
        this.AdminPassword = AdminPassword;
    }

    // Méthode pour afficher les informations du bibliothécaire
    @Override
    public void Afficher() {
        super.Afficher();
        System.out.println("AdminName : " + AdminName);
        System.out.println("AdminPassword : " + AdminPassword);
    }

    // Méthode pour saisir les informations du bibliothécaire
    @Override
    public void Saisir() {
        Scanner scanner = new Scanner(System.in);
        super.Saisir();

        System.out.println("Entrez le nom d'utilisateur Admin: ");
        this.AdminName = scanner.nextLine();

        System.out.println("Entrez le mot de passe Admin: ");
        this.AdminPassword = scanner.nextLine();
    }

    // Surcharge de la méthode toString
    @Override
    public String toString() {
        return super.toString() +
                "\nAdminName : " + AdminName +
                "\nAdminPassword : " + AdminPassword;
    }

    // Méthode equals pour comparer deux bibliothécaires
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bibliothecaire)) return false;
        if (!super.equals(o)) return false;
        Bibliothecaire that = (Bibliothecaire) o;
        return AdminName.equals(that.AdminName) && AdminPassword.equals(that.AdminPassword);
    }

    public static void main(String[] args) {
        String Nom = "";
        String Prenom = "";
        String AdminName = "";
        String AdminPassword = "";

        Bibliothecaire bibliothecaire = new Bibliothecaire(Nom, Prenom, AdminName, AdminPassword);

        bibliothecaire.Saisir();

        bibliothecaire.Afficher();

        Bibliothecaire bibliothecaire1 = new Bibliothecaire();

        bibliothecaire1.Afficher();

        if (bibliothecaire == bibliothecaire1) {
            System.out.println("Ce sont les mêmes bibliothecaires");
        } else {
            System.out.println("Ce sont des bibliothécaires différents");
        }

        Bibliothecaire bibliothecaire2 = new Bibliothecaire("Toto", "Pierre", "TotoAdmin", "TotoWord");

        bibliothecaire2.Afficher();

        // Nouveaux tests
        System.out.println("Test de la méthode toString :");
        System.out.println(bibliothecaire.toString());
        System.out.println(bibliothecaire1.toString());
        System.out.println(bibliothecaire2.toString());

        System.out.println("Test de la méthode equals :");
        System.out.println("bibliothecaire.equals(bibliothecaire1) : " + bibliothecaire.equals(bibliothecaire1));
        System.out.println("bibliothecaire.equals(bibliothecaire2) : " + bibliothecaire.equals(bibliothecaire2));
    }
}
