package ClassesMétier;

import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Auteur extends Personne {
    private Date dateNaissance;
    private String rue;
    private int numero;
    private String codePostal;
    private String ville;
    private String pays;

    public Auteur() {
        super();
        dateNaissance = null;
        rue = "";
        numero = 0;
        codePostal = "";
        ville = "";
        pays = "";
    }

    public Auteur(String nom, String prenom, Date dateNaissance, String rue, int numero, String codePostal, String ville, String pays) {
        super(nom, prenom);
        this.dateNaissance = dateNaissance;
        this.rue = rue;
        this.numero = numero;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @Override
    public void Afficher() {
        super.Afficher();
        System.out.println("Date de naissance : " + dateNaissance);
        System.out.println("Adresse : " + numero + " " + rue + ", " + codePostal + " " + ville + ", " + pays);
    }

    @Override
    public void Saisir() {
        super.Saisir();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la date de naissance (jj/mm/aaaa) : ");
        String DateEncodage = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            dateNaissance = dateFormat.parse(DateEncodage);
        }
        catch (ParseException e)
        {
            System.out.println("Format de date invalide. Utilisez le format dd/MM/yyyy.");
        }

        System.out.println("Entrez la rue : ");
        this.rue = scanner.nextLine();

        System.out.println("Entrez le numéro : ");
        this.numero = scanner.nextInt();
        scanner.nextLine(); // Nettoyer le tampon de ligne

        System.out.println("Entrez le code postal : ");
        this.codePostal = scanner.nextLine();

        System.out.println("Entrez la ville : ");
        this.ville = scanner.nextLine();

        System.out.println("Entrez le pays : ");
        this.pays = scanner.nextLine();
    }

    @Override
    public String toString() {
        return "ClassesMétier.Auteur{" +
                "Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", Adresse='" + numero + " " + rue + ", " + codePostal + " " + ville + ", " + pays + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Auteur)) return false;
        if (!super.equals(o)) return false;
        Auteur auteur = (Auteur) o;
        return numero == auteur.numero &&
                dateNaissance.equals(auteur.dateNaissance) &&
                rue.equals(auteur.rue) &&
                codePostal.equals(auteur.codePostal) &&
                ville.equals(auteur.ville) &&
                pays.equals(auteur.pays);
    }

    public static void main(String[] args) {
        // Test de la classe ClassesMétier.Auteur

        // Test du constructeur par défaut
        Auteur auteur1 = new Auteur();
        System.out.println("ClassesMétier.Auteur par défaut :");
        System.out.println(auteur1);

        // Test du constructeur d'initialisation
        Auteur auteur2 = new Auteur("Dupont", "Jean", null, "Rue de la Paix", 10, "75001", "Paris", "France");
        System.out.println("\nClassesMétier.Auteur initialisé :");
        System.out.println(auteur2);

        // Test des setters
        auteur1.setNom("Martin");
        auteur1.setPrenom("Sophie");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

// Analyser la chaîne de date pour créer un objet Date
        Date dateNaissance = null;
        try
        {
            dateNaissance = dateFormat.parse("15/05/1975");
        }
        catch (ParseException e)
        {
            e.printStackTrace(); // Gérer l'exception selon vos besoins
        }
        auteur1.setDateNaissance(dateNaissance);
        auteur1.setRue("Avenue des Champs-Élysées");
        auteur1.setNumero(20);
        auteur1.setCodePostal("75008");
        auteur1.setVille("Paris");
        auteur1.setPays("France");
        System.out.println("\nClassesMétier.Auteur après modification :");
        System.out.println(auteur1);

        // Test de la méthode Saisir
        Auteur auteur3 = new Auteur();
        System.out.println("\nEntrez les informations pour un nouvel auteur :");
        auteur3.Saisir();
        System.out.println("\nNouvel auteur saisi :");
        System.out.println(auteur3);

        // Test de la méthode equals
        System.out.println("\nTest de l'égalité entre auteur1 et auteur2 : " + auteur1.equals(auteur2));
        System.out.println("Test de l'égalité entre auteur1 et auteur3 : " + auteur1.equals(auteur3));
    }
}
