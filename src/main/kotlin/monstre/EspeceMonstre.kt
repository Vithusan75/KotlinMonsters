package monstre

import java.io.File

/**
 * Représente une espèce de monstre (et non un individu).
 *
 * Note : La description contient 255 char max.
 *
 * @property id Identifiant unique de l'espèce.
 * @property nom Nom de l'espèce.
 * @property type Type de l'espèce (ex: Plante, Feu, Eau...).
 * @property baseAttaque Score de base d'attaque.
 * @property baseDefense Score de base de défense.
 * @property baseVitesse Score de base de vitesse.
 * @property baseAttaqueSpe Score de base d'attaque spéciale.
 * @property baseDefenseSpe Score de base de défense spéciale.
 * @property basePv Score de base de PV.
 * @property modAttaque Multiplicateur de croissance d'attaque.
 * @property modDefense Multiplicateur de croissance de défense.
 * @property modVitesse Multiplicateur de croissance de vitesse.
 * @property modAttaqueSpe Multiplicateur de croissance d'attaque spéciale.
 * @property modDefenseSpe Multiplicateur de croissance de défense spéciale.
 * @property modPv Multiplicateur de croissance de PV.
 * @property description Description courte de l'espèce (255 char max).
 * @property particularites Traits ou compétences physiques distinctifs.
 * @property caractères Traits de comportement généraux.
 */
class EspeceMonstre(
    var id: Int,
    var nom: String,
    var type: String,
    val baseAttaque: Int,
    val baseDefense: Int,
    val baseVitesse: Int,
    val baseAttaqueSpe: Int,
    val baseDefenseSpe: Int,
    val basePv: Int,
    val modAttaque: Double,
    val modDefense: Double,
    val modVitesse: Double,
    val modAttaqueSpe: Double,
    val modDefenseSpe: Double,
    val modPv: Double,
    val description: String = "",
    val particularites: String = "",
    val caractères: String = ""
) {
    /**
     * Affiche la représentation artistique ASCII du monstre.
     *
     * @param deFace Détermine si l'art affiché est de face (true) ou de dos (false).
     *               La valeur par défaut est true.
     * @return Une chaîne de caractères contenant l'art ASCII du monstre avec les codes couleur ANSI.
     *         L'art est lu à partir d'un fichier texte dans le dossier resources/art.
     */
    fun afficheArt(deFace: Boolean = true): String {
        val nomFichier = if (deFace) "front" else "back"
        val art = File("src/main/resources/art/${this.nom.lowercase()}/$nomFichier.txt").readText()
        val safeArt = art.replace("/", "∕")
        return safeArt.replace("\\u001B", "\u001B")
    }
}