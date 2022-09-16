/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve

import org.jetbrains.kotlin.fir.NoMutableState
import org.jetbrains.kotlin.fir.java.isJavaRecord
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticNamesProvider
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol
import org.jetbrains.kotlin.load.java.getPropertyNamesCandidatesByAccessorName
import org.jetbrains.kotlin.load.java.possibleGetMethodNames
import org.jetbrains.kotlin.load.java.setMethodName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.SpecialNames

@NoMutableState
object FirJavaSyntheticNamesProvider : FirSyntheticNamesProvider() {
    override fun possibleGetterNamesByPropertyName(name: Name): List<Name> {
        return possibleGetMethodNames(name)
    }

    override fun setterNameByGetterName(name: Name): Name {
        return setMethodName(getMethodName = name)
    }

    override fun possiblePropertyNamesByAccessorName(name: Name, containingClassSymbol: FirRegularClassSymbol?): List<Name> {
        val regularNames = getPropertyNamesCandidatesByAccessorName(name)
        return if (containingClassSymbol.isJavaRecord) {
            regularNames + name
        } else {
            regularNames
        }
    }

    override fun possibleAdditionalGetterNamesByPropertyName(name: Name, containingClassSymbol: FirRegularClassSymbol?): Name? {
        return name.takeIf { containingClassSymbol.isJavaRecord }
    }

    private val FirRegularClassSymbol?.isJavaRecord: Boolean
        get() = this?.fir?.isJavaRecord ?: false
}
