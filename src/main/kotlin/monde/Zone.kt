package monde

import monstre.EspeceMonstre
import java.time.LocalDateTime

/**
 * Représente une zone géographique dans le monde du jeu.
 *
 * Les zones forment une chaîne de route permettant le déplacement.
 *
 * @property id Identifiant unique de la zone.
 * @property nom Nom de la zone.
 * @property expZone Quantité d'expérience attribuée dans cette zone.
 * @property especesMonstres Liste mutable des espèces de monstres présentes.
 * @property zoneSuivante Zone suivante dans la chaîne de routes.
 * @property zonePrecedente Zone précédente dans la chaîne de routes.
 */
class Zone(
    var id: Int,
    var nom: String,
    var expZone: Int,
    var especesMonstres: MutableList<EspeceMonstre> = mutableListOf(),
    var zoneSuivante: Zone? = null,
    var zonePrecedente: Zone? = null
) {
    // TODO: faire la méthode genereMonstre()
    // TODO: faire la méthode rencontreMonstre()
}