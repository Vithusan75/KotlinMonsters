package org.ldv.item

import org.ldv.monstre.IndividuMonstre // Remplace par le package exact de la classe IndividuMonstre

/**
 * Interface définissant le comportement d'un objet ou d'une action
 * pouvant être utilisé(e) sur un [IndividuMonstre].
 */
interface Utilisable {
    /**
     * Applique l'effet de l'objet ou de l'action sur le monstre cible.
     *
     * @param cible Le [IndividuMonstre] sur lequel l'objet est utilisé.
     * @return true si l'action a eu un effet, false sinon.
     */
    fun utiliser(cible: IndividuMonstre): Boolean
}