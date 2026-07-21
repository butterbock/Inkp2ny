package com.inkp2ny.smartinkopslista.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ForestGreenLightColorScheme = lightColorScheme(
    primary = ForestGreenPrimaryLight,
    onPrimary = OnForestGreenPrimaryLight,
    primaryContainer = ForestGreenPrimaryContainerLight,
    onPrimaryContainer = OnForestGreenPrimaryContainerLight,
    secondary = ForestSecondaryLight,
    onSecondary = OnForestSecondaryLight,
    secondaryContainer = ForestSecondaryContainerLight,
    onSecondaryContainer = OnForestSecondaryContainerLight,
    tertiary = ForestTertiaryLight,
    onTertiary = OnForestTertiaryLight,
    tertiaryContainer = ForestTertiaryContainerLight,
    onTertiaryContainer = OnForestTertiaryContainerLight,
    error = ForestErrorLight,
    onError = OnForestErrorLight,
    errorContainer = ForestErrorContainerLight,
    onErrorContainer = OnForestErrorContainerLight,
    background = ForestBackgroundLight,
    onBackground = OnForestBackgroundLight,
    surface = ForestSurfaceLight,
    onSurface = OnForestSurfaceLight,
    surfaceVariant = ForestSurfaceVariantLight,
    onSurfaceVariant = OnForestSurfaceVariantLight,
    outline = ForestOutlineLight,
    outlineVariant = ForestOutlineVariantLight,
    scrim = ForestScrimLight,
    inverseSurface = ForestInverseSurfaceLight,
    inverseOnSurface = ForestInverseOnSurfaceLight,
    inversePrimary = ForestInversePrimaryLight,
)

private val ForestGreenDarkColorScheme = darkColorScheme(
    primary = ForestGreenPrimaryDark,
    onPrimary = OnForestGreenPrimaryDark,
    primaryContainer = ForestGreenPrimaryContainerDark,
    onPrimaryContainer = OnForestGreenPrimaryContainerDark,
    secondary = ForestSecondaryDark,
    onSecondary = OnForestSecondaryDark,
    secondaryContainer = ForestSecondaryContainerDark,
    onSecondaryContainer = OnForestSecondaryContainerDark,
    tertiary = ForestTertiaryDark,
    onTertiary = OnForestTertiaryDark,
    tertiaryContainer = ForestTertiaryContainerDark,
    onTertiaryContainer = OnForestTertiaryContainerDark,
    error = ForestErrorDark,
    onError = OnForestErrorDark,
    errorContainer = ForestErrorContainerDark,
    onErrorContainer = OnForestErrorContainerDark,
    background = ForestBackgroundDark,
    onBackground = OnForestBackgroundDark,
    surface = ForestSurfaceDark,
    onSurface = OnForestSurfaceDark,
    surfaceVariant = ForestSurfaceVariantDark,
    onSurfaceVariant = OnForestSurfaceVariantDark,
    outline = ForestOutlineDark,
    outlineVariant = ForestOutlineVariantDark,
    scrim = ForestScrimDark,
    inverseSurface = ForestInverseSurfaceDark,
    inverseOnSurface = ForestInverseOnSurfaceDark,
    inversePrimary = ForestInversePrimaryDark,
)

@Composable
fun SmartInkopslistaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) ForestGreenDarkColorScheme else ForestGreenLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = ForestTypography,
        content = content,
    )
}
