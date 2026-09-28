package monstre

import dresseur.Entraineur
import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Représente un monstre individuel avec lequel le joueur ou un dresseur interagit.
 *
 * Note : deux individus peuvent appartenir à la même espèce exemple Canaros.
 *
 * @property id Identifiant de l'individu.
 * @property nom Nom ou surnom attribué à l'individu.
 * @property espece Espèce à laquelle appartient le monstre.
 * @property entraineur Dresseur possédant le monstre (null si sauvage).
 */
class IndividuMonstre(
    var id: Int,
    var nom: String,
    val espece: EspeceMonstre,
    var entraineur: Entraineur? = null,
    expInit: Double = 0.0
) {
    var niveau: Int = 1
    var attaque: Int = espece.baseAttaque + (-2..2).random()
    var defense: Int = espece.baseDefense + (-2..2).random()
    var vitesse: Int = espece.baseVitesse + (-2..2).random()
    var attaqueSpe: Int = espece.baseAttaqueSpe + (-2..2).random()
    var defenseSpe: Int = espece.baseDefenseSpe + (-2..2).random()
    var pvMax: Int = espece.basePv + (-5..5).random()
    val potentiel: Double = (50..200).random() / 100.0

    /**
     * @property pv Points de vie actuels.
     * Ne peut pas être inférieur à 0 ni supérieur à [pvMax].
     */
    var pv: Int = pvMax
        get() = field
        set(nouveauPv) {
            field = nouveauPv.coerceIn(0, pvMax)
        }

    var exp: Double = 0.0
        get() = field
        set(nouvelleExp) {
            field = nouvelleExp
            while (field >= palierExp(niveau + 1)) {
                levelUp()
            }
        }

    init {
        this.exp = expInit // applique le setter et déclenche un éventuel level-up
    }

    /**
     * Calcule l'expérience totale nécessaire pour atteindre un niveau donné.
     *
     * @param niveau Niveau cible.
     * @return Expérience cumulée nécessaire pour atteindre ce niveau.
     */
    fun palierExp(niveau: Int): Double {
        return 100.0 * (niveau - 1).toDouble().pow(2.0)
    }

    /**
     * Augmente le niveau d'un monstre, incrémente son niveau et recalcule ses statistiques.
     */
    fun levelUp() {
        niveau++

        val gainAttaque = (espece.modAttaque * potentiel).roundToInt() + (-2..2).random()
        val gainDefense = (espece.modDefense * potentiel).roundToInt() + (-2..2).random()
        val gainVitesse = (espece.modVitesse * potentiel).roundToInt() + (-2..2).random()
        val gainAttaqueSpe = (espece.modAttaqueSpe * potentiel).roundToInt() + (-2..2).random()
        val gainDefenseSpe = (espece.modDefenseSpe * potentiel).roundToInt() + (-2..2).random()
        val gainPvMax = (espece.modPv * potentiel).roundToInt() + (-5..5).random()

        attaque += gainAttaque.coerceAtLeast(1)
        defense += gainDefense.coerceAtLeast(1)
        vitesse += gainVitesse.coerceAtLeast(1)
        attaqueSpe += gainAttaqueSpe.coerceAtLeast(1)
        defenseSpe += gainDefenseSpe.coerceAtLeast(1)

        val diffPvMax = gainPvMax.coerceAtLeast(1)
        pvMax += diffPvMax
        pv += diffPvMax

        println("$nom monte au niveau$niveau !")
    }

    /**
     * Attaque un autre [IndividuMonstre] et inflige des dégâts.
     *
     * Les dégâts sont calculés de manière très simple pour le moment :
     * `dégâts = attaque - (défense / 2)` (minimum 1 dégât).
     *
     * @param cible Monstre cible de l'attaque.
     */
    fun attaquer(cible: IndividuMonstre) {
        val degats = (this.attaque - (cible.defense / 2)).coerceAtLeast(1)
        cible.pv -= degats
        println("${this.nom} attaque ${cible.nom} et lui inflige$degats dégâts !")
    }

    /**
     * Demande au joueur de renommer le monstre.
     * Si l'utilisateur entre un texte vide, le nom n'est pas modifié.
     */
    fun renommer() {
        print("Entrez le nouveau nom pour ${this.nom} (laisser vide pour annuler) : ")
        val nouveauNom = readlnOrNull()?.trim()
        if (!nouveauNom.isNullOrEmpty()) {
            println("Le monstre ${this.nom} a été renommé en $nouveauNom.")
            this.nom = nouveauNom
        } else {
            println("Nom inchangé.")
        }
    }

    /**
     * Affiche les caractéristiques détaillées du monstre ainsi que son ASCII Art.
     */
    fun afficheDetail() {
        println("=== Détails de $nom ===")
        println("Espèce    : ${espece.nom} (${espece.type})")
        println("Niveau    : $niveau")
        println("PV        : $pv / $pvMax")
        println("Attaque   : $attaque | Attaque Spé : $attaqueSpe")
        println("Défense   : $defense | Défense Spé : $defenseSpe")
        println("Vitesse   : $vitesse")
        println("EXP       : $exp / ${palierExp(niveau + 1)}")
        println("Potentiel : $potentiel")
        println("------------------------")
        println(espece.afficheArt(deFace = true))
    }
}