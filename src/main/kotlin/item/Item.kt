package org.ldv.item

/**
 * Classe de base représentant un objet du jeu.
 *
 * @property id Identifiant unique de l'objet.
 * @property nom Nom de l'objet.
 * @property description Description détaillée de l'objet.
 */
open class Item(
    val id: Int,
    val nom: String,
    val description: String
)