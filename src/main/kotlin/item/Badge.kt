package org.ldv.item

import org.ldv.entraineur.Entraineur // Remplace par le package exact de la classe Entraineur

/**
 * Représente un Badge obtenu en battant un champion d'arène.
 * Hérite de la classe Item.
 *
 * @param id Identifiant du badge.
 * @param nom Nom du badge.
 * @param description Description du badge.
 * @property champion Le dresseur/entraîneur à battre pour obtenir ce badge.
 */
class Badge(
    id: Int,
    nom: String,
    description: String,
    var champion: Entraineur
) : Item(id, nom, description)