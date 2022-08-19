/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.gradle.plugin.mpp.targetHierarchy

internal data class KotlinTargetHierarchy(
    val group: String,
    val children: Set<KotlinTargetHierarchy>
) {
    override fun toString(): String {
        if (children.isEmpty()) return group
        return group + "\n" + children.joinToString("\n").prependIndent("----")
    }
}