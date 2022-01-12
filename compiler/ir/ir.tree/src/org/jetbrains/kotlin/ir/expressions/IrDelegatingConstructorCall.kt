/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.expressions

import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

abstract class IrDelegatingConstructorCall(
    typeArgumentsCount: Int,
    valueArgumentsCount: Int,
) : IrFunctionAccessExpression(typeArgumentsCount, valueArgumentsCount) {
    abstract override val symbol: IrConstructorSymbol

    override fun <R, D> accept(visitor: IrElementVisitor<R, D>, data: D): R =
        visitor.visitDelegatingConstructorCall(this, data)
}
