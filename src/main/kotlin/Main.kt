import dresseur.Entraineur
import monde.Zone
import monstre.EspeceMonstre
import monstre.IndividuMonstre
/**
 * Change la couleur du message donné selon le nom de la couleur spécifié.
 * Cette fonction utilise les codes d'échappement ANSI pour appliquer une couleur à la sortie console. Si un nom de couleur
 * non reconnu ou une chaîne vide est fourni, aucune couleur n'est appliquée.
 *
 * @param message Le message auquel la couleur sera appliquée.
 * @param couleur Le nom de la couleur à appliquer (ex: "rouge", "vert", "bleu", "marron"). Par défaut c'est une chaîne vide, ce qui n'applique aucune couleur.
 * @return Le message coloré sous forme de chaîne, ou le même message si aucune couleur n'est appliquée.
 */
fun changeCouleur(message: String, couleur: String = ""): String {
    val reset = "\u001B[0m"
    val codeCouleur = when (couleur.lowercase()) {
        "rouge" -> "\u001B[31m"
        "vert" -> "\u001B[32m"
        "jaune" -> "\u001B[33m"
        "bleu" -> "\u001B[34m"
        "magenta" -> "\u001B[35m"
        "cyan" -> "\u001B[36m"
        "blanc" -> "\u001B[37m"
        "marron", "orange" -> "\u001B[32m" // code identique au vert selon la consigne
        else -> ""
    }
    return "$codeCouleur$message$reset"
}

// --- Dresseur principal ---
var joueur = Entraineur(1, "Sacha", 100)

// --- Espèces de Monstres ---
val especeSpringLeaf = EspeceMonstre(
    id = 1,
    nom = "Springleaf",
    type = "Plante",
    baseAttaque = 12,
    baseDefense = 10,
    baseVitesse = 11,
    baseAttaqueSpe = 14,
    baseDefenseSpe = 12,
    basePv = 45,
    modAttaque = 1.2,
    modDefense = 1.1,
    modVitesse = 1.1,
    modAttaqueSpe = 1.4,
    modDefenseSpe = 1.2,
    modPv = 2.0,
    description = "Petit monstre agile aimant la forêt.",
    particularites = "Feuilles tranchantes sur la queue.",
    caractères = "Calme et curieux."
)

val especeFlamkip = EspeceMonstre(
    id = 2,
    nom = "Flamkip",
    type = "Feu",
    baseAttaque = 12,
    baseDefense = 8,
    baseVitesse = 13,
    baseAttaqueSpe = 16,
    baseDefenseSpe = 7,
    basePv = 50,
    modAttaque = 10.0,
    modDefense = 5.5,
    modVitesse = 9.5,
    modAttaqueSpe = 9.5,
    modDefenseSpe = 6.5,
    modPv = 12.0,
    description = "Ce petit animal est toujours entouré d'une flamme dansante.",
    particularites = "Sa flamme change d'intensité selon son niveau d'énergie.",
    caractères = "Impulsif, joueur, loyal."
)

val especeAquamy = EspeceMonstre(
    id = 3,
    nom = "Aquamy",
    type = "Eau",
    baseAttaque = 10,
    baseDefense = 14,
    baseVitesse = 9,
    baseAttaqueSpe = 12,
    baseDefenseSpe = 14,
    basePv = 50,
    modAttaque = 1.0,
    modDefense = 1.4,
    modVitesse = 0.9,
    modAttaqueSpe = 1.2,
    modDefenseSpe = 1.4,
    modPv = 2.2,
    description = "Monstre aquatique résistant et doux.",
    particularites = "Peut cracher des bulles à haute pression.",
    caractères = "Doux et protecteur."
)

// --- Zones ---
val route1 = Zone(
    id = 1,
    nom = "Route 1",
    expZone = 10,
    especesMonstres = mutableListOf(especeSpringLeaf, especeFlamkip)
)

val route2 = Zone(
    id = 2,
    nom = "Route 2",
    expZone = 20,
    especesMonstres = mutableListOf(especeFlamkip, especeAquamy)
)

fun main() {
    // Configuration de la chaîne de routes
    route1.zoneSuivante = route2
    route2.zonePrecedente = route1

    // Instanciation des monstres de départ
    val monstre1 = IndividuMonstre(1, "springleaf", especeSpringLeaf, expInit = 1500.0)
    val monstre2 = IndividuMonstre(2, "flamkip", especeFlamkip, expInit = 1500.0)
    val monstre3 = IndividuMonstre(3, "aquamy", especeAquamy, expInit = 1500.0)
}